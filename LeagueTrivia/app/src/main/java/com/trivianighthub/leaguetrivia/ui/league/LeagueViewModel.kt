package com.trivianighthub.leaguetrivia.ui.league

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.trivianighthub.leaguetrivia.model.League
import com.trivianighthub.leaguetrivia.model.Team
import com.trivianighthub.leaguetrivia.util.DataResult
import com.trivianighthub.leaguetrivia.util.ErrorHandler
import com.trivianighthub.leaguetrivia.util.ValidationResult
import com.trivianighthub.leaguetrivia.util.validateWithDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LeagueViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _leagues = MutableStateFlow<DataResult<List<League>>>(DataResult.Loading)
    val leagues: StateFlow<DataResult<List<League>>> = _leagues

    private val _teams = MutableStateFlow<DataResult<List<Team>>>(DataResult.Loading)
    val teams: StateFlow<DataResult<List<Team>>> = _teams

    private val _createLeagueResult = MutableStateFlow<DataResult<League>?>(null)
    val createLeagueResult: StateFlow<DataResult<League>?> = _createLeagueResult

    init {
        fetchLeagues()
    }

    fun createLeague(name: String, description: String, isOpenLeague: Boolean) {
        viewModelScope.launch {
            try {
                val league = League(
                    name = name.trim(),
                    description = description.trim(),
                    adminId = auth.currentUser?.uid ?: "",
                    isOpenLeague = isOpenLeague
                )

                val validationResult = league.validateWithDetails()
                if (validationResult is ValidationResult.Error) {
                    _createLeagueResult.value = DataResult.Error(
                        ErrorHandler.handleValidationResult(validationResult)
                    )
                    return@launch
                }

                val docRef = firestore.collection("leagues").document()
                val leagueWithId = league.copy(id = docRef.id)
                docRef.set(leagueWithId).await()

                _createLeagueResult.value = DataResult.Success(leagueWithId)
                fetchLeagues() // Refresh the leagues list
            } catch (e: Exception) {
                _createLeagueResult.value = DataResult.Error(ErrorHandler.handleException(e))
            }
        }
    }

    private fun fetchLeagues() {
        viewModelScope.launch {
            try {
                val snapshot = firestore.collection("leagues")
                    .whereEqualTo("adminId", auth.currentUser?.uid)
                    .get()
                    .await()

                val leaguesList = snapshot.documents.mapNotNull { doc ->
                    doc.toObject(League::class.java)
                }
                _leagues.value = DataResult.Success(leaguesList)
            } catch (e: Exception) {
                _leagues.value = DataResult.Error(ErrorHandler.handleException(e))
            }
        }
    }

    fun clearCreateLeagueResult() {
        _createLeagueResult.value = null
    }
} 
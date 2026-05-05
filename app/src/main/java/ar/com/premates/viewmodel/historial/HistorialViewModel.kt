package ar.com.premates.ui.historial

import androidx.lifecycle.ViewModel
import ar.com.premates.model.historial.Partida
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistorialViewModel : ViewModel() {

    private val _partidas = MutableStateFlow<List<Partida>>(emptyList())
    val partidas: StateFlow<List<Partida>> = _partidas.asStateFlow()

    init {
        cargarPartidas()
    }

    private fun cargarPartidas() {
        _partidas.value = listOf(
            Partida("Viego",   "Derrota",  4,  7,  2,  28),
            Partida("Jinx",    "Victoria", 12, 2,  10, 24),
            Partida("Graves",  "Derrota",  5,  6,  3,  32),
            Partida("Yasuo",   "Victoria", 9,  3,  7,  41),
            Partida("Thresh",  "Derrota",  1,  8,  12, 35),
            Partida("Lux",     "Victoria", 7,  4,  15, 27),
        )
    }
}
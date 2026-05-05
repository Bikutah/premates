package ar.com.premates.model.historial

data class Partida(
    val campeon: String,
    val resultado: String,
    val kills: Int,
    val muertes: Int,
    val asistencias: Int,
    val duracionMinutos: Int
)
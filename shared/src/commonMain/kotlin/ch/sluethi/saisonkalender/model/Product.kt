package ch.sluethi.saisonkalender.model

data class Product(
    val name: String,
    val description: String,
    val url: String,
    val season: Map<Int, Boolean>
)
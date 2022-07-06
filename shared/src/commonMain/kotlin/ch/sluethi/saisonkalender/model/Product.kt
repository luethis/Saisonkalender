package ch.sluethi.saisonkalender.model

data class Product(
    val name: Map<String, String>,
    val description: Map<String, String>,
    val season: BooleanArray,
    val url: String,
)
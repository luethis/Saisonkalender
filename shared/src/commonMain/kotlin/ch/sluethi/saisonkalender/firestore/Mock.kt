package ch.sluethi.saisonkalender.firestore

import ch.sluethi.saisonkalender.model.Product

val mockData = listOf(
    Product(
        name = mapOf("de" to "Produkt 1", "en" to "Product 1"),
        description = mapOf(
            "de" to "Beschreibung von Produkt 1",
            "en" to "Description of product 1"
        ),
        url = "https://firebasestorage.googleapis.com/v0/b/saisonkalender-f0dac.appspot.com/o/tomate.jpg?alt=media&token=528fb022-b805-45fa-96ec-29fdaa6eef7d",
        season = booleanArrayOf(
            false,
            true,
            false,
            true,
            false,
            true,
            false,
            true,
            false,
            true,
            false,
            true
        )
    ),
    Product(
        name = mapOf("de" to "Produkt 2", "en" to "Product 2"),
        description = mapOf(
            "de" to "Beschreibung von Produkt 2",
            "en" to "Description of product 2"
        ),
        url = "https://firebasestorage.googleapis.com/v0/b/saisonkalender-f0dac.appspot.com/o/tomate.jpg?alt=media&token=528fb022-b805-45fa-96ec-29fdaa6eef7d",
        season = booleanArrayOf(
            true,
            false,
            true,
            false,
            true,
            false,
            true,
            false,
            true,
            false,
            true,
            false
        )

    ),
    Product(
        name = mapOf("de" to "product 2", "en" to "product 2"),
        description = mapOf(
            "de" to "Beschreibung von Produkt 3",
            "en" to "Description of product 3"
        ),
        url = "https://firebasestorage.googleapis.com/v0/b/saisonkalender-f0dac.appspot.com/o/tomate.jpg?alt=media&token=528fb022-b805-45fa-96ec-29fdaa6eef7d",
        season = booleanArrayOf(
            false,
            false,
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            false,
            false,
            false
        )
    )
)
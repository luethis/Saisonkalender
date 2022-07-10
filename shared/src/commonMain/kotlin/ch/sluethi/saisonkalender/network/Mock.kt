package ch.sluethi.saisonkalender.network

import ch.sluethi.saisonkalender.model.Product

val mockData = listOf(
    Product(
        id = "1",
        name = "Product 1",
        description = "Beschreibung von Produkt 1",
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
        id = "2",
        name = "Product 2",
        description = "Beschreibung von Produkt 2",
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
        id = "3",
        name = "Produkt 3",
        description = "Beschreibung von Produkt 3",
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
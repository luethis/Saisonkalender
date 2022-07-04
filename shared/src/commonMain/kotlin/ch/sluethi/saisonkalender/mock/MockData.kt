package ch.sluethi.saisonkalender.mock

import ch.sluethi.saisonkalender.model.Product

val mockData = listOf(
    Product(
        name = "product 1",
        description = "description product 2",
        url = "https://firebasestorage.googleapis.com/v0/b/playground-7e732.appspot.com/o/frog.png?alt=media&token=0557f753-af3f-4558-bee2-2951a4353fac",
        season = mapOf(
            0 to true,
            1 to false,
            2 to false,
            3 to true,
            4 to false,
            5 to false,
            6 to true,
            7 to false,
            8 to false,
            9 to false,
            10 to false,
            11 to false
        )
    ),
    Product(
        name = "product 2",
        description = "description product 2",
        url = "https://firebasestorage.googleapis.com/v0/b/playground-7e732.appspot.com/o/lama.png?alt=media&token=a3ff9884-8d92-474e-a6ec-4b3dee79610f",
        season = mapOf(
            0 to true,
            1 to false,
            2 to false,
            3 to true,
            4 to false,
            5 to false,
            6 to true,
            7 to false,
            8 to false,
            9 to false,
            10 to false,
            11 to false
        )
    ),
    Product(
        name = "product 3",
        description = "description product 3",
        url = "https://firebasestorage.googleapis.com/v0/b/playground-7e732.appspot.com/o/meerkat.png?alt=media&token=d77a72c4-295c-41c1-abfb-af313e74bb50",
        season = mapOf(
            0 to true,
            1 to false,
            2 to false,
            3 to true,
            4 to false,
            5 to false,
            6 to true,
            7 to false,
            8 to false,
            9 to false,
            10 to false,
            11 to false
        )
    )
)
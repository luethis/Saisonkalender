package ch.sluethi.saisonkalender.model

import io.realm.kotlin.types.RealmObject

data class Product(
    val name: String,
    val description: String,
    val season: BooleanArray,
    val url: String,
)

class RealmProduct : RealmObject {
    var name: String = ""
    var description: String = ""
    var season: BooleanArray = booleanArrayOf()
    var url: String = ""
}
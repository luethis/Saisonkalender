package ch.sluethi.saisonkalender.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

data class Product(
    val id : String,
    val name: String,
    val description: String,
    val season: BooleanArray,
    val url: String,
)

class RealmProduct : RealmObject {
    @PrimaryKey
    var id : String = ""
    var name: String = ""
    var description: String = ""
    var season: BooleanArray = booleanArrayOf()
    var url: String = ""
}
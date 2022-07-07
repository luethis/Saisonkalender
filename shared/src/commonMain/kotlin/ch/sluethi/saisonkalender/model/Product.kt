package ch.sluethi.saisonkalender.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

data class Product(
    val name: Map<String, String>,
    val description: Map<String, String>,
    val season: BooleanArray,
    val url: String,
)

class RealmProduct : RealmObject {
    var name: RealmList<String> = realmListOf()
    var description: RealmList<String> = realmListOf()
    var season: BooleanArray = booleanArrayOf()
    var url: String = ""
}
package ch.sluethi.saisonkalender.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val season: List<Boolean>,
    val url: String,
)

class RealmProduct : RealmObject {
    @PrimaryKey
    var id: String = ""
    var name: String = ""
    var description: String = ""
    var season: RealmList<Boolean> = realmListOf()
    var url: String = ""
}
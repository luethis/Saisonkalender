package ch.sluethi.saisonkalender.firestore

import ch.sluethi.saisonkalender.model.Product
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class Firestore {

    /**
     * fetch data from firebase firestore and put them in a list for further usage
     */
    suspend fun fetchData(): List<Product> {
        val list = mutableListOf<Product>()
        Firebase.firestore.collection(COLLECTION_PRODUCTS).get().documents.forEach { document ->
            runCatching {
                val name = document.get<Map<String, String>>(FIELD_NAME)
                val description = document.get<Map<String, String>>(FIELD_DESCPRIPTION)
                val season = document.get<BooleanArray>(FIELD_SEASON)
                val pictureUrl = document.get<String>(FIELD_PICTURE_URL)
                list.add(Product(name, description, season, pictureUrl))
            }
        }
        return list
    }
}
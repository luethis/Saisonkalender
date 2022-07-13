package ch.sluethi.saisonkalender.network

import ch.sluethi.saisonkalender.model.Product
import co.touchlab.kermit.Logger
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class Firestore {

    /**
     * fetch data from firebase firestore and put them in a list for further usage
     */
    suspend fun fetchData(): List<Product> {
        writeLog("fetch databse from firebase")
        val list = mutableListOf<Product>()
        runCatching {
            Firebase.firestore.collection(COLLECTION_PRODUCTS).get().documents.forEach { document ->
                val id = document.id
                val name = document.get<String>(FIELD_NAME)
                val description = document.get<String>(FIELD_DESCPRIPTION)
                val season = document.get<List<Boolean>>(FIELD_SEASON)
                val pictureUrl = document.get<String>(FIELD_PICTURE_URL)
                list.add(Product(id, name, description, season, pictureUrl))
            }
        }
        return list
    }

    /**
     * fetch database version from firebase firestore to check if an update is needed
     */
    suspend fun fetchVersion(): Int {
        writeLog("fetch database version")
        runCatching {
            return Firebase.firestore.collection(COLLECTION_ADMINISTRATION)
                .document(DOCUMENT_CONFIGURATION)
                .get().get(FIELD_DATABASE_VERSION)
        }
        return -1
    }

    private fun writeLog(message: String) {
        Logger.v("Firebase - $message")
    }
}
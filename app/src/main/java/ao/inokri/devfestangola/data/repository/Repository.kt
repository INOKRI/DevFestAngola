package ao.inokri.devfestangola.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class Repository : IRepository {

    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override suspend fun getData(path: String, querySearch: String): Task<QuerySnapshot> {
        return if (querySearch.isEmpty())
            firestore.collection(path).get()
        else
            firestore.collection(path).whereEqualTo("title", querySearch).get()
    }
}
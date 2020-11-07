package ao.inokri.devfestangola.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class Repository : IRepository {

    private val firestore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override suspend fun getData(path: String): Task<QuerySnapshot> {
        return firestore.collection(path).get()
    }
}
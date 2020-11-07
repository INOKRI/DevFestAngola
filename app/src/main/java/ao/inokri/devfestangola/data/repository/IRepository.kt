package ao.inokri.devfestangola.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface IRepository {

    suspend fun getData(path: String, querySearch: String): Task<QuerySnapshot>

}
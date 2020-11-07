package ao.inokri.devfestangola.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ao.inokri.devfestangola.data.Status
import ao.inokri.devfestangola.data.models.AgendaModel
import ao.inokri.devfestangola.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgendaViewModel(private var repository: Repository) : ViewModel() {

    private var agenda: MutableList<AgendaModel> = mutableListOf()
    private val agendaLiveData = MutableLiveData<MutableList<AgendaModel>>()

    fun getAgenda(path: String, callback: (status: Status) -> Unit): LiveData<MutableList<AgendaModel>> {
        agenda = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            callback(Status.LOADING)
            repository.getData(path).addOnSuccessListener {
                callback(Status.SUCCESS)
                it.documents.forEach { response ->
                    agenda.add(response.toObject(AgendaModel::class.java)!!)
                }
                agendaLiveData.value = agenda

            }.addOnFailureListener {
                callback(Status.ERROR)
            }
        }
        return agendaLiveData
    }
}
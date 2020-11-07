package ao.inokri.devfestangola.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ao.inokri.devfestangola.data.Status
import ao.inokri.devfestangola.data.models.EventModel
import ao.inokri.devfestangola.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventsViewModel(private var repository: Repository) : ViewModel() {

    private var events: MutableList<EventModel> = mutableListOf()
    private val eventsLiveData = MutableLiveData<MutableList<EventModel>>()

    fun getEvents(path: String, callback: (status: Status) -> Unit): LiveData<MutableList<EventModel>> {
        events = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            callback(Status.LOADING)
            repository.getData(path).addOnSuccessListener {
                callback(Status.SUCCESS)
                it.documents.forEach { response ->
                    events.add(response.toObject(EventModel::class.java)!!)
                }
                eventsLiveData.value = events

            }.addOnFailureListener {
                callback(Status.ERROR)
            }
        }
        return eventsLiveData
    }
}
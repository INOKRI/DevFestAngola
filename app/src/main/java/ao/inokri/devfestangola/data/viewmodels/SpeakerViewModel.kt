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

class SpeakerViewModel(private var repository: Repository) : ViewModel() {

    private var speaker: MutableList<AgendaModel> = mutableListOf()
    private val speakerLiveData = MutableLiveData<MutableList<AgendaModel>>()

    fun getSpeaker(path: String, callback: (status: Status) -> Unit): LiveData<MutableList<AgendaModel>> {
        speaker = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            callback(Status.LOADING)
            repository.getData(path).addOnSuccessListener {
                callback(Status.SUCCESS)
                it.documents.forEach { response ->
                    speaker.add(response.toObject(AgendaModel::class.java)!!)
                }
                speakerLiveData.value = speaker

            }.addOnFailureListener {
                callback(Status.ERROR)
            }
        }
        return speakerLiveData
    }
}
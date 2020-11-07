package ao.inokri.devfestangola.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ao.inokri.devfestangola.data.Status
import ao.inokri.devfestangola.data.models.AgendaModel
import ao.inokri.devfestangola.data.models.SponsorsModel
import ao.inokri.devfestangola.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SponsorViewModel(private var repository: Repository) : ViewModel() {

    private var sponsor: MutableList<SponsorsModel> = mutableListOf()
    private val sponsorLiveData = MutableLiveData<MutableList<SponsorsModel>>()

    fun getSponsor(path: String, callback: (status: Status) -> Unit): LiveData<MutableList<SponsorsModel>> {
        sponsor = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            callback(Status.LOADING)
            repository.getData(path, "").addOnSuccessListener {
                callback(Status.SUCCESS)
                it.documents.forEach { response ->
                    sponsor.add(response.toObject(SponsorsModel::class.java)!!)
                }
                sponsorLiveData.value = sponsor

            }.addOnFailureListener {
                callback(Status.ERROR)
            }
        }
        return sponsorLiveData
    }
}
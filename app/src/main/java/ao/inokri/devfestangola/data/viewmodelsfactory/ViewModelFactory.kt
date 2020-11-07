@file:Suppress("UNCHECKED_CAST")

package ao.inokri.devfestangola.data.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ao.inokri.devfestangola.data.repository.Repository
import ao.inokri.devfestangola.data.viewmodels.*

class ViewModelFactory(var repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel() as T
            }
            modelClass.isAssignableFrom(EventsViewModel::class.java) -> {
                EventsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AgendaViewModel::class.java) -> {
                AgendaViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SpeakerViewModel::class.java) -> {
                SpeakerViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SponsorViewModel::class.java) -> {
                SponsorViewModel(repository) as T
            }
            else -> {
                throw IllegalArgumentException("Unknown class name")
            }
        }
    }
}

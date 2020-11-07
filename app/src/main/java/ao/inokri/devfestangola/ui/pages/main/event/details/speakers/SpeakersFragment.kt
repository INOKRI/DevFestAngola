package ao.inokri.devfestangola.ui.pages.main.event.details.speakers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.data.Status
import ao.inokri.devfestangola.data.models.AgendaModel
import ao.inokri.devfestangola.data.repository.Repository
import ao.inokri.devfestangola.data.viewmodels.SpeakerViewModel
import ao.inokri.devfestangola.data.viewmodelsfactory.ViewModelFactory
import ao.inokri.devfestangola.ui.adapters.SpeakerAdapter
import ao.inokri.devfestangola.utils.gone
import ao.inokri.devfestangola.utils.toast
import ao.inokri.devfestangola.utils.visible

class SpeakersFragment : Fragment() {

    private lateinit var speakerViewModel: SpeakerViewModel
    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var txtState: AppCompatTextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false).apply {

            speakerViewModel = ViewModelProvider(
                    this@SpeakersFragment, ViewModelFactory(Repository())
            )[SpeakerViewModel::class.java]

            recycler = findViewById(R.id.recyclerRoot)
            progress = findViewById(R.id.progressRoot)
            txtState = findViewById(R.id.txtStateRoot)

            recycler.layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL,
                    false
            )

            requestEvent(recycler)
        }
    }

    private fun requestEvent(recycler: RecyclerView?) {
        speakerAdapter = SpeakerAdapter(
                iAgenda = object : SpeakerAdapter.IAgenda {
                    override fun onClick(agendaModel: AgendaModel) {
                        toast(agendaModel.sessionTitle.toString(), requireContext())
                        val bundle = Bundle().also {
                            it.putString("image", agendaModel.speakerImage)
                            it.putString("nameSpeaker", agendaModel.speakerName)
                            it.putString("descriptionSpeaker", agendaModel.speakerDesc)
                        }
                        findNavController().navigate(R.id.actionSpeakersToSpeakersDetails, bundle)

                    }
                }
        )

        speakerViewModel.getSpeaker("Agenda") { status ->
            when (status) {
                Status.ERROR -> {
                    progress.gone()
                    txtState.visible()
                    txtState.text = "Error to load Speaker..."
                }
                Status.LOADING -> {
                    progress.visible()
                    txtState.gone()
                }
                Status.SUCCESS -> {
                    progress.gone()
                    txtState.gone()
                }
            }
        }.observe(viewLifecycleOwner, {
            when {
                it.isNotEmpty() -> {
                    speakerAdapter.addAll(it)
                    recycler!!.adapter = speakerAdapter
                }
                else -> {
                    txtState.visible()
                    txtState.text = "List speaker empty."
                }
            }
        })
    }

}
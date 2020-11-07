package ao.inokri.devfestangola.ui.pages.main.event.details.agenda

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.data.Status
import ao.inokri.devfestangola.data.models.AgendaModel
import ao.inokri.devfestangola.data.repository.Repository
import ao.inokri.devfestangola.data.viewmodels.AgendaViewModel
import ao.inokri.devfestangola.data.viewmodelsfactory.ViewModelFactory
import ao.inokri.devfestangola.ui.adapters.AgendaAdapter
import ao.inokri.devfestangola.utils.gone
import ao.inokri.devfestangola.utils.toast
import ao.inokri.devfestangola.utils.visible

class AgendaFragment : Fragment() {

    private lateinit var agendaViewModel: AgendaViewModel
    private lateinit var agendaAdapter: AgendaAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var txtState: AppCompatTextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false).apply {

            agendaViewModel = ViewModelProvider(
                    this@AgendaFragment, ViewModelFactory(Repository())
            )[AgendaViewModel::class.java]

            recycler = findViewById(R.id.recyclerRoot)
            progress = findViewById(R.id.progressRoot)
            txtState = findViewById(R.id.txtStateRoot)

            recycler.layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL,
                    false
            )

            requestAgenda(recycler)
        }
    }

    private fun requestAgenda(recycler: RecyclerView?) {
        agendaAdapter = AgendaAdapter(
                iAgenda = object : AgendaAdapter.IAgenda {
                    override fun onClick(agendaModel: AgendaModel) {
                        toast(agendaModel.sessionTitle.toString(), requireContext())
                    }
                }
        )

        agendaViewModel.getAgenda("Agenda") { status ->
            when (status) {
                Status.ERROR -> {
                    progress.gone()
                    txtState.visible()
                    txtState.text = "Error to load Agenda..."
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
                    agendaAdapter.addAll(it)
                    recycler!!.adapter = agendaAdapter
                }
                else -> {
                    txtState.visible()
                    txtState.text = "List agenda empty."
                }
            }
        })
    }
}
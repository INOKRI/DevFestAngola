package ao.inokri.devfestangola.ui.pages.main.event.details.sponsors

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import ao.inokri.devfestangola.data.models.SponsorsModel
import ao.inokri.devfestangola.data.repository.Repository
import ao.inokri.devfestangola.data.viewmodels.EventsViewModel
import ao.inokri.devfestangola.data.viewmodels.SponsorViewModel
import ao.inokri.devfestangola.data.viewmodelsfactory.ViewModelFactory
import ao.inokri.devfestangola.ui.adapters.SponsorAdapter
import ao.inokri.devfestangola.utils.gone
import ao.inokri.devfestangola.utils.visible
import com.google.firebase.firestore.FirebaseFirestore

class SponsorsFragment : Fragment() {

    private lateinit var sponsorViewModel: SponsorViewModel
    private lateinit var eventAdapter: SponsorAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var txtState: AppCompatTextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false).apply {

            sponsorViewModel = ViewModelProvider(
                    this@SponsorsFragment, ViewModelFactory(Repository())
            )[SponsorViewModel::class.java]

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

    @SuppressLint("SetTextI18n")
    private fun requestEvent(recycler: RecyclerView?) {
        eventAdapter = SponsorAdapter(
                iSponsor = object : SponsorAdapter.ISponsor {
                    override fun onClick(sponsorsModel: SponsorsModel) {
                        val url = "${sponsorsModel.link}"
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    }
                }
        )

        sponsorViewModel.getSponsor("Sponsors") { status ->
            when (status) {
                Status.ERROR -> {
                    progress.gone()
                    txtState.visible()
                    txtState.text = "Error to load Sponsors..."
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
                    eventAdapter.addAll(it)
                    recycler!!.adapter = eventAdapter
                }
                else -> {
                    txtState.visible()
                    txtState.text = "List Sponsors empty."
                }
            }
        })
    }

}
package ao.inokri.devfestangola.ui.pages.main.event

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.data.Status
import ao.inokri.devfestangola.data.models.EventModel
import ao.inokri.devfestangola.data.repository.Repository
import ao.inokri.devfestangola.data.viewmodels.EventsViewModel
import ao.inokri.devfestangola.data.viewmodelsfactory.ViewModelFactory
import ao.inokri.devfestangola.ui.adapters.EventAdapter
import ao.inokri.devfestangola.utils.gone
import ao.inokri.devfestangola.utils.toast
import ao.inokri.devfestangola.utils.visible

class EventsFragment : Fragment() {

    private lateinit var eventsViewModel: EventsViewModel
    private lateinit var eventAdapter: EventAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var progress: ProgressBar
    private lateinit var txtState: AppCompatTextView
    private lateinit var searchView: SearchView
    private lateinit var queryTextListener: SearchView.OnQueryTextListener

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_root, container, false).apply {

            eventsViewModel = ViewModelProvider(
                    this@EventsFragment, ViewModelFactory(Repository())
            )[EventsViewModel::class.java]

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.event_menu, menu)

        val searchItem = menu.findItem(R.id.searchEvents)
        val searchManager =
                requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                when {
                    TextUtils.isEmpty(newText) -> {
                        requestEvent(recycler)
                    }
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                when {
                    TextUtils.isEmpty(query) -> {
                        requestEvent(recycler)
                    }
                    else -> {
                        searchEvents(query)
                        searchView.clearFocus()
                    }
                }
                return true
            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
    }

    @SuppressLint("SetTextI18n")
    private fun requestEvent(recycler: RecyclerView?) {
        eventAdapter = EventAdapter(iVent = object : EventAdapter.IVent {
            override fun onClick(eventModel: EventModel) {
                val bundle = Bundle().also {
                    it.putString("image", eventModel.image)
                    it.putString("title", eventModel.title)
                    it.putString("description", eventModel.description)
                }
                findNavController().navigate(R.id.actionEventsToEventDetails, bundle)
            }
        })

        eventsViewModel.getEvents("Events") { status ->
            when (status) {
                Status.ERROR -> {
                    progress.gone()
                    txtState.visible()
                    txtState.text = "Error to load Events..."
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
                    txtState.text = "List events empty."
                }
            }
        })
    }

    private fun searchEvents(query: String) {
        eventAdapter = EventAdapter(iVent = object : EventAdapter.IVent {
            override fun onClick(eventModel: EventModel) {
                toast(eventModel.title.toString(), requireContext())
                val bundle = Bundle().also {
                    it.putString("image", eventModel.image)
                    it.putString("title", eventModel.title)
                    it.putString("description", eventModel.description)
                }
                findNavController().navigate(R.id.actionEventsToEventDetails, bundle)
            }
        })

        eventsViewModel.getSearchEvents("Events", query) { status ->
            when (status) {
                Status.ERROR -> {
                    progress.gone()
                    txtState.visible()
                    txtState.text = "Error to load Events..."
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
                    recycler.adapter = eventAdapter
                }
                else -> {
                    txtState.visible()
                    txtState.text = "List events empty."
                }
            }
        })
    }
}
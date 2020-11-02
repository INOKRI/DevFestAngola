package ao.inokri.devfestangola.ui.pages.event

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.core.widget.ContentLoadingProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.data.models.EventModel
import ao.inokri.devfestangola.ui.adapters.EventAdapter
import ao.inokri.devfestangola.utils.toast

class EventsFragment : Fragment() {

    private lateinit var eventAdapter: EventAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var progress: ProgressBar

    private var listEvents: MutableList<EventModel> = mutableListOf(
        EventModel(
            dateEnd = "07/11/2020",
            dateStart = "08/11/2020",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            id = "2",
            image = "https://sessionize.com/image?f=ef7c40f77f3efc91a988bf6a8bb2d05e,1140,400,0,1,e8-9fc2-4fc6-a807-546cea70486f.c5249970-203b-4d23-9896-97c7ec3f34d7.png",
            isActive = true,
            location = "Luanda, Viana, Vila De Viana",
            title = "DevFest Angola",
        ),
        EventModel(
            dateEnd = "07/11/2020",
            dateStart = "08/11/2020",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            id = "2",
            image = "https://sessionize.com/image?f=ef7c40f77f3efc91a988bf6a8bb2d05e,1140,400,0,1,e8-9fc2-4fc6-a807-546cea70486f.c5249970-203b-4d23-9896-97c7ec3f34d7.png",
            isActive = true,
            location = "Luanda, Viana, Vila De Viana",
            title = "DevFest Angola",
        ),
        EventModel(
            dateEnd = "07/11/2020",
            dateStart = "08/11/2020",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            id = "2",
            image = "https://sessionize.com/image?f=ef7c40f77f3efc91a988bf6a8bb2d05e,1140,400,0,1,e8-9fc2-4fc6-a807-546cea70486f.c5249970-203b-4d23-9896-97c7ec3f34d7.png",
            isActive = true,
            location = "Luanda, Viana, Vila De Viana",
            title = "DevFest Angola",
        ),
        EventModel(
            dateEnd = "07/11/2020",
            dateStart = "08/11/2020",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            id = "2",
            image = "https://sessionize.com/image?f=ef7c40f77f3efc91a988bf6a8bb2d05e,1140,400,0,1,e8-9fc2-4fc6-a807-546cea70486f.c5249970-203b-4d23-9896-97c7ec3f34d7.png",
            isActive = true,
            location = "Luanda, Viana, Vila De Viana",
            title = "DevFest Angola",
        ),
        EventModel(
            dateEnd = "07/11/2020",
            dateStart = "08/11/2020",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            id = "2",
            image = "https://sessionize.com/image?f=ef7c40f77f3efc91a988bf6a8bb2d05e,1140,400,0,1,e8-9fc2-4fc6-a807-546cea70486f.c5249970-203b-4d23-9896-97c7ec3f34d7.png",
            isActive = true,
            location = "Luanda, Viana, Vila De Viana",
            title = "DevFest Angola",
        ),
        EventModel(
            dateEnd = "07/11/2020",
            dateStart = "08/11/2020",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            id = "2",
            image = "https://sessionize.com/image?f=ef7c40f77f3efc91a988bf6a8bb2d05e,1140,400,0,1,e8-9fc2-4fc6-a807-546cea70486f.c5249970-203b-4d23-9896-97c7ec3f34d7.png",
            isActive = true,
            location = "Luanda, Viana, Vila De Viana",
            title = "DevFest Angola",
        ),
        EventModel(
            dateEnd = "07/11/2020",
            dateStart = "08/11/2020",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            id = "2",
            image = "https://sessionize.com/image?f=ef7c40f77f3efc91a988bf6a8bb2d05e,1140,400,0,1,e8-9fc2-4fc6-a807-546cea70486f.c5249970-203b-4d23-9896-97c7ec3f34d7.png",
            isActive = true,
            location = "Luanda, Viana, Vila De Viana",
            title = "DevFest Angola",
        ),
        EventModel(
            dateEnd = "07/11/2020",
            dateStart = "08/11/2020",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            id = "2",
            image = "https://sessionize.com/image?f=ef7c40f77f3efc91a988bf6a8bb2d05e,1140,400,0,1,e8-9fc2-4fc6-a807-546cea70486f.c5249970-203b-4d23-9896-97c7ec3f34d7.png",
            isActive = true,
            location = "Luanda, Viana, Vila De Viana",
            title = "DevFest Angola",
        ),
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false).apply {

            recycler = findViewById(R.id.recyclerEvents)
            progress = findViewById(R.id.progressEvent)

            recycler.layoutManager = LinearLayoutManager(
                requireContext(),
                RecyclerView.VERTICAL,
                false
            )

            requestEvent(recycler)
        }
    }

    private fun requestEvent(recycler: RecyclerView?) {
        eventAdapter = EventAdapter(
            iVent = object : EventAdapter.IVent {
                override fun onClick(eventModel: EventModel) {
                    requireContext().toast(eventModel.title.toString())
                }
            }
        )
        eventAdapter.addAll(listEvents)
        recycler!!.adapter = eventAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.event_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    /* override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         view.findViewById<Button>(R.id.button_first).setOnClickListener {
             findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
         }
     }*/
}
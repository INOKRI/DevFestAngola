package ao.inokri.devfestangola.ui.pages.main.event.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.utils.toast
import com.bumptech.glide.Glide

class EventDetailsFragment : Fragment() {

    private lateinit var imageEventDetails: AppCompatImageView
    private lateinit var titleEvent: AppCompatTextView
    private lateinit var descriptionEvent: AppCompatTextView

    private lateinit var cardAgenda: CardView
    private lateinit var cardSpeakers: CardView
    private lateinit var cardTeam: CardView
    private lateinit var cardSponsors: CardView
    private lateinit var cardFaq: CardView
    private lateinit var cardLocate: CardView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events_details, container, false).apply {

            val image: String? = arguments?.getString("image")
            val title: String? = arguments?.getString("title")
            val description: String? = arguments?.getString("description")

            imageEventDetails = findViewById(R.id.imageEventDetails)
            titleEvent = findViewById(R.id.titleEvent)
            descriptionEvent = findViewById(R.id.descriptionEvent)

            cardAgenda = findViewById(R.id.card_agenda)
            cardSpeakers = findViewById(R.id.card_speakers)
            cardTeam = findViewById(R.id.card_team)
            cardSponsors = findViewById(R.id.card_sponsors)
            cardFaq = findViewById(R.id.card_faq)
            cardLocate = findViewById(R.id.card_locate)

            Glide.with(this).load(image).into(imageEventDetails)
            titleEvent.text = title
            descriptionEvent.text = description

            cardAgenda.setOnClickListener {
                findNavController().navigate(R.id.agendaFragment)
            }

            cardSpeakers.setOnClickListener {
                findNavController().navigate(R.id.speakersFragment)
            }

            cardTeam.setOnClickListener {
                toast("Disponível na próxima versão", requireContext())
            }

            cardSponsors.setOnClickListener {
                findNavController().navigate(R.id.sponsorsFragment)
            }

            cardFaq.setOnClickListener {
                toast("Disponível na próxima versão", requireContext())
            }
            cardLocate.setOnClickListener {
                toast("Disponível na próxima versão", requireContext())
            }
        }
    }
}
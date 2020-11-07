package ao.inokri.devfestangola.ui.pages.main.event.details.speakers.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import ao.inokri.devfestangola.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class SpeakersDetails : Fragment() {

    private lateinit var imageSpeaker: CircleImageView
    private lateinit var txtSpeakerName: AppCompatTextView
    private lateinit var txtSpeakerDescription: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speakers_details, container, false).apply {

            val image: String? = arguments?.getString("image")
            val nameSpeaker: String? = arguments?.getString("nameSpeaker")
            val descriptionSpeaker: String? = arguments?.getString("descriptionSpeaker")

            imageSpeaker = findViewById(R.id.imageSpeaker)
            txtSpeakerName = findViewById(R.id.txtSpeakerName)
            txtSpeakerDescription = findViewById(R.id.txtSpeakerDescription)

            Glide.with(this).load(image).into(imageSpeaker)
            txtSpeakerName.text = nameSpeaker.toString()
            txtSpeakerDescription.text = descriptionSpeaker.toString()

        }
    }
}
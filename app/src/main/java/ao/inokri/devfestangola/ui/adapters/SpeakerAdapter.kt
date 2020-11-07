package ao.inokri.devfestangola.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.data.models.AgendaModel
import com.bumptech.glide.Glide
import kotlin.random.Random

class SpeakerAdapter(
    private val iAgenda: IAgenda
) : RecyclerView.Adapter<SpeakerAdapter.AgendaHolder>() {

    private var agenda: MutableList<AgendaModel> = mutableListOf()

    inner class AgendaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val photoAgenda: AppCompatImageView = itemView.findViewById(R.id.logoSponsor)
        private val talkTitleHolder: AppCompatTextView =
            itemView.findViewById(R.id.sponsorName)
        private val speakerNameHolder: AppCompatTextView =
            itemView.findViewById(R.id.txtSpeakerNameAgenda)
        private val speakerDescriptionHolder: AppCompatTextView =
            itemView.findViewById(R.id.txtSpeakerDescriptionAgenda)
        private val durationHolder: AppCompatTextView =
            itemView.findViewById(R.id.txtDurationAgenda)
        private val timeHolder: AppCompatTextView = itemView.findViewById(R.id.txtTimeAgenda)

        @SuppressLint("SetTextI18n")
        fun bindAgenda(agendaModel: AgendaModel) {
            Glide.with(itemView.context).load(agendaModel.speakerImage).into(photoAgenda)
            talkTitleHolder.text = agendaModel.sessionTitle.toString()
            speakerNameHolder.text = agendaModel.speakerName
            speakerDescriptionHolder.text = agendaModel.speakerDesc.toString()
            durationHolder.text = agendaModel.sessionStartTime
            timeHolder.text = agendaModel.sessionTotalTime

            setRandomColor(itemView.context, speakerNameHolder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.item_speaker,
            parent,
            false
        )
        return AgendaHolder(inflater)
    }

    override fun onBindViewHolder(holder: AgendaHolder, position: Int) {
        holder.bindAgenda(agenda[position])
        holder.itemView.setOnClickListener {
            iAgenda.onClick(agenda[position])
        }
    }

    override fun getItemCount(): Int {
        return agenda.count()
    }

    fun addAll(responseAgenda: MutableList<AgendaModel>) {
        agenda = responseAgenda
        notifyDataSetChanged()
    }

    interface IAgenda {
        fun onClick(agendaModel: AgendaModel)
    }

    private fun setRandomColor(context: Context, speakerNameHolder: AppCompatTextView) {
        val colors = arrayListOf(
            R.color.colorAmber,
            R.color.colorRed,
            R.color.colorBlue,
            R.color.colorGreen
        )
        val p = Random.nextInt(colors.size)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            speakerNameHolder.setTextColor(context.resources.getColor(colors[p], null))
        }
    }

}
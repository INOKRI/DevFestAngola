package ao.inokri.devfestangola.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.data.models.SponsorsModel
import com.bumptech.glide.Glide

class SponsorAdapter(
        private val iSponsor: ISponsor
) : RecyclerView.Adapter<SponsorAdapter.AgendaHolder>() {

    private var sponsor: MutableList<SponsorsModel> = mutableListOf()

    inner class AgendaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val logoSponsor: AppCompatImageView = itemView.findViewById(R.id.logoSponsor)
        private val sponsorName: AppCompatTextView = itemView.findViewById(R.id.sponsorName)

        @SuppressLint("SetTextI18n")
        fun bindAgenda(agendaModel: SponsorsModel) {
            Glide.with(itemView.context).load(agendaModel.logo).into(logoSponsor)
            sponsorName.text = agendaModel.name.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgendaHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
                R.layout.item_sponsor,
                parent,
                false
        )
        return AgendaHolder(inflater)
    }

    override fun onBindViewHolder(holder: AgendaHolder, position: Int) {
        holder.bindAgenda(sponsor[position])
        holder.itemView.setOnClickListener {
            iSponsor.onClick(sponsor[position])
        }
    }

    override fun getItemCount(): Int = sponsor.count()

    fun addAll(responseSponsor: MutableList<SponsorsModel>) {
        sponsor = responseSponsor
        notifyDataSetChanged()
    }

    interface ISponsor {
        fun onClick(sponsorsModel: SponsorsModel)
    }
}
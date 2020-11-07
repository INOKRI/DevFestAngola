package ao.inokri.devfestangola.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import ao.inokri.devfestangola.R
import ao.inokri.devfestangola.data.models.EventModel
import com.bumptech.glide.Glide

class EventAdapter(
        private val iVent: IVent
) : RecyclerView.Adapter<EventAdapter.EventHolder>() {

    private var events: MutableList<EventModel> = mutableListOf()

    inner class EventHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageHolder: AppCompatImageView = itemView.findViewById(R.id.imageEvent)
        private val titleHolder: AppCompatTextView = itemView.findViewById(R.id.titleEvent)
        private val descriptionHolder: AppCompatTextView = itemView.findViewById(
                R.id.descriptionEvent
        )
        private val dateStartHolder: AppCompatTextView = itemView.findViewById(R.id.dateStartEvent)
        private val dateEndEHolder: AppCompatTextView = itemView.findViewById(R.id.dateEndEvent)

        fun bindEvent(eventModel: EventModel) {
            Glide.with(itemView.context).load(eventModel.image).into(imageHolder)
            titleHolder.text = eventModel.title
            descriptionHolder.text = eventModel.description
            dateStartHolder.text = eventModel.dateStart
            dateEndEHolder.text = eventModel.dateEnd
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
                R.layout.item_event,
                parent,
                false
        )
        return EventHolder(inflater)
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bindEvent(events[position])
        holder.itemView.setOnClickListener {
            iVent.onClick(events[position])
        }
    }

    override fun getItemCount(): Int = events.count()

    fun addAll(responseEvents: MutableList<EventModel>) {
        events = responseEvents
        notifyDataSetChanged()
    }

    interface IVent {
        fun onClick(eventModel: EventModel)
    }
}
package za.co.garycampbell.aiva.assistant
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import za.co.garycampbell.aiva.R
import za.co.garycampbell.aiva.data.Assistant

class AssistantAdapter : Adapter<AssistantAdapter.ViewHolder>() {

    var data = listOf<Assistant>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }


    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val assistantMessage : TextView = itemView.findViewById(R.id.ai_message)
        val myMessage : TextView = itemView.findViewById(R.id.human_message)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.assitant_item_layout, parent, false) as ConstraintLayout
        return ViewHolder(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.assistantMessage.text = item.assistant_message
        holder.myMessage.text = item.human_message
    }
}
package br.com.hussan.ifprbancodedados

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.hussan.ifprbancodedados.data.Serie

class SerieAdapter(
    private val dataSet: MutableList<Serie>,
    private val callback: (Serie) -> Unit
) : RecyclerView.Adapter<SerieAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textName: TextView
        val textStreaming: TextView
        init {
            textName = view.findViewById(R.id.txt_name)
            textStreaming = view.findViewById(R.id.txt_streaming)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_serie, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textName.text = dataSet[position].name
        viewHolder.textStreaming.text = dataSet[position].streaming
        viewHolder.itemView.setOnClickListener {
            callback(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size

    fun addItems(item: List<Serie>) {
        dataSet.addAll(item)
        notifyItemRangeInserted(0, dataSet.size)
    }

//    fun updateItem(position: Int, editTextInput: String) {
//        dataSet[position].name = editTextInput
//        notifyItemChanged(position)
//    }
//
//    fun deleteItem(item: String) {
//        val position = dataSet.indexOf(item)
//        dataSet.remove(item)
//        notifyItemRemoved(position)
//    }
}
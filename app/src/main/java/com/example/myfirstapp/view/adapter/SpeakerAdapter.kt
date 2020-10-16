package com.example.myfirstapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myfirstapp.R
import com.example.myfirstapp.model.Speaker


// TODO for create an Adapter class
/*
* 1- Create a class and extend it to RecyclerView.Adapter
* 2- Implement the 3 method onCreateViewHolder, getItemCount, onBindViewHolder
* 3- Create a class ViewHolder for bind de values in fragment
* */
class SpeakerAdapter(val speakerListener: SpeakerListener) : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    val listSpeaker = ArrayList<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_speaker, parent, false ))

    override fun getItemCount() =  listSpeaker.size

    override fun onBindViewHolder(holder: SpeakerAdapter.ViewHolder, position: Int) {
       val speaker = listSpeaker[position] as Speaker

        // GET IMAGE WITH GLIDE
        /*
        * 1 - Load  - Where it's the image?
        * 2 - Apply - Transform in circular image
        * 3 - Into  - Where we are going to put the image on
        * */
        Glide.with(holder.itemView.context)
            .load(speaker.image)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.speakerImage)

        holder.nameOfSpeaker.text    = speaker.name
        holder.speakerTopic.text     = speaker.jobTitle
        holder.speakerWorkplace.text = speaker.workplace

        holder.itemView.setOnClickListener{
            speakerListener.onSpeakerClicked(speaker, position)
        }


    }

    fun updateData(data: List<Speaker>){
        listSpeaker.clear()
        listSpeaker.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val speakerImage        = itemView.findViewById<ImageView>(R.id.speakerImage)
        val nameOfSpeaker       = itemView.findViewById<TextView>(R.id.nameOfSpeaker)
        val speakerTopic        = itemView.findViewById<TextView>(R.id.speakerTopic)
        val speakerWorkplace    = itemView.findViewById<TextView>(R.id.speakerWorkplace)
    }
}
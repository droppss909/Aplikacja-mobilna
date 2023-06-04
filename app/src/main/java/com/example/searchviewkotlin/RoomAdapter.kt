package com.example.searchviewkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RoomAdapter(var mList: List<RoomData>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(itemId: RoomData){
            val ClikedItem=itemId

        }

    }

    inner class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)

    }

    fun setFilteredList(mList: List<RoomData>){
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item , parent , false)

        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.logo.setImageResource(mList[position].logo)
        holder.titleTv.text = mList[position].title
        holder.itemView.setOnClickListener{
            listener.onItemClick(mList[position])
        }


    }

    override fun getItemCount(): Int {
      return mList.size
    }
}
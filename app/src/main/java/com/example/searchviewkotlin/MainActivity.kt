package com.example.searchviewkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import java.util.*

class MainActivity : AppCompatActivity(), RoomAdapter.OnItemClickListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var item: RecyclerView
    private var mList = ArrayList<RoomData>()
    private lateinit var adapter: RoomAdapter
    var whichImg=0


    override fun onItemClick(itemId: RoomData) {
        nextPage(itemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = RoomAdapter(mList,this)
        recyclerView.adapter = adapter


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<RoomData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    fun nextPage(itemId: RoomData){
        val intent=Intent(this@MainActivity,ImageActivity::class.java)
        intent.putExtra("itemId", itemId)
        startActivity(intent)
    }


    private fun addDataToList() {
        mList.add(RoomData("P6", R.drawable.polibuda))
        mList.add(RoomData("2.7.6", R.drawable.polibuda))
        mList.add(RoomData("1.6.18", R.drawable.polibuda))
        mList.add(RoomData("2.7.14", R.drawable.polibuda))
        mList.add(RoomData("2.7.16", R.drawable.polibuda))
        mList.add(RoomData("2.7.21", R.drawable.polibuda))
    }


}
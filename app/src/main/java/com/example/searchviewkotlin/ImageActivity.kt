package com.example.searchviewkotlin

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class ImageActivity:AppCompatActivity() {

    lateinit var itemId: RoomData
    var whichImg=1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemId=intent.getSerializableExtra("itemId") as RoomData
        setContentView(R.layout.activity_img)
        val bind=findViewById<ImageView>(R.id.imageView)
        val storageRef= FirebaseStorage.getInstance().reference.child("${itemId.title}/${whichImg}.jpg")
        val local= File.createTempFile("tmpimage","jpg")
        storageRef.getFile(local).addOnSuccessListener {
            val bitmap= BitmapFactory.decodeFile(local.path)
            bind.setImageBitmap(bitmap)
        }.addOnFailureListener{
            goToList()
        }
        val clickNext=findViewById<AppCompatButton>(R.id.button)
        clickNext.setOnClickListener{
            nextHint(bind)
        }

        val clickPrevious=findViewById<AppCompatButton>(R.id.button3)
        clickPrevious.setOnClickListener{
            if (whichImg>1){
                previousHint(bind)
            }
        }



        val homeButton=findViewById<AppCompatButton>(R.id.button2)
        homeButton.setOnClickListener{
            goToList()
        }


    }

    fun goToList(){
        val intent2= Intent(this,MainActivity::class.java)
        startActivity(intent2)
    }

    fun nextHint(bind: ImageView){
        whichImg+=1;
        val storageRef= FirebaseStorage.getInstance().reference.child("${itemId.title}/${whichImg}.jpg")
        val local= File.createTempFile("tmpimage","jpg")
        storageRef.getFile(local).addOnSuccessListener {
            val bitmap= BitmapFactory.decodeFile(local.path)
            bind.setImageBitmap(bitmap)
        }.addOnFailureListener{
            goToList()
        }
    }

    fun previousHint(bind: ImageView){
        whichImg-=1;
        val storageRef= FirebaseStorage.getInstance().reference.child("${itemId.title}/${whichImg}.jpg")
        val local= File.createTempFile("tmpimage","jpg")
        storageRef.getFile(local).addOnSuccessListener {
            val bitmap= BitmapFactory.decodeFile(local.path)
            bind.setImageBitmap(bitmap)
        }.addOnFailureListener{
            goToList()
        }
    }

}
package com.example.project_test.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_test.R
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(public val image_data_list: ArrayList<Image_data>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.list_view_template,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val current_item=image_data_list[position]

        holder.image_image.setImageBitmap(current_item.image_uri)
        holder.image_label.text=current_item.image_label

    }

    override fun getItemCount(): Int {
        return image_data_list.size
    }


    class MyViewHolder(itemView: View ) :RecyclerView.ViewHolder(itemView)
    {
        val image_image : ImageView =itemView.findViewById(R.id.list_item_template_image)
        val image_label : TextView = itemView.findViewById(R.id.list_item_text)
    }
}
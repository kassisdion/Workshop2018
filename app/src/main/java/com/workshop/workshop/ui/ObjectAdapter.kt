package com.workshop.workshop.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.workshop.workshop.R
import com.workshop.workshop.extension.inflate
import kotlinx.android.synthetic.main.item_object.view.*

class ObjectAdapter : RecyclerView.Adapter<ObjectAdapter.ViewHolder>() {
    /*
    ************************************************************************************************
    ** Private field
    ************************************************************************************************
     */
    private val items: ArrayList<UiObjectModel> = ArrayList()

    /*
    ************************************************************************************************
    ** Public method
    ************************************************************************************************
     */
    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    fun populateObject(data: List<UiObjectModel>) {
        items.addAll(data)
        notifyDataSetChanged()
    }

    /*
    ************************************************************************************************
    ** RecyclerView.Adapter<ObjectAdapter.ViewHolder>() implementation
    ************************************************************************************************
     */
    override fun onBindViewHolder(holder: ObjectAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectAdapter.ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_object, false)
        return ViewHolder(inflatedView)
    }

    /**
     * Simple class used as place holder
     */
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        init {
            v.setOnClickListener(this)
        }

        /*
        ********************************************************************************************
        ** Public method
        ********************************************************************************************
         */
        fun bind(item: UiObjectModel) {
            Picasso.with(itemView.context)
                    .load(item.thumbnailUrl)
                    .placeholder(R.drawable.ic_loading)
                    .into(itemView.itemObject_imageView)

            itemView.itemObject_textView.text = item.title
        }

        /*
        ********************************************************************************************
        ** View.OnClickListener implementation
        ********************************************************************************************
         */
        override fun onClick(v: View?) {
            //Could show a new activity or a popup and display info about the selected item
        }

    }
}
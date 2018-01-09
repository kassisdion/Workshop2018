package com.workshop.workshop.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.workshop.workshop.R
import com.workshop.workshop.extension.inflate
import com.workshop.workshop.ui.main.UiObjectModel
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
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_object, false)
        return ViewHolder(inflatedView)
    }

    /**
     * Simple class used as place holder
     */
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var item: UiObjectModel? = null
        private var shouldShowExtra: Boolean = false

        init {
            v.setOnClickListener(this)
        }

        /*
        ********************************************************************************************
        ** Public method
        ********************************************************************************************
         */
        fun bind(item: UiObjectModel) {
            this.item = item
            this.shouldShowExtra = false

            showData()
            populateData()
        }

        /*
        ********************************************************************************************
        ** View.OnClickListener implementation
        ********************************************************************************************
         */
        override fun onClick(v: View?) {
            shouldShowExtra = !shouldShowExtra

            showData()
            populateExtraData()
        }

        /*
        ********************************************************************************************
        ** Private method
        ********************************************************************************************
         */
        private fun showData() {
            when(shouldShowExtra) {
                true -> {
                    itemView.itemObject_mainInfo.visibility = View.GONE
                    itemView.itemObject_extraInfo.visibility = View.VISIBLE
                }
                false -> {
                    itemView.itemObject_mainInfo.visibility = View.VISIBLE
                    itemView.itemObject_extraInfo.visibility = View.GONE
                }
            }
        }
        private fun populateData() {
            item?.let {
                Picasso.with(itemView.context)
                        .load(it.thumbnailUrl)
                        .placeholder(R.drawable.ic_loading)
                        .into(itemView.itemObject_imageView)

                itemView.itemObject_textView.text = it.title
            }
        }

        private fun populateExtraData() {
            item?.let {
                Picasso.with(itemView.context)
                        .load(it.url)
                        .placeholder(R.drawable.ic_loading)
                        .into(itemView.itemObject_imageView_full)

                itemView.itemObject_textView_full.text = it.title
            }
        }
    }
}
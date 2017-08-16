package com.example.guirassy.moviz.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.guirassy.moviz.R
import com.example.guirassy.moviz.model.Director
import kotlin.properties.Delegates

/**
 * Created by guirassy on 11/08/2017.
 */
class CustomDrawerAdapter(context: Activity, resource: Int, objects: MutableList<Director>) : ArrayAdapter<Director>(context, resource, objects) {

    var directorsList  : MutableList<Director> = mutableListOf()
    var context : Activity
    var layoutResId: Int by Delegates.notNull<Int>()

    init {
        directorsList = objects
        this.context = context
        layoutResId = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View ? = convertView
        if(view == null){

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.menu_list_row_item, null)
            var directorPicIV = view.findViewById<ImageView>(R.id.list_director_picture_iv)
            var directorNameTV = view.findViewById<TextView>(R.id.list_director_name_tv)

            directorPicIV.setImageResource(directorsList[position].pictureResId)
            directorNameTV.text = directorsList[position].name
        }

        return view!!
    }

    override fun getCount(): Int {
        return directorsList.size
    }

    override fun getItem(position: Int): Director {
        return directorsList[position]
    }
}
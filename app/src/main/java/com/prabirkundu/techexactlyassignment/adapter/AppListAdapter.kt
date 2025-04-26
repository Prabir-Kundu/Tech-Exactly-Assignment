package com.prabirkundu.techexactlyassignment.adapter

import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prabirkundu.techexactlyassignment.R
import com.prabirkundu.techexactlyassignment.model.App

class AppListAdapter(
    private val context: Context,
    private val appList: MutableList<App>
): RecyclerView.Adapter<AppListAdapter.MyViewHolder>(){
    private var appListAll: List<App> = appList.toList()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.apply {
            Glide.with(this)
                .load(appList.get(position).app_icon)
                .into( findViewById<ImageView>(R.id.icon));
            findViewById<TextView>(R.id.tvAppName).text = appList.get(position).app_name
            try {
                context.packageManager.getPackageInfo(appList.get(position).app_package_name, 0)
                findViewById<Switch>(R.id.switch1).isChecked  = true
            } catch (e: PackageManager.NameNotFoundException) {
                findViewById<Switch>(R.id.switch1).isChecked  = false
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }

    fun getFilter(): Filter = filter

    private val filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = if (constraint.isNullOrEmpty()) {
                appListAll.toMutableList()
            } else {
                appListAll.filter {
                    it.app_name.lowercase().startsWith(constraint.toString().lowercase())
                }.toMutableList()
            }

            return FilterResults().apply { values = filteredList }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            appList.clear()
            @Suppress("UNCHECKED_CAST")
            appList.addAll(results.values as Collection<App>)
            notifyDataSetChanged()
        }
    }
}
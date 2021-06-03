package com.kanulp.esrandroidtest.ui.list

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kanulp.esrandroidtest.R
import com.kanulp.esrandroidtest.data.model.Item

class NearByAdapter(var recyclerDataArrayList: List<Item>?, mcontext: Context?, var listener: OnItemClickListener) : RecyclerView.Adapter<NearByAdapter.PostViewHolder>() {

    var mcontext : Context? =null
    private var selectedItems: SparseBooleanArray? = null

    init {
        selectedItems = SparseBooleanArray()
        this.mcontext = mcontext
         setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        // Inflate Layout
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val recyclerData: Item = recyclerDataArrayList!![position]
        holder.mBackground.isSelected = selectedItems!!.get(position, false);


//        if (selectedItems!!.get(position, false)) {
//            selectedItems?.delete(position);
//            holder.mBackground.isSelected = false;
//        }
//        else {
//            selectedItems?.put(position, true);
//            holder.mBackground.isSelected = true;
//        }

        holder.txt_title.text = recyclerData.title
        holder.txt_address.text = recyclerData.location.userFriendlyLocation
        holder.txt_ctype.text = recyclerData.ctype

        Glide.with(holder.itemView)
                .load(recyclerData.displayImage.raw)
                .into(holder.img);
        holder.itemView.setOnClickListener{
            listener?.onItemClick(position)
            holder.mBackground.isSelected = true;
        }
    }

    override fun getItemCount(): Int {
        return recyclerDataArrayList!!.size
    }
    override fun getItemViewType(position: Int) = position
    override fun getItemId(position: Int): Long = position.toLong()


    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var txt_title: TextView = itemView.findViewById(R.id.txt_title)
        var txt_address: TextView = itemView.findViewById(R.id.txt_address)
        var txt_ctype: TextView = itemView.findViewById(R.id.txt_ctype)

        var img: AppCompatImageView = itemView.findViewById(R.id.img)
        var mBackground : LinearLayout = itemView.findViewById(R.id.mBackground);

        override fun onClick(v: View?) {
            if (selectedItems!!.get(adapterPosition, false)) {
                selectedItems?.delete(adapterPosition);
                mBackground.isSelected = false;
            }
            else {
                selectedItems?.put(adapterPosition, true);
                mBackground.isSelected = true;
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
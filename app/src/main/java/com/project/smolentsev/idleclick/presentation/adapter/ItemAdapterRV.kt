package com.project.smolentsev.idleclick.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.smolentsev.idleclick.domain.entity.DataItem
import com.project.smolentsev.idleclick.R
import com.project.smolentsev.idleclick.domain.ImageShopItem
import com.project.smolentsev.idleclick.domain.ShortDescShop
import com.project.smolentsev.idleclick.domain.TitleShopItem


class ItemAdapterRV(val context: Context): RecyclerView.Adapter<ItemAdapterRV.ItemAdapterViewHolder>() {
    var itemList = listOf<DataItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemClickListener: ((DataItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapterViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
      return ItemAdapterViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: ItemAdapterViewHolder, position: Int) {
        val _itemList = itemList[position]
        val titleId = TitleShopItem.getTitleShop(_itemList.id, context)
        var shortDesc = ""

        viewHolder.view.setOnClickListener{
            onShopItemClickListener?.invoke(_itemList)
        }

        if(titleId == "") throw RuntimeException("Нет всех совпадений в TitleShopItem" +
                " с Базой Данных")

        shortDesc = ShortDescShop.getShortDescShop(
            _itemList.id,
            context,
            itemList)

        if(shortDesc== "") throw RuntimeException("Нет всех совпадений в ShortDescShop" +
                " с Базой Данных")

        val image = ImageShopItem.getimageShop(_itemList.id) ?: throw
                RuntimeException("Нет всех совпадений в ImageShopItem" +
                    " с Базой Данных")

        viewHolder.viewTitle.text = titleId
        viewHolder.viewCount.text = "${context.getString(R.string.level)} ${_itemList.level}"
        viewHolder.viewPrice.text = "${_itemList.price}$"
        viewHolder.viewImage.setImageResource(image)
        viewHolder.viewShortDesc.text = shortDesc
        Log.d("itemList_AdapterRV",itemList.toString())
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemAdapterViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val viewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val viewShortDesc = view.findViewById<TextView>(R.id.textViewShortDesc)
        val viewImage = view.findViewById<ImageView>(R.id.imageView)
        val viewCount = view.findViewById<TextView>(R.id.textViewCount)
        val viewPrice = view.findViewById<TextView>(R.id.textViewPrice)
    }
}
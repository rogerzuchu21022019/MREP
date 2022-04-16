package nam.zuchu.asm.adapters.users_with_types

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import nam.zuchu.asm.databinding.ItemRvDetailExpenditureBinding
import nam.zuchu.asm.databinding.ItemRvDetailReceiptBinding
import nam.zuchu.asm.models.users.UsersItem
import nam.zuchu.asm.models.userswithtypes.UsersWithTypesItem

class DetailReceiptAdapter :
    RecyclerView.Adapter<DetailReceiptAdapter.DetailExpenditureViewHolder>() {
    var listDetail :List<UsersWithTypesItem> = emptyList()
    var listUser: List<UsersItem> = emptyList()
    lateinit var layoutInflater: LayoutInflater
    lateinit var onClickItemInRecyclerView: OnClickItemInRecyclerView
    lateinit var itemRvDetailReceiptBinding: ItemRvDetailReceiptBinding

    class DetailExpenditureViewHolder(itemRvDetailReceiptBinding: ItemRvDetailReceiptBinding,onClickItemInRecyclerView: OnClickItemInRecyclerView) :
        RecyclerView.ViewHolder(itemRvDetailReceiptBinding.root) {
        init {
            itemRvDetailReceiptBinding.root.setOnClickListener {
                onClickItemInRecyclerView.onClickItem(absoluteAdapterPosition,it)
            }
            itemRvDetailReceiptBinding.clMain.setOnClickListener {
                onClickItemInRecyclerView.onClickItem(absoluteAdapterPosition,it)
            }

            onClickItemInRecyclerView.onClickItem(absoluteAdapterPosition,itemRvDetailReceiptBinding.root)
        }
    }
    fun setOnClick(onClickItemInRecyclerView: OnClickItemInRecyclerView){
        this.onClickItemInRecyclerView = onClickItemInRecyclerView
    }
     fun setDataForAdapter(listUser: List<UsersItem>, listDetail:List<UsersWithTypesItem>){
         this.listUser = listUser
         this.listDetail = listDetail
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailExpenditureViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        itemRvDetailReceiptBinding = ItemRvDetailReceiptBinding.inflate(layoutInflater,parent,false)
        return DetailExpenditureViewHolder(itemRvDetailReceiptBinding = itemRvDetailReceiptBinding, onClickItemInRecyclerView = onClickItemInRecyclerView)
    }

    override fun onBindViewHolder(holder: DetailExpenditureViewHolder, position: Int) {
        var currentDetail = listDetail[position]
        var currentUser = listUser[position]
        itemRvDetailReceiptBinding.user =currentUser
        itemRvDetailReceiptBinding.detail = currentDetail
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
    interface OnClickItemInRecyclerView{
        fun onClickItem(position: Int,view:View)
    }
}
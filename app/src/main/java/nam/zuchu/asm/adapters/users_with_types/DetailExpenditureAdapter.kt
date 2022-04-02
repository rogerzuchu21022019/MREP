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
import nam.zuchu.asm.models.users.UsersItem
import nam.zuchu.asm.models.userswithtypes.UsersWithTypesItem

class DetailExpenditureAdapter :
    RecyclerView.Adapter<DetailExpenditureAdapter.DetailExpenditureViewHolder>() {
    var listDetail :List<UsersWithTypesItem> = emptyList()
    var listUser: List<UsersItem> = emptyList()
    var user:UsersItem?=null
    lateinit var layoutInflater: LayoutInflater
    lateinit var itemRvDetailExpenditureBinding: ItemRvDetailExpenditureBinding

    class DetailExpenditureViewHolder(itemRvDetailExpenditureBinding: ItemRvDetailExpenditureBinding) :
        RecyclerView.ViewHolder(itemRvDetailExpenditureBinding.root) {

    }
     fun setDataForAdapter(listUser: List<UsersItem>, listDetail:List<UsersWithTypesItem>){
         this.listUser = listUser
         this.listDetail = listDetail

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailExpenditureViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        itemRvDetailExpenditureBinding = ItemRvDetailExpenditureBinding.inflate(layoutInflater,parent,false)
        return DetailExpenditureViewHolder(itemRvDetailExpenditureBinding = itemRvDetailExpenditureBinding)
    }

    override fun onBindViewHolder(holder: DetailExpenditureViewHolder, position: Int) {
        var currentDetail = listDetail[position]
        var currentUser = listUser[position]

         itemRvDetailExpenditureBinding.user = currentUser
        itemRvDetailExpenditureBinding.detail = currentDetail
    }

    override fun getItemCount(): Int {
        return listDetail.size
    }
}
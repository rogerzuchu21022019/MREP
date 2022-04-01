package nam.zuchu.asm.adapters.users_with_types

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nam.zuchu.asm.databinding.ItemRvDetailExpenditureBinding
import nam.zuchu.asm.models.users.UsersItem
import nam.zuchu.asm.models.userswithtypes.UsersWithTypesItem

class DetailExpenditureAdapter :
    RecyclerView.Adapter<DetailExpenditureAdapter.DetailExpenditureViewHolder>() {

    var listDetailExpenditure: List<UsersWithTypesItem> = emptyList()
    var listUser: List<UsersItem> = emptyList()
    lateinit var layoutInflater: LayoutInflater
    lateinit var itemRvDetailExpenditureBinding: ItemRvDetailExpenditureBinding

    class DetailExpenditureViewHolder(itemRvDetailExpenditureBinding: ItemRvDetailExpenditureBinding) :
        RecyclerView.ViewHolder(itemRvDetailExpenditureBinding.root) {

    }
     fun setDataForAdapter(listDetailExpenditure: List<UsersWithTypesItem>,listUser: List<UsersItem>){
        this.listDetailExpenditure = listDetailExpenditure
         this.listUser = listUser
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailExpenditureViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        itemRvDetailExpenditureBinding = ItemRvDetailExpenditureBinding.inflate(layoutInflater,parent,false)
        return DetailExpenditureViewHolder(itemRvDetailExpenditureBinding = itemRvDetailExpenditureBinding)
    }

    override fun onBindViewHolder(holder: DetailExpenditureViewHolder, position: Int) {
        val currentDetail = listDetailExpenditure[position]
        val currentUser = listUser[position]
        itemRvDetailExpenditureBinding.user = currentUser
        
        itemRvDetailExpenditureBinding.detail = currentDetail
    }

    override fun getItemCount(): Int {
        return listDetailExpenditure.size
    }
}
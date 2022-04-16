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
import nam.zuchu.asm.databinding.ItemRvUsersBinding
import nam.zuchu.asm.models.users.UsersItem
import nam.zuchu.asm.models.userswithtypes.UsersWithTypesItem

class HomeAdapter :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    var listUser: List<UsersItem> = emptyList()
    lateinit var layoutInflater: LayoutInflater
    lateinit var itemHome:ItemRvUsersBinding

    class HomeViewHolder(itemHome:ItemRvUsersBinding ) :
        RecyclerView.ViewHolder(itemHome.root) {

    }
     fun setDataForAdapter(listUser: List<UsersItem>){
         this.listUser = listUser
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        itemHome = ItemRvUsersBinding.inflate(layoutInflater,parent,false)
        return HomeViewHolder(itemHome = itemHome)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var currentUser = listUser[position]
        itemHome.users =currentUser
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}
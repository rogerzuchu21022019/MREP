package nam.zuchu.asm.adapters.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nam.zuchu.asm.databinding.ItemRvUsersBinding
import nam.zuchu.asm.models.users.UsersItem

class UserInformationsAdapter:RecyclerView.Adapter<UserInformationsAdapter.UserInformationViewHolder>(){

     var listUsers:List<UsersItem> = emptyList()
    lateinit var layoutInflater: LayoutInflater
    lateinit var itemRvUsersBinding: ItemRvUsersBinding
    lateinit var onItemClick: onItemClickListenerInRecyclerView

    class UserInformationViewHolder(itemRvUsersBinding: ItemRvUsersBinding,onItemClick: onItemClickListenerInRecyclerView) : RecyclerView.ViewHolder(itemRvUsersBinding.root) {
        init {
            onItemClick.onItemClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInformationViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        itemRvUsersBinding = ItemRvUsersBinding.inflate(layoutInflater,parent,false)
        return UserInformationViewHolder(itemRvUsersBinding=itemRvUsersBinding, onItemClick = onItemClick)
    }

    fun setDataForAdapter(listUsers:List<UsersItem>){
        this.listUsers = listUsers
        notifyDataSetChanged()
    }
    fun setItemOnClickListenter(onItemClick: onItemClickListenerInRecyclerView){
        this.onItemClick = onItemClick
    }

    override fun onBindViewHolder(holder: UserInformationViewHolder, position: Int) {
        var currentUser= listUsers[position]
        itemRvUsersBinding.users = currentUser
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }
    interface onItemClickListenerInRecyclerView{
        fun onItemClick(position: Int)
    }

}
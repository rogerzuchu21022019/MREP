package nam.zuchu.asm.fragments.features.receipts

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.databinding.FragmentListReceiptBinding
import nam.zuchu.asm.databinding.ItemRvTypesOfNameBinding
import nam.zuchu.asm.fragments.crud.AddNewReceiptBottomSheetFM
import nam.zuchu.asm.fragments.ui.ReceiptFM
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService


@DelicateCoroutinesApi
class ListReceiptFM : Fragment(), TypesOfNameAdapter.OnClickItemInRecyclerView {
    lateinit var fmReceiptBinding: FragmentListReceiptBinding
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    lateinit var itemRvTypesOfNameBinding: ItemRvTypesOfNameBinding
    lateinit var onClickItemInRecyclerView: TypesOfNameAdapter.OnClickItemInRecyclerView
    lateinit var snackbar: Snackbar
    lateinit var adapterType: TypesOfNameAdapter
    lateinit var navController:NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fmReceiptBinding = FragmentListReceiptBinding.inflate(layoutInflater)
        initRecyclerView()
        getAllTypes()
        initClick()
        swipeLeftToDelete()
        return fmReceiptBinding.root
    }

    private fun getAllTypes() {
        GlobalScope.launch(Dispatchers.Main) {
            fmReceiptBinding.pbWait.visibility = View.VISIBLE
            val response = apiService.getTypeByStatus("Thu")
            adapterType.setDataForAdapter(response.body()!!)
            fmReceiptBinding.pbWait.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        adapterType = TypesOfNameAdapter()


        fmReceiptBinding.rvListReceipt.adapter = adapterType
        adapterType.setOnClickItem(this)

        fmReceiptBinding.rvListReceipt.setHasFixedSize(true)
        fmReceiptBinding.rvListReceipt.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    fun initClick() {
        fmReceiptBinding.lavAddReceipt.setOnClickListener {
            findNavController().navigate(R.id.action_drawerReceipt_to_addNewReceiptBottomSheetFragment2)
        }
    }

    fun swipeLeftToDelete() {
        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // TODO: get position
                val position: Int = viewHolder.absoluteAdapterPosition
                GlobalScope.launch(Dispatchers.Main){
                    val  responseType = apiService.getTypeByStatus("Thu")
                    val id =responseType.body()!![position].idType
                    val status = responseType.body()!![position].status
                    val typeOfName= responseType.body()!![position].typeOfName
                    val responseUser = apiService.getAllUsers()

                    if (responseType.isSuccessful){
                        val userName =responseUser.body()!![position].userName
                        apiService.deleteTypeOfExpenditure(id)
//                        apiService.deleteDetail(id,userName)
//                        snackbar= Snackbar.make(requireView(),"$id",3000)
//                        initRecyclerView()
//                        getDataFromAPI()
//                        snackbar.setAction("UNDO DELETE $id") {
//                            GlobalScope.launch(Dispatchers.Main){
//                                apiService.createNewType(status,typeOfName)
//                            }
//                        }

                    }

                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addBackgroundColor(R.color.red)
                    .addSwipeLeftActionIcon(R.drawable.delete)
                    .addSwipeLeftLabel("DELETE")
                    .create()
                    .decorate();

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(fmReceiptBinding.rvListReceipt)


    }

    override fun onItemClick(position: Int, view: View) {
        if(position==-1){
            return
        }else{
            snackbar = Snackbar.make(requireView(),"Position $position",1000)
            snackbar.show()
        }
        val id = view.id
        if (id==R.id.ivUpdate){

            findNavController().navigate(R.id.action_drawerReceipt_to_addNewReceiptBottomSheetFragment2)

        }
    }


}
package nam.zuchu.asm.fragments.features.expenditures

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.*
import nam.zuchu.asm.R
import nam.zuchu.asm.adapters.types.TypesOfNameAdapter
import nam.zuchu.asm.databinding.FragmentListExpenditureBinding
import nam.zuchu.asm.databinding.ItemRvTypesOfNameBinding
import nam.zuchu.asm.networks.API
import nam.zuchu.asm.networks.APIService


@DelicateCoroutinesApi
 class ListExpendituresFM : Fragment(), TypesOfNameAdapter.OnClickItemInRecyclerView {
    lateinit var fmExpenditureBinding: FragmentListExpenditureBinding
    lateinit var adapter: TypesOfNameAdapter
    lateinit var itemRvTypesOfNameBinding: ItemRvTypesOfNameBinding
    var apiService: APIService = API.getAPI().create(APIService::class.java)
    lateinit var snackbar: Snackbar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fmExpenditureBinding = FragmentListExpenditureBinding.inflate(layoutInflater)
        initRecyclerView()
        getDataFromAPI()
        initClick()
        swipeLeftToDelete()
        return fmExpenditureBinding.root
    }

    private fun getDataFromAPI() {
        GlobalScope.launch(Dispatchers.Main) {
            fmExpenditureBinding.pbWait.visibility = View.VISIBLE
            val response = apiService.getTypeByStatus("Chi")
            if (response.isSuccessful) {
                adapter.setDataForAdapter(response.body()!!)
                fmExpenditureBinding.pbWait.visibility = View.GONE

            }
        }
    }

    private fun initRecyclerView() {
        adapter = TypesOfNameAdapter()
        itemRvTypesOfNameBinding = ItemRvTypesOfNameBinding.inflate(layoutInflater)
        adapter.setOnClickItem(this)
        fmExpenditureBinding.rvListExpenditure.setHasFixedSize(true)
        fmExpenditureBinding.rvListExpenditure.adapter = adapter
        fmExpenditureBinding.rvListExpenditure.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }


    private fun initClick() {
        fmExpenditureBinding.lavAddExpenditure.setOnClickListener {
            findNavController().navigate(R.id.action_drawerExpenditure_to_addNewExpenditureBottomSheetFragment)
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
                    val  responseType = apiService.getTypeByStatus("Chi")
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
        itemTouchHelper.attachToRecyclerView(fmExpenditureBinding.rvListExpenditure)


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
            findNavController().navigate(R.id.action_drawerExpenditure_to_addNewDetailExpenditureBSFM)

        }
    }


}





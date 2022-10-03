package com.example.secondprojectbymvvm.view.checkout.order

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.ItemViewOrderBinding
import com.example.secondprojectbymvvm.model.data.order.Order
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutSummaryFragment
import com.example.secondprojectbymvvm.view.checkout.checkout.CheckoutSummaryFragment.Companion.BILL_TOTAL
import com.example.secondprojectbymvvm.view.checkout.order.orderdetails.OrderDetailsFragment
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel

class OrderAdapter(
    private val viewModel: CheckoutViewModel,
    private val orderArrayList: List<Order>,
    private val context: Context
)
    :RecyclerView.Adapter<OrderAdapter.OrderViewHolder>(){

    private lateinit var binding:ItemViewOrderBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemViewOrderBinding.inflate(layoutInflater, parent, false)
        sharedPreferences = context.getSharedPreferences(LoginActivity.Account_Information, AppCompatActivity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return OrderViewHolder(binding.root)
    }

    override fun getItemCount() = orderArrayList.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(
            order = orderArrayList[position]
        )
    }
    inner class OrderViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bind(order: Order) {
            binding.txtOrderId.text = order.orderId.toString()
            binding.txtOrderDate.text = order.order_date
            binding.txtOrderAmount.text = order.bill_amount

            itemView.setOnClickListener { p0 ->
                val activity = p0!!.context as AppCompatActivity
                val orderDetailsFragment = OrderDetailsFragment()
                val bundle = Bundle()
                val orderId = order.orderId
                bundle.putInt(ORDER_ID, orderId)
                orderDetailsFragment.arguments = bundle
                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout_full, orderDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
    companion object{
        const val ORDER_ID = "order_Id"
    }
}
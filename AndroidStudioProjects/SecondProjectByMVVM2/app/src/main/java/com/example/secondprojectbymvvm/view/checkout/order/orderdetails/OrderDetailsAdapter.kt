package com.example.secondprojectbymvvm.view.checkout.order.orderdetails

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.secondprojectbymvvm.databinding.ItemViewOrderDetailsBinding
import com.example.secondprojectbymvvm.model.data.order.Order
import com.example.secondprojectbymvvm.view.authentication.LoginActivity
import com.example.secondprojectbymvvm.viewmodel.CheckoutViewModel

class OrderDetailsAdapter(
    private val viewModel: CheckoutViewModel,
    private val orderList:List<Order>,
    private val context:Context)
    :RecyclerView.Adapter<OrderDetailsAdapter.OrderViewHolder>(){

    private lateinit var binding : ItemViewOrderDetailsBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemViewOrderDetailsBinding.inflate(layoutInflater, parent, false)
        sharedPreferences = context.getSharedPreferences(LoginActivity.Account_Information, AppCompatActivity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        return OrderViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(
            order = orderList[position]
        )
    }

    override fun getItemCount() = orderList.size

    inner class OrderViewHolder(view: View):RecyclerView.ViewHolder(view) {
        fun bind(order: Order){
            binding.txtOrderDetailsAddress.text = order.address
            binding.txtOrderDetailsAddressTitle.text = order.addressTitle
            binding.txtOrderDetailsPaymentInfo.text = order.payment_method
            binding.txtOrderStatusValue.text = order.order_status
            binding.txtOrderDetailsBillAmountValue.text = order.bill_amount
            binding.txtOrderDetailsPickUpOption.text = order.delivery_type

        }
    }

}
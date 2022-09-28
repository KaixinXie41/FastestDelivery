package com.example.secondprojectbymvvm.view.checkout.order.checkout

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.secondprojectbymvvm.R
import com.example.secondprojectbymvvm.databinding.AddAddressBinding
import com.example.secondprojectbymvvm.model.local.address.Address
import com.example.secondprojectbymvvm.view.authentication.LoginActivity.Companion.Account_Information
import com.example.secondprojectbymvvm.view.authentication.LoginActivity.Companion.USER_ID

class CheckoutDeliveryFragment : Fragment() {

    private lateinit var addressLArrayList: ArrayList<Address>
    private lateinit var currentView: View
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var addressRadioGroup: RadioGroup
    private lateinit var title:String
    private lateinit var address:String
    private lateinit var hashMap : HashMap<Int,Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout_delivery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentView = view
        sharedPreferences = this.requireActivity()
            .getSharedPreferences(Account_Information, AppCompatActivity.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        hashMap = HashMap()
        val userId = sharedPreferences.getString(USER_ID, "-1")
        userId.let {
        }

        addressRadioGroup = currentView.findViewById(R.id.radio_group_choose_address)
        addressRadioGroup.setOnCheckedChangeListener() { group: RadioGroup, checkId: Int ->
            val radioButtonCheck = group.findViewById<RadioButton>(group.checkedRadioButtonId)
            radioButtonCheck?.let {
                val index = hashMap[radioButtonCheck.id]
                if (index != null) {
                    title = addressLArrayList[index].title
                    address = addressLArrayList[index].address
                }
            }
        }
        val btnNext: Button = view.findViewById(R.id.btn_next_page)
        btnNext.setOnClickListener {
            editor.apply {
                putString(ADDRESS_TITLE, title)
                putString(ADDRESS, address)
            }.apply()
            (this.parentFragment as CheckoutMealFragment).nextPager()
        }

        val btnAdd: Button = view.findViewById(R.id.btn_add_address)
        btnAdd.setOnClickListener {
            showAddDialog()
        }
    }
        private fun showAddDialog() {
            val dialogBinding = AddAddressBinding.inflate(layoutInflater)

            val builder = AlertDialog.Builder(currentView.context).apply {
                setView(dialogBinding.root)
                setCancelable(false)
            }

            val dialog = builder.create()
            dialogBinding.apply {
                btnSave.setOnClickListener{
                    val title = edtAddressTitle.text.toString()
                    val address = edtAddress.text.toString()
                    val userId = sharedPreferences.getString(USER_ID, "-1")
                    userId?.let {
                    }
                    dialog.dismiss()
                }
                btnCancel.setOnClickListener{
                    dialog.dismiss()
                }
            }
            dialog.show()
        }
    companion object {
        const val ADDRESS_TITLE = "address_title"
        const val ADDRESS = "address_address"
    }
}


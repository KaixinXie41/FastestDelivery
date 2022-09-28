package com.example.secondprojectbymvvm.model.local.cart

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.secondprojectbymvvm.model.local.cart.Constants.Constants.TABLE_NAME

class CartDao (private val context:Context){
    private val dbHelper = DBHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addCart(cart: Cart):Long{
        val contentValues = ContentValues()
        contentValues.apply {
            put("mealName", cart.mealName)
            put("mealId", cart.mealId)
            put("mealImageUrl",cart.mealImageUrl)
            put("mealPrice",cart.mealPrice)
            put("count",cart.count)
        }
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateCartMeal(cart: Cart):Boolean{
        val contentValues = ContentValues()
        contentValues.apply {
            put("mealName", cart.mealName)
            put("mealId", cart.mealId)
            put("mealImageUrl",cart.mealImageUrl)
            put("mealPrice",cart.mealPrice)
            put("count",cart.count)
        }
        val numOfChange:Int = db.update(TABLE_NAME, contentValues, "cartId = ${cart.cartId}", null)
        return numOfChange == 1
    }

    fun deleteCartMeal(cartId: Long):Boolean{
        val numOfChange : Int = db.delete(TABLE_NAME, "cartId = $cartId", null)
        return numOfChange == 1
    }

    fun clearTable() {
        db.execSQL("delete from $TABLE_NAME")
    }

    @SuppressLint("Range", "Recycle")
    fun getCartProduct(cartId: Int): Cart?{
        val cursor : Cursor = db.query(TABLE_NAME, null,"cartId =?", arrayOf("$cartId"), null, null, null )
        if(cursor.moveToFirst()) {
            val cartId = cursor.getLong(cursor.getColumnIndex("cartId"))
            val mealName = cursor.getString(cursor.getColumnIndex("mealName"))
            val mealId = cursor.getString(cursor.getColumnIndex("mealId"))
            val mealPrice = cursor.getDouble(cursor.getColumnIndex("mealPrice"))
            val mealImageUrl = cursor.getString(cursor.getColumnIndex("mealImageUrl"))
            val count = cursor.getInt(cursor.getColumnIndex("count"))
            return Cart(cartId, mealName, mealId, mealPrice, count, mealImageUrl)
        }
        return null
    }
    @SuppressLint("Range")
    fun getAllCartMeal():ArrayList<Cart>{
        val cartMealList = ArrayList<Cart>()
        val cursor: Cursor = db.query(TABLE_NAME, null,null,null,null,null,null)
        if(cursor.moveToFirst()){
            do{
                val cartId = cursor.getLong(cursor.getColumnIndex("cartId"))
                val mealName = cursor.getString(cursor.getColumnIndex("mealName"))
                val mealId = cursor.getString(cursor.getColumnIndex("mealId"))
                val mealPrice = cursor.getDouble(cursor.getColumnIndex("mealPrice"))
                val mealImageUrl = cursor.getString(cursor.getColumnIndex("mealImageUrl"))
                val count = cursor.getInt(cursor.getColumnIndex("count"))
                cartMealList.add(Cart(cartId, mealName, mealId, mealPrice, count, mealImageUrl))
            }while (cursor.moveToNext())
        }
        return cartMealList
    }

    @SuppressLint("Range")
    fun getCartMealByMealId(mealId:Int): Cart?{
        val cursor : Cursor = db.query(TABLE_NAME, null,"mealId =?", arrayOf("$mealId"), null, null, null )
        if(cursor.moveToFirst()) {
            val cartId = cursor.getLong(cursor.getColumnIndex("cartId"))
            val mealName = cursor.getString(cursor.getColumnIndex("mealName"))
            val mealId = cursor.getString(cursor.getColumnIndex("mealId"))
            val mealPrice = cursor.getDouble(cursor.getColumnIndex("mealPrice"))
            val mealImageUrl = cursor.getString(cursor.getColumnIndex("mealImageUrl"))
            val count = cursor.getInt(cursor.getColumnIndex("count"))
            return Cart(cartId, mealName, mealId, mealPrice, count, mealImageUrl)
        }
        return null
    }
}
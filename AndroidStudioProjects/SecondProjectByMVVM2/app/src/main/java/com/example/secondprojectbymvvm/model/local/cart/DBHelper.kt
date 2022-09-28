package com.example.secondprojectbymvvm.model.local.cart

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.secondprojectbymvvm.model.local.cart.Constants.Constants.CREATE_CART_TABLE
import com.example.secondprojectbymvvm.model.local.cart.Constants.Constants.DB_NAME
import com.example.secondprojectbymvvm.model.local.cart.Constants.Constants.DB_VERSION

class DBHelper (context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_CART_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion < newVersion){
            db?.execSQL(CREATE_CART_TABLE)
        }
    }
}
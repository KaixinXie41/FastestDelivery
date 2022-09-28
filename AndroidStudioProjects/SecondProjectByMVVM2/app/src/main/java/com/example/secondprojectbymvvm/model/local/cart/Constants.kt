package com.example.secondprojectbymvvm.model.local.cart

object Constants {
    object Constants {

        const val DB_NAME = "Meal_App_DB"
        const val DB_VERSION = 1
        const val TABLE_NAME = "cartMeal"

        val CREATE_CART_TABLE = """CREATE TABLE cartMeal (
        cartId INTEGER PRIMARY KEY AUTOINCREMENT,
        mealName TEXT,
        mealId TEXT,
        price DOUBLE,
        mealImageUrl TEXT,
        count INTEGER
        )""".trimIndent()
    }

}
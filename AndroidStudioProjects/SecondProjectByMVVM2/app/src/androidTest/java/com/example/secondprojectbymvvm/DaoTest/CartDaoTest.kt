package com.example.secondprojectbymvvm.DaoTest

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.dao.AddressDao
import com.example.secondprojectbymvvm.model.local.dao.CartDao
import com.example.secondprojectbymvvm.model.local.entities.Address
import com.example.secondprojectbymvvm.model.local.entities.Cart
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CartDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var cartDao: CartDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        cartDao = appDatabase.getCartDao()
    }

    @Test
    fun testSaveNote() {
        val  cart = Cart(
            0,
            "Barbeque Chicken",
            "1",
            20.00,
            2,
            40.00,
            "www.google.com",
            "Chicken"
        )

        val cartId = cartDao.addCart(cart)
        val saveItem = cartDao.getCartMealByCartId(cartId)

        Assert.assertNotNull(saveItem)
        Assert.assertEquals(cartId, saveItem?.cartId)
    }


    @Test
    fun testDeleteNote() {
        val  cart = Cart(
            0,
            "Barbeque Chicken",
            "1",
            20.00,
            2,
            40.00,
            "www.google.com",
            "Chicken"
        )

        val cartId = cartDao.addCart(cart)
        val saveItem = cartDao.getCartMealByCartId(cartId)

        Assert.assertNotNull(saveItem)
        Assert.assertEquals(cartId, saveItem?.cartId)

        cartDao.deleteCart(saveItem)

        junit.framework.Assert.assertNull(cartDao.getCartMealByCartId(cartId))
    }

    @Test
    fun testUpdateNote() {
            val  cart = Cart(
                0,
                "Barbeque Chicken",
                "1",
                20.00,
                2,
                40.00,
                "www.google.com",
                "Chicken"
            )

        val cartId = cartDao.addCart(cart)
        val saveItem = cartDao.getCartMealByCartId(cartId)
            val newImageUrl = "www.facebook.com"

            saveItem?.mealImageUrl = newImageUrl
            cartDao.updateCart(saveItem!!)

            val updateNote = cartDao.getCartMealByCartId(cartId)

            junit.framework.Assert.assertEquals(saveItem.mealImageUrl, updateNote?.mealImageUrl)
        }
}
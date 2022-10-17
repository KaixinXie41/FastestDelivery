package com.example.secondprojectbymvvm.DaoTest

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.dao.OrderDao
import com.example.secondprojectbymvvm.model.local.entities.Order
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class OrderDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var orderDao: OrderDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        orderDao = appDatabase.getOrderDao()
    }

    @Test
    fun testSaveNote() {
        runBlocking {
            val order = Order(
                0,
                "1721 N Custer Rd",
                "Home",
                20.00,
                "10/12/2022. 11:10",
                "out of delivery",
                "paypal",
                "Delivery"
            )

            val orderId: Long = orderDao.insert(order)
            val saveItem = orderDao.getOrderByOrderId(orderId)

            Assert.assertNotNull(saveItem)
            Assert.assertEquals(orderId, saveItem.orderId)
        }
    }

    @Test
    fun testDeleteNote() {
        runBlocking {
            val order = Order(
                0,
                "1721 N Custer Rd",
                "Home",
                20.00,
                "10/12/2022. 11:10",
                "out of delivery",
                "paypal",
                "Delivery"
            )

            val orderId: Long = orderDao.insert(order)
            val saveItem = orderDao.getOrderByOrderId(orderId)

            Assert.assertNotNull(saveItem)
            Assert.assertEquals(orderId, saveItem?.orderId)

            orderDao.delete(saveItem!!)

            junit.framework.Assert.assertNull(orderDao.getOrderByOrderId(orderId))
        }
    }

    @Test
    fun testUpdateNote() {
        runBlocking {
            val order = Order(
                0,
                "1721 N Custer Rd",
                "Home",
                20.00,
                "10/12/2022. 11:10",
                "out of delivery",
                "paypal",
                "Delivery"
            )

            val orderId: Long = orderDao.insert(order)
            val saveItem = orderDao.getOrderByOrderId(orderId)
            val newAddressTitle = "Office"

            saveItem?.addressTitle = newAddressTitle
            orderDao.update(saveItem!!)

            val updateNote = orderDao.getOrderByOrderId(orderId)

            junit.framework.Assert.assertEquals(saveItem.addressTitle, updateNote?.addressTitle)
        }
    }
}
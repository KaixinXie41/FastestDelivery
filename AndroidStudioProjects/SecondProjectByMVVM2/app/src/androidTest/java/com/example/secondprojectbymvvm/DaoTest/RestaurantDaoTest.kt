package com.example.secondprojectbymvvm.DaoTest

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.dao.AddressDao
import com.example.secondprojectbymvvm.model.local.dao.RestaurantDao
import com.example.secondprojectbymvvm.model.local.entities.Address
import com.example.secondprojectbymvvm.model.local.entities.Restaurant
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RestaurantDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var restaurantDao: RestaurantDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        restaurantDao = appDatabase.getRestaurantDao()
    }

    @Test
    fun testSaveNote() {
        val restaurant = Restaurant(0,"Canes","1902 N US 75-Central Expy","4",33.227983889437134, -96.63681366926913)

        val restaurantId = restaurantDao.insert(restaurant)
        val saveItem = restaurantDao.getRestaurantById(restaurantId)

        Assert.assertNotNull(saveItem)
        Assert.assertEquals(restaurantId, saveItem.resId)
    }


    @Test
    fun testDeleteNote() {
        val restaurant = Restaurant(1,"Canes","1902 N US 75-Central Expy","4",33.227983889437134, -96.63681366926913)


        val restaurantId = restaurantDao.insert(restaurant)
        val saveItem = restaurantDao.getRestaurantById(restaurantId)

        Assert.assertNotNull(saveItem)
        Assert.assertEquals(restaurantId, saveItem?.resId)

        restaurantDao.delete(saveItem!!)

        junit.framework.Assert.assertNull(restaurantDao.getRestaurantById(restaurantId))
    }

    @Test
    fun testUpdateNote() {
        runBlocking {
            val restaurant = Restaurant(0,"Canes","1902 N US 75-Central Expy","4",33.227983889437134, -96.63681366926913)


            val restaurantId = restaurantDao.insert(restaurant)
            val saveItem = restaurantDao.getRestaurantById(restaurantId)
            val newRestaurantName = "Pizza Hut"

            saveItem?.res_name = newRestaurantName
            restaurantDao.update(saveItem!!)

            val updateNote = restaurantDao.getRestaurantById(restaurantId)

            junit.framework.Assert.assertEquals(saveItem.res_name, updateNote?.res_name)
        }
    }
}
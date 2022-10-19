package com.example.secondprojectbymvvm.dao

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.dao.AddressDao
import com.example.secondprojectbymvvm.model.local.entities.Address
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AddressDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var addressDao: AddressDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        addressDao = appDatabase.getAddressDao()
    }

    @Test
    fun testSaveNote() {
            val address = Address(
                0,
                "1721 N Custer Rd",
                "Home"
            )

            val addressId = addressDao.insert(address)
            val saveItem = addressDao.getAddressByAddressId(addressId)

            assertNotNull(saveItem)
            assertEquals(addressId, saveItem.addressId)
        }


    @Test
    fun testDeleteNote() {
            val address = Address(
                0,
                "1721 N Custer Rd",
                "Home"
            )

        val addressId = addressDao.insert(address)
        val saveItem = addressDao.getAddressByAddressId(addressId)

            assertNotNull(saveItem)
            assertEquals(addressId, saveItem.addressId)

        addressDao.delete(saveItem)

            assertNull(addressDao.getAddressByAddressId(addressId))
        }

    @Test
    fun testUpdateNote() {
        runBlocking {
            val address = Address(
                0,
                "1721 N Custer Rd",
                "Home"
            )

            val addressId = addressDao.insert(address)
            val saveItem = addressDao.getAddressByAddressId(addressId)
            val newAddressTitle = "Office"

            saveItem.title = newAddressTitle
            addressDao.update(saveItem)

            val updateNote = addressDao.getAddressByAddressId(addressId)

            assertEquals(saveItem.title, updateNote.title)
        }
    }
}
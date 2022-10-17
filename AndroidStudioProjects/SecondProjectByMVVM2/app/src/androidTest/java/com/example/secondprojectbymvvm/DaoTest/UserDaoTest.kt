package com.example.secondprojectbymvvm.DaoTest

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.secondprojectbymvvm.model.local.AppDatabase
import com.example.secondprojectbymvvm.model.local.dao.UserDao
import com.example.secondprojectbymvvm.model.local.entities.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = appDatabase.getUserDao()
    }

    @Test
    fun testSaveNote() {
        val user = User(0,"Kaixin Xie","2149980092","kaixin@yahoo.com","123456789")

        val userId = userDao.addUser(user)
        val saveItem = userDao.getUserByUserId(userId)

        Assert.assertNotNull(saveItem)
        Assert.assertEquals(userId, saveItem.userId)
    }


    @Test
    fun testDeleteNote() {
        val user = User(0,"Kaixin Xie","2149980092","kaixin@yahoo.com","123456789")

        val userId = userDao.addUser(user)
        val saveItem = userDao.getUserByUserId(userId)

        Assert.assertNotNull(saveItem)
        Assert.assertEquals(userId, saveItem?.userId)

        userDao.deleteUser(saveItem!!)

        junit.framework.Assert.assertNull(userDao.getUserByUserId(userId))
    }

    @Test
    fun testUpdateNote() {
        runBlocking {
            val user = User(0,"Kaixin Xie","2149980092","kaixin@yahoo.com","123456789")

            val userId = userDao.addUser(user)
            val saveItem = userDao.getUserByUserId(userId)
            val newEmail = "kaixin@gmail.com"

            saveItem?.fullName = newEmail
            userDao.updateUser(saveItem!!)

            val updateNote = userDao.getUserByUserId(userId)

            junit.framework.Assert.assertEquals(saveItem.fullName, updateNote?.fullName)
        }
    }
}
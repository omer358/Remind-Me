package com.example.remindme.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PeopleDao {

    @Insert
    fun insert(people: People)

    @Update
    fun update (people: People)

    @Query("SELECT * FROM people_table WHERE id = :key")
    fun getPerson(key: Long):LiveData<People>

    @Query("DELETE FROM people_table")
    fun clear()

    @Query("SELECT * FROM people_table ORDER BY id DESC")
    fun getAllPeople(): LiveData<List<People>>

    @Query("DELETE FROM people_table WHERE id = :key")
    fun removePerson(key: Long)


}
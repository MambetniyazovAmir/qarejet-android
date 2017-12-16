package com.shagalalab.qarejet.data.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by atabek on 12/14/2017.
 */

@Entity(tableName = "accounts")
data class AccountDbModel(@PrimaryKey val id: Int, val title: String)
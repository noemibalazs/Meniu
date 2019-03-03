package com.noemi.android.meniu.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "orders")
data class Order(@PrimaryKey var data: String,
                 var supa: String,
                 var feluldoi: String,
                 var garnitura: String)

package com.imaginato.homeworkmvvm.data.local.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Login.TABLE_NAME)
data class Login(
    /**
     * column for user id
     */
    @PrimaryKey
    @ColumnInfo(name = COLUMN_USER_ID)
    val userId: String,
    /**
     * column for User name
     */
    @ColumnInfo(name = COLUMN_USER_NAME)
    val userName: String,
    /**
     * column for X-Acc
     */
    @ColumnInfo(name = COLUMN_X_ACC)
    val xAcc: String,

    /**
     * Column for deleted or not
     */
    @ColumnInfo(name = COLUMN_IS_DELETED)
    val isDeleted: Boolean
) {

    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_USER_NAME = "user_name"
        const val COLUMN_X_ACC = "x_acc"
        const val COLUMN_IS_DELETED = "is_deleted"
    }
}
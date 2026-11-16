package com.example.iot

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "FireSafe.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // crear la base de datos
        db.execSQL(
            """
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                correo TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL,
                rol TEXT NOT NULL
            )
            """.trimIndent()
        )

        insertarUsuarioInicial(db, "usuario@firesafe.com", "1234", "USUARIO")
        insertarUsuarioInicial(db, "admin@firesaf123e.com", "admin123", "ADMINISTRADOR")
        insertarUsuarioInicial(db, "sistema@firesafe.com", "sys123", "SISTEMA")
    }

    private fun insertarUsuarioInicial(db: SQLiteDatabase, correo: String, pass: String, rol: String) {
        val valores = ContentValues().apply {
            put("correo", correo)
            put("password", pass)
            put("rol", rol)
        }
        db.insert("usuarios", null, valores)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    //Función para validar credenciales y devolver el rol (o null si no coincide)
    fun validarLogin(correo: String, password: String): String? {
        val db = readableDatabase
        var rolEncontrado: String? = null

        val cursor = db.rawQuery(
            "SELECT rol FROM usuarios WHERE correo = ? AND password = ?",
            arrayOf(correo.trim().lowercase(), password.trim())
        )

        if (cursor.moveToFirst()) {
            rolEncontrado = cursor.getString(0)
        }

        cursor.close()
        return rolEncontrado
    }
}
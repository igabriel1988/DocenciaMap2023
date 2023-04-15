package com.example.docenciamap2023.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.example.docenciamap2023.Modelo.Usuario;
import java.util.ArrayList;
public class DbUsuario extends DbHelper {


    Context context;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(String nombre, String telefono, String correo_electronico) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);

            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Usuario> mostrarUsuarios() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario;
        Cursor cursorUsuario;

        cursorUsuario = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " ORDER BY nombre ASC", null);

        if (cursorUsuario.moveToFirst()) {
            do {
                usuario = new Usuario();
                usuario.setId(cursorUsuario.getInt(0));
                usuario.setNombre(cursorUsuario.getString(1));
                usuario.setCorreo(cursorUsuario.getString(3));
                listaUsuarios.add(usuario);
            } while (cursorUsuario.moveToNext());
        }

        cursorUsuario.close();

        return listaUsuarios;
    }

    public Usuario verUsuario(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuario usuario = null;
        Cursor cursorUsuario;

        cursorUsuario = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorUsuario.moveToFirst()) {
            usuario = new Usuario();
            usuario.setId(cursorUsuario.getInt(0));
            usuario.setNombre(cursorUsuario.getString(1));
            usuario.setCorreo(cursorUsuario.getString(3));
        }

        cursorUsuario.close();

        return usuario;
    }

    public boolean editarContacto(int id, String nombre, String telefono, String correo_electronico) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_USUARIOS + " SET nombre = '" + nombre + "', telefono = '" + telefono + "', correo_electronico = '" + correo_electronico + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarUsuario(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_USUARIOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}

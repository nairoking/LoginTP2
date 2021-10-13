package com.peluffo.logintp2.request;

import android.content.Context;
import android.util.Log;

import com.peluffo.logintp2.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ApiClient {
   /* private static File file;

    private static File conectar(Context context){
        if(file==null){
            file = new File(context.getDataDir(), "usuarios.txt");
        }
        return file;
    }*/
    public static void guardar(Context context, Usuario usuario){
        File file = new File(context.getDataDir(), "usuarios.txt");;
        try {
            FileOutputStream nodo = new FileOutputStream(file);
            BufferedOutputStream buffer = new BufferedOutputStream(nodo);
            ObjectOutputStream objectOutput = new ObjectOutputStream(buffer);
            objectOutput.writeObject(usuario);
            objectOutput.flush();
            objectOutput.close();
        }catch (FileNotFoundException ex){
            Log.d("Error", "File not found Exception");
        }catch (IOException io){
            Log.d("Error", "Error al escribir archivo");
        }
    }
    public static Usuario leer(Context context){
        Usuario user = null;
        File file = new File(context.getDataDir(), "usuarios.txt");;
        try{
            FileInputStream nodo = new FileInputStream(file);
            BufferedInputStream buffer = new BufferedInputStream(nodo);
            ObjectInputStream objectInput = new ObjectInputStream(buffer);
            /*int i = objectInput.available();
            while(objectInput.available()> -1){
                user = (Usuario) objectInput.readObject();
            }*/
            user = (Usuario) objectInput.readObject();
            objectInput.close();
            return user;
        }catch (FileNotFoundException ex){
            Log.d("Error", "File not found Exception");
            return user;
        }catch (IOException io){
            Log.d("Error", "Error al leer archivo");
            return user;
        } catch (ClassNotFoundException e) {
            Log.d("Error", "Class not found Exception");
            return user;
        }
        //return user;
    }
    public static Usuario login(Context context, String email, String contra){
        Usuario logeado = null;
        Usuario user = leer(context);
        Long dni = user.getDni();
        String apellido = user.getApellido();
        String nombre = user.getNombre();
        String mail = user.getMail();
        String password = user.getPassword();
        if(email.equals(mail) && contra.equals(password)){
            logeado = new Usuario(dni, apellido, nombre, mail, password);
        }
        return logeado;
    }
}

package com.peluffo.logintp2.ui.login;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.peluffo.logintp2.model.Usuario;
import com.peluffo.logintp2.request.ApiClient;
import com.peluffo.logintp2.ui.registro.Registro;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> mensajeM;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Integer> getMensajeM() {
        if(mensajeM == null){
            mensajeM = new MutableLiveData<>();
        }
        return mensajeM;
    }

    public void iniciarSesion(String em, String contra){
        Usuario user = ApiClient.login(context, em, contra);
        if(user != null){
            Intent intent = new Intent(context, Registro.class);
            intent.putExtra("valor", 1);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else{
            mensajeM.setValue(0);
        }
    }
}

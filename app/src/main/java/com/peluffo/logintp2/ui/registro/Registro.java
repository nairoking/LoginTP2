package com.peluffo.logintp2.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.peluffo.logintp2.R;
import com.peluffo.logintp2.model.Usuario;

public class Registro extends AppCompatActivity {
    private TextView tvInfo;
    private EditText etDni, etApellido, etNombre, etRMail, etRPassword;
    private Button btGuardar;
    private RegistroViewModel registroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        registroViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        iniciarVista();
        registroViewModel.getUsuarioM().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText(usuario.getDni()+"");
                etApellido.setText(usuario.getApellido());
                etNombre.setText(usuario.getNombre());
                etRMail.setText(usuario.getMail());
                etRPassword.setText(usuario.getPassword());
            }
        });
        registroViewModel.getEstadoM().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvInfo.setVisibility(integer);
            }
        });
        registroViewModel.getMensajeM().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvInfo.setText(s);
            }
        });
        registroViewModel.cargarUsuario();
    }
    private void iniciarVista(){
        tvInfo = findViewById(R.id.tvInfo);
        etDni = findViewById(R.id.etDni);
        etApellido = findViewById(R.id.etApellido);
        etNombre = findViewById(R.id.etNombre);
        etRMail = findViewById(R.id.etRMail);
        etRPassword = findViewById(R.id.etRPassword);
        btGuardar = findViewById(R.id.btGuardar);
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registroViewModel.guardarUsuario(
                        Integer.parseInt(etDni.getText().toString()),
                        etApellido.getText().toString(),
                        etNombre.getText().toString(),
                        etRMail.getText().toString(),
                        etRPassword.getText().toString()
                );
            }
        });
    }
}
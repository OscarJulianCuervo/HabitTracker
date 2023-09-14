package com.app.habittrackerv1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class SingUp extends AppCompatActivity {

    TextView BienvenidoLabel, continuarlabel, nuevoUsuario;
    ImageView singUpImageView;
    TextInputLayout usuarioTextField, contrasenaTextField;
    MaterialButton inicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        singUpImageView = findViewById(R.id.singUpImageView);
        BienvenidoLabel = findViewById(R.id.BienvenidoLabel);
        continuarlabel = findViewById(R.id.continuarlabel);
        usuarioTextField = findViewById(R.id.usuarioTextField);
        contrasenaTextField = findViewById(R.id.contrasenaTextField);
        inicioSesion = findViewById(R.id.inicioSesion);
        nuevoUsuario = findViewById(R.id.nuevoUsuario);

        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transitionBack();
            }
        });
    }

    @Override
    public void onBackPressed(){
        transitionBack();
    }

    public void transitionBack (){
        Intent createPackageContext;
        Intent intent = new Intent( SingUp.this, LoginActivity.class);

        Pair[] pairs = new Pair[7];
        pairs[0] = new Pair<View, String>(singUpImageView, "logoImageTrans");
        pairs[1] = new Pair<View, String>(BienvenidoLabel, "textTrans");
        pairs[2] = new Pair<View, String>(continuarlabel, "iniciaSesionTextTrans");
        pairs[3] = new Pair<View, String>(usuarioTextField, "emailInputTextTrans");
        pairs[4] = new Pair<View, String>(contrasenaTextField, "passwordInputTextTrans");
        pairs[5] = new Pair<View, String>(inicioSesion, "buttonSingInTrans");
        pairs[6] = new Pair<View, String>(nuevoUsuario, "newUserTrans");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation( SingUp.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
            finish();
        }

    }
}
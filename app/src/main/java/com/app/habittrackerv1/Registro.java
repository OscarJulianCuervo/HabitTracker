package com.app.habittrackerv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    Button Registrar;
    EditText usuario, clave, correo;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        usuario = findViewById(R.id.usuario);
        clave = findViewById(R.id.clave);
        correo = findViewById(R.id.correo);
        Registrar = findViewById(R.id.registrar);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameUser = usuario.getText().toString().trim();
                String emailUser = correo.getText().toString().trim();
                String passUser = clave.getText().toString().trim();

                if (nameUser.isEmpty() || emailUser.isEmpty() || passUser.isEmpty()) {
                    Toast.makeText(Registro.this, "COMPLETE LOS DATOS", Toast.LENGTH_SHORT).show();

                } else {
                    RegisterUser(nameUser, emailUser, passUser);

                }
            }

            ;

            private void RegisterUser(String nameUser, String emailUser, String passUser) {
                mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String id = mAuth.getCurrentUser().getUid();
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", id);
                        map.put("usuario", nameUser);
                        map.put("clave", passUser);
                        map.put("correo", emailUser);

                        mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();
                                startActivity(new Intent(Registro.this, MainActivity.class));
                                Toast.makeText(Registro.this, "usuario guardado", Toast.LENGTH_SHORT).show();
                            }                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Registro.this, "error al guardar", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro.this, "error al registrar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
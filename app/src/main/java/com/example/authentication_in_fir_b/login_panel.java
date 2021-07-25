package com.example.authentication_in_fir_b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_panel extends AppCompatActivity {


    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_panel);

        TextView email1 =(TextView)findViewById(R.id.email1);
        TextView password1 =(TextView)findViewById(R.id.password1);
        Button btn1 =(Button)findViewById(R.id.already1);

        Auth= FirebaseAuth.getInstance();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaild =email1.getText().toString();
                String passwo=password1.getText().toString();

                Auth.signInWithEmailAndPassword(emaild, passwo)
                        .addOnCompleteListener(login_panel.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(login_panel.this,Welcome.class);
                                    intent.putExtra("emails",Auth.getCurrentUser().getEmail());
                                    intent.putExtra("uids",Auth.getCurrentUser().getUid());
                                    startActivity(intent);

                                }else{
                                   email1.setText("");
                                   password1.setText("");
                                   Toast.makeText(login_panel.this, "lawde lag gaye!!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });


    }


}
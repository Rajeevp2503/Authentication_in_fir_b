package com.example.authentication_in_fir_b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button signin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        signin=(Button)findViewById(R.id.signin);




        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String em= email.getText().toString();
                String pass = password.getText().toString();
                String uid;

               // Log.e("raj", "authorization ke pehle sab thik hai");

                // Initialize Firebase Auth
                mAuth = FirebaseAuth.getInstance();
                //now main authentication will be done
                mAuth.createUserWithEmailAndPassword(em , pass)
                .addOnCompleteListener(MainActivity.this ,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.e("raj", "authorization ho gaya hai");
                            //now with sign redirect to welcome page!!
                            Intent intent = new Intent(MainActivity.this,Welcome.class);
                            intent.putExtra("emails",mAuth.getCurrentUser().getEmail());
                            intent.putExtra("uids",mAuth.getCurrentUser().getUid());
                            startActivity(intent);



                            Toast.makeText(getApplicationContext(),"registerd",Toast.LENGTH_LONG).show();
                        }else{
                            email.setText("");
                            password.setText("");
                            Toast.makeText(getApplicationContext(),"ERRRRRORR",Toast.LENGTH_LONG).show(); }
                    }
                });
            }
        });


}

    public void already(View view){

        startActivity(new Intent(MainActivity.this,login_panel.class));

    }

    }


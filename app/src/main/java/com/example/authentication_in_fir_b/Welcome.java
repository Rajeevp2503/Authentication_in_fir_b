package com.example.authentication_in_fir_b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        TextView mail, uid;
        mail= (TextView)findViewById(R.id.mail);
        uid=(TextView)findViewById(R.id.uid);
        Button signout= (Button)findViewById(R.id.signout);

        mail.setText(getIntent().getStringExtra("emails").toString());
        uid.setText(getIntent().getStringExtra("uids").toString());

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Welcome.this,MainActivity.class));
            }
        });

    }
}
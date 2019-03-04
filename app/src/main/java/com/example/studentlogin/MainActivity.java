package com.example.studentlogin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button register;
    TextView login;

    FirebaseAuth mAuth;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        register=(Button) findViewById(R.id.register);
        login=(TextView) findViewById(R.id.login);

        FirebaseApp.initializeApp(this);

    }

     void RegisterUser()
    {
      String lemail=email.getText().toString().trim();   //local varible lmail
      String lpassword=password.getText().toString().trim(); //local variable password

      if(TextUtils.isEmpty(lemail)){
          //emial is empty
          Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
          //stopping function execution further
          return;
      }

      if(TextUtils.isEmpty(lpassword)){
          //password is empty
          Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
          //stopping function execution further
          return;
      }
      //user has entered his details now put it into firebase
        mAuth.createUserWithEmailAndPassword(lemail, lpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Registration successful",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Registration Failed",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }


    public void func1(View view)
    {
        if(view == register)
        {
            RegisterUser();
        }

        if(view == login) {

        }
    }

}

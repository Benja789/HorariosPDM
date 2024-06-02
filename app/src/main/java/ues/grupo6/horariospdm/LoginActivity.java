package ues.grupo6.horariospdm;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText textEmail, textPassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textPassword = findViewById(R.id.text_password);
        textEmail = findViewById(R.id.text_email);
        mAuth = FirebaseAuth.getInstance();
    }

    private void initSessionWithEmailAndPassword () {
        String email =  textEmail.getText().toString();
        String password =  textPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
//                    Toast.makeText(LoginActivity.this, user.getUid().toString(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Inicio de sesion erronea, favor de revisar sus credenciales",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void signInWithEmailAndPassword (View v) {
        initSessionWithEmailAndPassword();
    }
    public void signInWithGoogle (View v) {

    }
}
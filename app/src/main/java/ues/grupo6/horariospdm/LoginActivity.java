package ues.grupo6.horariospdm;

import android.content.Intent;
import android.os.Bundle;
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
import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;

public class LoginActivity extends AppCompatActivity {
    private static final String ONESIGNAL_APP_ID = "05bc1393-e1bb-491a-bde1-a7bd25007941";

    private EditText textEmail, textPassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textPassword = findViewById(R.id.text_password);
        textEmail = findViewById(R.id.text_email);
        mAuth = FirebaseAuth.getInstance();


        // Verbose Logging set to help debug issues, remove before releasing your app.
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);

        // OneSignal Initialization
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);

        // requestPermission will show the native Android notification permission prompt.
        // NOTE: It's recommended to use a OneSignal In-App Message to prompt instead.
        OneSignal.getNotifications().requestPermission(false, Continue.none());
    }
    @Override
    protected void onStart () {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) redirectToHomePage(user);
    }

    private void initSessionInFirebase ( String provider ) {
        if ( provider.equals("email") ) {
            String email =  textEmail.getText().toString();
            String password =  textPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if ( task.isSuccessful() ) redirectToHomePage(mAuth.getCurrentUser());
                    else Toast.makeText(LoginActivity.this, "Inicio de sesion erronea, favor de revisar sus credenciales", Toast.LENGTH_SHORT).show();
                }
            });
        }  else if ( provider.equals("google") ) {

        } else Toast.makeText(this, "Inicio de sesion no valido", Toast.LENGTH_SHORT).show();

    }
    public void signInWithEmailAndPassword (View v) {
        initSessionInFirebase("email");
    }
    public void signInWithGoogle (View v) {
        initSessionInFirebase("google");
    }

    private void redirectToHomePage (FirebaseUser user) {
        Intent redirectToMain = new Intent(this, MainActivity.class);
        startActivity(redirectToMain);
    }
}
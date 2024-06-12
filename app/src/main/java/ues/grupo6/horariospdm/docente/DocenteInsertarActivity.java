package ues.grupo6.horariospdm.docente;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;
import ues.grupo6.horariospdm.evento.EventoInsertarActivity;

public class DocenteInsertarActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText text_first_name, text_second_name,text_first_lastname,text_second_lastname, text_married_name, text_profession, txt_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        text_first_name = findViewById(R.id.text_first_name);
        text_second_name = findViewById(R.id.text_second_name);
        text_first_lastname = findViewById(R.id.text_first_lastname);
        text_second_lastname = findViewById(R.id.text_second_lastname);
        text_profession = findViewById(R.id.text_profession);
        text_married_name = findViewById(R.id.text_married_lastname);
        txt_email = findViewById(R.id.text_email);
    }

    public void cleanFields (View v) {
        text_first_name.setText("");
        text_second_name.setText("");
        text_first_lastname.setText("");
        text_second_lastname.setText("");
        text_profession.setText("");
        text_married_name.setText("");
        txt_email.setText("");
    }

    public void saveNewTeacher (View v) {
        Docente newTeacher = new Docente();
        newTeacher.setFirstName(text_first_name.getText().toString());
        newTeacher.setSecondName(text_second_name.getText().toString());
        newTeacher.setFirstLastName(text_first_lastname.getText().toString());
        newTeacher.setSecondLastName(text_second_lastname.getText().toString());
        newTeacher.setProfession(text_profession.getText().toString());
        newTeacher.setMarriedName(text_married_name.getText().toString());
        newTeacher.setActive(true);

        if (newTeacher.getFirstName().equals("")) {
            Toast.makeText(this, "Es obligatorio el primer nombre del docente", Toast.LENGTH_SHORT).show();
            return;
        } else if ( newTeacher.getFirstName().length() >=  25) {
            Toast.makeText(this, "Campo de primer nombre solo con un maximo de 25 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newTeacher.getSecondName().equals("")) {
            Toast.makeText(this, "Es obligatorio el segundo nombre del docente", Toast.LENGTH_SHORT).show();
            return;
        } else if ( newTeacher.getSecondName().length() >=  25) {
            Toast.makeText(this, "Campo de segundo nombre solo con un maximo de 25 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newTeacher.getFirstLastName().equals("")) {
            Toast.makeText(this, "Es obligatorio el primer apellido del docente", Toast.LENGTH_SHORT).show();
            return;
        } else if ( newTeacher.getFirstLastName().length() >=  25) {
            Toast.makeText(this, "Campo de primer apellido solo con un maximo de 25 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newTeacher.getSecondLastName().equals("")) {
            Toast.makeText(this, "Es obligatorio el segundo apellido del docente", Toast.LENGTH_SHORT).show();
            return;
        } else if ( newTeacher.getSecondLastName().length() >=  25) {
            Toast.makeText(this, "Campo de segundo apellido solo con un maximo de 25 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (newTeacher.getProfession().equals("")) {
            Toast.makeText(this, "Es obligatorio la profesion", Toast.LENGTH_SHORT).show();
            return;
        } else if ( newTeacher.getSecondLastName().length() >=  25) {
            Toast.makeText(this, "Campo de profesion solo con un maximo de 25 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (txt_email.getText().equals("") ){
            Toast.makeText(this, "Es obligatorio el correo electronico", Toast.LENGTH_SHORT).show();
            return;
        } else if ( txt_email.getText().length() >=  50) {
            Toast.makeText(this, "Campo de correo electronico solo con un maximo de 50 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }


        CollectionReference myRef = db.collection("users");

        // Crea un nuevo documento con los datos que deseas guardar
        Map<String, Object> data = new HashMap<>();
        data.put("firstName",newTeacher.getFirstName() + " "+ newTeacher.getSecondName());
        data.put("lastName", newTeacher.getFirstLastName() + " "+ newTeacher.getSecondLastName());
        data.put("lastNameMarried", newTeacher.getMarriedName());
        data.put("docente_titulo", newTeacher.getProfession());
        data.put("email", txt_email.getText().toString());
        data.put("estado", true);
        data.put("deleted", false);
        data.put("role", "teacher");


        // Verifica si el correo electrónico ya existe en la base de datos
        db.collection("users")
                .whereEqualTo("email", txt_email.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                Toast.makeText(DocenteInsertarActivity.this, "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show();
                            } else {
                                myRef.add(data)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(DocenteInsertarActivity.this, "Dato guardado correctamente", Toast.LENGTH_SHORT).show();
                                                cleanFields(null);
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error adding document", e);
                                            }
                                        });
                            }
                        }
                    }
                });


    }
}
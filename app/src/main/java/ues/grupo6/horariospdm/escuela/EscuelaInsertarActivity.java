package ues.grupo6.horariospdm.escuela;

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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import ues.grupo6.horariospdm.R;
import ues.grupo6.horariospdm.asignatura.AsignaturaInsertarActivity;

public class EscuelaInsertarActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText editNombre_escuela;
    EditText editPrioridad_escuela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_insertar);
        editNombre_escuela = (EditText) findViewById(R.id.editNombre_escuela);
        editPrioridad_escuela = (EditText) findViewById(R.id.editPrioridad_escuela);
    }

    public void insertarEscuela(View v) {
        String emailInput = editNombre_escuela.getText().toString();
        CollectionReference myRef = db.collection("schools");

        // Verifica si el correo electrónico ya existe en la base de datos
        db.collection("schools")
                .whereEqualTo("name", emailInput)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                Toast.makeText(EscuelaInsertarActivity.this, "Escuela ya existe", Toast.LENGTH_SHORT).show();
                            } else {
                                // Crea un nuevo documento con los datos que deseas guardar
                                Map<String, Object> data = new HashMap<>();
                                data.put("name", editNombre_escuela.getText().toString());
                                data.put("priority", Integer.parseInt(editPrioridad_escuela.getText().toString()));

                                // Agrega el nuevo documento a la colección
                                myRef.add(data)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(EscuelaInsertarActivity.this, "Escuela insertada correctamente", Toast.LENGTH_SHORT).show();
                                                limpiarTexto(v);
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
    public void limpiarTexto(View v) {

        editNombre_escuela.setText("");
        editPrioridad_escuela.setText("");
    }
}

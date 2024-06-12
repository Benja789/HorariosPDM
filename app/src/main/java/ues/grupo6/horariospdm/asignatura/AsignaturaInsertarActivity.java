package ues.grupo6.horariospdm.asignatura;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ues.grupo6.horariospdm.R;
public class AsignaturaInsertarActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Spinner spinnerId_Escuela;
    EditText editNombre_asignatura;
    EditText editCodigo_asignatura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_asignatura_insertar);
        spinnerId_Escuela = (Spinner) findViewById(R.id.spinnerEscuela);
        editNombre_asignatura = (EditText) findViewById(R.id.editNombreAsignatura);
        editCodigo_asignatura = (EditText) findViewById(R.id.editCodigoAsignatura);
        cargarEscuelasActivas();
    }
    private void cargarEscuelasActivas() {
        CollectionReference myRef = db.collection("schools");
        myRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        list.add(name);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AsignaturaInsertarActivity.this, android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Spinner spinner = findViewById(R.id.spinnerEscuela);
                    spinner.setAdapter(adapter);
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });

    }
    public void insertarAsignatura(View v) {
        CollectionReference myRef = db.collection("subject");

        // Obtén una referencia al documento que deseas referenciar
        db.collection("schools")
                .whereEqualTo("name", spinnerId_Escuela.getSelectedItem().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference tipoEventoRef = document.getReference();
                                // Crea un nuevo documento con los datos que deseas guardar
                                Map<String, Object> data = new HashMap<>();
                                data.put("name", editNombre_asignatura.getText().toString());
                                data.put("code", editCodigo_asignatura.getText().toString());
                                data.put("schoolReference", tipoEventoRef);

                                // Agrega el nuevo documento a la colección
                                myRef.add(data)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(AsignaturaInsertarActivity.this, "Materia creada correctamente", Toast.LENGTH_SHORT).show();
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

    public void limpiarTexto (View v) {
        editNombre_asignatura.setText("");
        editCodigo_asignatura.setText("");
    }

}
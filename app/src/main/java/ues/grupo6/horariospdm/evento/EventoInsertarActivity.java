package ues.grupo6.horariospdm.evento;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

public class EventoInsertarActivity extends Activity {

    EditText edNombreEvento;
    Spinner spTipoEventos;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_insertar);
        edNombreEvento = (EditText) findViewById(R.id.editNombreEvento);
        spTipoEventos = (Spinner) findViewById(R.id.spinnerTipoEvento);
        getData();

    }

    public void insertarEvento(View v) {
        // Obtén una referencia a la colección de Firestore
        CollectionReference myRef = db.collection("evento");

        // Obtén una referencia al documento que deseas referenciar
        db.collection("tipo_evento")
                .whereEqualTo("nombre_tipo_evento", spTipoEventos.getSelectedItem().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference tipoEventoRef = document.getReference();

                                // Crea un nuevo documento con los datos que deseas guardar
                                Map<String, Object> data = new HashMap<>();
                                data.put("nombre_evento", edNombreEvento.getText().toString());
                                data.put("estado_evento", true);
                                data.put("id_tipo_evento", tipoEventoRef);

                                // Agrega el nuevo documento a la colección
                                myRef.add(data)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Toast.makeText(EventoInsertarActivity.this, "Evento insertado", Toast.LENGTH_SHORT).show();
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
        edNombreEvento.setText("");
    }

    public void getData() {
        CollectionReference myRef = db.collection("tipo_evento");
        myRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("nombre_tipo_evento");
                        list.add(name);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(EventoInsertarActivity.this, android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Spinner spinner = findViewById(R.id.spinnerTipoEvento);
                    spinner.setAdapter(adapter);
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
    }

}
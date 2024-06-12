package ues.grupo6.horariospdm.docente;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import ues.grupo6.horariospdm.R;

public class DocenteConsultarActivity extends AppCompatActivity {
    EditText textSearch;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView firstName,secondName,firstLastname, secondLastName, profession, status, marriedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        textSearch = findViewById(R.id.text_search_teacher);
        firstName = findViewById(R.id.text_result_first_name_teacher);
        secondName = findViewById(R.id.text_result_second_name_teacher);
        firstLastname = findViewById(R.id.text_result_first_lastname_teacher);
        secondLastName = findViewById(R.id.text_result_second_lastname_teacher);
        marriedName =  findViewById(R.id.text_married_name);
        profession = findViewById(R.id.text_result_profession_teacher);
        status = findViewById(R.id.text_status_teacher);
    }


    public void cleanSearchText (View v) {
        firstName.setText("");
        secondName.setText("");
        firstLastname.setText("");
        secondLastName.setText("");
        profession.setText("");
        marriedName.setText("");
        status.setText("");
    }

    public void searchTeacher (View v) {

        // Verifica si el correo electrónico ya existe en la base de datos
        db.collection("users")
                .whereEqualTo("email", textSearch.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                String firstNameUser = task.getResult().getDocuments().get(0).getString("firstName");
                                String lastName = task.getResult().getDocuments().get(0).getString("lastName");
                                // El correo electrónico ya existe en la base de datos
                                firstName.setText(firstNameUser.split(" ")[0]);
                                secondName.setText(firstNameUser.split(" ")[1]);
                                firstLastname.setText(lastName.split(" ")[0]);
                                secondLastName.setText(lastName.split(" ")[1]);
                                profession.setText(task.getResult().getDocuments().get(0).getString("docente_titulo"));
                                marriedName.setText(task.getResult().getDocuments().get(0).getString("lastNameMarried"));
                                if (task.getResult().getDocuments().get(0).getBoolean("estado")) status.setText("Activo");
                                else status.setText("Inactivo");

                            } else {
                                Toast.makeText(DocenteConsultarActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
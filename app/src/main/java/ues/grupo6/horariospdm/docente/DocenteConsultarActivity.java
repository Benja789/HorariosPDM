package ues.grupo6.horariospdm.docente;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;

public class DocenteConsultarActivity extends AppCompatActivity {
    ControlBD helper;
    EditText textSearch;

    TextView firstName,secondName,firstLastname, secondLastName, profession, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        helper = new ControlBD(this);
        textSearch = findViewById(R.id.text_search_teacher);
        firstName = findViewById(R.id.text_result_first_name_teacher);
        secondName = findViewById(R.id.text_result_second_name_teacher);
        firstLastname = findViewById(R.id.text_result_first_lastname_teacher);
        secondLastName = findViewById(R.id.text_result_second_lastname_teacher);
        profession = findViewById(R.id.text_result_profession_teacher);
        status = findViewById(R.id.text_status_teacher);
    }


    public void cleanSearchText (View v) {
        firstName.setText("");
        secondName.setText("");
        firstLastname.setText("");
        secondLastName.setText("");
        profession.setText("");
        status.setText("");
    }

    public void searchTeacher (View v) {
        cleanSearchText(null);
        helper.abrir();
        Docente docenteData = helper.consultarDocente(Integer.parseInt(String.valueOf(textSearch.getText())));
        helper.cerrar();
        if(docenteData == null) Toast.makeText(this, "Docente no registrado", Toast.LENGTH_LONG).show();
        else {
            firstName.setText(String.valueOf(docenteData.getFirstName()));
            secondName.setText(String.valueOf(docenteData.getSecondName()));
            firstLastname.setText(String.valueOf(docenteData.getFirstLastName()));
            secondLastName.setText(String.valueOf(docenteData.getSecondLastName()));
            profession.setText(String.valueOf(docenteData.getProfession()));
            if( docenteData.getActive() ) status.setText("Activo");
            else status.setText("Inactivo");
        }
    }
}
package ues.grupo6.horariospdm.docente;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;

public class DocenteActualizarActivity extends AppCompatActivity {

    ControlBD helper;
    Docente teacher;
    EditText idDocente, nameDocente, secondNameDocente, firstLastnameDocente, secondLastNameDocente, marriedNameDocente, professionDocente;
    RadioButton activeDocente, inactiveDocente;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        helper = new ControlBD(this);
        idDocente = findViewById(R.id.text_search_update_teacher);
        nameDocente = findViewById(R.id.text_first_update_name);
        secondNameDocente = findViewById(R.id.text_second_update_name);
        firstLastnameDocente = findViewById(R.id.text_first_lastname_update);
        secondLastNameDocente = findViewById(R.id.text_second_lastname_update);
        marriedNameDocente = findViewById(R.id.text_married_lastname_update);
        professionDocente = findViewById(R.id.text_profession_update);
        activeDocente = findViewById(R.id.radio_btn_active_teacher);
        inactiveDocente = findViewById(R.id.radio_btn_inactive_teacher);
        btnUpdate = findViewById(R.id.btn_save_teacher);
    }


    public void handleChangeRadioButton (View v) {
        if ( teacher != null ) teacher.setActive(v.getId() == R.id.radio_btn_active_teacher);
    }
    public void updateTeacher ( View v ) {
        if ( teacher == null  ) {
            // Para buscar el docente
            helper.abrir();
            Docente docenteData = helper.consultarDocente(Integer.parseInt(String.valueOf(idDocente.getText())));
            helper.cerrar();
            if(docenteData == null) Toast.makeText(this, "Docente no registrado", Toast.LENGTH_LONG).show();
            else {
                nameDocente.setText(String.valueOf(docenteData.getFirstName()));
                nameDocente.setEnabled(true);
                secondNameDocente.setText(String.valueOf(docenteData.getSecondName()));
                secondNameDocente.setEnabled(true);
                firstLastnameDocente.setText(String.valueOf(docenteData.getFirstLastName()));
                firstLastnameDocente.setEnabled(true);
                secondLastNameDocente.setText(String.valueOf(docenteData.getSecondLastName()));
                secondLastNameDocente.setEnabled(true);
                professionDocente.setText(String.valueOf(docenteData.getProfession()));
                professionDocente.setEnabled(true);
                marriedNameDocente.setText(String.valueOf(docenteData.getMarriedName()));
                marriedNameDocente.setEnabled(true);
                if( docenteData.getActive() ) activeDocente.setActivated(true);
                else inactiveDocente.setActivated(true);
                btnUpdate.setText(R.string.btn_actualizar);
            }
        } else {
            // Para actualizarlo
        }

    }

    public void cleanFields (View v ) {

    }
}
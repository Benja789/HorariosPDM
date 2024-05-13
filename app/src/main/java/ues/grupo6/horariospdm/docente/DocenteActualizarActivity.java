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
            teacher = helper.consultarDocente(Integer.parseInt(String.valueOf(idDocente.getText())));
            helper.cerrar();
            if(teacher == null) Toast.makeText(this, "Docente no registrado", Toast.LENGTH_LONG).show();
            else {
                teacher.setIdDocente(Integer.parseInt(String.valueOf(idDocente.getText())));
                nameDocente.setText(teacher.getFirstName());
                nameDocente.setEnabled(true);
                secondNameDocente.setText(teacher.getSecondName());
                secondNameDocente.setEnabled(true);
                firstLastnameDocente.setText(teacher.getFirstLastName());
                firstLastnameDocente.setEnabled(true);
                secondLastNameDocente.setText(teacher.getSecondLastName());
                secondLastNameDocente.setEnabled(true);
                professionDocente.setText(teacher.getProfession());
                professionDocente.setEnabled(true);
                marriedNameDocente.setText(teacher.getMarriedName());
                marriedNameDocente.setEnabled(true);
                activeDocente.setEnabled(true);
                inactiveDocente.setEnabled(true);
                idDocente.setEnabled(false);
                if( teacher.getActive() ) activeDocente.setChecked(true);
                else inactiveDocente.setChecked(true);
                btnUpdate.setText(R.string.btn_actualizar);
            }
        } else {
            // Para actualizarlo
            teacher.setFirstName(String.valueOf(nameDocente.getText()));
            teacher.setSecondName(String.valueOf(secondNameDocente.getText()));
            teacher.setFirstLastName(String.valueOf(firstLastnameDocente.getText()));
            teacher.setSecondLastName(String.valueOf(secondLastNameDocente.getText()));
            teacher.setProfession(String.valueOf(professionDocente.getText()));
            teacher.setMarriedName(String.valueOf(marriedNameDocente.getText()));
            teacher.setActive(activeDocente.isChecked());
            helper.abrir();
            Boolean estado = helper.updateTeacher(teacher);
            helper.cerrar();
            if (estado) {
                Toast.makeText(this, "Docente actualizado", Toast.LENGTH_SHORT).show();
                cleanFields(null);
                teacher = null ;
                btnUpdate.setText(R.string.btn_consultar);
            } else {
                Toast.makeText(this, "No se logro actualizar el registro", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void cleanFields (View v ) {
        idDocente.setEnabled(true);
        nameDocente.setEnabled(false);
        secondNameDocente.setEnabled(false);
        firstLastnameDocente.setEnabled(false);
        secondLastNameDocente.setEnabled(false);
        professionDocente.setEnabled(false);
        marriedNameDocente.setEnabled(false);
        activeDocente.setEnabled(false);
        inactiveDocente.setEnabled(false);
        idDocente.setText("");
        nameDocente.setText("");
        secondNameDocente.setText("");
        firstLastnameDocente.setText("");
        secondLastNameDocente.setText("");
        professionDocente.setText("");
        marriedNameDocente.setText("");
        activeDocente.setChecked(false);
        inactiveDocente.setChecked(false);
    }
}
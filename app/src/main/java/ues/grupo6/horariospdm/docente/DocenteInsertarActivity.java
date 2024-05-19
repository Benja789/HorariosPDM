package ues.grupo6.horariospdm.docente;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;

public class DocenteInsertarActivity extends AppCompatActivity {
    ControlBD helper;
    EditText text_first_name, text_second_name,text_first_lastname,text_second_lastname, text_married_name, text_profession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        helper = new ControlBD(this);
        text_first_name = findViewById(R.id.text_first_name);
        text_second_name = findViewById(R.id.text_second_name);
        text_first_lastname = findViewById(R.id.text_first_lastname);
        text_second_lastname = findViewById(R.id.text_second_lastname);
        text_profession = findViewById(R.id.text_profession);
        text_married_name = findViewById(R.id.text_married_lastname);
    }

    public void cleanFields (View v) {
        text_first_name.setText("");
        text_second_name.setText("");
        text_first_lastname.setText("");
        text_second_lastname.setText("");
        text_profession.setText("");
        text_married_name.setText("");
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
        helper.abrir();
        Toast.makeText(this,  helper.insertarDocente(newTeacher), Toast.LENGTH_SHORT).show();
        helper.cerrar();
        cleanFields(null);
    }
}
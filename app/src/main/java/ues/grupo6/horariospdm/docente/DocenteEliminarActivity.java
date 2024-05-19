package ues.grupo6.horariospdm.docente;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;

public class DocenteEliminarActivity extends AppCompatActivity {
    ControlBD helper;

    EditText deleteText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);
        helper = new ControlBD(this);
        deleteText = findViewById(R.id.text_delete_teacher);
    }

    public void eliminarDocente(View v){
        Docente teacher = new Docente();
        teacher.setIdDocente(Integer.parseInt(String.valueOf(deleteText.getText())));
        helper.abrir();
        helper.eliminateDocent(teacher);
        helper.cerrar();
        Toast.makeText(this, "Registro eliminado correctamente",Toast.LENGTH_SHORT).show();
    }
}
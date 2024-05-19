package ues.grupo6.horariospdm.tipo_evento;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;


public class TipoEventoEliminarActivity extends Activity {
    EditText editIdTipoEvento;
    ControlBD controlhelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_eliminar);
        controlhelper=new ControlBD (this);
        editIdTipoEvento=(EditText)findViewById(R.id.editIdTipoEvento);
    }

    public void eliminarTipoEvento(View v){
        String regEliminadas;
        TipoEvento tipoEvento=new TipoEvento();
        // Verificar si el campo de ID del tipo de evento está vacío
        if (editIdTipoEvento.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un ID de tipo de evento", Toast.LENGTH_SHORT).show();
            return;
        }
        tipoEvento.setId_tipo_evento(Integer.parseInt(editIdTipoEvento.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(tipoEvento);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

    }

}
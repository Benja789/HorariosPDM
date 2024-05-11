package ues.grupo6.horariospdm.tipo_evento;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;

public class TipoEventoConsultarActivity extends Activity {
    ControlBD helper;
    EditText editIdTipoEvento;
    EditText editNombre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_consultar);
        helper = new ControlBD(this);
        editIdTipoEvento = (EditText) findViewById(R.id.editIdTipoEvento);
        editNombre = (EditText) findViewById(R.id.editNombre);
    }

    public void consultarTipoEvento(View v){
        //Verifica que el campo no esté vacio
        if (editIdTipoEvento.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un ID de tipo de evento", Toast.LENGTH_SHORT).show();
            return;
        }
        int idTipoEvento = Integer.parseInt(editIdTipoEvento.getText().toString());
        helper.abrir();
        TipoEvento tipoEvento = helper.consultar(idTipoEvento);
        helper.cerrar();

        if(tipoEvento.getEstado_tipo_evento()!=1)
            Toast.makeText(this, "Tipo de evento no encontrado ", Toast.LENGTH_LONG).show();
        else{
            editNombre.setText(tipoEvento.getNombre_tipo_evento());
            //editEstado.setText(String.valueOf(tipoEvento.getEstado_tipo_evento()));
        }
    }

    public void limpiarTexto(View v){
        editIdTipoEvento.setText("");
        editNombre.setText("");
        //editEstado.setText("");
    }
}
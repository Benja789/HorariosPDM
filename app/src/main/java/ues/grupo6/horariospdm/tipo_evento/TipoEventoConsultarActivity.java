package ues.grupo6.horariospdm.tipo_evento;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;
import ues.grupo6.horariospdm.tipo_grupo.Tipo_Grupo;

public class TipoEventoConsultarActivity extends Activity {
    ControlBD helper;
    EditText editIdTipoEvento;
    EditText editNombreTipoEvento;
    EditText editEstadoTipoEvento;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_consultar);
        helper = new ControlBD(this);
        editIdTipoEvento = (EditText) findViewById(R.id.editIdTipoEvento);
        editNombreTipoEvento = (EditText) findViewById(R.id.editNombreTipoEvento);
        editEstadoTipoEvento = (EditText) findViewById(R.id.editEstadoTipoEvento);
    }

    public void consultarTipoEvento(View v){
        //Verifica que el campo no esté vacio
        if (editIdTipoEvento.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un ID de tipo de evento", Toast.LENGTH_SHORT).show();
            return;
        }

        helper.abrir();
        TipoEvento tipoEvento = helper.consultarTipoEvento(Integer.parseInt(String.valueOf(editIdTipoEvento.getText())));
        helper.cerrar();

        if(tipoEvento == null)
            Toast.makeText(this, "Tipo de evento no encontrado ", Toast.LENGTH_LONG).show();
        else{
            editIdTipoEvento.setText(String.valueOf(tipoEvento.getId_tipo_evento()));
            editNombreTipoEvento.setText(tipoEvento.getNombre_tipo_evento());
            if(tipoEvento.getEstado_tipo_evento()==1){
                editEstadoTipoEvento.setText("Activo");
            } else{
                editEstadoTipoEvento.setText("Inactivo");
            }
        }
    }

    public void limpiarTexto(View v){
        editIdTipoEvento.setText("");
        editNombreTipoEvento.setText("");
        editEstadoTipoEvento.setText("");
    }
}
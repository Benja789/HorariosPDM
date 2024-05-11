package ues.grupo6.horariospdm.tipo_evento;

import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;


public class TipoEventoActualizarActivity extends Activity {
    ControlBD helper;
    EditText editIdtipoEvento;
    EditText editNombre;
    EditText editEstado;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_actualizar);
        helper = new ControlBD(this);
        editIdtipoEvento = (EditText) findViewById(R.id.editIdTipoEvento);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editEstado = (EditText) findViewById(R.id.editEstado);
    }
    public void actualizarTipoEvento(View v) {

        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setId_tipo_evento(Integer.parseInt(editIdtipoEvento.getText().toString()));
        tipoEvento.setNombre_tipo_evento(editNombre.getText().toString());
        tipoEvento.setEstado_tipo_evento(Integer.parseInt(editEstado.getText().toString()));
        helper.abrir();
        String estado = helper.actualizar(tipoEvento);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdtipoEvento.setText("");
        editNombre.setText("");
        editEstado.setText("");
    }
}
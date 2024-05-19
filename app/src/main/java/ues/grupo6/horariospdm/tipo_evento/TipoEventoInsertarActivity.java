package ues.grupo6.horariospdm.tipo_evento;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;


public class TipoEventoInsertarActivity extends Activity {
    ControlBD helper;
    EditText editNombre;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_evento_insertar);
        helper = new ControlBD(this);
        editNombre = (EditText) findViewById(R.id.editNombre);
    }

    public void insertarTipoEvento(View v) {
        String nombre = editNombre.getText().toString();
        String regInsertados;

        TipoEvento tipoEvento = new TipoEvento();
        tipoEvento.setNombre_tipo_evento(nombre);
        tipoEvento.setEstado_tipo_evento(1);

        helper.abrir();
        regInsertados = helper.insertarTipoEvento(tipoEvento);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editNombre.setText("");
    }
}
package ues.grupo6.horariospdm.tipo_ciclo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;
import ues.grupo6.horariospdm.tipo_evento.TipoEvento;

public class TipoCicloInsertarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editNombreTipoCiclo;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_ciclo_insertar);
        helper = new ControlBD(this);
        editNombreTipoCiclo = (EditText) findViewById(R.id.editNombreTipoCiclo);

    }

    public void insertarTipoCiclo(View v) {
        String nombre = editNombreTipoCiclo.getText().toString();

        String regInsertados;

        TipoCiclo tipoCiclo = new TipoCiclo();
        tipoCiclo.setNombre_tipo_ciclo(nombre);
        tipoCiclo.setEstado_tipo_ciclo(1);

        helper.abrir();
        regInsertados = helper.insertarTipoCiclo(tipoCiclo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editNombreTipoCiclo.setText("");
    }
}
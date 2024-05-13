package ues.grupo6.horariospdm.tipo_salon;

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

public class TipoSalonInsertarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editNombreTipoSalon;
    EditText editDisponibleTipoSalon;
    EditText editCodigoTipoSalon;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_salon_insertar);
        helper = new ControlBD(this);
        editNombreTipoSalon = (EditText) findViewById(R.id.editNombreTipoSalon);
        editDisponibleTipoSalon = (EditText) findViewById(R.id.editDisponibleTipoSalon);
        editCodigoTipoSalon = (EditText) findViewById(R.id.editCodigoTipoSalon);
    }

    public void insertarTipoSalon(View v) {
        String nombre = editNombreTipoSalon.getText().toString();
        int disponible = Integer.parseInt(editDisponibleTipoSalon.getText().toString());
        String codigo = editCodigoTipoSalon.getText().toString();
        String regInsertados;

        TipoSalon tipoSalon = new TipoSalon();
        tipoSalon.setNombreTipoSalon(nombre);
        tipoSalon.setDisponibleTipoSalon(disponible);
        tipoSalon.setCodigoTipoSalon(codigo);
        tipoSalon.setEstadoTipoSalon(1);

        helper.abrir();
        regInsertados = helper.insertarTipoSalon(tipoSalon);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editNombreTipoSalon.setText("");
        editDisponibleTipoSalon.setText("");
        editCodigoTipoSalon.setText("");
    }
}
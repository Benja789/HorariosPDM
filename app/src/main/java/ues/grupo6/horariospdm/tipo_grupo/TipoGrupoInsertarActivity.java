package ues.grupo6.horariospdm.tipo_grupo;

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

public class TipoGrupoInsertarActivity extends AppCompatActivity {
    ControlBD helper;
    EditText editNombre_tipo_grupo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_insertar);
        helper = new ControlBD(this);
        editNombre_tipo_grupo = (EditText) findViewById(R.id.editNombre_tipo_grupo);
    }

    public void insertarTipoEvento(View v) {
        String nombre = editNombre_tipo_grupo.getText().toString();
        String regInsertados;

        Tipo_Grupo tipoGrupo = new Tipo_Grupo();
        tipoGrupo.setNombre_tipo_grupo(nombre);
        tipoGrupo.setEstado_tipo_grupo(1);

        helper.abrir();
        regInsertados = helper.insertar(tipoGrupo);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editNombre_tipo_grupo.setText("");
    }
}
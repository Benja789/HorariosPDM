package ues.grupo6.horariospdm.tipo_grupo;

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

public class TipoGrupoEliminarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editId_tipo_grupo;
    EditText editNombre_tipo_grupo;
    EditText editEstado_tipo_grupo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_consultar);
        helper = new ControlBD(this);
        editId_tipo_grupo = (EditText) findViewById(R.id.editIdTipoGrupo);
        editNombre_tipo_grupo = (EditText) findViewById(R.id.editNombre_tipo_grupo);
        editEstado_tipo_grupo = (EditText) findViewById(R.id.editEstadoTipoGrupo);
    }
    public void eliminarTipoGrupo(View v){
        Tipo_Grupo tipoGrupo = new Tipo_Grupo();
        tipoGrupo.setId_tipo_grupo(Integer.parseInt(String.valueOf(editId_tipo_grupo.getText())));
        helper.abrir();
        helper.eliminarTipoGrupo(tipoGrupo);
        helper.cerrar();
        Toast.makeText(this, "Registro eliminado correctamente",Toast.LENGTH_SHORT).show();
    }
}
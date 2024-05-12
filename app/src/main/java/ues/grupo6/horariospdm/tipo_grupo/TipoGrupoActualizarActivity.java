package ues.grupo6.horariospdm.tipo_grupo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;

public class TipoGrupoActualizarActivity extends AppCompatActivity {
        ControlBD helper;
        EditText editId_tipo_grupo;
        EditText editNombre_tipo_grupo;
        EditText editEstado_tipo_grupo;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tipo_grupo_actualizar);
            helper = new ControlBD(this);
            editId_tipo_grupo = (EditText) findViewById(R.id.editIdTipoGrupo);
            editNombre_tipo_grupo = (EditText) findViewById(R.id.editNombre_tipo_grupo);
            editEstado_tipo_grupo = (EditText) findViewById(R.id.editEstadoTipoGrupo);

        }
        public void actualizarTipoGrupo(View v) {
            Tipo_Grupo tipoGrupo = new Tipo_Grupo();
            tipoGrupo.setId_tipo_grupo(Integer.parseInt(String.valueOf(editId_tipo_grupo.getText())));
            tipoGrupo.setNombre_tipo_grupo(editNombre_tipo_grupo.toString());
            tipoGrupo.setEstado_tipo_grupo(Integer.parseInt(String.valueOf(editEstado_tipo_grupo.getText())));
            helper.abrir();
            String estado = helper.actualizarTipoGrupo(tipoGrupo);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }
        public void limpiarTexto(View v) {
            editId_tipo_grupo.setText("");
            editNombre_tipo_grupo.setText("");
            editEstado_tipo_grupo.setText("");

        }
}

package ues.grupo6.horariospdm.tipo_grupo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ues.grupo6.horariospdm.ControlBD;
import ues.grupo6.horariospdm.R;

public class TipoGrupoConsultarActivity extends Activity {
    ControlBD helper;
    EditText editId_tipo_grupo;
    EditText editNombre_tipo_grupo;
    EditText editEstado_tipo_grupo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_consultar);
        helper = new ControlBD(this);
        editId_tipo_grupo = (EditText) findViewById(R.id.editIdTipoGrupo);
        editNombre_tipo_grupo = (EditText) findViewById(R.id.editNombre_tipo_grupo);
        editEstado_tipo_grupo = (EditText) findViewById(R.id.editEstadoTipoGrupo);
    }
    public void consultarTipoGrupo(View v) {
        helper.abrir();
        Tipo_Grupo tipoGrupo = helper.consultarTipoGrupo(Integer.parseInt(String.valueOf(editId_tipo_grupo.getText())));
        helper.cerrar();
        if(tipoGrupo == null)
            Toast.makeText(this, "Tipo grupo no registrado",
                    Toast.LENGTH_LONG).show();
        else{
            editId_tipo_grupo.setText(String.valueOf(tipoGrupo.getId_tipo_grupo()));
            editNombre_tipo_grupo.setText(String.valueOf(tipoGrupo.getNombre_tipo_grupo()));
            if(tipoGrupo.getEstado_tipo_grupo()==1){
                editEstado_tipo_grupo.setText("Activo");
            }
            else{
                editEstado_tipo_grupo.setText("Inactivo");
            }
        }
    }
    public void limpiarTexto(View v) {
        editId_tipo_grupo.setText("");
        editNombre_tipo_grupo.setText("");
        editEstado_tipo_grupo.setText("");
    }
}

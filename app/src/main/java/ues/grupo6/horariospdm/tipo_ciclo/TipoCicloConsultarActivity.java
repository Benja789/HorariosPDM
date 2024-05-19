package ues.grupo6.horariospdm.tipo_ciclo;

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

public class TipoCicloConsultarActivity extends AppCompatActivity {

    ControlBD helper;
    EditText editIdTipoCiclo;
    EditText editNombreTipoCiclo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_ciclo_consultar);
        helper = new ControlBD(this);
        editIdTipoCiclo = (EditText) findViewById(R.id.editIdTipoCiclo);
        editNombreTipoCiclo = (EditText) findViewById(R.id.editNombreTipoCiclo);
    }

    public void consultarTipoCiclo(View v){
        //Verifica que el campo no esté vacio
        if (editIdTipoCiclo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un ID de tipo de ciclo", Toast.LENGTH_SHORT).show();
            return;
        }
        int idTipoCiclo = Integer.parseInt(editIdTipoCiclo.getText().toString());
        helper.abrir();
        TipoCiclo tipoCiclo = helper.consultarTipoCiclo(idTipoCiclo);
        helper.cerrar();

        if(tipoCiclo.getEstado_tipo_ciclo()!=1)
            Toast.makeText(this, "Tipo de ciclo no encontrado ", Toast.LENGTH_LONG).show();
        else{
            editNombreTipoCiclo.setText(tipoCiclo.getNombre_tipo_ciclo());
            //editEstadoTipoCiclo.setText(String.valueOf(tipoEvento.getEstado_tipo_ciclo()));
        }
    }

    public void limpiarTexto(View v){
        editIdTipoCiclo.setText("");
        editNombreTipoCiclo.setText("");
        //editEstado.setText("");
    }
}
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

public class TipoCicloEliminarActivity extends AppCompatActivity {

    EditText editIdTipoCiclo;
    ControlBD controlhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tipo_ciclo_eliminar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void eliminarTipoCiclo(View v){
        String regEliminadas;
        TipoCiclo tipoCiclo=new TipoCiclo();
        // Verificar si el campo de ID del tipo de evento está vacío
        if (editIdTipoCiclo.getText().toString().isEmpty()) {
            Toast.makeText(this, "Ingrese un ID de tipo de evento", Toast.LENGTH_SHORT).show();
            return;
        }
        tipoCiclo.setId_tipo_ciclo(Integer.parseInt(editIdTipoCiclo.getText().toString()));
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(tipoCiclo);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();

    }
}
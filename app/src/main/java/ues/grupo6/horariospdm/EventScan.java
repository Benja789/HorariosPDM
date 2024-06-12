package ues.grupo6.horariospdm;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;

import ues.grupo6.horariospdm.databinding.ActivityEventScanBinding;
import ues.grupo6.horariospdm.databinding.ActivityMainBinding;
import ues.grupo6.horariospdm.evento.Evento;
import ues.grupo6.horariospdm.evento.EventoInsertarActivity;

public class EventScan extends AppCompatActivity {

    ActivityEventScanBinding binding;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(), result ->{
        if (result.getContents() == null){
            Toast.makeText(this, "CANCELADO",Toast.LENGTH_SHORT).show();
        } else{
            //binding.etCodigo.setText(result.getContents());
            String scannedId = result.getContents();
            fetchEventData(scannedId);

        }
    });

    private void fetchEventData(String id_evento) {
        CollectionReference myRef = db.collection("evento");

        myRef.whereEqualTo("id_evento",id_evento)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Evento evento = document.toObject(Evento.class);
                                    // Asigna los valores a los EditText
                                    binding.editIdEvento.setText(evento.getId_evento());
                                    binding.editEstadoEvento.setText(evento.getEstado_evento());
                                    binding.editIdTipoEvento.setText(evento.getId_tipo_evento());
                                    binding.editNombreEvento.setText(evento.getNombre_evento());
                                }
                            } else {
                                Toast.makeText(EventScan.this, "Evento no encontrado", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventScanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnleer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escanear();
            }
        });
    }
    public void getData() {
        CollectionReference myRef = db.collection("evento");
        myRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("nombre_evento");
                        list.add(name);
                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
    }
    private void escanear() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
        options.setPrompt("ESCANEAR CODIGO");
        options.setCameraId(0);
        options.setOrientationLocked(false);
        options.setBeepEnabled(false);
        options.setCaptureActivity(CaptureActivityPortraint.class);
        options.setBarcodeImageEnabled(false);

        barcodeLauncher.launch(options);
    }
}
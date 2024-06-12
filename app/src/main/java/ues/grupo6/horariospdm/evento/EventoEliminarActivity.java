package ues.grupo6.horariospdm.evento;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import ues.grupo6.horariospdm.R;
public class EventoEliminarActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_eliminar);
        getDataInitial();
    }

    public void getDataInitial () {
        linearLayout = findViewById(R.id.card_container);
        linearLayout.removeAllViews();
        CollectionReference myRef = db.collection("evento");
        myRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        View cardView = getLayoutInflater().inflate(R.layout.activity_card_delete_event, null);

                        TextView tvTitle = cardView.findViewById(R.id.tv_title);
                        TextView tvDescription = cardView.findViewById(R.id.tv_description);
                        TextView tvId = cardView.findViewById(R.id.tv_id);

                        tvTitle.setText(document.getString("nombre_evento"));
                        tvDescription.setText(document.getId());
                        tvId.setText(document.getId());

                        linearLayout.addView(cardView);
                    }
                }
            }
        });
    }


    public void deleteEventSelected(View v) {
        TextView text = findViewById(R.id.tv_description);
        System.out.println(text.getText().toString());
        db.collection("evento").document(text.getText().toString()).delete();
        getDataInitial();
    }
}
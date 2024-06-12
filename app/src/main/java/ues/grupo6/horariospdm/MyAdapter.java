package ues.grupo6.horariospdm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<DocumentSnapshot> documentList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_title);
            description = v.findViewById(R.id.tv_description);
        }
    }

    public MyAdapter(List<DocumentSnapshot> documentList) {
        this.documentList = documentList;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_event_home, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DocumentSnapshot document = documentList.get(position);
        holder.title.setText(document.getString("nombre_evento"));
        holder.description.setText(document.getString("description"));
    }

    @Override
    public int getItemCount() {
        return documentList.size();
    }

    // Método para agregar un documento a la lista
    public void addDocument(DocumentSnapshot document) {
        documentList.add(document);
        notifyItemInserted(documentList.size() - 1);
    }

    // Método para eliminar un documento de la lista
    public void removeDocument(int position) {
        if (position >= 0 && position < documentList.size()) {
            documentList.remove(position);
            notifyItemRemoved(position);
        }
    }
}
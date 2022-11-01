package ammovil.com.excelsior;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ammovil.com.excelsior.Model.ModelTeam;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {

    List<ModelTeam> listDatos;
    Context context;
    List<ModelTeam> originalItems;

    public RecyclerViewAdaptador(List<ModelTeam> listDatos) {
        this.listDatos = listDatos;
        this.context = context;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(listDatos);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelTeam datos = listDatos.get(position);

        holder.nombre.setText(datos.getNombre());
        holder.estado.setText(datos.getEstado());
        holder.saldo.setText(datos.getSaldo());
        holder.plan.setText(datos.getPlan());
        holder.invitados.setText(datos.getInvitados());

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, estado, saldo , plan, invitados;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nameIntTeam);
            estado = (TextView) itemView.findViewById(R.id.estadoIntTeam);
            saldo = (TextView) itemView.findViewById(R.id.saldoIntTeam);
            plan= (TextView) itemView.findViewById(R.id.planIntTeam);
            invitados= (TextView) itemView.findViewById(R.id.invitadoNumIntTeam);

        }
    }

}

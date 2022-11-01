package ammovil.com.excelsior.controller.Adapter;

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
import ammovil.com.excelsior.R;
import ammovil.com.excelsior.RecyclerViewAdaptador;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;

public class AdapterInvestor extends RecyclerView.Adapter<AdapterInvestor.ViewHolder> {

    List<TiposMembresiaResponseDto> listDatosInvestor;
    Context context;
    List<TiposMembresiaResponseDto> originalItems;

    public AdapterInvestor(List<ModelTeam> listDatos) {
        this.listDatosInvestor = listDatosInvestor;
        this.context = context;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(listDatosInvestor);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_investor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TiposMembresiaResponseDto datos = listDatosInvestor.get(position);

        holder.descripcion.setText(datos.getDescripcion());
        holder.fee.setText(datos.getFee().toString());
        holder.rendimiento.setText(datos.getRendimiento().toString());
        holder.rangoIcial.setText(datos.getRangoInicial().toString());
        holder.rangoFinal.setText(datos.getRangoFinal().toString());

    }

    @Override
    public int getItemCount() {return listDatosInvestor.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView descripcion;
        TextView fee, rendimiento, rangoIcial, rangoFinal;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            descripcion = (TextView) itemView.findViewById(R.id.titu2Plan6);
            fee = itemView.findViewById(R.id.tituAct2Plan);
            rendimiento = itemView.findViewById(R.id.red2Plan);
            rangoIcial= itemView.findViewById(R.id.saldoPlanInicial);
            rangoFinal= itemView.findViewById(R.id.saldoPlanFinal);

        }
    }
}

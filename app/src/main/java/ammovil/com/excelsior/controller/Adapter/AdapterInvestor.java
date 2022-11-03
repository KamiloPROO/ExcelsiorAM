package ammovil.com.excelsior.controller.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ammovil.com.excelsior.R;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;

public class AdapterInvestor extends RecyclerView.Adapter<AdapterInvestor.ViewHolder> implements View.OnClickListener {

    List<TiposMembresiaResponseDto> listDatosInvestor;
    private View.OnClickListener clickListener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion, fee, rendimiento, rangoIcial, rangoFinal;
        Button btnActPlan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            descripcion = (TextView) itemView.findViewById(R.id.titu2Plan6);
            fee = itemView.findViewById(R.id.tituAct2Plan);
            rendimiento = itemView.findViewById(R.id.red2Plan);
            rangoIcial = itemView.findViewById(R.id.saldoPlanInicial);
            rangoFinal = itemView.findViewById(R.id.saldoPlanFinal);
            btnActPlan = itemView.findViewById(R.id.btnActPlan);

        }
    }

    public AdapterInvestor(List<TiposMembresiaResponseDto> items, Context context) {
        this.listDatosInvestor = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return listDatosInvestor.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_investor, parent, false);
        view.setOnClickListener((View.OnClickListener) this);

        return new ViewHolder(view);
    }

    public void setClickListener(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (clickListener!=null){
            clickListener.onClick(v);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TiposMembresiaResponseDto datos = listDatosInvestor.get(position);

        holder.descripcion.setText(datos.getDescripcion());
        holder.fee.setText(datos.getFee().toString());
        holder.rendimiento.setText(datos.getRendimiento().toString());
        holder.rangoIcial.setText(datos.getRangoInicial().toString());
        holder.rangoFinal.setText(datos.getRangoFinal().toString());

        holder.btnActPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, datos.Id.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

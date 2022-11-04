package ammovil.com.excelsior.controller.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ammovil.com.excelsior.ActivarPlan1;
import ammovil.com.excelsior.R;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;

public class AdapterInvestor extends RecyclerView.Adapter<AdapterInvestor.ViewHolder> implements View.OnClickListener {

    List<TiposMembresiaResponseDto> listDatosInvestor;
    private View.OnClickListener clickListener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTxtDescripcionInvestor, idTxtRendimiento, idTxtRangoInicial, idTxtRangoFinal, idTxtFee;
        ImageView idImgLogoRendimiento, idImgActivacionMensual;
        Button idBtnActivarPlan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTxtDescripcionInvestor = itemView.findViewById(R.id.idTxtDescripcionInvestor);
            idTxtFee = itemView.findViewById(R.id.idTxtFee);
            idTxtRendimiento = itemView.findViewById(R.id.idTxtRendimiento);
            idTxtRangoInicial = itemView.findViewById(R.id.idTxtRangoInicial);
            idTxtRangoFinal = itemView.findViewById(R.id.idTxtRangoFinal);
            idBtnActivarPlan = itemView.findViewById(R.id.idBtnActivarPlan);
            idImgLogoRendimiento = itemView.findViewById(R.id.idImgLogoRendimiento);
            idImgActivacionMensual = itemView.findViewById(R.id.idImgActivacionMensual);

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
        if (clickListener != null) {
            clickListener.onClick(v);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TiposMembresiaResponseDto datos = listDatosInvestor.get(position);

        holder.idTxtDescripcionInvestor.setText(datos.getDescripcion());
        holder.idTxtFee.setText(datos.getFee().toString());
        holder.idTxtRendimiento.setText(datos.getRendimiento().toString());
        holder.idTxtRangoInicial.setText(datos.getRangoInicial().toString());
        holder.idTxtRangoFinal.setText(datos.getRangoFinal().toString());

        holder.idBtnActivarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ActivarPlan1.class);
                i.putExtra("idPlan", ""+position+".0");
                context.startActivity(i);

            }
        });
    }


}

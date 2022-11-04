package ammovil.com.excelsior.controller.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ammovil.com.excelsior.R;
import ammovil.com.excelsior.data.response.MisInversionesResponseDto;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;

public class InversionesAdapter extends RecyclerView.Adapter<InversionesAdapter.ViewHolder> implements View.OnClickListener {

    List<MisInversionesResponseDto> listDatosInvestor;
    private View.OnClickListener clickListener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTxtNobrePlan, idTxtRendimientoMensual, idTxtRangoInicial, idTxtRangoFinal, idTxtCantidadInvertida;
        Button idBtnActivarPlan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTxtNobrePlan = itemView.findViewById(R.id.idTxtNobrePlan);
            idTxtRendimientoMensual = itemView.findViewById(R.id.idTxtRendimientoMensual);
            idTxtRangoInicial = itemView.findViewById(R.id.idTxtRangoInicial);
            idTxtRangoFinal = itemView.findViewById(R.id.idTxtRangoFinal);
            idTxtCantidadInvertida = itemView.findViewById(R.id.idTxtCantidadInvertida);

        }
    }

    public InversionesAdapter(List<MisInversionesResponseDto> items, Context context) {
        this.listDatosInvestor = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return listDatosInvestor.size();
    }

    @NonNull
    @Override
    public InversionesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inversiones, parent, false);
        view.setOnClickListener((View.OnClickListener) this);

        return new InversionesAdapter.ViewHolder(view);
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


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InversionesAdapter.ViewHolder holder, int position) {
        MisInversionesResponseDto datos = listDatosInvestor.get(position);

        holder.idTxtNobrePlan.setText(datos.Descripcion);
        holder.idTxtRendimientoMensual.setText(datos.Rendimiento.toString());
        holder.idTxtRangoInicial.setText(datos.RangoInicial.toString());
        holder.idTxtRangoFinal.setText(datos.RangoFinal.toString());
        holder.idTxtCantidadInvertida.setText(datos.Cant_invertir);
    }


}

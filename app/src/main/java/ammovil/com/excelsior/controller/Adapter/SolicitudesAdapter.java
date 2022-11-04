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
import ammovil.com.excelsior.data.response.InverionBResponseDto;
import ammovil.com.excelsior.data.response.MisInversionesResponseDto;

public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.ViewHolder> implements View.OnClickListener {

    List<InverionBResponseDto> listaSolicitudes;
    private View.OnClickListener clickListener;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTxtNombre, idTxtDireccionWallet, idTxtMontoSOlicitado, idTxtFecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idTxtNombre = itemView.findViewById(R.id.idTxtNombre);
            idTxtDireccionWallet = itemView.findViewById(R.id.idTxtDireccionWallet);
            idTxtMontoSOlicitado = itemView.findViewById(R.id.idTxtMontoSOlicitado);
            idTxtFecha = itemView.findViewById(R.id.idTxtFecha);

        }
    }

    public SolicitudesAdapter(List<InverionBResponseDto> items, Context context) {
        this.listaSolicitudes = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return listaSolicitudes.size();
    }

    @NonNull
    @Override
    public SolicitudesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solicitudes, parent, false);
        view.setOnClickListener((View.OnClickListener) this);

        return new SolicitudesAdapter.ViewHolder(view);
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
    public void onBindViewHolder(@NonNull SolicitudesAdapter.ViewHolder holder, int position) {
        InverionBResponseDto datos = listaSolicitudes.get(position);

        holder.idTxtNombre.setText("...");
        holder.idTxtDireccionWallet.setText("...");
        holder.idTxtMontoSOlicitado.setText(datos.Monto.toString());
        holder.idTxtFecha.setText(datos.Fecha);
    }


}

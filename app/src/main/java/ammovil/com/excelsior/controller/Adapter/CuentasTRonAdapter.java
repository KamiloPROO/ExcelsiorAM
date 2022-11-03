package ammovil.com.excelsior.controller.adapter;

import android.annotation.SuppressLint;
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
import ammovil.com.excelsior.data.response.CuentaTronResponseDto;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;

public class CuentasTRonAdapter extends RecyclerView.Adapter<CuentasTRonAdapter.ViewHolder> {

    List<CuentaTronResponseDto> listDatosInvestor;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView referencia, saldoUSDT, fechaCreacion, fechaActualizacion, nombrePropietario;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            referencia = (TextView) itemView.findViewById(R.id.idTxtReferencia);
            nombrePropietario = itemView.findViewById(R.id.idTxtNombrePropietario);
            saldoUSDT = itemView.findViewById(R.id.idTxtSaldoUSDT);
            fechaCreacion = itemView.findViewById(R.id.txtFechaCreacion);
            fechaActualizacion = itemView.findViewById(R.id.txtFechaActualizacion);

        }
    }

    public CuentasTRonAdapter(List<CuentaTronResponseDto> items, Context context) {
        this.listDatosInvestor = items;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return listDatosInvestor.size();
    }

    @NonNull
    @Override
    public CuentasTRonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuentas_tron, parent, false);
        return new CuentasTRonAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CuentasTRonAdapter.ViewHolder holder, int position) {

        CuentaTronResponseDto datos = listDatosInvestor.get(position);
        holder.referencia.setText(datos.Referencia);
        holder.nombrePropietario.setText(datos.PrivateKey);
        holder.saldoUSDT.setText(datos.SaldoUSDT.toString());
        holder.fechaCreacion.setText(datos.FechaCreacion);
        holder.fechaActualizacion.setText(datos.FechaActulizacion);
    }


}

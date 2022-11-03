package ammovil.com.excelsior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ammovil.com.excelsior.controller.adapter.CuentasTRonAdapter;
import ammovil.com.excelsior.data.request.ConsultaCuentasTronDto;
import ammovil.com.excelsior.data.request.EnviarMensajeTexto;
import ammovil.com.excelsior.data.request.InversionRequestDto;
import ammovil.com.excelsior.data.response.CodigoGeneradoResponse;
import ammovil.com.excelsior.data.response.CuentaTronResponseDto;
import ammovil.com.excelsior.data.response.InversionResponseDto;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;
import ammovil.com.excelsior.databinding.ActivityActivarPlanBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivarPlan1 extends AppCompatActivity {

    private ActivityActivarPlanBinding binding;
    private ProgressBar progressBar;
    private ConsultaCuentasTronDto consultaCuentasTronDto;
    private TiposMembresiaResponseDto tiposMembresiaResponseDto;
    private Double idPlan;
    private InversionRequestDto inversionRequestDto;
    private InversionResponseDto inversionResponseDto;
    private String cantidadInvertir, numeroTelefono, codigoRespuesta;
    private EnviarMensajeTexto enviarMensajeTexto;
    //private CodigoGeneradoResponse codigoGeneradoResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActivarPlanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressBar = binding.idProgresbarActivarPlan;

        idPlan = Double.valueOf(recuperarExtras());
        cantidadInvertir = binding.idTxtMontoAOperar.getText().toString();
        llamarRetrofit(idPlan);
        binding.idBtnComprarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCompraPlan(idPlan, cantidadInvertir);
            }
        });

        //revisar esto que hace falta

        binding.idBtnEnviarCodigoVerificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = binding.idTxtNumeroVerificacion.getText().toString();

                //GenerarCodigoVerificacion();


            }
        });
    }

    private String recuperarExtras() {
        String idPlan = getIntent().getExtras().getString("idPlan");
        return idPlan;
    }

    private void guardarCompraPlan(Double idPlan, String cant_invertir) {
        progressBar.setVisibility(View.VISIBLE);
        inversionRequestDto = new InversionRequestDto();
        inversionRequestDto.IdPersona = Double.valueOf(Constantes.ID_PERSONA);
        inversionRequestDto.planid = idPlan;
        inversionRequestDto.totalvalue = cant_invertir;

        Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
        Call<InversionResponseDto> call = apiervice.GuardarInversion(inversionRequestDto);
        call.enqueue(new Callback<InversionResponseDto>() {
            @Override
            public void onResponse(Call<InversionResponseDto> call, Response<InversionResponseDto> response) {
                progressBar.setVisibility(View.GONE);
                inversionResponseDto = response.body();
                Toast.makeText(ActivarPlan1.this, inversionResponseDto.IdPersona.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<InversionResponseDto> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void llamarRetrofit(Double idPlan) {
        progressBar.setVisibility(View.VISIBLE);
        consultaCuentasTronDto = new ConsultaCuentasTronDto();
        consultaCuentasTronDto.IdPersona = idPlan;
        Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
        Call<TiposMembresiaResponseDto> call = apiervice.VerDetalleMembresiaPorId(consultaCuentasTronDto);
        call.enqueue(new Callback<TiposMembresiaResponseDto>() {
            @Override
            public void onResponse(Call<TiposMembresiaResponseDto> call, Response<TiposMembresiaResponseDto> response) {
                progressBar.setVisibility(View.GONE);
                tiposMembresiaResponseDto = response.body();
                Toast.makeText(ActivarPlan1.this, tiposMembresiaResponseDto.Descripcion, Toast.LENGTH_SHORT).show();
                ponerDatosEnLaVista(tiposMembresiaResponseDto);
            }

            @Override
            public void onFailure(Call<TiposMembresiaResponseDto> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void EnviarCodigoVerificacion(String numero, String mensaje) {
        progressBar.setVisibility(View.VISIBLE);
        enviarMensajeTexto = new EnviarMensajeTexto();
        enviarMensajeTexto.numero = numero;
        enviarMensajeTexto.mensaje = mensaje;

        Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_PERSONAS).create(Apiervice.class);
        Call<Boolean> call = apiervice.EnviarCodigoVerificacion(enviarMensajeTexto);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean respuesta = response.body();

                if (respuesta) {
                    binding.idBtnVerificarCodigo.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    private void GenerarCodigoVerificacion(CodigoGeneradoResponse codigoGeneradoResponse) {
        progressBar.setVisibility(View.VISIBLE);
        Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_PERSONAS).create(Apiervice.class);
        Call<CodigoGeneradoResponse> call = apiervice.GenerarCodigoVerificacion();
        call.enqueue(new Callback<CodigoGeneradoResponse>() {
            @Override
            public void onResponse(Call<CodigoGeneradoResponse> call, Response<CodigoGeneradoResponse> response) {
                codigoGeneradoResponse.codigo = response.body().codigo;

                Toast.makeText(ActivarPlan1.this, "Nulo", Toast.LENGTH_SHORT).show();
                Toast.makeText(ActivarPlan1.this, codigoGeneradoResponse.codigo, Toast.LENGTH_SHORT).show();
                //call.isExecuted();

            }

            @Override
            public void onFailure(Call<CodigoGeneradoResponse> call, Throwable t) {

            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void ponerDatosEnLaVista(TiposMembresiaResponseDto tiposMembresiaResponseDto) {
        binding.idTxtDescripcionPlan.setText(tiposMembresiaResponseDto.Descripcion);
        binding.idTxtRangoInicial.setText(tiposMembresiaResponseDto.RangoInicial.toString());
        binding.idTxtRangoFinal.setText(tiposMembresiaResponseDto.RangoFinal.toString());
        binding.idTxtRendimientoMensual.setText(tiposMembresiaResponseDto.Rendimiento.toString());
        binding.idTxtActivacionMensual.setText(tiposMembresiaResponseDto.Fee.toString());
    }
}
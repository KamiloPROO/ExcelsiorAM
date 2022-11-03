package ammovil.com.excelsior.ui.Wallet;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import static androidx.core.content.ContextCompat.getSystemService;

import static ammovil.com.excelsior.utils.Constantes.BASE_URL_PERSONAS;
import static ammovil.com.excelsior.utils.Constantes.NOMBRE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ammovil.com.excelsior.R;
import ammovil.com.excelsior.controller.adapter.AdapterInvestor;
import ammovil.com.excelsior.controller.adapter.CuentasTRonAdapter;
import ammovil.com.excelsior.data.request.ConsultaCuentasTronDto;
import ammovil.com.excelsior.data.request.ConsultaPersonaRequestDto;
import ammovil.com.excelsior.data.request.CrearCuentaTronRequest;
import ammovil.com.excelsior.data.response.CrearCuentaTronResponseDto;
import ammovil.com.excelsior.data.response.CuentaTronResponseDto;
import ammovil.com.excelsior.data.response.PersonaDto;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;
import ammovil.com.excelsior.databinding.FragmentWalletBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.ClipboardUtil;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WalletFragment extends Fragment {
    private FragmentWalletBinding binding;
    private CrearCuentaTronResponseDto cuentaTronResponseDto;
    private CrearCuentaTronRequest crearCuentaTronRequest;
    private ConsultaCuentasTronDto consultaCuentasTronDto;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private CuentasTRonAdapter cuentasTRonAdapter;
    private ConsultaPersonaRequestDto consultaPersonaRequestDto;
    private PersonaDto personaDto;
    private String propietario = " ";
    private Double idPersona = 0.0;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWalletBinding.inflate(inflater, container, false);
        progressBar = binding.idProgresVarCuentasTron;
        idPersona = Double.valueOf(Constantes.ID_PERSONA);

        listarCuentasTron(idPersona);

        binding.idBtnCrearCuentaTron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearCuentaTron();
            }
        });
        return binding.getRoot();

    }


    private void listarCuentasTron(Double idPersona) {
        try {
            consultaCuentasTronDto = new ConsultaCuentasTronDto();
            consultaCuentasTronDto.IdPersona = idPersona;
            Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
            Call<CuentaTronResponseDto> call = apiervice.ListarCuentasTron(consultaCuentasTronDto);
            call.enqueue(new Callback<CuentaTronResponseDto>() {
                @Override
                public void onResponse(Call<CuentaTronResponseDto> call, Response<CuentaTronResponseDto> response) {
                    final CuentaTronResponseDto listaCuentas = response.body();

                    progressBar.setVisibility(View.GONE);
                    if (response.body() != null) {
                        ponerDatosCuentaTron(listaCuentas);
                        binding.idBtnCrearCuentaTron.setVisibility(View.GONE);
                        binding.idTxtNombrePropietario.setText(NOMBRE);
                    }
                }

                @Override
                public void onFailure(Call<CuentaTronResponseDto> call, Throwable t) {
                    Log.e("Errrr", t.toString());
                    Toast.makeText(requireContext(), Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            //Toast.makeText(requireContext(), Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
        }
    }

    private void consultarPersonaPorId(Double idPersona) {
        personaDto = new PersonaDto();
        consultaPersonaRequestDto = new ConsultaPersonaRequestDto();
        consultaPersonaRequestDto.IdPersona = idPersona;
        Apiervice apiervice = RetrofitHelper.retrofilBuild(BASE_URL_PERSONAS).create(Apiervice.class);
        Call<PersonaDto> call = apiervice.BuscarPersonaPorIdPersona(consultaPersonaRequestDto);
        call.enqueue(new Callback<PersonaDto>() {
            @Override
            public void onResponse(Call<PersonaDto> call, Response<PersonaDto> response) {
                personaDto = response.body();
                binding.idTxtNombrePropietario.setText(personaDto.PRIMER_NOMBRE);
                Log.e("ddd", "sdf");
            }

            @Override
            public void onFailure(Call<PersonaDto> call, Throwable t) {

            }
        });
    }

    private void crearCuentaTron() {
        crearCuentaTronRequest = new CrearCuentaTronRequest();
        crearCuentaTronRequest.IdPersona = Double.valueOf(Constantes.ID_PERSONA);

        Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
        Call<CrearCuentaTronResponseDto> call = apiervice.CrearCuentaTron(crearCuentaTronRequest);
        call.enqueue(new Callback<CrearCuentaTronResponseDto>() {
            @Override
            public void onResponse(Call<CrearCuentaTronResponseDto> call, Response<CrearCuentaTronResponseDto> response) {
                binding.idBtnCrearCuentaTron.setVisibility(View.GONE);
                //listarCuentasTron();
            }

            @Override
            public void onFailure(Call<CrearCuentaTronResponseDto> call, Throwable t) {
                //binding.idBtnCrearCuentaTron.setVisibility(View.GONE);
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void ponerDatosCuentaTron(CuentaTronResponseDto cuentaTronResponseDto) {
        binding.idTxtReferencia.setText(cuentaTronResponseDto.Referencia);
        binding.idTxtSaldoUSDT.setText(cuentaTronResponseDto.SaldoUSDT.toString());
        binding.idTxtSaldoTRX.setText(cuentaTronResponseDto.SaldoTRX.toString());
        binding.txtFechaCreacion.setText(cuentaTronResponseDto.FechaCreacion);
        binding.txtFechaActualizacion.setText(cuentaTronResponseDto.FechaActulizacion);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    private CuentaTronResponseDto cuentaTronResponseDto;
    private CrearCuentaTronRequest crearCuentaTronRequest;
    private ConsultaCuentasTronDto consultaCuentasTronDto;
    private LinearLayout progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private CuentasTRonAdapter cuentasTRonAdapter;
    private ConsultaPersonaRequestDto consultaPersonaRequestDto;
    private PersonaDto personaDto;
    private String propietario = " ";
    private Double idPersona = 0.0;
    private CardView cardView;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentWalletBinding.inflate(inflater, container, false);
        progressBar = binding.idProgresVarCuentasTron;
        cardView = binding.itemCardCuenta;

        idPersona = Double.valueOf(recuperarSharedPreferences());
        try {
            listarCuentasTron(idPersona);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Errrr" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        binding.idBtnCrearCuentaTron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearCuentaTron();
            }
        });

        binding.btnCopiarDireccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.idTxtReferencia.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) requireContext().getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("DireccionWallet", text);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(requireContext(), "Dirección Copiada", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();

    }


    private void listarCuentasTron(Double idPersonaGet) {
        try {
            consultaCuentasTronDto = new ConsultaCuentasTronDto();
            consultaCuentasTronDto.IdPersona = idPersonaGet;

            Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
            Call<CuentaTronResponseDto> call = apiervice.ListarCuentasTron(consultaCuentasTronDto);
            call.enqueue(new Callback<CuentaTronResponseDto>() {
                @Override
                public void onResponse(Call<CuentaTronResponseDto> call, Response<CuentaTronResponseDto> response) {
                    cuentaTronResponseDto = response.body();
                    final CuentaTronResponseDto listaCuentas = response.body();
                    if (call.isExecuted()) {
                        progressBar.setVisibility(View.GONE);
                        //Toast.makeText(requireContext(), cuentaTronResponseDto.Referencia, Toast.LENGTH_SHORT).show();

                        if (response.body() != null && response.body().IdUsuario != null) {
                            ponerDatosCuentaTron(listaCuentas);
                            binding.idBtnCrearCuentaTron.setVisibility(View.GONE);
                            cardView.setVisibility(View.VISIBLE);

                            response.body().toString();
                            consultarPersonaPorId(idPersona);

                        } else {
                            if (response.body() != null) {
                                response.body().toString();
                                progressBar.setVisibility(View.GONE);
                                binding.idBtnCrearCuentaTron.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<CuentaTronResponseDto> call, Throwable t) {
                    Toast.makeText(requireContext(), Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            //Toast.makeText(requireContext(), Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
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
                if (call.isExecuted()) {
                    if (response.body() != null) {
                        binding.idTxtNombrePropietario.setText(personaDto.PRIMER_NOMBRE);
                        response.body().toString();
                    }
                }
            }

            @Override
            public void onFailure(Call<PersonaDto> call, Throwable t) {

            }
        });
    }

    private void crearCuentaTron() {
        progressBar.setVisibility(View.VISIBLE);
        crearCuentaTronRequest = new CrearCuentaTronRequest();
        crearCuentaTronRequest.IdPersona = Double.valueOf(Constantes.ID_PERSONA);

        Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
        Call<CrearCuentaTronResponseDto> call = apiervice.CrearCuentaTron(crearCuentaTronRequest);
        call.enqueue(new Callback<CrearCuentaTronResponseDto>() {
            @Override
            public void onResponse(Call<CrearCuentaTronResponseDto> call, Response<CrearCuentaTronResponseDto> response) {
                binding.idBtnCrearCuentaTron.setVisibility(View.GONE);
                if (response.body()!=null){
                    listarCuentasTron(idPersona);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<CrearCuentaTronResponseDto> call, Throwable t) {
                //binding.idBtnCrearCuentaTron.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void ponerDatosCuentaTron(CuentaTronResponseDto cuentaTronResponseDto) {
        try {
            binding.idTxtReferencia.setText(cuentaTronResponseDto.Referencia);
            long saldoTrxConvert = (long) (cuentaTronResponseDto.SaldoTRX / Math.pow(10, 6));
            long saldoUSDTConvert = (long) (cuentaTronResponseDto.SaldoUSDT / Math.pow(10, 6));
            binding.idTxtSaldoUSDT.setText(String.valueOf(saldoUSDTConvert));
            binding.idTxtSaldoTRX.setText(String.valueOf(saldoTrxConvert));

            binding.txtFechaCreacion.setText(cuentaTronResponseDto.FechaCreacion);
            binding.txtFechaActualizacion.setText(cuentaTronResponseDto.FechaActulizacion);
        } catch (Exception e) {
            //
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private String recuperarSharedPreferences() {
        SharedPreferences prefs = requireContext().getSharedPreferences("MY_PREFS_EXCELSIOR", MODE_PRIVATE);
        String id = prefs.getString("idPersona", "vacio");

        if (id == null || id.equals("vacio")) {
            Constantes.ID_PERSONA = "0.0";
        } else {
            Constantes.ID_PERSONA = id;
        }

        return id;
    }
}
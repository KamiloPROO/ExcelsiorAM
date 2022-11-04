package ammovil.com.excelsior.ui.solicitudesRetiro;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import ammovil.com.excelsior.data.request.InverionRequestDto;
import ammovil.com.excelsior.data.response.InverionBResponseDto;
import ammovil.com.excelsior.data.response.InversionResponseDto;
import ammovil.com.excelsior.databinding.FragmentSolicitudesRetiroBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitudesRetiroFragment extends Fragment {

    private FragmentSolicitudesRetiroBinding binding;
    private InverionRequestDto inverionRequestDto;
    private InverionBResponseDto inverionResponseDto;
    private Double idPersonaRecuperado;
    private Double monto;
    private LinearLayout progresBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSolicitudesRetiroBinding.inflate(inflater, container, false);
        idPersonaRecuperado = Double.valueOf(recuperarSharedPreferences());
        progresBar = binding.idProgresBarSolicitud;
        binding.idBtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.idTxtRequireMount.getText().toString().isEmpty()) {
                    monto = Double.valueOf(binding.idTxtRequireMount.getText().toString());
                    llamarRetrofit(monto);
                } else {
                    binding.idTxtRequireMount.setError(Constantes.ERROR_FORMULARIO_VACIO);
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void llamarRetrofit(Double monto) {
        inverionRequestDto = new InverionRequestDto();
        inverionRequestDto.IdPersona = idPersonaRecuperado;
        inverionRequestDto.Monto = monto;
        progresBar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
                    Call<InverionBResponseDto> call = apiervice.GuardarSolicitudPago(inverionRequestDto);
                    call.enqueue(new Callback<InverionBResponseDto>() {
                        @Override
                        public void onResponse(Call<InverionBResponseDto> call, Response<InverionBResponseDto> response) {
                            progresBar.setVisibility(View.GONE);
                            if (call.isExecuted() && response.body() != null) {
                                inverionResponseDto = response.body();
                                binding.idTxtRequireMount.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<InverionBResponseDto> call, Throwable t) {
                            progresBar.setVisibility(View.GONE);
                        }
                    });
                } catch (Exception e) {
                    //
                    progresBar.setVisibility(View.GONE);
                }
            }
        }).start();
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
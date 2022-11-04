package ammovil.com.excelsior.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import ammovil.com.excelsior.data.response.BalanceSaldosResponseDto;
import ammovil.com.excelsior.data.response.ReponseMothersDto;
import ammovil.com.excelsior.databinding.ActivityIndexMainAdminBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexMainAdminActivity extends AppCompatActivity {
    ActivityIndexMainAdminBinding binding;
    LinearLayout progresBar;
    private BalanceSaldosResponseDto balanceSaldosResponseDto;
    private ReponseMothersDto respoReponseMothersDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIndexMainAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progresBar = binding.idProgresSaldoRestante;
        ObtenerBalanceMadre();

        binding.idBtnTranferirAMadre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarBalanceAMadre();
            }
        });

    }

    private void ObtenerBalanceMadre() {
        try {
            balanceSaldosResponseDto = new BalanceSaldosResponseDto();
            progresBar.setVisibility(View.VISIBLE);
            Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
            Call<BalanceSaldosResponseDto> call = apiervice.ObtenerBalanceMadre();
            call.enqueue(new Callback<BalanceSaldosResponseDto>() {
                @Override
                public void onResponse(Call<BalanceSaldosResponseDto> call, Response<BalanceSaldosResponseDto> response) {
                    balanceSaldosResponseDto = response.body();
                    progresBar.setVisibility(View.GONE);
                    if (call.isExecuted()) {
                        if (response.body() != null) {
                            ponerDatosEnVista(balanceSaldosResponseDto);
                        }
                    }
                }

                @Override
                public void onFailure(Call<BalanceSaldosResponseDto> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            progresBar.setVisibility(View.GONE);
        }
    }

    private void EnviarBalanceAMadre() {
        try {
            respoReponseMothersDto = new ReponseMothersDto();
            progresBar.setVisibility(View.VISIBLE);
            Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
            Call<ReponseMothersDto> call = apiervice.EnviarBalanceAMadre();
            call.enqueue(new Callback<ReponseMothersDto>() {
                @Override
                public void onResponse(Call<ReponseMothersDto> call, Response<ReponseMothersDto> response) {
                    respoReponseMothersDto = response.body();
                    progresBar.setVisibility(View.GONE);
                    if (call.isExecuted()) {
                        response.body().toString();
                        if (!response.body().success) {
                            Toast.makeText(IndexMainAdminActivity.this, "No fue posile recuperar los saldos", Toast.LENGTH_SHORT).show();
                        } else {
                            ObtenerBalanceMadre();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ReponseMothersDto> call, Throwable t) {
                    progresBar.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            progresBar.setVisibility(View.GONE);

        }
    }

    private void ponerDatosEnVista(BalanceSaldosResponseDto balanceSaldosResponseDto) {
        try {
            binding.idTxtSladoUSDTWalletMadre.setText(balanceSaldosResponseDto.balanceusdt);
            binding.idTxtSladoTRXWalletMadre.setText(balanceSaldosResponseDto.balancetrx);
        } catch (Exception e) {
            //
        }
    }
}
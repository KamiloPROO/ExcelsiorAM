package ammovil.com.excelsior.ui.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ammovil.com.excelsior.R;
import ammovil.com.excelsior.controller.adapter.AdapterInvestor;
import ammovil.com.excelsior.controller.adapter.SolicitudesAdapter;
import ammovil.com.excelsior.data.response.InverionBResponseDto;
import ammovil.com.excelsior.databinding.ActivityIndexMainAdminBinding;
import ammovil.com.excelsior.databinding.FragmentSolicitudesAdminBinding;
import ammovil.com.excelsior.databinding.FragmentSolicitudesRetiroBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SolicitudesAdminFragment extends AppCompatActivity {
    private FragmentSolicitudesAdminBinding binding;
    private List<InverionBResponseDto> listaSolicituifdes;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private SolicitudesAdapter solicitudesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentSolicitudesAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        llamarRetrofit();

    }


    private void llamarRetrofit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
                    Call<List<InverionBResponseDto>> call = apiervice.ListarSolicitudes();
                    call.enqueue(new Callback<List<InverionBResponseDto>>() {
                        @Override
                        public void onResponse(Call<List<InverionBResponseDto>> call, Response<List<InverionBResponseDto>> response) {
                            if (response.body() != null && response.body().size() > 0) {
                                listaSolicituifdes = response.body();
                                recyclerView = findViewById(R.id.idRecyclerViewSolicitudedsAsdmin);
                                recyclerView.setHasFixedSize(true);
                                lManager = new LinearLayoutManager(SolicitudesAdminFragment.this);

                                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
                                recyclerView.setLayoutManager(lManager);
                                solicitudesAdapter = new SolicitudesAdapter(listaSolicituifdes, SolicitudesAdminFragment.this);

                                recyclerView.setAdapter(solicitudesAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<InverionBResponseDto>> call, Throwable t) {

                        }
                    });
                } catch (Exception e) {

                }
            }
        }).start();

    }
}
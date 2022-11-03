package ammovil.com.excelsior.ui.Investor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import ammovil.com.excelsior.ActivarPlan1;
import ammovil.com.excelsior.Model.ModelTeam;
import ammovil.com.excelsior.R;
import ammovil.com.excelsior.RecyclerViewAdaptador;
import ammovil.com.excelsior.controller.adapter.AdapterInvestor;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;
import ammovil.com.excelsior.databinding.FragmentInvestorBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestorFragment extends Fragment {

    private List<TiposMembresiaResponseDto> listDatos;
    private RecyclerViewAdaptador adaptadorDatos;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private AdapterInvestor adapterInvestor;
    private List<TiposMembresiaResponseDto> list;
    private FragmentInvestorBinding binding;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInvestorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressBar = binding.idProgresbarInvestor;

        llamarRetrofit(root);

        return root;

    }

    private void llamarRetrofit(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Apiervice service = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
        Call<List<TiposMembresiaResponseDto>> call = service.ListarTiposMembresia();

        call.enqueue(new Callback<List<TiposMembresiaResponseDto>>() {
            @Override
            public void onResponse(Call<List<TiposMembresiaResponseDto>> call, Response<List<TiposMembresiaResponseDto>> response) {
                final List<TiposMembresiaResponseDto> listaMembresias = response.body();
                //list = response.body();
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    Log.e("LISTAAAA", response.body().toString());
                } else {
                    Toast.makeText(requireContext(), "Esat vacio", Toast.LENGTH_SHORT).show();
                }


                recyclerView = view.findViewById(R.id.rcInvestor);
                recyclerView.setHasFixedSize(true);
                lManager = new LinearLayoutManager(requireContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setLayoutManager(lManager);
                adapterInvestor = new AdapterInvestor(listaMembresias, requireContext());

                recyclerView.setAdapter(adapterInvestor);
                adapterInvestor.setClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapterInvestor.setClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(requireContext(), ActivarPlan1.class);
                                i.putExtra("idPlan", listaMembresias.get(recyclerView.getChildAdapterPosition(v)).getId().toString());
                                startActivity(i);
                            }
                        });
                    }
                });

            }

            @Override
            public void onFailure(Call<List<TiposMembresiaResponseDto>> call, Throwable t) {
                Toast.makeText(requireContext(), Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<ModelTeam> obtenerIncestors() {

        List<ModelTeam> team = new ArrayList<>();

        team.add(new ModelTeam("Juan Camilo", "Activo", "Master Inventor", "3", "1000.00"));
        team.add(new ModelTeam("Juan Camilo", "Activo", "Master Inventor", "3", "1000.00"));
        team.add(new ModelTeam("Juan Camilo", "Activo", "Master Inventor", "3", "1000.00"));
        team.add(new ModelTeam("Juan Camilo", "Activo", "Master Inventor", "3", "1000.00"));


        return team;
    }

}
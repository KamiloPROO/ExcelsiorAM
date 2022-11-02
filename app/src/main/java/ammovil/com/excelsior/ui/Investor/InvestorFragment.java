package ammovil.com.excelsior.ui.Investor;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInvestorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //View view = inflater.inflate(R.layout.fragment_investor, container, false);
        //llamarRetrofit(root);
        Apiervice service = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
        Call<List<TiposMembresiaResponseDto>> call = service.ListarTiposMembresia();

        call.enqueue(new Callback<List<TiposMembresiaResponseDto>>() {
            @Override
            public void onResponse(Call<List<TiposMembresiaResponseDto>> call, Response<List<TiposMembresiaResponseDto>> response) {
                //final List<TiposMembresiaResponseDto> listaMembresias = response.body();
                list = response.body();

                //Log.e("LISTAAAAA", list.toString());
                try {
                    if (!list.isEmpty()) {

                        recyclerView = root.findViewById(R.id.rcInvestor);
                        recyclerView.setHasFixedSize(true);
                        lManager = new LinearLayoutManager(requireContext());

                        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
                        recyclerView.setLayoutManager(lManager);
                        adapterInvestor = new AdapterInvestor(list);

                        recyclerView.setAdapter(adapterInvestor);
                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(Call<List<TiposMembresiaResponseDto>> call, Throwable t) {
                Toast.makeText(requireContext(), Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
            }
        });
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llamarRetrofit(view);

    }
    /*  public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentInvestorBinding binding = FragmentInvestorBinding.inflate(inflater, container, false);
        // View view = inflater.inflate(R.layout.fragment_investor, container, false);

        llamarRetrofit(binding.getRoot());
        return binding.getRoot();
    }
*/


    private void llamarRetrofit(View view) {
        Apiervice service = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);
        Call<List<TiposMembresiaResponseDto>> call = service.ListarTiposMembresia();

        call.enqueue(new Callback<List<TiposMembresiaResponseDto>>() {
            @Override
            public void onResponse(Call<List<TiposMembresiaResponseDto>> call, Response<List<TiposMembresiaResponseDto>> response) {
                //final List<TiposMembresiaResponseDto> listaMembresias = response.body();
                list = response.body();

                //Log.e("LISTAAAAA", list.toString());
                try {
                    if (!list.isEmpty()) {

                        recyclerView = view.findViewById(R.id.rcInvestor);
                        recyclerView.setHasFixedSize(true);
                        lManager = new LinearLayoutManager(requireContext());

                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                        recyclerView.setLayoutManager(lManager);
                        adapterInvestor = new AdapterInvestor(list);

                        recyclerView.setAdapter(adapterInvestor);
                    }
                } catch (Exception e) {

                }


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
package ammovil.com.excelsior.ui.Dashboard;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import ammovil.com.excelsior.R;
import ammovil.com.excelsior.controller.adapter.AdapterInvestor;
import ammovil.com.excelsior.controller.adapter.InversionesAdapter;
import ammovil.com.excelsior.data.request.ConsultaCuentasTronDto;
import ammovil.com.excelsior.data.response.InversionResponseDto;
import ammovil.com.excelsior.data.response.MisInversionesResponseDto;
import ammovil.com.excelsior.databinding.FragmentDashboardBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboarFragment extends Fragment {

    private ConsultaCuentasTronDto consultaInversiones;
    private List<MisInversionesResponseDto> listaInversiones;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager lManager;
    private InversionesAdapter inversionesAdapter;


    private ProgressBar progressBar;
    int i = 0;
    private TextView mTextView1, mTextView2, progressText;
    private Double idPersona = 0.0;

    SliderView sliderView;
    int[] images = {
            R.drawable.imagen_carrusel_1,
            R.drawable.carrusel_img_2,
            R.drawable.carrusel_img_3,
            R.drawable.excelsior,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,


    };


    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel galleryViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        idPersona = Double.valueOf(recuperarSharedPreferences());

////////////////////////// ProgressBar //////////////////////////////////////////////////////
        progressBar = binding.progressBar;
        progressText = binding.progressText;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= 100) {
                    progressText.setText(i + "%");
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this, 200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        }, 200);

////////////////////////// Marquee //////////////////////////////////////////////////////

        mTextView2 = binding.marquee2;
        mTextView1 = binding.marquee;

        mTextView2.setSelected(true);
        mTextView1.setSelected(true);

////////////////////////// Slider //////////////////////////////////////////////////////

        sliderView = binding.WelcomeSlider;
        SliderAdapter sliderAdapter = new SliderAdapter(images);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        ListarMisInversiones(idPersona, root);
        return root;


    }

    private void ListarMisInversiones(Double idPersonGet, View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consultaInversiones = new ConsultaCuentasTronDto();
                    consultaInversiones.IdPersona = idPersonGet;

                    Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_EXCELSIOR).create(Apiervice.class);

                    Call<List<MisInversionesResponseDto>> call = apiervice.ListarMisInversiones(consultaInversiones);
                    call.enqueue(new Callback<List<MisInversionesResponseDto>>() {
                        @Override
                        public void onResponse(Call<List<MisInversionesResponseDto>> call, Response<List<MisInversionesResponseDto>> response) {
                            if (call.isExecuted()) {

                                if (response.body().size() > 0) {
                                    listaInversiones = response.body();
                                    Log.e("lisyaaa", "datos" + listaInversiones.get(0).IdPersona.toString());

                                    recyclerView = view.findViewById(R.id.idRecyclerViewMisInversiones);
                                    recyclerView.setHasFixedSize(true);

                                    lManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                                    //lManager = new LinearLayoutManager(requireContext());

                                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                                    recyclerView.setLayoutManager(lManager);
                                    inversionesAdapter = new InversionesAdapter(listaInversiones, requireContext());

                                    recyclerView.setAdapter(inversionesAdapter);

                                } else {
                                    //
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<MisInversionesResponseDto>> call, Throwable t) {

                        }
                    });


                } catch (Exception e) {

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
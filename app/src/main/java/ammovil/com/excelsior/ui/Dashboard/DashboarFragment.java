package ammovil.com.excelsior.ui.Dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import ammovil.com.excelsior.R;
import ammovil.com.excelsior.databinding.FragmentDashboardBinding;

public class DashboarFragment extends Fragment {


    private ProgressBar progressBar;
    int i = 0;
    private TextView mTextView1, mTextView2, progressText;

    SliderView sliderView;
    int[] images = {R.drawable.excelsior,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six};


    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel galleryViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

////////////////////////// ProgressBar //////////////////////////////////////////////////////
        progressBar = binding.progressBar;
        progressText = binding.progressText;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(i <= 100){
                    progressText.setText(i + "%");
                    progressBar.setProgress(i);
                    i++;
                    handler.postDelayed(this,200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        },200);

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

        final TextView textView = binding.textDashboard;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
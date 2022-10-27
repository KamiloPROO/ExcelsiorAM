package ammovil.com.excelsior.ui.Finances;

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

import ammovil.com.excelsior.databinding.FragmentFinancesBinding;

public class FinancesFragment extends Fragment {
    private ProgressBar progressBarFin;
    int i = 0;
    private TextView progressTextFin;

    private FragmentFinancesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FinancesViewModel homeViewModel =
                new ViewModelProvider(this).get(FinancesViewModel.class);

        binding = FragmentFinancesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ////////////////////////// ProgressBar //////////////////////////////////////////////////////
        progressBarFin = binding.progressBarFin;
        progressTextFin = binding.progressTextFin;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(i <= 100){
                    progressTextFin.setText(i + "%");
                    progressBarFin.setProgress(i);
                    i++;
                    handler.postDelayed(this,200);
                } else {
                    handler.removeCallbacks(this);
                }
            }
        },200);


        final TextView textView = binding.textFinances;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
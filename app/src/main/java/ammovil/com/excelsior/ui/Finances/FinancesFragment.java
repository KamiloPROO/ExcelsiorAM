package ammovil.com.excelsior.ui.Finances;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ammovil.com.excelsior.databinding.FragmentFinancesBinding;

public class FinancesFragment extends Fragment {

    private FragmentFinancesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FinancesViewModel homeViewModel =
                new ViewModelProvider(this).get(FinancesViewModel.class);

        binding = FragmentFinancesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
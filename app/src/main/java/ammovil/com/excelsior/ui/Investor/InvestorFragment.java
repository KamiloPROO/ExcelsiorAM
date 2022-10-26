package ammovil.com.excelsior.ui.Investor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ammovil.com.excelsior.databinding.FragmentInvestorBinding;

public class InvestorFragment extends Fragment {

    private FragmentInvestorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InvestorViewModel homeViewModel =
                new ViewModelProvider(this).get(InvestorViewModel.class);

        binding = FragmentInvestorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textInvestor;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
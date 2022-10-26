package ammovil.com.excelsior.ui.Education;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ammovil.com.excelsior.databinding.FragmentEducationBinding;

public class EducationFragment extends Fragment {

    private FragmentEducationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        EducationViewModel galleryViewModel =
                new ViewModelProvider(this).get(EducationViewModel.class);

        binding = FragmentEducationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textEducation;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
package ammovil.com.excelsior.ui.MyTeam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ammovil.com.excelsior.databinding.FragmentMyteamBinding;

public class MyTeamFragment extends Fragment {

    private FragmentMyteamBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyTeamViewModel slideshowViewModel =
                new ViewModelProvider(this).get(MyTeamViewModel.class);

        binding = FragmentMyteamBinding .inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyteam;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
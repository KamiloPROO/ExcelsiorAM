package ammovil.com.excelsior.ui.Investor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ammovil.com.excelsior.R;
import ammovil.com.excelsior.databinding.FragmentInvestorBinding;

public class InvestorFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_investor, container, false);

        return view;
    }

}
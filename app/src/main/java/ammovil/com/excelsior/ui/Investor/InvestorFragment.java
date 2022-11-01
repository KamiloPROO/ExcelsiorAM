package ammovil.com.excelsior.ui.Investor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ammovil.com.excelsior.ActivarPlan1;
import ammovil.com.excelsior.ActivarPlan2;
import ammovil.com.excelsior.ActivarPlan3;
import ammovil.com.excelsior.ActivarPlan4;
import ammovil.com.excelsior.ActivarPlan5;
import ammovil.com.excelsior.ActivarPlan6;
import ammovil.com.excelsior.PopPapTeam;
import ammovil.com.excelsior.R;
import ammovil.com.excelsior.databinding.FragmentInvestorBinding;

public class InvestorFragment extends Fragment {

    Button plan1, plan2 , plan3, plan4 , plan5 ,plan6 ;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_investor, container, false);



        plan1 = view.findViewById(R.id.btnActPlan1);

        plan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivarPlan1.class);
                startActivity(intent);

            }
        });



        plan2 = view.findViewById(R.id.btnActPlan2);

        plan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivarPlan2.class);
                startActivity(intent);

            }
        });

        plan3 = view.findViewById(R.id.btnActPlan3);

        plan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivarPlan3.class);
                startActivity(intent);

            }
        });

        plan4 = view.findViewById(R.id.btnActPlan4);

        plan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivarPlan4.class);
                startActivity(intent);

            }
        });

        plan5 = view.findViewById(R.id.btnActPlan5);

        plan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivarPlan5.class);
                startActivity(intent);

            }
        });

        plan6 = view.findViewById(R.id.btnActPlan6);

        plan6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ActivarPlan6.class);
                startActivity(intent);

            }
        });



        return view;
    }

}
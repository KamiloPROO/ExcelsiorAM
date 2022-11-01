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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ammovil.com.excelsior.ActivarPlan1;
import ammovil.com.excelsior.ActivarPlan2;
import ammovil.com.excelsior.ActivarPlan3;
import ammovil.com.excelsior.ActivarPlan4;
import ammovil.com.excelsior.ActivarPlan5;
import ammovil.com.excelsior.ActivarPlan6;
import ammovil.com.excelsior.Model.ModelTeam;
import ammovil.com.excelsior.PopPapTeam;
import ammovil.com.excelsior.R;
import ammovil.com.excelsior.RecyclerViewAdaptador;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;
import ammovil.com.excelsior.databinding.FragmentInvestorBinding;

public class InvestorFragment extends Fragment {

    List<TiposMembresiaResponseDto> listDatos;
    RecyclerViewAdaptador adaptadorDatos;
    RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_investor, container, false);

        recyclerView = view.findViewById(R.id.rcInvestor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),1));
        listDatos = new ArrayList<TiposMembresiaResponseDto>();

        adaptadorDatos = new RecyclerViewAdaptador(obtenerIncestors());
        recyclerView.setAdapter(adaptadorDatos);




        return view;
    }

    public List<ModelTeam> obtenerIncestors() {

        List<ModelTeam> team = new ArrayList<>();

        team.add(new ModelTeam("Juan Camilo","Activo","Master Inventor","3","1000.00"));
        team.add(new ModelTeam("Juan Camilo","Activo","Master Inventor","3","1000.00"));
        team.add(new ModelTeam("Juan Camilo","Activo","Master Inventor","3","1000.00"));
        team.add(new ModelTeam("Juan Camilo","Activo","Master Inventor","3","1000.00"));


        return team;
    }

}
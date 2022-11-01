package ammovil.com.excelsior.ui.MyTeam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ammovil.com.excelsior.Model.ModelTeam;
import ammovil.com.excelsior.PopPapTeam;
import ammovil.com.excelsior.R;
import ammovil.com.excelsior.RecyclerViewAdaptador;
import ammovil.com.excelsior.databinding.FragmentMyteamBinding;

public class MyTeamFragment extends Fragment {

    List<ModelTeam> listDatos;
    RecyclerViewAdaptador adaptadorDatos;
    RecyclerView recyclerView;

    ImageView add;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_myteam, container, false);

        recyclerView = view.findViewById(R.id.rcItem);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),1));
        listDatos = new ArrayList<ModelTeam>();

        adaptadorDatos = new RecyclerViewAdaptador(obtenerTeam());
        recyclerView.setAdapter(adaptadorDatos);



        add = view.findViewById(R.id.imgTeamAdd);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),PopPapTeam.class);
                startActivity(intent);

            }
        });

        return view;
    }

    public List<ModelTeam> obtenerTeam() {

        List<ModelTeam> team = new ArrayList<>();

        team.add(new ModelTeam("Juan Camilo","Activo","Master Inventor","3","1000.00"));
        team.add(new ModelTeam("Juan Camilo","Activo","Master Inventor","3","1000.00"));
        team.add(new ModelTeam("Juan Camilo","Activo","Master Inventor","3","1000.00"));
        team.add(new ModelTeam("Juan Camilo","Activo","Master Inventor","3","1000.00"));


        return team;
    }



}
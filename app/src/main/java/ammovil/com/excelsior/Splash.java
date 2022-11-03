package ammovil.com.excelsior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ammovil.com.excelsior.utils.Constantes;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String idPersana;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        idPersana = recuperarSharedPreferences();
        recuperarSharedPreferences2();
        TimerTask splash = new TimerTask() {
            @Override
            public void run() {
                if (!idPersana.equals("vacio")) {
                    Intent intent = new Intent(Splash.this, MainMenuActivity.class);
                    intent.putExtra("Primera", "No");

                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(splash, 4000);

    }

    private String recuperarSharedPreferences() {
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_EXCELSIOR", MODE_PRIVATE);
        SharedPreferences prefs2 = getSharedPreferences("MY_PREFS_EXCELSIOR2", MODE_PRIVATE);
        String id = prefs.getString("idPersona", "vacio");
        String nombre = prefs2.getString("Nombre", "vacio");

        if (id == null || id.equals("vacio")) {
            Constantes.ID_PERSONA = "0.0";
        } else {
            Constantes.NOMBRE = nombre;
            Constantes.ID_PERSONA = id;
        }

        return id;
    }

    private String recuperarSharedPreferences2() {
        SharedPreferences prefs2 = getSharedPreferences("MY_PREFS_EXCELSIOR2", MODE_PRIVATE);
        String nombre = prefs2.getString("Nombre", "vacio");

        if (nombre == null || nombre.equals("vacio")) {
            Constantes.NOMBRE = "vacio";
        } else {
            Constantes.NOMBRE = nombre;
        }

        return nombre;
    }
}
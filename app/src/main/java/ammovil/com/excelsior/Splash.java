package ammovil.com.excelsior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        TimerTask splash = new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(Splash.this, Login.class);
                startActivity(intent);
                finish();

            }
        };

        Timer timer = new Timer();
        timer.schedule( splash,3000);

    }
}
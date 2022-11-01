package ammovil.com.excelsior;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Codigo_Veri extends AppCompatActivity {

    Button verificar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_veri);

        verificar = findViewById(R.id.btnVeri);

        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Codigo_Veri.this,MainMenuActivity.class);
                startActivity(intent);

            }
        });

    }
}
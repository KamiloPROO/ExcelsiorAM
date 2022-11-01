package ammovil.com.excelsior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    TextView sinup , forgot;
    Button login;
    ImageView forgopass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sinup = findViewById(R.id.txtNewC);
        forgot = findViewById(R.id.txtRC);
        login = findViewById(R.id.btnLogin);
        forgopass = findViewById(R.id.logoRC);

        sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,Singup.class);
                startActivity(intent);

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,Password_recovery.class);
                startActivity(intent);

            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this,Mensaje_Veri.class);
                startActivity(intent);

            }
        });

        forgopass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this,Password_recovery.class);
                startActivity(intent);

            }
        });

    }
}
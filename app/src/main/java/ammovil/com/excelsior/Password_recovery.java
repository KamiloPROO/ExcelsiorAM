package ammovil.com.excelsior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Password_recovery extends AppCompatActivity {

    TextView sinup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        sinup = findViewById(R.id.txtNewC);

        sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Password_recovery.this,Singup.class);
                startActivity(intent);

            }
    });
    }
}
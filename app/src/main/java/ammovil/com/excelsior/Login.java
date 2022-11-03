package ammovil.com.excelsior;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ammovil.com.excelsior.data.request.IniciaSesionRequestDto;
import ammovil.com.excelsior.data.response.PersonaResponseDto;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private TextView sinup, forgot;
    private Button btnLogin;
    private ImageView forgopass;
    private EditText txtUsuario, txtContasenia;

    private IniciaSesionRequestDto iniciaSesionRequestDto;
    private PersonaResponseDto personaResponseDto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        referenciaCamposFormulario();

        sinup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Singup.class);
                startActivity(intent);

            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Password_recovery.class);
                startActivity(intent);

            }

        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarCampoFormulario()) {
                    llamarRetrofit(txtUsuario.getText().toString(), txtContasenia.getText().toString());
                }
            }
        });

        forgopass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Login.this, Password_recovery.class);
                startActivity(intent);

            }
        });
    }

    /**
     * Hacemos el llamado al api servide de iniciar sesión
     */
    private void llamarRetrofit(String login, String password) {
        try {
            iniciaSesionRequestDto = new IniciaSesionRequestDto();
            iniciaSesionRequestDto.IdProyecto = Constantes.ID_PROYECTO;
            iniciaSesionRequestDto.IdPersona = 0.0;
            iniciaSesionRequestDto.Login = login;
            iniciaSesionRequestDto.Password = password;

            Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_PERSONAS).create(Apiervice.class);

            Call<PersonaResponseDto> call = apiervice.IniciarSesion(iniciaSesionRequestDto);
            call.enqueue(new Callback<PersonaResponseDto>() {
                @Override
                public void onResponse(Call<PersonaResponseDto> call, Response<PersonaResponseDto> response) {
                    personaResponseDto = response.body();
                    Log.e("RESPONSELOGIN", personaResponseDto.CodigoRespuesta + " Mensaje " +
                            personaResponseDto.MensajeRespuesta);

                    if (personaResponseDto != null) {
                        if (personaResponseDto.CodigoRespuesta.equals(Integer.toString(Constantes.CODIGO_EXITOSO))) {
                            Toast.makeText(Login.this, personaResponseDto.MensajeRespuesta, Toast.LENGTH_SHORT).show();

                            guardarSharedPreferences(personaResponseDto.Id.toString());
                            guardarSharedPreferences2(personaResponseDto.PrimerNombre);

                            Intent i = new Intent(Login.this, MainMenuActivity.class);
                            i.putExtra("Primera", "Si");
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(Login.this, personaResponseDto.MensajeRespuesta, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "Datos vacios", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PersonaResponseDto> call, Throwable t) {
                    Toast.makeText(Login.this, Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(Login.this, Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Referencia datos formulario
     */
    private void referenciaCamposFormulario() {
        try {
            txtUsuario = findViewById(R.id.edUser);
            txtContasenia = findViewById(R.id.edPasw);
            sinup = findViewById(R.id.txtNewC);
            forgot = findViewById(R.id.txtRC);
            forgopass = findViewById(R.id.logoRC);

            btnLogin = findViewById(R.id.btnLogin);
        } catch (Exception e) {
            Toast.makeText(this, Constantes.ERROR_REFERENCIA_FORMULARIO, Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Referencia datos formulario
     */
    private boolean validarCampoFormulario() {
        Boolean esValido = true;
        try {
            if (txtUsuario.getText().toString().isEmpty()) {
                txtUsuario.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtUsuario.setError(null);
                esValido = true;
            }
            if (txtContasenia.getText().toString().isEmpty()) {
                txtContasenia.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtContasenia.setError(null);
                esValido = true;
            }

        } catch (Exception e) {
            Toast.makeText(this, Constantes.ERROR_VALIDANDO_FORMULARIO, Toast.LENGTH_SHORT).show();
        }
        return esValido;
    }

    private void guardarSharedPreferences(String id){
        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_EXCELSIOR", MODE_PRIVATE).edit();
        editor.putString("idPersona", id);
        editor.apply();
    }

    private void guardarSharedPreferences2(String nombre){
        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_EXCELSIOR2", MODE_PRIVATE).edit();
        editor.putString("Nombre", nombre);
        editor.apply();
    }
}
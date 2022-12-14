package ammovil.com.excelsior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ammovil.com.excelsior.data.PersonaModelDto;
import ammovil.com.excelsior.data.request.ResponseDto;
import ammovil.com.excelsior.databinding.ActivityMainBinding;
import ammovil.com.excelsior.databinding.ActivitySingupBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Singup extends AppCompatActivity {
    ActivitySingupBinding binding;
    TextView login;
    EditText txtNombre, txtTelefono, txtCorreo, idTxtNumeroIdentifiacion;
    Button btnRegistrame;

    PersonaModelDto personaModelDto;
    ResponseDto responseDto;
    private LinearLayout progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySingupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        referenciaDatosFormulario();

        progressBar = binding.idProgresBarRegistro;
        login = findViewById(R.id.txtLoginC);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Singup.this, Login.class);
                startActivity(intent);

            }
        });

        btnRegistrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCamposFormulario()) {
                    llamarRetrofit();
                }
            }

        });
    }


    private void referenciaDatosFormulario() {
        txtNombre = findViewById(R.id.idTxtNombre);
        txtTelefono = findViewById(R.id.idTxtTelefono);
        txtCorreo = findViewById(R.id.idTxtCorreo);
        idTxtNumeroIdentifiacion = findViewById(R.id.idTxtNumeroIdentifiacion);
        btnRegistrame = findViewById(R.id.idBtnRegistarme);
    }

    /**
     * Llamado consumo de api service
     */
    private void llamarRetrofit() {
        personaModelDto = new PersonaModelDto();
        progressBar.setVisibility(View.VISIBLE);
        personaModelDto.PrimerNombre = txtNombre.getText().toString();
        personaModelDto.Telefono = txtTelefono.getText().toString();
        personaModelDto.Correo = txtCorreo.getText().toString();
        personaModelDto.Documento = idTxtNumeroIdentifiacion.getText().toString();
        personaModelDto.IdProyecto = Constantes.ID_PROYECTO;
        personaModelDto.IdRol = Constantes.ID_ROL_PERSONA_NATURAL;
        Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_PERSONAS).create(Apiervice.class);
        Call<ResponseDto> call = apiervice.GuardarNuevaPersona(personaModelDto);
        call.enqueue(new Callback<ResponseDto>() {
            @Override
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                responseDto = response.body();
                progressBar.setVisibility(View.GONE);
                if (response.body() != null) {
                    //Log.e("RESPONSER ", responseDto.Codigo + " mensaje " + responseDto.Mensaje);
                    if (responseDto.Codigo == Constantes.CODIGO_EXITOSO) {
                        IrVerificacionCodigo(String.valueOf(responseDto.IdPersona), personaModelDto.Telefono);
                        progressBar.setVisibility(View.GONE);
                    } else {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDto> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Validaciones de fomulario
     */
    private boolean validarCamposFormulario() {
        Boolean esValido = true;
        try {
            if (txtNombre.getText().toString().isEmpty()) {
                txtNombre.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtNombre.setError(null);
                esValido = true;
            }

            if (txtTelefono.getText().toString().isEmpty()) {
                txtTelefono.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtTelefono.setError(null);
                esValido = true;
            }
            if (txtCorreo.getText().toString().isEmpty()) {
                txtCorreo.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtCorreo.setError(null);
                esValido = true;
            }
            if (idTxtNumeroIdentifiacion.getText().toString().isEmpty()) {
                idTxtNumeroIdentifiacion.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                idTxtNumeroIdentifiacion.setError(null);
                esValido = true;
            }
        } catch (Exception e) {
            Toast.makeText(this, Constantes.ERROR_VALIDANDO_FORMULARIO, Toast.LENGTH_SHORT).show();
        }
        return esValido;
    }

    private void IrVerificacionCodigo(String idPersona, String login) {
        Intent i = new Intent(Singup.this, Codigo_Veri.class);
        i.putExtra("IdPersonaVerificar", idPersona);
        i.putExtra("Login", login);
        startActivity(i);
    }
}
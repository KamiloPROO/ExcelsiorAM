package ammovil.com.excelsior;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import ammovil.com.excelsior.data.PersonaModelDto;
import ammovil.com.excelsior.data.request.ConsultaUsuarioRequestDto;
import ammovil.com.excelsior.data.request.ResponseDto;
import ammovil.com.excelsior.databinding.ActivityCodigoVeriBinding;
import ammovil.com.excelsior.network.RetrofitHelper;
import ammovil.com.excelsior.network.services.Apiervice;
import ammovil.com.excelsior.utils.Constantes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Codigo_Veri extends AppCompatActivity {
    ActivityCodigoVeriBinding binding;
    private EditText txtPrimerNumero, txtSegundoNumero, txtTercerNumero, txtCuartoNumero;
    private Button idBtnVerificar;
    private ConsultaUsuarioRequestDto consultaUsuarioRequestDto;
    private String idPersonaRecuperada = "0.0";
    private String login = "";
    private String codigo = "";
    private String uno = "";
    private String dos = "";
    private String tres = "";
    private String cuatro = "";
    private LinearLayout progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCodigoVeriBinding.inflate(getLayoutInflater());
        progressBar = binding.idProgresBarVerificarCodigo;
        setContentView(binding.getRoot());
        referenciaCamposFomulario();
        uno = binding.idUno.getText().toString();
        dos = binding.editText.getText().toString();
        tres = binding.editText2.getText().toString();
        cuatro = binding.editText3.getText().toString();


        idPersonaRecuperada = getIntent().getExtras().getString("IdPersonaVerificar");
        login = getIntent().getExtras().getString("Login");
        idBtnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCamposFormulario()) {
                    codigo = txtPrimerNumero.getText().toString()
                            + txtSegundoNumero.getText().toString()
                            + txtTercerNumero.getText().toString()
                            + txtCuartoNumero.getText().toString();
                    llamarRetrofit(Double.valueOf(idPersonaRecuperada), codigo, login);
                }
            }
        });
    }


    /**
     * Recuperamos el IdPerosna que nos llega del registro
     */
    private Double recuperarExtrasIdPersona() {
        Double idpersona = 0.0;
        try {
            idpersona = Double.valueOf(getIntent().getExtras().getInt("IdPersonaVerificar", 0));
        } catch (Exception e) {
            //
        }
        return idpersona;
    }

    /**
     * Recuperamos el CodigoEscrito por el usuario
     */
    private String recuperarCodigoEscritoFormulario() {
        String codigo = "";
        try {

        } catch (Exception e) {

        }
        return codigo;
    }

    /**
     * Llamado consumo de api service
     */
    private void llamarRetrofit(Double idPersona, String codigoVerifacion, String login) {

        consultaUsuarioRequestDto = new ConsultaUsuarioRequestDto();
        consultaUsuarioRequestDto.IdProyecto = Constantes.ID_PROYECTO;
        consultaUsuarioRequestDto.IdPersona = idPersona;
        consultaUsuarioRequestDto.Login = login;
        consultaUsuarioRequestDto.CodigoVerificacion = codigoVerifacion;

        progressBar.setVisibility(View.VISIBLE);
        Apiervice apiervice = RetrofitHelper.retrofilBuild(Constantes.BASE_URL_PERSONAS).create(Apiervice.class);
        Call<Boolean> call = apiervice.VerificarCodigo(consultaUsuarioRequestDto);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean responseInter = response.body();
                if (response.body()!=null){
                    progressBar.setVisibility(View.GONE);
                    if (responseInter) {
                        Toast.makeText(Codigo_Veri.this, "Por favor evisa tu correo para conocer tus credenciales de acceso", Toast.LENGTH_SHORT).show();
                        irHomeActivity();
                        progressBar.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(Codigo_Veri.this, "C??digo de verficaci??n no valido", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(Codigo_Veri.this, Constantes.ERROR_RETROFIT, Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Validamos campos formulario
     */
    private boolean validarCamposFormulario() {
        Boolean esValido = true;
        try {
            if (txtPrimerNumero.getText().toString().isEmpty()) {
                txtPrimerNumero.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtPrimerNumero.setError(null);
                esValido = true;
            }

            if (txtSegundoNumero.getText().toString().isEmpty()) {
                txtSegundoNumero.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtSegundoNumero.setError(null);
                esValido = true;
            }
            if (txtTercerNumero.getText().toString().isEmpty()) {
                txtTercerNumero.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtTercerNumero.setError(null);
                esValido = true;
            }
            if (txtCuartoNumero.getText().toString().isEmpty()) {
                txtCuartoNumero.setError(Constantes.ERROR_FORMULARIO_VACIO);
                esValido = false;
            } else {
                txtCuartoNumero.setError(null);
                esValido = true;
            }
        } catch (Exception e) {
            Toast.makeText(this, Constantes.ERROR_VALIDANDO_FORMULARIO, Toast.LENGTH_SHORT).show();
        }
        return esValido;
    }

    /**
     * Referencia Campos Fomulario
     */
    private void referenciaCamposFomulario() {
        try {
            txtPrimerNumero = findViewById(R.id.idUno);
            txtSegundoNumero = findViewById(R.id.editText);
            txtTercerNumero = findViewById(R.id.editText2);
            txtCuartoNumero = findViewById(R.id.editText3);
            idBtnVerificar = findViewById(R.id.idBtnVerificar);

        } catch (Exception e) {
            Toast.makeText(this, Constantes.ERROR_REFERENCIA_FORMULARIO, Toast.LENGTH_SHORT).show();
        }
    }

    private void irHomeActivity() {
        try {
            Intent i = new Intent(Codigo_Veri.this, Login.class);
            startActivity(i);
            finish();
        } catch (Exception e) {
            //
        }
    }
}
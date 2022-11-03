package ammovil.com.excelsior.network.services;

import java.util.List;

import ammovil.com.excelsior.data.PersonaModelDto;
import ammovil.com.excelsior.data.request.ConsultaCuentasTronDto;
import ammovil.com.excelsior.data.request.ConsultaPersonaRequestDto;
import ammovil.com.excelsior.data.request.ConsultaUsuarioRequestDto;
import ammovil.com.excelsior.data.request.CrearCuentaTronRequest;
import ammovil.com.excelsior.data.request.EnviarMensajeTexto;
import ammovil.com.excelsior.data.request.IniciaSesionRequestDto;
import ammovil.com.excelsior.data.request.InversionRequestDto;
import ammovil.com.excelsior.data.request.ResponseDto;
import ammovil.com.excelsior.data.response.CodigoGeneradoResponse;
import ammovil.com.excelsior.data.response.CrearCuentaTronResponseDto;
import ammovil.com.excelsior.data.response.CuentaTronResponseDto;
import ammovil.com.excelsior.data.response.InversionResponseDto;
import ammovil.com.excelsior.data.response.PersonaDto;
import ammovil.com.excelsior.data.response.PersonaResponseDto;
import ammovil.com.excelsior.data.response.TiposMembresiaResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Apiervice {
    @POST("/api/persona/CrearPersona")
    Call<ResponseDto> GuardarNuevaPersona(@Body PersonaModelDto personaRequest);

    @POST("/api/persona/VerificarCodigo")
    Call<Boolean> VerificarCodigo(@Body ConsultaUsuarioRequestDto consultaUsuarioRequestDto);

    @POST("/api/persona/IniciarSesion")
    Call<PersonaResponseDto> IniciarSesion(@Body IniciaSesionRequestDto iniciaSesionRequestDto);

    @POST("/api/CuentaTron/CrearCuentaTron")
    Call<CrearCuentaTronResponseDto> CrearCuentaTron(@Body CrearCuentaTronRequest crearCuentaTronRequest);

    @POST("/api/CuentaTron/ListarCuentasTron")
    Call<CuentaTronResponseDto> ListarCuentasTron(@Body ConsultaCuentasTronDto consultaCuentasTronDto);

    @POST("/api/TiposMembresia/VerDetalleMembresiaPorId")
    Call<TiposMembresiaResponseDto> VerDetalleMembresiaPorId(@Body ConsultaCuentasTronDto consultaCuentasTronDto);

    @POST("/api/TiposMembresia/GuardarInversion")
    Call<InversionResponseDto> GuardarInversion(@Body InversionRequestDto inversionRequestDto);

    @POST("/api/Persona/EnviarCodigoVerificacion")
    Call<Boolean> EnviarCodigoVerificacion(@Body EnviarMensajeTexto enviarMensajeTexto);

    @POST("/api/Persona/BuscarPersonaPorIdPersona")
    Call<PersonaDto> BuscarPersonaPorIdPersona(@Body ConsultaPersonaRequestDto consultaPersonaRequestDto);

    @GET("/api/TiposMembresia/ListarTiposMembresia")
    Call<List<TiposMembresiaResponseDto>> ListarTiposMembresia();

    @GET("/api/Persona/GenerarCodigoVerificacion")
    Call<CodigoGeneradoResponse> GenerarCodigoVerificacion();

}

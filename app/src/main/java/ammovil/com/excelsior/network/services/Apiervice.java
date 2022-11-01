package ammovil.com.excelsior.network.services;

import java.util.List;

import ammovil.com.excelsior.data.PersonaModelDto;
import ammovil.com.excelsior.data.request.ConsultaUsuarioRequestDto;
import ammovil.com.excelsior.data.request.IniciaSesionRequestDto;
import ammovil.com.excelsior.data.request.ResponseDto;
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

    @GET("/api/TiposMembresia/ListarTiposMembresia")
    Call<List<TiposMembresiaResponseDto>> ListarTiposMembresia();

}

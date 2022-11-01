package ammovil.com.excelsior.network.services;

import ammovil.com.excelsior.data.PersonaModelDto;
import ammovil.com.excelsior.data.request.ConsultaUsuarioRequestDto;
import ammovil.com.excelsior.data.request.ResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Apiervice {
    @POST("/api/persona/CrearPersona")
    Call<ResponseDto> GuardarNuevaPersona(@Body PersonaModelDto personaRequest);


    @POST("/api/persona/VerificarCodigo")
    Call<Boolean> VerificarCodigo(@Body ConsultaUsuarioRequestDto consultaUsuarioRequestDto);

}
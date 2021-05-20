
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;

class CodigoAPI {

    // Usando como ejemplo el sistema de SARES  ==> Este del mismo Passport: 001301
    public static  final String CODIGOSISTEMA = "001356";
    public static  final String IDROL = "212";
    public static  final String CLAVEACCESO = "";

    public static final String LOGINDATA = "8599C880D2A56628B6AB8B1670FC088006B703F9BB4346E0C6A1D48F2F78BD3B";
    public static final String OBTENERUSUARIO = "F96D6A72075AB245401E74344FA9936DE9B7FBDA1E3FA03E260371581B7FDDCF";
    public static final String OBTENERROLESUSUARIO = "D61DA415E7C931CEEB0498C811E5D70E581B0048ADFC2748B6BD63CFE65BCAFC";
    public static final String OBTENERTIPOSDOCUMENTO = "55DD21E93D545B1DCD907CDAC2F0BE0BD7ABD30305D33CF6ABC4A8625668F241";
    public static final String OBTENERTIPOSSEDE = "5986B3693AB11BBDB51450A5677C17848CAE29B9AAD5F0CCE1E173E0BCCE9BBF";
    public static final String OBTENERSEDES = "C5279DCF1631297322D77CC23E3A6CC1B7968713027EE1237918AA04D12B684E";
    public static final String OBTENERROLES = "D610177D8A95DE42FC68A7348458E71A819DCFD8F0FCAAECB198A008CC80A31D";
    public static final String NUEVOUSUARIO = "51DC5CD71C6A5EA093149201DA56096517906986CA05151D394DDCA6EDDF84BD";
    public static final String MODIFICARUSUARIO = "41DB95B103638BCC780B0C101D555DA581D0353509D3961C9FA30DE478373C9E";
    public static final String NUEVOROLUSUARIO = "C41622268587CAA1F40EED64FF4EB59F9B52E2E27AA8478C8DCF2E4CEF23F321";
    public static final String MODIFICAROLUSUARIO = "3B7FEE1745578A964588A4A1CEFC92C63F2C8199F5A0A3F7A0102DADB488E619";

}

public class APISeguridad {

    private String token;
    private Response response;

    @Before
    public void setup() {}


    @Test
    public void apiSeguridad() throws JsonProcessingException {

        response = given()
                .contentType(ContentType.JSON)
                .when()
                .post("https://test-passportv4-apiseguridad.minedu.gob.pe:9013/api/Seguridad/control/Boot");

        ResponseBoot resp = response.then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract()
                .body()
                .as(ResponseBoot.class);

        //System.out.println("Secure: " + resp.isIsSecure());
        this.token = resp.getToken();

        //System.out.println("token: " + this.token);

        JSONObject loginServicio = new JSONObject();
        loginServicio.put("CODIGO_SISTEMA",CodigoAPI.CODIGOSISTEMA);
        loginServicio.put("CLAVE_ACCESO",CodigoAPI.CLAVEACCESO);

        given()
                .contentType(ContentType.JSON)
                .when()
                .post("https://test-passportv4-apiseguridad.minedu.gob.pe:9013/api/Authentication/LoginServicioData");



    }



    @Test
    public void token() {

        Response response = given().
                auth().
                preemptive().
                basic("jb50@jb50.com", "12345").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                get("http://todo.ly/api/authentication/token.json");

        response.then().
                log().all().
                statusCode(200);

        this.token = response.then().extract().path("TokenString");
        System.out.println("INFO >  Token: "+this.token);

    }

    @After
    public void after() {}

}

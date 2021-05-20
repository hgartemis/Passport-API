import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import org.json.JSONObject;

import javax.mail.FetchProfile;
import java.io.IOException;

public class ResponseBoot{
	private boolean isSecure;
	private String token;

	public ResponseBoot(String valor) throws IOException {

		//System.out.println("INFO => " + jsonOb);
		JSONObject jsonOb = new JSONObject(valor);
		this.isSecure = jsonOb.getBoolean("IsSecure");
		this.token = jsonOb.optString("Token");

	}

	public ResponseBoot(boolean isSecure, String token) {
		this.isSecure = isSecure;
		this.token = token;
	}

	public void setIsSecure(boolean isSecure){
		this.isSecure = isSecure;
	}

	public boolean isIsSecure(){
		return isSecure;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}
}
/*
public class ItemDeserializer extends StdDeserializer<ResponseBoot> {

	public ItemDeserializer() {
		this(null);
	}

	public ItemDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ResponseBoot deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		JsonNode node = jp.getCodec().readTree(jp);

		boolean isSecure = node.get("IsSecure").asBoolean();
		String token = node.get("Token").asText();

		return new ResponseBoot(isSecure, token);

	}
}
*/
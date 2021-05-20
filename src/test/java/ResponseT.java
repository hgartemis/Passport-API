
public class ResponseT{
	private boolean isSecure;
	private String token;

	public ResponseT(boolean isSecure, String token) {
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

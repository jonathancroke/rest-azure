package com.microsoft.journey.azure.restcore;

public class Response {
	private int responseCode;
	private String response;
	
	Response(int responseCode, String response) {
		this.setResponseCode(responseCode);
		this.setResponse(response);
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}

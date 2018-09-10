package com.justfind.utils;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ResponseHeaders {

	public static final String ENCODING_UTF8 = "UTF-8";

	public static HttpHeaders createResponseHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.set("charset", ENCODING_UTF8);
		responseHeaders.set("pageEncoding", ENCODING_UTF8);

		responseHeaders.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

		return responseHeaders;
	}

}

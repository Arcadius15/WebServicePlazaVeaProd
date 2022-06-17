package com.plazavea.webservice.security.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint implements AuthenticationEntryPoint{
    @Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		try {
			System.out.println("MENSAJE");
		} catch (Exception e) {
			// TODO: handle exception
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No esta autorizado");
		
	}
}

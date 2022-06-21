package com.plazavea.webservice.security.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EntryPoint implements AuthenticationEntryPoint{
    @Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		try {
			log.info("Mensaje");
		} catch (Exception e) {
			log.warn("error");
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"No esta autorizado");
		
	}
}

package com.plazavea.webservice.security.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.plazavea.webservice.security.dto.UsuarioRes;
import com.plazavea.webservice.security.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable{
    private static final long serialVersionUID = -3553931089359371862L;

	public static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60; 

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private ModelMapper mapper;

	// Recupera el usuario que se envia en el JWT
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Recupera la fecha de expiración que se envia en el JWT
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// Obtiene la clave secreta del JWT
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// Comprueba si el token a caducado
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Genera un token para el usuario
	public String generateToken(Usuario userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", userDetails.getAuthorities());
		var userRes = mapper.map(userDetails, UsuarioRes.class);
		if (userDetails.getCliente()!=null) {
			claims.put("info", userRes.getCliente());
		}else if(userDetails.getEmpleado()!=null){
			claims.put("info", userRes.getEmpleado());
		}else if(userDetails.getRepartidor()!=null){
			claims.put("info", userRes.getRepartidor());
		}
		claims.put("enabled", userDetails.isEnabled());
		claims.put("expired", !userDetails.isCredentialsNonExpired());
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}

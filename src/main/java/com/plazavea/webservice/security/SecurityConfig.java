package com.plazavea.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.plazavea.webservice.security.enums.Roles;
import com.plazavea.webservice.security.service.UserDetService;
import com.plazavea.webservice.security.utils.EntryPoint;
import com.plazavea.webservice.security.utils.TokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
	private UserDetService service;

	@Autowired
	private EntryPoint entryPoint;
	
	@Autowired
	private TokenFilter filter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(encriptado());
    }

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/jwt/authenticate").permitAll()
			.antMatchers("/jwt/registro/empleado").hasAnyRole(Roles.MASTER.name(),Roles.ADMIN.name())
			.antMatchers("/jwt/registro").permitAll()
			//producto
			.antMatchers(HttpMethod.GET,"/producto").permitAll()
			.antMatchers(HttpMethod.GET,"/producto/{id}").permitAll()
			.antMatchers(HttpMethod.POST,"/producto").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/producto/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name())
			//admin
			.antMatchers(HttpMethod.GET,"/empleado/*").hasAnyRole(Roles.ADMIN.name(),Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/empleado").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/empleado/{id}").hasAnyRole(Roles.MASTER.name())
			//categoria
			.antMatchers(HttpMethod.GET,"/categoria/*").permitAll()
			.antMatchers(HttpMethod.POST,"/categoria").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/categoria/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name())
			//cliente
			.antMatchers(HttpMethod.GET,"/cliente/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name(),Roles.CLIENTE.name(),Roles.DELIVERY.name())
			.antMatchers(HttpMethod.GET,"/cliente").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name(),Roles.DELIVERY.name())
			.antMatchers(HttpMethod.POST,"/cliente").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/cliente/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			//historial venta
			.antMatchers(HttpMethod.GET,"/historial/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.MASTER.name(),Roles.CLIENTE.name(),Roles.DELIVERY.name())
			.antMatchers(HttpMethod.POST,"/historial").hasAnyRole(Roles.ADMIN.name(),Roles.MASTER.name(),Roles.CLIENTE.name(),Roles.DELIVERY.name())
			.antMatchers(HttpMethod.PUT,"/historial/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name(),Roles.DELIVERY.name())
			//orden
			.antMatchers(HttpMethod.GET,"/orden/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.CLIENTE.name(),Roles.MASTER.name(),Roles.CLIENTE.name(),Roles.DELIVERY.name())
			.antMatchers(HttpMethod.POST,"/orden").hasAnyRole(Roles.ADMIN.name(),Roles.MASTER.name(),Roles.CLIENTE.name(),Roles.DELIVERY.name())
			.antMatchers(HttpMethod.PUT,"/orden/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.MASTER.name(),Roles.CLIENTE.name(),Roles.DELIVERY.name())
			//proveedor
			.antMatchers(HttpMethod.GET,"/proveedor/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/proveedor").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/proveedor").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/proveedor/{id}").hasAnyRole(Roles.MASTER.name())
			//ruc
			.antMatchers(HttpMethod.GET,"/ruc/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/ruc").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/ruc").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/ruc/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			//subcategoria
			.antMatchers(HttpMethod.GET,"/subcategoria/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/subcategoria").permitAll()
			.antMatchers(HttpMethod.POST,"/subcategoria").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/subcategoria/{id}").hasAnyRole(Roles.MASTER.name())
			//subtipo
			.antMatchers(HttpMethod.GET,"/subtipo/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/subtipo").permitAll()
			.antMatchers(HttpMethod.POST,"/subtipo").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/subtipo/{id}").hasAnyRole(Roles.MASTER.name())
			//tarjeta
			.antMatchers(HttpMethod.GET,"/tarjeta/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/tarjeta").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/tarjeta").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/tarjeta/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			//tienda
			.antMatchers(HttpMethod.GET,"/tienda/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/tienda").permitAll()
			.antMatchers(HttpMethod.POST,"/tienda").hasAnyRole(Roles.MASTER.name(),Roles.ADMIN.name())
			.antMatchers(HttpMethod.PUT,"/tienda/{id}").hasAnyRole(Roles.MASTER.name(),Roles.ADMIN.name())
			//tipo
			.antMatchers(HttpMethod.GET,"/tipo/{id}").permitAll()
			.antMatchers(HttpMethod.GET,"/tipo").permitAll()
			.antMatchers(HttpMethod.POST,"/tipo").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/tipo/{id}").hasAnyRole(Roles.MASTER.name())
			//usuario
			.antMatchers(HttpMethod.GET,"/usuario/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/usuario").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/usuario").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/usuario/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			//pedido
			.antMatchers(HttpMethod.GET,"/pedido/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/pedido").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/pedido").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/pedido/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			//historialpedido
			.antMatchers(HttpMethod.GET,"/historialpedido/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/historialpedido").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/historialpedido").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/historialpedido/{id}").hasAnyRole(Roles.MASTER.name(),Roles.CLIENTE.name())
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(entryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
			.csrf().disable();
    }

    @Bean
	public PasswordEncoder encriptado() {
		return new BCryptPasswordEncoder();
	}

}

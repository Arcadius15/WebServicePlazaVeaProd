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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.plazavea.webservice.security.enums.Roles;
import com.plazavea.webservice.security.service.UserDetService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
	private UserDetService service;

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
			.antMatchers("/jwt/**").permitAll()
			//producto
			.antMatchers(HttpMethod.GET,"/producto").permitAll()
			.antMatchers(HttpMethod.POST,"/producto").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.PUT,"/producto/{id}").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name())
			//admin
			.antMatchers(HttpMethod.GET,"/empleado").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.GET,"/empleado/*").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.POST,"/empleado").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.PUT,"/empleado/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			//categoria
			.antMatchers(HttpMethod.GET,"/categoria").permitAll()
			.antMatchers(HttpMethod.GET,"/categoria/*").permitAll()
			.antMatchers(HttpMethod.POST,"/categoria").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.PUT,"/categoria/{id}").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name())
			//cliente
			.antMatchers(HttpMethod.GET,"/cliente/{id}").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_DELIVERY.name())
			.antMatchers(HttpMethod.GET,"/cliente").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_DELIVERY.name())
			.antMatchers(HttpMethod.POST,"/cliente").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/cliente/{id}").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			//historial venta
			.antMatchers(HttpMethod.GET,"/historial/{id}").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_DELIVERY.name())
			.antMatchers(HttpMethod.POST,"/historial").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_DELIVERY.name())
			.antMatchers(HttpMethod.PUT,"/historial/{id}").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_DELIVERY.name())
			//orden
			.antMatchers(HttpMethod.GET,"/orden/{id}").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_DELIVERY.name())
			.antMatchers(HttpMethod.POST,"/orden").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_DELIVERY.name())
			.antMatchers(HttpMethod.PUT,"/orden/{id}").hasAnyRole(Roles.ROLE_ADMIN.name(),Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name(),Roles.ROLE_DELIVERY.name())
			//proveedor
			.antMatchers(HttpMethod.GET,"/proveedor/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.GET,"/proveedor").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.POST,"/proveedor").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.PUT,"/proveedor/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			//ruc
			.antMatchers(HttpMethod.GET,"/ruc/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/ruc").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/ruc").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/ruc/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			//subcategoria
			.antMatchers(HttpMethod.GET,"/subcategoria/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.GET,"/subcategoria").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.POST,"/subcategoria").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.PUT,"/subcategoria/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			//subtipo
			.antMatchers(HttpMethod.GET,"/subtipo/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.GET,"/subtipo").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.POST,"/subtipo").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.PUT,"/subtipo/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			//tarjeta
			.antMatchers(HttpMethod.GET,"/tarjeta/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/tarjeta").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/tarjeta").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/tarjeta/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			//tienda
			.antMatchers(HttpMethod.GET,"/tienda/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_ADMIN.name())
			.antMatchers(HttpMethod.GET,"/tienda").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_ADMIN.name())
			.antMatchers(HttpMethod.POST,"/tienda").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_ADMIN.name())
			.antMatchers(HttpMethod.PUT,"/tienda/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_ADMIN.name())
			//tipo
			.antMatchers(HttpMethod.GET,"/tipo/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.GET,"/tipo").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.POST,"/tipo").hasAnyRole(Roles.ROLE_MASTER.name())
			.antMatchers(HttpMethod.PUT,"/tipo/{id}").hasAnyRole(Roles.ROLE_MASTER.name())
			//usuario
			.antMatchers(HttpMethod.GET,"/usuario/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/usuario").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/usuario").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/usuario/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			//pedido
			.antMatchers(HttpMethod.GET,"/pedido/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/pedido").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/pedido").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/pedido/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			//historialpedido
			.antMatchers(HttpMethod.GET,"/historialpedido/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/historialpedido").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/historialpedido").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/historialpedido/{id}").hasAnyRole(Roles.ROLE_MASTER.name(),Roles.ROLE_CLIENTE.name())
			.and()
			.httpBasic()
			.and()
			.csrf().disable();
    }

    @Bean
	public PasswordEncoder encriptado() {
		return new BCryptPasswordEncoder();
	}

}

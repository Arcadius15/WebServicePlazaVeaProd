package com.plazavea.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.plazavea.webservice.enums.Roles;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
	@Autowired
	private UserDetService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.inMemoryAuthentication()
		.withUser("developer")
		.password(encriptado().encode("master"))
		.roles("MASTER");

		auth.userDetailsService(service).passwordEncoder(encriptado());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
			//producto
			.antMatchers(HttpMethod.GET,"/producto").permitAll()
			.antMatchers(HttpMethod.POST,"/producto").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/producto/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name())
			//admin
			.antMatchers(HttpMethod.GET,"/admin/*").hasAnyRole(Roles.ADMIN.name(),Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/admin").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/admin/{id}").hasAnyRole(Roles.MASTER.name())
			//categoria
			.antMatchers(HttpMethod.GET,"/categoria/*").permitAll()
			.antMatchers(HttpMethod.POST,"/categoria").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/categoria/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name())
			//cliente
			.antMatchers(HttpMethod.GET,"/cliente/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.GET,"/cliente").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/cliente").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/cliente/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			//historial venta
			.antMatchers(HttpMethod.GET,"/historial/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/historial").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/historial/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name())
			//orden
			.antMatchers(HttpMethod.GET,"/orden/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.EMPLEADO.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.POST,"/orden").hasAnyRole(Roles.ADMIN.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			.antMatchers(HttpMethod.PUT,"/orden/{id}").hasAnyRole(Roles.ADMIN.name(),Roles.MASTER.name(),Roles.CLIENTE.name())
			//producto
			.antMatchers(HttpMethod.GET,"/producto/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/producto").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/producto").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/producto/{id}").hasAnyRole(Roles.MASTER.name())
			//proveedor
			.antMatchers(HttpMethod.GET,"/proveedor/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/proveedor").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/proveedor").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/proveedor/{id}").hasAnyRole(Roles.MASTER.name())
			//ruc
			.antMatchers(HttpMethod.GET,"/ruc/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/ruc").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/ruc").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/ruc/{id}").hasAnyRole(Roles.MASTER.name())
			//subcategoria
			.antMatchers(HttpMethod.GET,"/subcategoria/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/subcategoria").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/subcategoria").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/subcategoria/{id}").hasAnyRole(Roles.MASTER.name())
			//subtipo
			.antMatchers(HttpMethod.GET,"/subtipo/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/subtipo").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/subtipo").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/subtipo/{id}").hasAnyRole(Roles.MASTER.name())
			//tarjeta
			.antMatchers(HttpMethod.GET,"/tarjeta/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/tarjeta").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/tarjeta").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/tarjeta/{id}").hasAnyRole(Roles.MASTER.name())
			//tienda
			.antMatchers(HttpMethod.GET,"/tienda/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/tienda").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/tienda").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/tienda/{id}").hasAnyRole(Roles.MASTER.name())
			//tipo
			.antMatchers(HttpMethod.GET,"/tipo/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/tipo").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/tipo").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/tipo/{id}").hasAnyRole(Roles.MASTER.name())
			//usuario
			.antMatchers(HttpMethod.GET,"/usuario/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/usuario").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/usuario").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/usuario/{id}").hasAnyRole(Roles.MASTER.name())
			//pedido
			.antMatchers(HttpMethod.GET,"/pedido/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/pedido").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/pedido").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/pedido/{id}").hasAnyRole(Roles.MASTER.name())
			//historialpedido
			.antMatchers(HttpMethod.GET,"/historialpedido/{id}").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.GET,"/historialpedido").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.POST,"/historialpedido").hasAnyRole(Roles.MASTER.name())
			.antMatchers(HttpMethod.PUT,"/historialpedido/{id}").hasAnyRole(Roles.MASTER.name())
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

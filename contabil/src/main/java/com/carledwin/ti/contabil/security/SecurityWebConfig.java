package com.carledwin.ti.contabil.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter{
	/*
	private static final String QUERY_USUARIO_POR_LOGIN="SELECT login, senha, ativo, name FROM usuario WHERE login=?";
	private static final String QUERY_PERMISSOES_POR_USUARIO="SELECT u.login, p.nome FROM usuario_permissao up"
															+" JOIN usuario u ON u.id=up.usuarios_id"
															+" JOIN permissao p ON p.id=up.permissoes_id"
															+" WHERE u.login=?";
	private static final String QUERY_PERMISSOES_POR_GRUPO="SELECT g.id, g.nome, p.nome FROM grupo_permissao gp"
															+" JOIN grupo g ON g.id=gp.grupos_id"
															+" JOIN permissao p ON p.id=gp.permissoes_id"
															+" JOIN usuario_grupo ug ON ug.grupos_id=g.id"
															+" JOIN usuario u ON u.id=ug.usuarios_id"
															+" WHERE u.login=?";
	
	*/
	
	//@Autowired
	//private DataSource dataSource;
	
	@Autowired
	private ComercialUserDetailsService comercialUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http
			.formLogin()
			.loginPage("/login.html")
			.failureUrl("/login-error.html")
		.and()
			.logout()
			.logoutSuccessUrl("/index.html");*/
			
		
	/*	http
		.authorizeRequests()
		.antMatchers("/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.rememberMe();*/
		//.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password("admin")
		.roles("ADM")
		.and()
		.withUser("carl")
		.password("carl")
		.roles("USER");
		
		/*
		auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery(QUERY_USUARIO_POR_LOGIN)
		.authoritiesByUsernameQuery(QUERY_PERMISSOES_POR_USUARIO)
		.groupAuthoritiesByUsername(QUERY_PERMISSOES_POR_GRUPO)
		.rolePrefix("ROLE_");
		*/
	}
	

	
	public static void main(String[] args){
		//System.out.println(new BCryptPasswordEncoder().encode("carl"));
		//$2a$10$rqgbJ.bT97qzGUhNWPiMz.pREQZV.WFSPFAxDtVWFF0Frt5OFEn12
		//INSERT INTO usuario(id, nome, login, senha, ativo) VALUES(1,"Carl Edwin Antonio Nascimento","carledwinti","$2a$10$rqgbJ.bT97qzGUhNWPiMz.pREQZV.WFSPFAxDtVWFF0Frt5OFEn12", true);
		 
	//INSERT INTO USUARIO(id, nome, login, senha, ativo) VALUES(1,'Carl Edwin Antonio Nascimento','carledwinti','$2a$10$rqgbJ.bT97qzGUhNWPiMz.pREQZV.WFSPFAxDtVWFF0Frt5OFEn12', 1);

	}
	
}

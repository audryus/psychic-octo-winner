package avaliacao.ekan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
    		.requestMatchers("/swagger-ui/**").permitAll()
    		.requestMatchers("/v3/api-docs/**").permitAll()
    		.requestMatchers("/**").authenticated()
        ).httpBasic(Customizer.withDefaults());
    
    return http.build();
	}
	
	// Password Encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		var senha = "123";
		
        var admin = User.withUsername("admin")
                .password(encoder.encode(senha))
                .roles("ADMIN", "WRITE", "READ")
                .build();

        var writer = User.withUsername("writer")
                .password(encoder.encode(senha))
                .roles("WRITE", "READ")
                .build();

        var reader = User.withUsername("reader")
        		.password(encoder.encode(senha))
        		.roles("READ")
        		.build();

        return new InMemoryUserDetailsManager(admin, writer, reader);
    }



}

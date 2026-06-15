package helpdesk.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // статика и H2
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        // публичные GET страницы
                        .requestMatchers(HttpMethod.GET,
                                "/", "/about", "/contacts", "/faq",
                                "/tickets/new", "/tickets/*/success", "/login"
                        ).permitAll()
                        // публичный POST (создание заявки)
                        .requestMatchers(HttpMethod.POST, "/tickets").permitAll()
                        // админ-зона только для ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // всё остальное требует входа
                        .anyRequest().authenticated()
                )
                // CSRF для H2
                .csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/admin/tickets", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("USER", "ADMIN")
                .build();
        UserDetails user = User.withUsername("user")
                .password("{noop}user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
}

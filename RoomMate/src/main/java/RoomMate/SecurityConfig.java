package RoomMate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain configure(HttpSecurity chainBuilder) throws Exception {
            chainBuilder.authorizeRequests(a-> a
                            .anyRequest().authenticated()
                    )
                    .oauth2Login(Customizer.withDefaults());

            return chainBuilder.build();
        }
    }


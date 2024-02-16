package RoomMate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class SecurityConfig {

    @Value("${RoomMate.rollen.admin}")
    private Set<String>admins;

        @Bean
        public SecurityFilterChain configure(HttpSecurity chainBuilder) throws Exception {
            chainBuilder.authorizeRequests(a-> a
                            .requestMatchers("/css/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .logout(c->c.logoutSuccessUrl("/"))
                    .csrf(c->c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                    .oauth2Login(Customizer.withDefaults());

            return chainBuilder.build();
        }
    @Bean
    OAuth2UserService<OAuth2UserRequest, OAuth2User> createUserService() {
        DefaultOAuth2UserService defaultService = new DefaultOAuth2UserService();
        return userRequest -> {
            OAuth2User oauth2User = defaultService.loadUser(userRequest);

            var attributes = oauth2User.getAttributes(); //keep existing attributes

            var authorities = new HashSet<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            String login = attributes.get("login").toString();
            System.out.printf("USER LOGIN: %s%n", login);

            if (admins.contains(login)) {
                System.out.printf("GRANTING ADMIN PRIVILEGES TO USER %s%n", login);
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                System.out.printf("DENYING ADMIN PRIVILEGES TO USER %s%n", login);
            }

            return new DefaultOAuth2User(authorities, attributes, "login");
        };
    }
    }


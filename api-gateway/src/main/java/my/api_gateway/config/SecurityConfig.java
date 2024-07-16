package my.api_gateway.config;

import my.api_gateway.client.UserServiceClient;
import my.api_gateway.jwt.JWTFilter;
import my.api_gateway.jwt.JWTUtil;
import my.api_gateway.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    private final JWTUtil jwtUtil;
    private final UserServiceClient userServiceClient;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil, UserServiceClient userServiceClient) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.userServiceClient = userServiceClient;
    }

    //loginFilter 주입 메서드 bean생성
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    //암호화
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


        //csrf disable
        http
                .csrf(AbstractHttpConfigurer::disable)
//
//        //Form 로그인 방식 disable
//        http
                .formLogin(AbstractHttpConfigurer::disable)
//
//        //http basic 인증 방식 disable
//        http
                .httpBasic(AbstractHttpConfigurer::disable);

        //경로별 인가 설정
        http
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/login","/","/join").permitAll()  //해당경로 모두허용
                        .requestMatchers("/admin").hasRole("ADMIN")  //admin경로는 admin만
                        .anyRequest().permitAll());               //나머지는 로그인사용자만 허용

        //필터 위치, 대체
        http
                .addFilterBefore(new JWTFilter(jwtUtil, userServiceClient), LoginFilter.class);

        http
                .addFilterBefore(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, userServiceClient), UsernamePasswordAuthenticationFilter.class);

        //세선 설정 stateless상태로 설정 중요
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


}

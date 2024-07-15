package my.api_gateway.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.api_gateway.client.UserDTO;
import my.api_gateway.client.UserServiceClient;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
//import my.user_service.dto.CustomUserDetails;
//import my.user_service.entity.UserEntity;

import java.io.IOException;
import java.util.Collections;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserServiceClient userServiceClient;

    public JWTFilter(JWTUtil jwtUtil, UserServiceClient userServiceClient) {

        this.jwtUtil = jwtUtil;
        this.userServiceClient = userServiceClient;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authoriation 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {

//            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메서드 종료(필수)
            return;
        }

        String token = authorization.split(" ")[1];

        //토큰 소멸시간 검증
        if (jwtUtil.isExpired(token)) {

//            System.out.println("token expired");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메서드 종료(필수)
            return;
        }

        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // jwtfilter를 api-gateway로 옮기며 userentity를 feignclient를 사용해 userdto로 가져오며 변경
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(username);
//        userEntity.setPassword("password"); //임의의 비밀번호 지정하여 초기화.
//        userEntity.setRole(role);
//
//        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //UserServiceClient를 사용해 사용자 정보를 가져옴(기존에는 userentity를 가져왔었지)
        UserDTO userDTO = userServiceClient.findByUsername(username);
        if (userDTO == null) {
            filterChain.doFilter(request, response);
        }

        UserDetails userDetails = new User(userDTO.getUsername(), userDTO.getPassword(), Collections.emptyList());

        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}

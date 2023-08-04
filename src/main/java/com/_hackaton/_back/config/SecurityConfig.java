package com._hackaton._back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() //이 주소로 들어오면 인증이 필요해.
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //admin 또는 manager권한 필요
                .antMatchers("admin/**").access("hasRole('ROLE_ADMIN')") //admin만 허용
                .anyRequest().permitAll() // 해당 url이 아닌 경우 모두 허용한다는 코드
                .and()
                .formLogin()//일반적인 로그인 방식의 성공, 실패처리를 사용
                .loginPage("/loginForm"); //사용자가 따로 만든 로그인 페이지를 사용하려고 할때 설정한다.

        return http.build();
    }
}

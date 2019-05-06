package com.zxw.security.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxw.filters.security.CustomCorsFilter;
import com.zxw.security.RestAuthenticationEntryPoint;
import com.zxw.security.auth.ajax.AjaxAuthenticationProvider;
import com.zxw.security.auth.ajax.AjaxLoginProcessingFilter;
import com.zxw.security.auth.jwt.JwtAuthenticationProvider;
import com.zxw.security.auth.jwt.JwtTokenAuthenticationProcessingFilter;
import com.zxw.security.auth.jwt.SkipPathRequestMatcher;
import com.zxw.security.auth.jwt.extractor.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @Author zhangxw
 * @Description:
 * @Date Created in 2019/3/29  14:48.
 * @Modified by:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启security注解
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    public static final String AUTHENTICATION_URL = "/api/jwt/login";
    public static final String REFRESH_TOKEN_URL = "/api/jwt/token";
    public static final String API_ROOT_URL = "/api/**";
   /* @Autowired
    private SecurityUserDetail securityUserDetail;


    @Override
    protected void configure(AuthenticationManagerBuilder jwt) throws Exception {
        //使用自定义的用户信息,并将数据加密
        jwt.userDetailsService(securityUserDetail).passwordEncoder(new BCryptPasswordEncoder());
       // super.configure(jwt);
    }*/

 //   public void configure(WebSecurity web) throws Exception {
      //  web.ignoring().antMatchers("/templates/**");
   //     web.ignoring().antMatchers("/**/*.css", "/swagger-ui/**","/**/*.js","/v2/api-docs", "/webjars/**","/swagger-resources/**");
   // }

    //note : authorize:认证
 /*   protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login","/swagger-ui.html").permitAll()//制定该路径可以访问,其他不可以访问
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/user/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .exceptionHandling().accessDeniedPage("/access?error");
        // @formatter:on
    }*/
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private AjaxAuthenticationProvider ajaxAuthenticationProvider;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ObjectMapper objectMapper;

    protected AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter(String loginEntryPoint) throws Exception {
        AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(loginEntryPoint, successHandler, failureHandler, objectMapper);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter(List<String> pathsToSkip, String pattern) throws Exception {
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
        JwtTokenAuthenticationProcessingFilter filter
                = new JwtTokenAuthenticationProcessingFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> permitAllEndpointList = Arrays.asList(
                AUTHENTICATION_URL,
                REFRESH_TOKEN_URL,
                "/console"
        );

        http
                .csrf().disable() // We don't need CSRF for JWT based authentication
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               // .and().formLogin().loginPage("/login").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(permitAllEndpointList.toArray(new String[permitAllEndpointList.size()]))
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(API_ROOT_URL).authenticated() // Protected API End-points
                .and()
                .addFilterBefore(new CustomCorsFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildAjaxLoginProcessingFilter(AUTHENTICATION_URL), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(permitAllEndpointList,
                        API_ROOT_URL), UsernamePasswordAuthenticationFilter.class);
    }
}

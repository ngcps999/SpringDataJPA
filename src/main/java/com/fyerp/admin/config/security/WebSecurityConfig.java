/*
 * 作者：xuda
 * 创建时间：18-5-3 上午11:59
 * 模块名称：admin
 */

package com.fyerp.admin.config.security;

/**
 * Created by huangds on 2017/10/24.
 */


import com.fyerp.admin.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 登录安全配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private FyAuthenticationFailureHandler failureHandler;

    @Autowired
    private FyAuthenticationSuccessHandler successHandler;

    @Resource(name = "fyUserDetailService")
    private UserDetailsService userDetailsService;


//    @Value("${myProperties.default.username}")
//    private String defaultUsername;
//
//    @Value("${myProperties.default.password}")
//    private String defaultPassword;

//    public final static String SESSION_KEY="username";
//
//    @Bean
//    public SecurityInterceptor getSecurityInterceptor(){
//        return new SecurityInterceptor();
//    }
//
//    public void  addInterceptors(InterceptorRegistry registry){
//        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//
//        addInterceptor.excludePathPatterns("/error");
//        addInterceptor.excludePathPatterns("/login**");
//
//        addInterceptor.addPathPatterns("/**");
//    }
//
//    private class SecurityInterceptor extends HandlerInterceptorAdapter {
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//            HttpSession session = request.getSession();
//
////            判断是否已有该用户登录的session
//            if(session.getAttribute(SESSION_KEY) != null){
//                return true;
//            }
//
////            跳转到登录页
////            String url = "/loginPost";
////            response.sendRedirect(url);
//            return false;
//        }
//    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/unauth").loginProcessingUrl("/ajaxLogin")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout().permitAll()
        .and().cors();


//        http.formLogin().loginProcessingUrl("ajaxLogin").and().authorizeRequests().anyRequest().authenticated();

//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/unauth")
//                .defaultSuccessUrl("/ajaxLogin")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll().and().cors()
//        .and().csrf().disable();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
////        return new MyPasswordEncoder();
//    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Util.getMD5((String)charSequence);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(MD5Util.getMD5((String)charSequence));
            }
        });
    }
}

//package ro.itschool.homesphere.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import ro.itschool.homesphere.security.service.CustomUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class BasicAuthSecurityConfig {
//
//    @Bean
//    public CustomUserDetailsService userDetailsService(){
//        return new CustomUserDetailsService();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder(10);
//    }
//    @Bean
//    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/customers/**").hasRole("CUSTOMER")
//                .antMatchers("/api/test/all").permitAll()
//                .antMatchers("/api/test/user").hasAnyRole("CUSTOMER", "SERVICEPROVIDER", "ADMIN")
//                .antMatchers("/api/test/admin").hasRole("ADMIN")
//                .and()
//                .httpBasic();
//
//        return http.build();
//    }
//}

package ru.Product.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private UserDetailsService userDetailsService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        HttpBasicConfigurer<HttpSecurity> httpSecurityHttpBasicConfigurer = httpSecurity.csrf().disable().
                authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/api/v1/category/getOne").hasAnyRole("ADMIN", "USER", "STAFF")
                                .requestMatchers("/api/v1/category/getAll").hasAnyRole("ADMIN", "USER", "STAFF")

                                .requestMatchers("/api/v1/product/getAllProducts").hasAnyRole("ADMIN", "USER", "STAFF")
                                .requestMatchers("/api/v1/product/getOneProduct").hasAnyRole("ADMIN", "USER", "STAFF")
                                .requestMatchers("/api/v1/product/getAllProductsFromOneCategory").hasAnyRole("ADMIN", "USER", "STAFF")

                                .requestMatchers("api/v1/user/logout").hasAnyRole("ADMIN", "USER", "STAFF")

                                .requestMatchers("/api/v1/order/getAllOrdersByUserId").hasAnyRole("ADMIN", "USER", "STAFF")
                                .requestMatchers("/api/v1/order/getOrderById").hasAnyRole("ADMIN", "USER", "STAFF")

                                .requestMatchers("api/v1/user/getUser").hasAnyRole("ADMIN", "STAFF")

                                .requestMatchers("/api/v1/category/create").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/api/v1/category/update").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/api/v1/category/delete").hasAnyRole("ADMIN", "STAFF")

                                .requestMatchers("/api/v1/product/create").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/api/v1/product/update").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/api/v1/product/delete").hasAnyRole("ADMIN", "STAFF")
                                
                                .requestMatchers("/api/v1/order/getAllOrders").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/api/v1/order/updateProductQuantityInOrder").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/api/v1/order/addProductToOrder").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/api/v1/order/removeProductFromOrder").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("/api/v1/order/updateOrderStatus").hasAnyRole("ADMIN", "STAFF")

                                .requestMatchers("api/name/orderStatus/{name}").hasAnyRole("ADMIN", "STAFF")
                                .requestMatchers("api/name/orderStatus/getAll").hasAnyRole("ADMIN", "STAFF")

                                .requestMatchers("/api/v1/cart/addProductToCart").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/v1/cart/removeProductFromCart").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/v1/cart/getCart").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/v1/cart/removeAllProductsFromCart").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/v1/cart/getProductsInStock").hasAnyRole("USER", "ADMIN")

                                .requestMatchers("/api/v1/order/saveOrder").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("api/v1/user/updateUser").hasAnyRole("USER", "ADMIN")

                                .anyRequest().authenticated())

                .httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}

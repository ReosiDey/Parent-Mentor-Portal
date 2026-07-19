package com.university.parent.client;

import com.university.parent.config.FeignConfig; // Ensure this is imported
import com.university.parent.dto.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign Client to communicate with the Auth Microservice.
 * Added configuration = FeignConfig.class to pass the JWT token.
 */
@FeignClient(
        name = "auth-service",
        url = "http://localhost:8080",
        configuration = FeignConfig.class // <--- CRITICAL ADDITION
)
public interface AuthClient {

    /**
     * This must match the endpoint defined in your AuthController.
     * It sends the parent's mobile (username) and default password.
     */
    @PostMapping("/auth/register/parent")
    String registerParent(@RequestBody RegisterRequest request);
}
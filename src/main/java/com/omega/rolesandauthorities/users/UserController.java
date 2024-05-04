package com.omega.rolesandauthorities.users;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @GetMapping("user")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        return "Hello from user controller";
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Hello from admin controller";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("client")
    public String client() {
        return "Hello from client controller";
    }
}
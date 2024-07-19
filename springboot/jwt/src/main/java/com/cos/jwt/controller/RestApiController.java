package com.cos.jwt.controller;

import com.cos.jwt.model.User;
import com.cos.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RestApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String home() {
        return "<h1>home</h1>";
    }

    @PostMapping("token")
    public String token() {
        return "<h1>token</h1>";
    }



    @PostMapping("join")
    public String join(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER"); // Role은 기본으로 설정
        userRepository.save(user);
        return "회원가입 완료";
    }

    // user권한 이상 접근 가능
    @GetMapping("/api/v1/user")
    public String user() {
        return "user";
    }

    // 매니저 권한 이상 접근 가능
    @GetMapping("/api/v1/manager")
    public String manager() {
        return "manager";
    }

    // admin 권한만 접근 가능
    @GetMapping("/api/v1/admin")
    public String admin() {
        return "admin";
    }
}



package com.sociedade.catalogoback.controllers;

import com.sociedade.catalogoback.domain.company.Company;
import com.sociedade.catalogoback.domain.user.*;
import com.sociedade.catalogoback.domain.user.dto.AuthenticationDTO;
import com.sociedade.catalogoback.domain.user.dto.LoginResponseDTO;
import com.sociedade.catalogoback.domain.user.dto.RegisterDTO;
import com.sociedade.catalogoback.domain.user.dto.UserDTO;
import com.sociedade.catalogoback.repositories.UserRepository;
import com.sociedade.catalogoback.security.TokenService;
import com.sociedade.catalogoback.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorizationService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        User user = (User) this.repository.findByLogin(data.login());
        UserDTO userDTO = new UserDTO(user.getId(), user.getLogin(), user.getUsername());

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token, userDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        List<Company> companies = Collections.emptyList();
        User newUser = new User(null, data.login(), encryptedPassword, data.role(), companies);

        User save = this.repository.save(newUser);

        RegisterDTO userLogged = new RegisterDTO(save.getLogin(), null, save.getRole());

        return ResponseEntity.ok().body(userLogged);
    }


    @GetMapping("/companies/{userId}")
    public ResponseEntity<List<Company>> getCompaniesByUser(@PathVariable("userId") String userId) {
        List<Company> companies = authService.getCompaniesByUserId(userId);
        return ResponseEntity.ok(companies);
    }

}

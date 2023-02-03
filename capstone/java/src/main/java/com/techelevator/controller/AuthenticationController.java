package com.techelevator.controller;

import javax.validation.Valid;

import com.techelevator.dao.ApplicationDao;
import com.techelevator.dao.ContactDao;
import com.techelevator.model.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.UserDao;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class AuthenticationController {
    private static final String API_BASE_URL = "http://localhost:9000/";

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDao userDao;
    private ApplicationDao applicationDao;
    private ContactDao contactDao;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDao userDao, ApplicationDao applicationDao, ContactDao contactDao) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.applicationDao = applicationDao;
        this.contactDao = contactDao;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);
        
        User user = userDao.findByUsername(loginDto.getUsername());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponseDto(jwt, user), httpHeaders, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUserDto newUser) {
        newUser.setRole("ROLE_USER");
        try {
            User user = userDao.findByUsername(newUser.getUsername());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists.");
        } catch (UsernameNotFoundException e) {
            userDao.create(newUser.getUsername(),newUser.getPassword(), newUser.getRole());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public void createApp(@Valid @RequestBody ApplicationDTO applicationDTO, Principal principal) {
        applicationDao.createApp(applicationDTO, principal);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/applications/{id}")
    public List<ApplicationDTO> getApplications(@PathVariable String id) {
        return applicationDao.listApplicationDTO(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/applications/{id}/approve")
    public void approveApplication(@PathVariable int id) {
        applicationDao.updateStatus(id, "A");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/applications/{id}/deny")
    public void rejectApplication(@PathVariable int id) {
        applicationDao.updateStatus(id, "R");
    }

    @PreAuthorize("hasAnyRole('ADMIN','VOLUNTEER')")
    @GetMapping(value = "/volunteers")
    public List<ContactDTO> getContactInfo() {
        return contactDao.getListOfContactDTO();
    }

}


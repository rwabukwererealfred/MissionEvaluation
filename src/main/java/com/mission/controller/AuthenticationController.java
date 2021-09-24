package com.mission.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mission.config.JwtUtils;
import com.mission.request.LoginForm;
import com.mission.request.SignUpForm;
import com.mission.response.JwtResponse;
import com.mission.response.ResponseMessage;
import com.mission.service.IAuthenticationService;
import com.mission.service.UserDetailsImp;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="api/mission/authentication/")
public class AuthenticationController extends com.mission.response.Exception  {
	
	@Autowired
	private IAuthenticationService authenticationService;
	
	@Autowired
	private AuthenticationManager authonticationManager;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtutils;
	
	@PostMapping("signin")
	@ApiOperation("user account login")
	public ResponseEntity<?> SignIn(@Valid @RequestBody LoginForm login) {
		System.out.print("username: "+login.getUsername()+" password: "+login.getPassword());
		Authentication auth = authonticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = jwtutils.generateJwtToken(auth);
		UserDetailsImp us = (UserDetailsImp) auth.getPrincipal();
		 List<String> role = us.getAuthorities().stream().map(item ->
		 item.getAuthority()).collect(Collectors.toList());
		System.out.print("out put: " + us.getUsername()+ " role "+ role);
		return ResponseEntity.ok(new JwtResponse(us.getId(), us.getUsername(), us.getPassword(), us.isActive(),us.getEmployee(), role, jwt));
	}
	
	
	@SuppressWarnings("rawtypes")
	@PutMapping(value="updateUserAcccount")
	public ResponseEntity signup(@Valid @RequestBody SignUpForm signup) {
		try {
			
			String result = authenticationService.createAccount(signup.getUsername(),signup.getPassword(), signup.getUserId());
			
			if(result.equals("success")) {
			return ResponseEntity.ok(new ResponseMessage("Well Successfull created"));
			}else {
				return ResponseEntity.badRequest().body(new ResponseMessage(result));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
		}
	}
	
	
	
	

}

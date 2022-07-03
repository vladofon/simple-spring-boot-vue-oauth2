package com.annoying.auth.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	public MainController() {
	}

	@GetMapping("sessions/me")
	public ResponseEntity<?> user(Principal principal) {
		if (principal == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "unauthorized"));
		}
		return ResponseEntity.ok(principal);
	}

	@GetMapping("sessions/Google/callback")
	public void googleCallback(HttpServletResponse response) throws IOException {
		System.out.println("GOOGLE CALLBACK");
		response.sendRedirect("http://localhost:8081/");
	}

	@GetMapping("something")
	public ResponseEntity<?> someTest() {
		return ResponseEntity.ok("All be ok, bro");
	}
}

package com.sarascattone.MusicStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarascattone.MusicStore.Services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public int doLogin(@RequestParam String email, @RequestParam String password) {
		return 200;
	}
}

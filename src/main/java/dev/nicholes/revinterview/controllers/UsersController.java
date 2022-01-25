package dev.nicholes.revinterview.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.nicholes.revinterview.models.User;
import dev.nicholes.revinterview.services.AssociateService;

@RestController
@RequestMapping(path="/users")
@CrossOrigin(origins="http://rev-interview.s3-website.us-east-2.amazonaws.com/")
public class UsersController {
	private AssociateService associateServ;
	
	@Autowired
	public UsersController(AssociateService associateServ) {
		this.associateServ=associateServ;
	}
	
	@GetMapping
	public ResponseEntity<Set<User>> getAllUsers() {
		return ResponseEntity.ok(associateServ.getAllUsers());
	}
}

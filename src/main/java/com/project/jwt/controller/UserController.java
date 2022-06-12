package com.project.jwt.controller;

import com.project.jwt.entity.User;
import com.project.jwt.repository.UserRepository;
import com.project.jwt.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

@RestController
@EnableWebSecurity
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }
    
    @PreAuthorize("hasRole('Admin')")

    public  List<User> getAllUser(){
		return userService.getAllUser();
		
	}

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
    
    @GetMapping("/users")

    public  List<User>  getUsres(){
		List<User> users = new ArrayList<>();
	return	users = userService.getAllUser();
           	
    	
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('Admin')")
	@DeleteMapping("/delateusers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String id){
		
		User user = userRepository.findById(id).orElseThrow();
		
		userRepository.delete(user);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
		
		
	}
    
    
  
    
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(String  id , User user){
		
		return userService.updateUser(id, user);
		
	}
	
	
    
	

    
}

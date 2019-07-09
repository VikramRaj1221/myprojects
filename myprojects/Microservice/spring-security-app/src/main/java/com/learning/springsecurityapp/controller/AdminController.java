package com.learning.springsecurityapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springsecurityapp.model.UserInfo;
import com.learning.springsecurityapp.repo.UserRepository;
import com.learning.springsecurityapp.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	 
	@Autowired
	UserService userService; 
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	public String adduser(@RequestBody UserInfo user){
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "User added successfully";
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/check")
	public String adminCheck() {
		return "this is admin screen";
	}
	
	@GetMapping("/add/{a}/{b}")
	public List getInfo(@PathVariable("a") int a, @PathVariable("b") int b){
		return userService.add(a,b);
	}
	
	@GetMapping("/info/{userName}")
	public List getInfo(@PathVariable("userName") String userName){
		return userService.getQuery(userName);
	}
	
	@GetMapping("/all")
	public Page<UserInfo> getAll() {
		//return (List<UserInfo>) userRepo.findbyAll();
		Pageable peagable =PageRequest.of(0, 5, Sort.by("userName"));
		return userRepo.findAll(peagable);
	} 
	
	@GetMapping("/all/{userName}")
	public UserInfo getAllByName(@PathVariable("userName") String userName) {
		//return (List<UserInfo>) userRepo.findbyAll();
		UserInfo info=userRepo.findbyname(userName);
		return info;
	} 
	
	@PostMapping("/update/{old}/{id}")
	public String updateName(@PathVariable("old") String userName,
			@PathVariable("id") Long userId) {
		int count=userRepo.updateName(userName,userId);
		if(count<1) return "No record to update";
		return "Success";
	}
	
	@GetMapping("all1/{name}")
	public List getAll1(@PathVariable("name") String name){
		return userService.getDataFromCriteria(name);
	}
}

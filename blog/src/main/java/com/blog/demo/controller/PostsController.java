package com.blog.demo.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.dto.PostsDto;
import com.blog.demo.model.Post;
import com.blog.demo.service.PostsService;

@RestController
@RequestMapping("/api/posts")
public class PostsController {
	
	@Autowired
	private PostsService postsService;
	
	@PostMapping("/createPost")
	public ResponseEntity<Long> createPost(@RequestBody PostsDto postsDto) throws UserPrincipalNotFoundException{
		return ResponseEntity.ok().body(postsService.createPost(postsDto));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Post>> getAllPosts(){
		return ResponseEntity.ok().body(postsService.getAllPosts());
	}

}

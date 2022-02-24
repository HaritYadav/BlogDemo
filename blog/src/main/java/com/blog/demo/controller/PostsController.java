package com.blog.demo.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.dto.PostsDto;
import com.blog.demo.exception.NotAllowedException;
import com.blog.demo.model.Post;
import com.blog.demo.service.PostsService;

@RestController
@RequestMapping("/api/posts")
public class PostsController {
	
	@Autowired
	private PostsService postsService;
	
	@PostMapping("/createPost")
	public ResponseEntity<Long> createPost(@RequestBody PostsDto postsDto) throws UserPrincipalNotFoundException, IllegalArgumentException{
		return ResponseEntity.ok().body(postsService.createPost(postsDto));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Post>> getAllPosts(){
		return ResponseEntity.ok().body(postsService.getAllPosts());
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id){
		return ResponseEntity.ok().body(postsService.getPostById(id));
	}
	
//	https://stackoverflow.com/questions/48785300/missing-uri-template-variable-for-method-parameter-of-type-string/48785351
	@GetMapping("/getByUser/{user}")
	public ResponseEntity<List<Post>> getPostByUserName(@PathVariable("user") String username){
		return ResponseEntity.ok().body(postsService.getPostByUserName(username));
	}
	
	@PostMapping("/editPost/{id}")
	public ResponseEntity<Post> editPost(@RequestBody PostsDto postsDto, @PathVariable Long id) throws UserPrincipalNotFoundException, IllegalArgumentException, NotAllowedException{
		return ResponseEntity.ok().body(postsService.editPost(postsDto, id));
	}

}

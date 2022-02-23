package com.blog.demo.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blog.demo.dto.PostsDto;
import com.blog.demo.model.Post;
import com.blog.demo.repository.PostRepository;

@Service
public class PostsService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private AuthService authService;
	
	public List<Post> getAllPosts() {
		
		return postRepo.findAll();
	}

	public Long createPost(PostsDto postsDto) throws IllegalArgumentException, UserPrincipalNotFoundException {
		// TODO Auto-generated method stub
		Post newPost = new Post();
		newPost.setContent(postsDto.getContent());
		newPost.setTitle(postsDto.getTitle());
		newPost.setCreatedOn(Instant.now());
		newPost.setUpdatedOn(Instant.now());
		
		UserDetails useretails = authService.getUsername().orElseThrow(()-> new IllegalArgumentException("Error getting data!!"));
		newPost.setUsername(useretails.getUsername());
		
		return postRepo.save(newPost).getId();
	}

}

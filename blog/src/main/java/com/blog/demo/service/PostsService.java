package com.blog.demo.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blog.demo.dto.PostsDto;
import com.blog.demo.exception.NotAllowedException;
import com.blog.demo.model.Post;
import com.blog.demo.repository.PostRepository;

@Service
public class PostsService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private AuthService authService;
	
	
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
	
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return postRepo.findAll();
	}

	public Post getPostById(Long id) {
		// TODO Auto-generated method stub
		System.out.println(id);
		return postRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("No such Post Id!!"));
	}

	public List<Post> getPostByUserName(String username) {
		// TODO Auto-generated method stub
		return postRepo.findByUsername(username).orElseThrow(()-> new IllegalArgumentException("No Posts for the user!!"));
	}

	//https://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
	public Post editPost(PostsDto postsDto, Long id) throws IllegalArgumentException, UserPrincipalNotFoundException, NotAllowedException {
		// TODO Auto-generated method stub
		Post oldPost = postRepo.findById(id).orElseThrow(()->new IllegalArgumentException("No Posts with given Id!!"));
		if (oldPost.getUsername().equals(
				authService.getUsername()
				.orElseThrow(()-> new IllegalArgumentException("Error getting data!!"))
				.getUsername())){
			oldPost.setTitle(postsDto.getTitle());
			oldPost.setContent(postsDto.getContent());
			oldPost.setUpdatedOn(Instant.now());
			postRepo.save(oldPost);
			
			return postRepo.findById(id).orElseThrow(()->new IllegalArgumentException("No Posts with given Id!!"));
		}else {
			throw new NotAllowedException("Only Original Poster can edit a post!!");
		}
		
	}

}

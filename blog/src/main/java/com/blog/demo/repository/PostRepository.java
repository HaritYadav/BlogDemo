package com.blog.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Optional<List<Post>> findByUsername(String username);


}

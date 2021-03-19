package com.exemple.testIsands.repos;

import com.exemple.testIsands.domain.Post;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

@SpringBootApplication
public interface PostRepos extends JpaRepository<Post, Integer> {
}

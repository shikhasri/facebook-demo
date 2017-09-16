package com.fb.app.repo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fb.app.model.Post;

public interface FacebookPostRepository extends CrudRepository<Post, Integer>{
	
	public Post getPostBypostid(int postid);
	@Query("Select p.postid FROM Post p")
	Iterable<Integer> findallPostId();
}

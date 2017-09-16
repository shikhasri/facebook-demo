package com.fb.app.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.fb.app.model.Comments;
import com.fb.app.model.Post;


public interface FacebookCommentRepository extends CrudRepository<Comments, Integer>{
	
	public List<Comments> getCommentBypostid(Post postid);

}

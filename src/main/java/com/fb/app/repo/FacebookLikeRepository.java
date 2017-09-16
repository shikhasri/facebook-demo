package com.fb.app.repo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fb.app.model.Likes;
import com.fb.app.model.Post;
import com.fb.app.model.User;

public interface FacebookLikeRepository extends CrudRepository<Likes, Integer>{
	@Query("FROM Likes l WHERE l.postid=:pid and l.userid = :uid")
	public Likes getLikesforuser(@Param("pid")Post postid,@Param("uid")User userid);
	
	@Query("FROM Likes l WHERE l.postid=:pid")
	public Likes getLikesforpost(@Param("pid")Post postid);
}

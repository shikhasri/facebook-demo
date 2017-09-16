package com.fb.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fb.app.model.Post;
import com.fb.app.model.User;

public interface FacebookUserRepository extends CrudRepository<User, Integer>{
	User findByemail(String username);
	@Query("FROM User u WHERE u.name = :name")
	User findByname(@Param("name")String name);
	User findByuserid(int name);
	
	@Query("Select u.name FROM User u,Likes l WHERE u.userid = l.userid and l.likeflag=1 and l.postid=:pid")
	Iterable<User> findUserdetails(@Param("pid")Post pid);
	@Query("Select u.userid FROM User u")
	Iterable<Integer> findallUserId();
}


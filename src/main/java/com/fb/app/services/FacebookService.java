package com.fb.app.services;

import java.util.List;


import com.fb.app.model.Comments;
import com.fb.app.model.Likes;
import com.fb.app.model.Post;
import com.fb.app.model.Share;
import com.fb.app.model.User;


public interface FacebookService {
	
	User saveUser(User product);
	
	 Iterable<User> listAllUsers();

	Post savePost(Post product);

	Comments saveComment(Comments product);
	
	Share saveShare(Share share);
	//void autologin(String username, String password);
	
	boolean validateUser(String userid, String password);

	List<Comments> getCommentBypostid(Post fkpost);
	
	void deleteBypkcomment(Integer pkcomment);
	
	void deleteBypostid(Integer postid);
	
	Post findPostBypostid(int postid);
	
	User findByname(String name);

	User findByemail(String email);
	
	User findByuserid(int name);

	Iterable<Post> listAllPosts();

	Likes saveLike(Likes like);
	
	Likes getLikesforuser(Post postid,User userid);
	
	Likes getLikesforpost(Post postid);
	
	Iterable<Share> findAll();

	Iterable<Share> findBysharedto(String userid);
	
	Iterable<User> findUserdetails(Post pid);
	
	Iterable<Integer> findallUserId();
	
	Iterable<Integer> findallPostId();
}

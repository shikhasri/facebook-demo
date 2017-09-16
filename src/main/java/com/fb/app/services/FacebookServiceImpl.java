package com.fb.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.fb.app.model.Comments;
import com.fb.app.model.Likes;
import com.fb.app.model.Post;
import com.fb.app.model.Share;
import com.fb.app.model.User;

import com.fb.app.repo.FacebookCommentRepository;
import com.fb.app.repo.FacebookLikeRepository;
import com.fb.app.repo.FacebookPostRepository;
import com.fb.app.repo.FacebookShareRepository;
import com.fb.app.repo.FacebookUserRepository;

@Service
public class FacebookServiceImpl implements FacebookService{
	FacebookPostRepository postrepo;
	
	 
	  
	 @Autowired
	    public void setFBRepositry1(FacebookPostRepository fbrepo) {
	        this.postrepo = fbrepo;
	    }
	
	FacebookCommentRepository commentrepo;
		
	 @Autowired
	    public void setFBRepositry2(FacebookCommentRepository fbrepo) {
	        this.commentrepo = fbrepo;
	    }
	 
	 FacebookUserRepository userrepo;
	 @Autowired
	    public void setFBRepositry3(FacebookUserRepository fbrepo) {
	        this.userrepo = fbrepo;
	    }
	 
	 FacebookShareRepository sharerepo;
	 @Autowired
	    public void setFBRepositry4(FacebookShareRepository fbrepo) {
	        this.sharerepo = fbrepo;
	    }
	
	 FacebookLikeRepository likerepo;
	 @Autowired
	    public void setFBRepositry5(FacebookLikeRepository fbrepo) {
	        this.likerepo = fbrepo;
	    }
	@Override
	public Iterable<Post> listAllPosts() {
		return postrepo.findAll();
	}


	@Override
	public Post savePost(Post product) {
		return postrepo.save(product);
	}
	
	@Override
	public Comments saveComment(Comments product) {
		return commentrepo.save(product);
	}

	@Override
	public User saveUser(User user) {
		// user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userrepo.save(user);
	}
	
	public boolean validateUser(String userid, String password) {
		User user= new User();
		user=userrepo.findByemail(userid);
		if(user==null) {
			return false;
		}
		return user.getEmail().equalsIgnoreCase(userid) && user.getPassword().equalsIgnoreCase(password);

    }

	@Override
	public List<Comments> getCommentBypostid(Post postid){
		
		return commentrepo.getCommentBypostid(postid);
	}

	@Override
	public void deleteBypkcomment(Integer pkcomment) {
		commentrepo.delete(pkcomment);
	}

	@Override
	public void deleteBypostid(Integer postid) {
		postrepo.delete(postid);
		
	}

	@Override
	public Post findPostBypostid(int postid) {
		
		return postrepo.getPostBypostid(postid);
	}

	@Override
	public Share saveShare(Share share) {
		
		return sharerepo.save(share);
	}

	@Override
	public User findByname(String name) {
		
		return userrepo.findByname(name);
	}
	@Override
	public User findByemail(String email) {
		User user= new User();
		return user=userrepo.findByemail(email);
	}

	@Override
	public User findByuserid(int userid) {
		
		return userrepo.findByuserid(userid);
	}
	@Override
	public Likes saveLike(Likes like) {
		
		return likerepo.save(like);
	}
	@Override
	public Likes getLikesforuser(Post postid,User userid) {
		
		return likerepo.getLikesforuser(postid,userid);
	}
	@Override
	public Iterable<Share> findAll() {
		
		return sharerepo.findAll();
	}
	@Override
	public Iterable<Share> findBysharedto(String userid) {
		// TODO Auto-generated method stub
		return sharerepo.findByshareto(userid);
	}
	@Override
	public Iterable<User> findUserdetails(Post pid) {
		// TODO Auto-generated method stub
		return userrepo.findUserdetails(pid);
	}
	@Override
	public Iterable<Integer> findallUserId() {
		// TODO Auto-generated method stub
		return userrepo.findallUserId();
	}

	public Iterable<Integer> findallPostId(){
		
		return postrepo.findallPostId();
	}
	@Override
	public Iterable<User> listAllUsers() {
		// TODO Auto-generated method stub
		return userrepo.findAll();
	}
	@Override
	public Likes getLikesforpost(Post postid) {
		return likerepo.getLikesforpost(postid);
	}
}

package com.fb.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Likes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public int likeid;
	
	@ManyToOne
	@JoinColumn(name = "postid")
	public Post postid;
	
    public int likeflag;

    public String liketype;
    
    public Date liketime;
    
    @ManyToOne
	@JoinColumn(name = "userid")
    public User userid;
    
    
    public Likes() {
		
	}

	public int getLikeid() {
		return likeid;
	}

	public void setLikeid(int likeid) {
		this.likeid = likeid;
	}

	public Post getPostid() {
		return postid;
	}

	public void setPostid(Post postid) {
		this.postid = postid;
	}

	public int getLikeflag() {
		return likeflag;
	}

	public void setLikeflag(int likeflag) {
		this.likeflag = likeflag;
	}

	public String getLiketype() {
		return liketype;
	}

	public void setLiketype(String liketype) {
		this.liketype = liketype;
	}
    
	
	public Date getLiketime() {
		return liketime;
	}

	public void setLiketime(Date liketime) {
		this.liketime = liketime;
	}

	
	public User getUserid() {
		return userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

	@Override
    public String toString() { 
        return "likeid: '" + this.likeid + "', like/dislike: '" + this.likeflag ;//+"', users---- '"+this.userid;
    }
}

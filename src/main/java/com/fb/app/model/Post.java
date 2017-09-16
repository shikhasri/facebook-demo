package com.fb.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public int postid;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	public User userid;
	
	
	public String posttype;
	
	
	public String postcontent;
	
	
	public int likecount;
	

	public int commentcount;
	
	
	public int sharecount;
	
	public Date posttime;
	
	@JsonIgnore
	@OneToMany(mappedBy = "postid", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<Comments> comments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "postid", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Share> shares;
	
	@JsonIgnore
	@OneToMany(mappedBy = "postid", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Likes> likes;
	
	
	
	public Post(){
		
	}
	
	public int getPostid() {
		return postid;
	}
	public void setPostid(int pkpost) {
		this.postid = pkpost;
	}
	
	public String getType() {
		return posttype;
	}
	public void setType(String type) {
		this.posttype = type;
	}
	
	public String getContent() {
		return postcontent;
	}
	public void setContent(String content) {
		this.postcontent = content;
	}
	
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public int getCommentcount() {
		return commentcount;
	}
	public void setCommentcount(int commentcount) {
		this.commentcount = commentcount;
	}
	public int getSharecount() {
		return sharecount;
	}
	public void setSharecount(int sharecount) {
		this.sharecount = sharecount;
	}
	
	public User getUser() {
		return userid;
	}
	public void setUser(User user) {
		this.userid = user;
	}
		
	
	public Date getPosttime() {
		return posttime;
	}
	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}
	
	public Set<Comments> getComments() {
		return comments;
	}
	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}
	public Set<Share> getShares() {
		return shares;
	}
	public void setShares(Set<Share> shares) {
		this.shares = shares;
	}
	public Set<Likes> getLikes() {
		return likes;
	}
	public void setLikes(Set<Likes> likes) {
		this.likes = likes;
	}
	
	
	public int increaseLikeCount(Post post) {
		
		return post.getLikecount()+1;
	}
	/*@Override
    public String toString() { 
        return "id: '" + this.postid + "', pcontent: '" + 
    this.postcontent + "', ptype: '" + this.posttype +"', comment count: '"+this.commentcount
    + "', sharecount: '" + this.sharecount +"', like count: '"+this.likecount+"' ,user:'"+this.userid;
    } */
}

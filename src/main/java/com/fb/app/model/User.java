package com.fb.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

/*@NamedQuery(name = "User.findByname", query= "SELECT u.userid FROM User u WHERE LOWER(u.name) like LOWER('%?1%')")*/
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public int userid;
	
	@NotNull
    public String name;
	
	@NotNull @Column(unique = true)
    public String email;
	@NotNull @Size(min=4, max=10)
    public String password;

	/*
	 * @JsonIgnore
	 * @OneToMany(mappedBy = "userid", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Post> posts;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "sharedby", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Share> postshared;
	
	@JsonIgnore
	@OneToMany(mappedBy = "userid", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Likes> postliked;
	
	@JsonIgnore
	@OneToMany(mappedBy = "commentedby", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	public Set<Comments> comments;*/
	
  
	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userpk) {
		this.userid = userpk;
	}

		
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
/*
	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	
	
	public Set<Share> getPostshared() {
		return postshared;
	}

	public void setPostshared(Set<Share> postshared) {
		this.postshared = postshared;
	}

	
	public Set<Likes> getPostliked() {
		return postliked;
	}

	public void setPostliked(Set<Likes> postliked) {
		this.postliked = postliked;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}*/

	@Override
    public String toString() { 
        return "Name: '" + this.name + "', email: '" + 
    this.email + "', password: '" + this.password+"', user id--- '"+this.userid;
    } 
    
}

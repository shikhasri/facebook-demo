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
@Table(name = "comments")
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int commentid;
	
	@ManyToOne
	@JoinColumn(name = "postid")
	public Post postid;
	public String commentcontent;
	@ManyToOne
	@JoinColumn(name = "commentedby")
	public User commentedby;
	public String commenttype;
	public Date commenttime;
	
	
	public Comments() {
		
	}
	public int getPkcomment() {
		return commentid;
	}
	public void setPkcomment(int pkcomment) {
		this.commentid = pkcomment;
	}
	
	
	public Post getPostid() {
		return postid;
	}
	
	 
	public void setPostid(Post fkpost) {
		this.postid = fkpost;
	}
	
	public String getCommentcontent() {
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	public User getCommentedby() {
		return commentedby;
	}
	public void setCommentedby(User commentedby) {
		this.commentedby = commentedby;
	}
	public String getCommenttype() {
		return commenttype;
	}
	public void setCommenttype(String commenttype) {
		this.commenttype = commenttype;
	}
	
		
	public Date getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}
	/*@Override
    public String toString() { 
        return "id: '" + this.commentid + "', comcontent: '" + 
    this.commentcontent + "', pid: '" + this.postid ;
    } 
*/
}

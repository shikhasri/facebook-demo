package com.fb.app.model;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name = "share")
public class Share {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public int shareid;
	

	@ManyToOne
	@JoinColumn(name = "postid")
	public Post postid;
	
	public String aboutshare;
    
	@ManyToOne
	@JoinColumn(name = "sharedby")
    public User sharedby;
    
    public String shareto;
    
    public Date sharetime;
    
    public Share() {
		
	}
	public int getShareid() {
		return shareid;
	}
	public void setShareid(int shareid) {
		this.shareid = shareid;
	}

	public Post getPostid() {
		return postid;
	}
	public void setPostid(Post postid) {
		this.postid = postid;
	}
	public String getAboutshare() {
		return aboutshare;
	}
	public void setAboutshare(String aboutshare) {
		this.aboutshare = aboutshare;
	}
		
	public User getSharedby() {
		return sharedby;
	}
	public void setSharedby(User sharedby) {
		this.sharedby = sharedby;
	}
	
	public String getShareto() {
		return shareto;
	}
	
	public void setShareto(String shareto) {
		this.shareto = shareto;
	}
	public Date getSharetime() {
		return sharetime;
	}
	public void setSharetime(Date sharetime) {
		this.sharetime = sharetime;
	}
    
	/*@Override
    public String toString() { 
        return "share id: '" + this.shareid + "', about: '" + 
    this.aboutshare + "', time: '" + this.sharetime +"', post id: '" + this.postid;
    } */
}

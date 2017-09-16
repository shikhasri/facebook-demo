package com.fb.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;


import com.fb.app.model.Comments;
import com.fb.app.model.Likes;
import com.fb.app.model.Login;
import com.fb.app.model.Post;
import com.fb.app.model.Share;
import com.fb.app.model.User;
import com.fb.app.services.FacebookService;


@RequestMapping("api")
@RestController
public class FacebookRestController {
	
	FacebookService serv;
	FacebookController fbc = new FacebookController();
	Logger logger = Logger.getLogger(FacebookRestController.class.getName());
	
	 @Autowired
	    public void setFBService(FacebookService fbService) {
	        this.serv = fbService;
	    }
	 
	 
	 
	 @RequestMapping(value="/save", method = RequestMethod.POST,consumes="application/json")
	    public JSONObject submitForm(@RequestBody String userdata){
		 		JSONObject obj = new JSONObject();			
		 	try {
				
		 		obj=new JSONObject(userdata);
				User user = new User();
				user.setName(obj.getString("name"));
				user.setEmail(obj.getString("email"));
				user.setPassword(obj.getString("password"));
				serv.saveUser(user);
			 	
				/*HashMap map = new HashMap<>();
				if(serv.findByemail(user.getEmail())!=null) {
					 map.put("err_msg", "This e-mail is duplicate.");
				}
				else {
					serv.saveUser(user);
					map.put("succ_msg", "Form submitted successfully.");
				}
				return map;*/
		 		
				
			} catch (JSONException e) {
				
				e.printStackTrace();
			}
		 	return obj;
		 	
	     }
	 
	 
	 @RequestMapping(value="/validatelogin", method = RequestMethod.POST,consumes="application/json")

	    public Map showWelcomePage(@RequestBody Login data){
		 	HashMap map = new HashMap<>();
		 	
	        boolean isValidUser = serv.validateUser(data.getEmail(),data.getPassword());

	        if (!isValidUser) {
	        	 System.out.println("fail");
	        	 map.put("errorMessage", "InvalidCredentials");
	             return map;

	        }
	        else {
	        	System.out.println("true");
	        	User user= new User();
	        	user= serv.findByemail(data.getEmail());
	        	map.put("errorMessage","success");
	        	map.put("username", user.getName());
	        	map.put("userid", user.getUserid());
	        	map.put("sharedpoststome", serv.findBysharedto(String.valueOf(user.getUserid())));
	        	map.put("posts", serv.listAllPosts());
	        	return map;
	        }

	    }
	 
	
	/*@RequestMapping(value = "/submitpost", method = RequestMethod.POST)
	public String submitPost(@RequestParam String postdata,@RequestParam String userid){
		Post post = new Post();
		post.setContent(postdata.trim());
		User user = new User(); 
		Iterable<Integer> userids =serv.findallUserId();
		
		
		user=serv.findByuserid(Integer.parseInt(userid));
		post.setUser(user);
		post.setPosttime(new Date());
		System.out.println("Post Created------------------ "+serv.savePost(post));
		serv.savePost(post);
		for(Integer it :userids) {
			Likes likes = new Likes();
			User user1 = serv.findByuserid(it);
			likes.setUserid(user1);
			likes.setLikeflag(0);
			likes.setPostid(post);
			serv.saveLike(likes);
			System.out.println(it);
			
		}
		
		return post.getPostid()+","+user.getName();
	}*/
	
	 @RequestMapping(value = "/submitpost", method = RequestMethod.POST,consumes="application/json")
		public Post submitPost(@RequestBody String postcontent){
		 System.out.println("data received from client--- "+postcontent);
		 Post post = new Post();
		 post.setContent(postcontent.trim());
		 post.setPosttime(new Date());
		 serv.savePost(post);
		 
		 
		 Likes likes = new Likes();
		 likes.setLikeflag(0);
		 likes.setPostid(post);
		 serv.saveLike(likes);
		 
		 return post;
		}
	 
	 
	 @RequestMapping("/getallpost")
		public Iterable<Post> getPosts(){
		 System.out.println(serv.listAllPosts());
		 return serv.listAllPosts();
		}
	 
	 @RequestMapping("/allresult")
	    public Iterable<User> getForm(){
			
			System.out.println(serv.listAllUsers());
	        return serv.listAllUsers();
	        
	    }
	 
	/*@RequestMapping(value = "/likepost", method = RequestMethod.POST)
	
    public String likePost(@RequestParam String postpk,@RequestParam String userid){
		String likeflag = null;
		Post post = new Post();
		post=serv.findPostBypostid(Integer.parseInt(postpk));
		Likes like = new Likes();
		Likes like2 = new Likes();
		User user = new User();
		user=serv.findByuserid(Integer.parseInt(userid));
		like2= serv.getLikesforuser(post,user);
		
		if(like2==null) {
			System.out.println("like2---------------------  null");
			post.setLikecount(post.getLikecount()+1); 
			like.setLikeflag(1);
			likeflag="L";
		}
		else {
			like.setLikeid(like2.getLikeid());
			System.out.println("like2---------------------    not null");
			if(like2.getLikeflag()==1) {
				likeflag="D";
				like.setLikeflag(0);
				post.setLikecount(post.getLikecount()-1);
			}
			else {
				likeflag="L";
				like.setLikeflag(1);
				post.setLikecount(post.increaseLikeCount(post));
			}
			
		}
		
		like.setPostid(post);
		like.setLiketime(new Date());
		
		like.setUserid(user);
		System.out.println("like saved------------------ "+serv.saveLike(like));
		serv.saveLike(like);
		serv.savePost(post);
		return post.getLikecount()+","+likeflag;
    }
	*/
	 
	 
	 
	 
	 @RequestMapping(value = "/likepost", method = RequestMethod.POST,consumes="application/json")
		
	    public int likePost(@RequestBody Integer postid){
		    Post post = new Post();
			post=serv.findPostBypostid(postid);
			String likeflag = null;
			Likes like = new Likes();
			like= serv.getLikesforpost(post);
			
			if(like.getLikeflag()==1) {
				likeflag="D";
				like.setLikeflag(0);
				post.setLikecount(post.getLikecount()-1);
			}
			else {
				likeflag="L";
				like.setLikeflag(1);
				post.setLikecount(post.getLikecount()+1);
			}
			serv.saveLike(like);
			serv.savePost(post);
			return post.getLikecount();
	    }
		
	 @RequestMapping(value = "/submitcomment", method = RequestMethod.POST,consumes="application/json")
		
	    public Integer commentPost(@RequestBody String data){
		 Post post = new Post();
			try {
				
				JSONObject obj = new JSONObject(data);
				post=serv.findPostBypostid(Integer.parseInt(obj.get("postid").toString()));
				post.setCommentcount(post.getCommentcount()+1);
				serv.savePost(post);
				Comments coment = new Comments();
				coment.setCommentcontent(obj.get("comentval").toString());
				coment.setPostid(post);
				coment.setCommenttime(new Date());
				//coment.setCommentedby(user);
				serv.saveComment(coment);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return post.getCommentcount();
	    }
	 
	
	/*@RequestMapping(value = "/submitcomment", method = RequestMethod.POST)
	
    public String commentPost(@RequestParam String postpk,@RequestParam String postcomment,@RequestParam String userid){
		Comments coment = new Comments();
		Post post = new Post();
		User user = new User();
		user=serv.findByuserid(Integer.parseInt(userid));
		post=serv.findPostBypostid(Integer.parseInt(postpk));
		post.setCommentcount(post.getCommentcount()+1);
		serv.savePost(post);
		coment.setCommentcontent(postcomment);
		coment.setPostid(post);
		coment.setCommenttime(new Date());
		coment.setCommentedby(user);
		serv.saveComment(coment);
		System.out.println("Commented------------------ "+serv.saveComment(coment));
		return coment.getPkcomment()+","+post.getCommentcount()+","+user.getName();
    }*/

	/*@RequestMapping(value = "/fetchcomments", method = RequestMethod.POST)
	
	public List getPostComments(@RequestParam String postpk){
		
		Post post = new Post();
		post=serv.findPostBypostid(Integer.parseInt(postpk));
		
		System.out.println("Comments fetched------------------ ");
		
		return serv.getCommentBypostid(post);
	}*/

	@RequestMapping(value = "/deletecomment", method = RequestMethod.POST)
	
	public void delComments(@RequestParam String pkcomment){
		serv.deleteBypkcomment(Integer.parseInt(pkcomment));
		System.out.println("Comment deleted------------------ ");
		
	}
	@RequestMapping(value = "/deletepost", method = RequestMethod.POST)
	
	public void delPost(@RequestParam String postid){
		serv.deleteBypostid(Integer.parseInt(postid));
		System.out.println("post deleted------------------ ");
		
	}
	
	
	@RequestMapping(value = "/sharepost", method = RequestMethod.POST,consumes="application/json")
	public int sharePost(@RequestBody String postid){
		System.out.println("dta   "+postid);
		Post post= new Post();
		try {
			JSONObject obj = new JSONObject(postid);	
			System.out.println(obj);
			post=serv.findPostBypostid(Integer.parseInt(obj.getString("postid")));
			post.setSharecount(post.getSharecount()+1);
			serv.savePost(post);
		}catch(Exception e) {
		  e.getMessage();	
		}
		return post.getSharecount();
	}
	
	
	
	
	
	
	
	/*@RequestMapping(value = "/sharepost", method = RequestMethod.POST)
	
	public Map sharePost(@RequestParam String postid,@RequestParam String aboutpost,
			 @RequestParam String sharedby,@RequestParam String shareto){
		List usernotFound=new ArrayList();
		User sharedbyuser = new User();
		sharedbyuser=serv.findByuserid(Integer.parseInt(sharedby));
		List items = new ArrayList<String>();
		HashMap map = new HashMap<>();
		Post post = new Post();
		if(shareto.contains(",")) {
			items = Arrays.asList(shareto.split("\\s*,\\s*"));
			for(int i=0;i<items.size();i++) {
				System.out.println("for---- "+items.get(i));
				User user = new User();
				//Post post = new Post();
				Share share = new Share();
				user=serv.findByname((String) items.get(i));
				post=serv.findPostBypostid(Integer.parseInt(postid));
				if(user==null)
				{
					System.out.println("not found---- "+items.get(i));
					usernotFound.add(items.get(i));
					//return "No such user found "+usernotFound;
				}
				else {
					
					System.out.println("found---- "+items.get(i));
					
					post.setSharecount(post.getSharecount()+1);		
					share.setPostid(post);
					share.setAboutshare(aboutpost);
					share.setSharedby(sharedbyuser);
					share.setSharetime(new Date());
					share.setShareto(String.valueOf(user.getUserid()));
					serv.saveShare(share);
					
				}
				
			}
			serv.savePost(post);
			
		}
		else {
			
			Share share = new Share();
			User user = new User();
			post=serv.findPostBypostid(Integer.parseInt(postid));
			user=serv.findByname(shareto);
			if(user==null)
			{
				System.out.println(shareto+" not found");
				//return shareto+" not found";
				usernotFound.add(shareto);
			}
			else {
				
				
				post.setSharecount(post.getSharecount()+1);		
				share.setPostid(post);
				share.setAboutshare(aboutpost);
				share.setSharedby(sharedbyuser);
				share.setSharetime(new Date());
				share.setShareto(String.valueOf(user.getUserid()));
				serv.saveShare(share);
				serv.savePost(post);
			}
		
		}
		map.put("sharecount", post.getSharecount());
		map.put("usernotFound", usernotFound);
		System.out.println("post shared------------------ ");
		return map;
	}*/
	
	@RequestMapping("/getcomments")
	public Iterable<Comments> getPostComments(@RequestBody String postid){
		Post post = new Post();
		post=serv.findPostBypostid(Integer.parseInt(postid));
		return post.getComments();
	}
	
	@RequestMapping(value = "/fetchpplliked", method = RequestMethod.POST)
	public Iterable<User> getPostLikes(@RequestParam String postid){
		Post post = new Post();
		post=serv.findPostBypostid(Integer.parseInt(postid));
		Iterable<User> user=serv.findUserdetails(post);
		
		System.out.println(serv.findUserdetails(post));
		return serv.findUserdetails(post);
		
	}
	
	
	
	
	/*@RequestMapping("/test")
	public String getTest(){
		try {
			
			String post = new String("p1");
			String post1 = new String("p1");
			
			System.out.println("post.equals(post1)====   "+post.equals(post1));
			System.out.println("post==post1====   "+post==post1);
			
			System.out.println("---------new ends------------");
			System.out.println("---------without new starts-----------");
			String s="p1";
			String s1="p1";
			
			System.out.println("s.equals(s1)===   "+s.equals(s1));
			System.out.println("s==s1===   "+s==s1);
			
			
			common factors loPostLikesgic
			int p = 72,q = 56;
			 List cmnfacts = new ArrayList();
			int[] fac = null;
			for(int i=2;i<=p/2 && i<=q/2;i++) {
				 
				if(p%i==0 && q%i==0) {
					
					cmnfacts.add(i);
				}
			}
			System.out.print("common factors- ");
			for(int i=0;i<cmnfacts.size();i++) {
				System.out.print(i+ ",");
			}			
			reverse dictionary order logic
			System.out.println("");
			String reverse="I live in chhatarpur and Iam Shikha";
			System.out.println("statement---- "+reverse);
			reverse = reverse + " ";
			int l=reverse.length();
			int wordscount=0;
			     
			for(int i=0;i<l;i++)
				{
			       char ch=reverse.charAt(i);
			       if(ch==' ')
			          {
			    	   wordscount++;
			           
			          }
				}
			System.out.println("Total words---- "+wordscount);
			
			String[] words= new String[wordscount];
	        int startIndex = 0;
	        int currentWord = 0;
	        for (int i = 0; i < reverse.length(); i++) {
	            char ch = reverse.charAt(i);
	            if (ch == ' ') {
	                String word1 = reverse.substring(startIndex, i);
	                words[currentWord] = word1.toLowerCase();
	                currentWord++;
	                startIndex = i + 1;
	               
	            }
	        }
	        
	        // Sort words
	        for (int i = 0; i < words.length - 1; i++) {
	            for (int j = 0; j < words.length - i - 1; j++) {
	                if (words[j].compareTo(words[j + 1]) < 0) {
	                    String temp = words[j];
	                    words[j] = words[j + 1];
	                    words[j + 1] = temp;
	                }
	            }
	        }
	        // Print words
	        for (int i = 0; i < words.length; i++) {
	            System.out.print(words[i] + " ");
	        }
	        
	         Picking out closest key of value from Map logic
	        System.out.println("");
	        HashMap<String,Integer> map = new HashMap();
	        map.put("emp1", 45);
	        map.put("emp2",12);
	        map.put("emp3",20);
	        map.put("emp4", 56);
	        map.put("emp5",22);
	        map.put("emp6",23);
	        System.out.println(getKeyFromValue(map,22));
	        
	        
		}
		catch(Exception e) {
			System.out.println("in exception  "+e.getMessage());
		}
		
		return "success";
		
		
	}
	
	
	 public List getKeyFromValue(Map<String,Integer> map, Integer value) {
		
		 List bestKey = new ArrayList();
		 Integer mindistance=2;

		 for (Map.Entry<String,Integer> entry : map.entrySet()) {
			 
			 
			 
			 
			 while(entry.getValue()==value) {
				 bestKey.add(entry.getKey());
			    	return bestKey;
				}
			 
			Integer distance = Math.abs(entry.getValue() - value);
			   if(distance <=mindistance) {
				    bestKey.add(entry.getKey());
			    }
			}
		    return bestKey;
	 }*/
	
}

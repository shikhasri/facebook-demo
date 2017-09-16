package com.fb.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fb.app.model.Likes;
import com.fb.app.model.Post;
import com.fb.app.model.Share;
import com.fb.app.model.User;
import com.fb.app.services.FacebookService;

@Controller

public class FacebookController {

	FacebookService serv;
	
	 @Autowired
	    public void setFBService(FacebookService fbService) {
	        this.serv = fbService;
	    }
	@RequestMapping("/")
    String index(){
		
        return "Welcome";
    }

	
	/*@RequestMapping(value = "/Post", method = RequestMethod.GET)
    public String viewAndlist(Model model){
		System.out.println("in controller view");
        model.addAttribute("posts", serv.listAllPosts());
        System.out.println(serv.listAllPosts());
        System.out.println("Returning all posts:");
        return "Post";
    }*/
	
	@RequestMapping(value = "/registration")
	public String showRegistrationForm(Model model) {
	    User userDto = new User();
	    model.addAttribute("user", userDto);
	    return "registration";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveRegistration(User user,ModelMap model) {
		//System.out.println("here----------------"+bindingResult.hasErrors());
		String name= user.getName();
		
		String passowrd= user.getPassword();
		String email= user.getEmail();
		User uemail=serv.findByemail(email);
		if(name==null || name=="") {
			model.put("nameError", "Name can't be empty");
			return "registration";		
			
		}
		if(passowrd==null || passowrd=="") {
			model.put("passwordError", "Password can't be empty");
			return "registration";
		}
		if(email==null || email=="") {
			model.put("emailError", "Email can't be empty");
			return "registration";
		}
		if(uemail!=null) {
		if(email==uemail.getEmail()) {
			model.put("emailDuplicate", "This e-mail already exists");
			return "registration";
		}
		}
		serv.saveUser(user);
		Iterable<Integer> postids =serv.findallPostId();
		for(Integer it :postids) {
			Likes likes = new Likes();
			Post post1 = serv.findPostBypostid(it);
			likes.setUserid(user);
			likes.setLikeflag(0);
			likes.setPostid(post1);
			serv.saveLike(likes);
			System.out.println(it);
			
		}
		
		System.out.println("user saved---------------");
	        return "redirect:/login";
		    
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
	    return "login";
	}
		
	
	
   /* @RequestMapping(value="/validatelogin", method = RequestMethod.POST)

    public String showWelcomePage(ModelMap model, @RequestParam String email, @RequestParam String password){
    	
        boolean isValidUser = serv.validateUser(email, password);

        if (!isValidUser) {

            model.put("errorMessage", "InvalidCredentials");
            System.out.println("fail");
            return "login";

        }
        else {
        	System.out.println("true");
        	User user= new User();
        	user= serv.findByemail(email);
        	model.put("fname", user.getName());
        	model.put("userid", user.getUserid());
        	
        	model.put("sharedpoststome", serv.findBysharedto(String.valueOf(user.getUserid())));
        	model.addAttribute("posts", serv.listAllPosts());
        	return "Post";
        }

    }*/
    
    /*@RequestMapping("fetchpplliked/{postid}")
    public String showProduct(@PathVariable Integer postid, Model model){
    	
    	Post post = new Post();
		post=serv.findPostBypostid(postid);
        model.addAttribute("userdata", serv.findUserdetails(post));
        return "redirect:/validatelogin";//+post.getPostid();
    }*/
	
 
}

package com.rhcheng.twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rhcheng.redis.JedisTemplate;
import com.rhcheng.twitter.entity.User;

@Controller("twitterAction")
@RequestMapping(value="/twitter/")
public class TwitterAction {
	@Resource
	private ITwitterService twitterService;
	@Autowired
	private JedisTemplate jt;
	
	
	@RequestMapping(value="login")
	public String login(){
		return "twitter/login";
	}
	
	@RequestMapping(value="dologin")
	public String doLogin(User user,Model model){
		jt.getJedis().sadd("userOnLinelist", String.valueOf(user.getUserid()));
		List<User> onuser = getAllUserOnline();
		model.addAttribute("users", onuser);
		return "twitter/index";
	}
	
	@RequestMapping(value="doAddUser")
	@ResponseBody
	public int doAddUser(Model model){
		jt.incr("userid");
		int userid = jt.getAsInt("userid")+100;
		jt.hset("user:"+userid, "userid", String.valueOf(userid));
		jt.hset("user:"+userid, "name", "用户"+userid);
		return userid;
	}
	
	@RequestMapping(value="loginout")
	public String loginout(User user,Model model){
		jt.getJedis().srem("userOnLinelist", String.valueOf(user.getUserid()));
		return "twitter/login";
	}

	
	
	//-----------------------------
	public List<User> getAllUserOnline(){
		Set<String> userids =  jt.smembers("userOnLinelist");
		List<User> userOnline = new ArrayList<User>();
		for(String id:userids){
			User u = new User();
			u.setUserid(Integer.valueOf(id));
			u.setName(jt.hget("user:"+id, "name"));
			userOnline.add(u);
		}
		return userOnline;
	}
	
	
	
	@RequestMapping(value="index")
	public String index(){
		
		return "twitter/index";
	}
	
	
	
	
}

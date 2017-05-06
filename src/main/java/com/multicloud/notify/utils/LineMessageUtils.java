package com.multicloud.notify.utils;

import java.io.IOException;
import java.util.ArrayList;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.multicloud.notify.model.LinePush;
import com.multicloud.notify.model.LinePush.Message;

public class LineMessageUtils {

	
	public static void main(String[] args){
		try {
			Message m=new Message("text","test");
			m.text="hello yyy";
			LineMessageUtils.push(null, "Uf1e8cceebfe93c774ac77eb03f6465e0", m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//public static String mtoken="iklLqLaE+0fia7Rll+TG/yZpswO8MOBp6HUWMY/tswT2qBEEhW1TGKP8acPwUaj4qnLKvKvFBt5tSyoQIRSEBHFU9j4RNctQxHkdJIkRh4QI5vrjiyBINKgnuCkNDLxTf4+vYJXjH/4zDzVBZW5JRQdB04t89/1O/w1cDnyilFU=";
	
	
	public static void push(String token, String to, LinePush.Message... messages) throws IOException {
		String url="https://api.line.me/v2/bot/message/push";
		LinePush push = new LinePush();
		push.to = to;
		push.messages = new ArrayList<LinePush.Message>();
		
		for (LinePush.Message m : messages) {		
			push.messages.add(m); // copy array elements into list 
		}
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "Bearer " + token);
		headers.add("Accept", "application/json");     
		headers.add("Content-type", "application/json");     

		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity response = restTemplate.postForEntity( url, request, LinePush.class );
		//post.setHeader("Authorization", "Bearer " + token);
		
		
	
		String json = JsonUtils.toJson(push);
	    //StringEntity entity = new StringEntity(json);
	    //post.setEntity(entity);
	    //post.setHeader("Accept", "application/json");
	    //post.setHeader("Content-type", "application/json");
	 
	   // HttpResponse response = client.execute(post);
	    
		//System.out.println(response.getStatusLine().getStatusCode());
		
    }
}

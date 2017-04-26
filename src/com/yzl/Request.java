package com.yzl;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author  응택림 20178040
 * @version  create time：26 April 2017
 * class explain
 * The browser sends the request information into characters and intercept URL
 * 
 */
public class Request {
	
	//inputstream
	private InputStream input;
	//get url,for example,http://localhost:8080/index.html ，get url: /index.html
	private String uri;
	public Request(InputStream inputStream){
		this.input = inputStream;
	}
	
	public InputStream getInput() {
		return input;
	}
	public void setInput(InputStream input) {
		this.input = input;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	// get Character information from socket
	public void parse(){
		
			StringBuffer request = new StringBuffer(2048);
			int i = 0;
			byte[] buffer = new byte[2048];
			
			try {
				i = input.read(buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				i = -1;
			}
			for(int j = 0;j<i;j++){
					request.append((char)(buffer[j]));
			}
			System.out.println(request.toString());
			uri = parseUri(request.toString());
			}
	//get request URL
	private String parseUri(String requestString){
		
		int index1 = 0;
		int index2 = 0;
		index1 = requestString.indexOf(' ');
		if(index1!=-1){
			index2 = requestString.indexOf(' ',index1+1);
			if(index2>index1){
				return requestString.substring(index1+1,index2);
			}
		}
		
		return null;
	}
			
			
			
		
	}
	
	



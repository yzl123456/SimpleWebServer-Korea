package com.yzl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author  응택림 20178040
 * @version  create time：26 April 2017
 * class explain
 * According to the corresponding information to return results
 * 
 */
public class Response {
	
	private static final int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;
	public Response(OutputStream output){
		this.output = output;
	}
	
	public void sendStaticResource() throws IOException{
		
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		
		File file = new File(HttpServer.WEB_ROOT,request.getUri());
		if(file.exists()){
			try {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes,0,BUFFER_SIZE);
				while(ch != -1){
					output.write(bytes,0,ch);
					ch = fis.read(bytes,0,BUFFER_SIZE);
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if(fis !=null){
					fis.close();
				}
			}
			
		}else{
			//not found
			  String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
	          "Content-Type: text/html\r\n" +
	          "Content-Length: 23\r\n" +
	          "\r\n" +
	          "<h1>404 Not Found</h1>";
			  try {
				output.write(errorMessage.getBytes());
				output.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public OutputStream getOutput() {
		return output;
	}
	public void setOutput(OutputStream output) {
		this.output = output;
	}
	public static int getBUFFER_SIZE() {
		return BUFFER_SIZE;
	}
	
	

}

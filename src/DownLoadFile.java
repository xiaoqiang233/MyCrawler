
import java.io.*;
import java.io.IOException;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.CloseableHttpResponse;


public class DownLoadFile {

	public String getFileNameByUrl(String url,String contentType)
	{
		//移除http://
		
		url = url.substring(7);
		
		//text/html类型
		
		if(contentType.indexOf("html")!=-1)
		{
			 // String regex = "a+";  
		     //   Pattern pattern = Pattern.compile(regex);  
		    
		     //Matcher matcher = pattern.matcher("okaaaa LetmeAseeaaa aa booa");  
		     //   String s = matcher.replaceAll("A");  
		     //   System.out.println(s);  
		        
		    String regex = "[\\?/:*|<>\"]";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(url);
		    
		    url = matcher.replaceAll("_");
		    
		//	url = url.replace("[\\?/:*|<>\"]", "_");
			url = url+".html";
			return url;
		}
		else //如application/pdf类型
		{
		    String regex = "[\\?/:*|<>\"]";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(url);
		    
		    url = matcher.replaceAll("_");
		    
//			url = url.replace("[\\?/:*|<>\"]", "_")+"."+contentType.substring(contentType.lastIndexOf("/"+1));
		    url = url + "."+contentType.substring(contentType.lastIndexOf("/"+1));
			return url;
		}
	}
	
	/**
	 * 保存网页字节数组到本地文件，filepath 为要保存的文件相对地址
	 */
	
	private void saveToLocal(byte[] data,String filepath)
	{
		try{
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filepath)));
			
			for(int i=0;i<data.length;i++)
			{
				out.write(data[i]);
			}
			out.flush();
			out.close();
			
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//下载URL指向的网页
	
	public String downloadFIle(String url) throws Exception
	{
		   CloseableHttpClient httpclient = HttpClients.createDefault();
		   String filepath = null;
	        try {
	            HttpGet httpget = new HttpGet("http://httpbin.org/");

	            System.out.println("Executing request " + httpget.getRequestLine());

	            // Create a custom response handler
	            
	            CloseableHttpResponse response1 = httpclient.execute(httpget);
	            if(response1.getStatusLine().getStatusCode() != 200)
	            {
	            	System.out.println("Method failed:"+response1.getStatusLine().getStatusCode());
	            	filepath=null;
	            }
	            
	            byte[] responseBody = EntityUtils.toByteArray(response1.getEntity());
	            
	            filepath = "temp/"+getFileNameByUrl(url,response1.getEntity().getContentType().getValue());
	            
	            saveToLocal(responseBody,filepath);
	        }	catch (Exception e)
	        {
	        	System.out.println("Please check your provided http address");
	        	e.printStackTrace();
	        }
	        finally {
	            httpclient.close();
	        }
	        
	        return filepath;
	}
	
	
}

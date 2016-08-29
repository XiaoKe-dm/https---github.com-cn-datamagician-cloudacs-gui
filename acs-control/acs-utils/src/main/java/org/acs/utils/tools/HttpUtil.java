package org.acs.utils.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpUtil {

	public static final String SUCCESS = "success";
	public static final String UNSUCCESS = "unsuccess";
	public static final String ERROR = "error";
	public static final String QUEYED = "queued";
	public static final String RESULT = "result";
	public static final String TOTAL = "total";
	public static String getContentByGet(String urlStr) {
		HttpURLConnection httpConn = null;
		try {// 获取HttpURLConnection连接对象
			System.out.println("URLString==="+urlStr);
			URL url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(5000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				return ConvertStream2Json(httpConn.getInputStream());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {         
		    if (httpConn != null) {
		    	httpConn.disconnect();
		    }
		}
		return null;
	}
	public static Map<String,Object> getContentByGetOnPage(String urlStr) {
		HttpURLConnection httpConn = null;
		try {// 获取HttpURLConnection连接对象
			System.out.println("URLString==="+urlStr);
			URL url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setConnectTimeout(5000);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("GET");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				Map<String, List<String>> headerMap = httpConn.getHeaderFields();
				List<String> totalList = headerMap.get("total");
				long totalCount = 0;
				if(totalList.size()>0)totalCount = Long.parseLong(totalList.get(0));
				Map<String,Object> resultMap = new HashMap<String,Object>();
				resultMap.put(TOTAL, totalCount);
				resultMap.put(RESULT, ConvertStream2Json(httpConn.getInputStream()));
				return resultMap;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {         
		    if (httpConn != null) {
		    	httpConn.disconnect();
		    }
		}
		return null;
	}
	public static String getContentByPost(String urlStr,String params) {
		HttpURLConnection httpConn = null;
		
		try {// 获取HttpURLConnection连接对象
			URL url = new URL(urlStr);
			System.out.println(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出   
			httpConn.setDoInput(true);// 使用 URL 连接进行输入   
			httpConn.setUseCaches(false);// 忽略缓存   
			httpConn.setRequestMethod("POST");// 设置URL请求方法   
			
			
			httpConn.setRequestProperty("accept", "*/*");  
			httpConn.setRequestProperty("connection", "Keep-Alive");  
			if(params!=null)httpConn.setRequestProperty("Content-Length", String.valueOf(params.length()));  
			//httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
			httpConn.connect();  
			
			if(params!=null){
				System.out.println("post params:"+params);
				DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());  
				out.writeBytes(params);
				out.flush();  
				out.close(); 
			}
			
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			System.out.println(respCode);
			if (respCode == 200) {
				return SUCCESS;
			}else if(respCode == 202){
				return QUEYED;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {         
		    if (httpConn != null) {
		    	httpConn.disconnect();
		    }
		}
		return null;
	}
	public static String getContentByDelete(String urlStr) {
		HttpURLConnection httpConn = null;
		try {
			URL url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setRequestProperty("Content-Type",
	                "application/x-www-form-urlencoded");
			httpConn.setRequestMethod("DELETE");
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			System.out.println("URL:"+urlStr);
			System.out.println(respCode);
			if (respCode == 200) {
				return SUCCESS;
			}else if(respCode == 202){
				return UNSUCCESS;
			}else{
				return ERROR;
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {         
		    if (httpConn != null) {
		    	httpConn.disconnect();
		    }
		}
		return null;
	}
	public static String getContentByPut(String urlStr,String params){ 
		HttpURLConnection httpConn = null;
		try {
	        URL url = new URL(urlStr);  
	        httpConn = (HttpURLConnection)url.openConnection();  
	        httpConn.setRequestMethod("PUT");  
	        httpConn.setDoInput(true);  
	        httpConn.setDoOutput(true);  
	        System.out.println("URL:"+urlStr);
	        httpConn.connect();  
			if(params!=null){
				System.out.println("put params:"+params);
				DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());  
				out.writeBytes(params);
				out.flush();  
				out.close(); 
			}
			
			// 获取相应码
			int respCode = httpConn.getResponseCode();
			System.out.println(respCode);
			if (respCode == 200) {
				return SUCCESS;
			}else if(respCode == 202){
				return QUEYED;
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {         
		    if (httpConn != null) {
		    	httpConn.disconnect();
		    }
		}
        return null;          
    }
	private static String ConvertStream2Json(InputStream inputStream) {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		try {
			while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, len);
			}
			// 将内存流转换为字符串
			jsonStr = new String(out.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonStr;
	}
	public static String getContentByFileUpload(String urlStr,File file,String fileType,String oui,String productClass,String version){
		int respCode = 500;
		HttpURLConnection httpConn = null;
		try {
			System.out.println("url:"+urlStr+"fileName:"+file.getName());
			URL url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();  
			httpConn.setDoOutput(true);  
			httpConn.setDoInput(true);
			httpConn.setChunkedStreamingMode(1024*1024);   
			httpConn.setRequestMethod("PUT");
			httpConn.setRequestProperty("connection", "Keep-Alive");
			httpConn.setRequestProperty("Charsert", "UTF-8");  
			httpConn.setRequestProperty("Content-Type","multipart/form-data;file="+file.getName());  
			httpConn.setRequestProperty("filename",file.getName());
			httpConn.setRequestProperty("fileType", fileType);
			httpConn.setRequestProperty("oui", oui);
			httpConn.setRequestProperty("productClass", productClass);
			httpConn.setRequestProperty("version", version);
			OutputStream out = new DataOutputStream(httpConn.getOutputStream());  
			DataInputStream in = new DataInputStream(new FileInputStream(file));  
			int bytes = 0;  
			byte[] bufferOut = new byte[1024];  
			while ((bytes = in.read(bufferOut)) != -1) {  
				out.write(bufferOut, 0, bytes);  
			}  
			in.close();
			out.flush();  
			out.close();  
			
			respCode = httpConn.getResponseCode();
			if (respCode == 200) {
				return SUCCESS;
			}else if(respCode == 202){
				return QUEYED;
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(  
					httpConn.getInputStream()));  
			String line = null;
			while ((line = reader.readLine()) != null) {  
				System.out.println(line);  
			}
		}catch (Exception e) {  
		  System.out.println("发送POST请求出现异常！" + e);  
		  e.printStackTrace();  
		}finally {         
		    if (httpConn != null) {
		    	httpConn.disconnect();
		    }
		}
		if (respCode == 200) {
			return SUCCESS;
		}else if(respCode == 202){
			return QUEYED;
		}else{
			return ERROR;
		}
	}
	public static String getContentByFileUpload2(String urlStr,File file,String fileType,String oui,String productClass,String version){
//		String end = "\r\n";
//	    String twoHyphens = "--";
//	    String boundary = "*****";
	    try {
	      URL url = new URL(urlStr);
	      HttpURLConnection con = (HttpURLConnection) url.openConnection();
	      /* 允许Input、Output，不使用Cache */
	      con.setDoInput(true);
	      con.setDoOutput(true);
	      con.setUseCaches(false);
	      /* 设置传送的method=PUT */
	      con.setRequestMethod("PUT");
	      /* setRequestProperty */
	      con.setRequestProperty("Connection", "Keep-Alive");
	      con.setRequestProperty("Charset", "UTF-8");
	      con.setRequestProperty("Content-Type",
	          "binary/octet-stream");
	      System.out.println(fileType);
	      con.setRequestProperty("fileType", fileType);
	      con.setRequestProperty("oui", oui);
	      con.setRequestProperty("productClass", productClass);
	      con.setRequestProperty("version", version);
	      /* 设置DataOutputStream */
	      DataOutputStream ds = new DataOutputStream(con.getOutputStream());
//	      ds.writeBytes(twoHyphens + boundary + end);
//	      ds.writeBytes("Content-Disposition: form-data; "
//	          + "name=\"file1\";filename=\"" + file.getName() + "\"");
//	      ds.writeBytes(end);
	      /* 取得文件的FileInputStream */
	      FileInputStream fStream = new FileInputStream(file);
	      /* 设置每次写入1024bytes */
	      int bufferSize = 1024;
	      byte[] buffer = new byte[bufferSize];
	      int length = -1;
	      /* 从文件读取数据至缓冲区 */
	      while ((length = fStream.read(buffer)) != -1) {
	        /* 将资料写入DataOutputStream中 */
	        ds.write(buffer, 0, length);
	      }
//	      ds.writeBytes(end);
//	      ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
	      /* close streams */
	      fStream.close();
	      ds.flush();
	      /* 取得Response内容 */
	      InputStream is = con.getInputStream();
	      int ch;
	      StringBuffer b = new StringBuffer();
	      while ((ch = is.read()) != -1) {
	        b.append((char) ch);
	      }
	      /* 将Response显示于Dialog */
	      /* 关闭DataOutputStream */
	      ds.close();
	      System.out.println(con.getResponseCode());
	      return SUCCESS;
	    } catch (Exception e) {
	      System.out.println("上传失败" + e);
	    }
	    return "";
	}
}

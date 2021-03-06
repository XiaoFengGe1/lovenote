package com.app.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONException;
import org.json.JSONObject;
/** 
 * 公众平台通用接口工具类 
 *  
 *
 */  
public class WeixinBuilderTool {

	 public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET"; 
	 public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	 /** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = new JSONObject(buffer.toString());  
        } catch (ConnectException ce) {  
            //log.error("Weixin server connection timed out.");  
        } catch (Exception e) {  
            //log.error("https request error:{}", e);  
        }  
        return jsonObject;  
    }  
    
    public static AccessToken getAccessToken(String appid, String appsecret) {  
        AccessToken accessToken = null;  
      
        String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        // 如果请求成功  
        if (null != jsonObject) {  
            try {  
                accessToken = new AccessToken();  
                accessToken.setToken(jsonObject.getString("access_token"));  
                accessToken.setExpiresIn(jsonObject.getInt("expires_in"));  
            } catch (JSONException e) {  
                accessToken = null;  
                // 获取token失败  
              //  log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
        return accessToken;  
    }  
    
    static int createMenu(Menu menu, String accessToken) {  
        int result = 0;  
      
        // 拼装创建菜单的url  
        String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);  
        // 将菜单对象转换成json字符串  
        String jsonMenu = new JSONObject(menu).toString();  
        // 调用接口创建菜单  
        JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);  
      
        if (null != jsonObject) {  
            if (0 != jsonObject.getInt("errcode")) {  
                result = jsonObject.getInt("errcode");  
              //  log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
            }  
        }  
      
        return result;  
    }  
}  
 
 /** 
  * 证书信任管理器（用于https请求） 
  * 
  */  
 class MyX509TrustManager implements X509TrustManager {  
   
     public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
     }  
   
     public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {  
     }  
   
     public X509Certificate[] getAcceptedIssuers() {  
         return null;  
     }  
 }  

 
 class AccessToken {  
	    // 获取到的凭证  
	    private String token;  
	    // 凭证有效时间，单位：秒  
	    private int expiresIn;  
	  
	    public String getToken() {  
	        return token;  
	    }  
	  
	    public void setToken(String token) {  
	        this.token = token;  
	    }  
	  
	    public int getExpiresIn() {  
	        return expiresIn;  
	    }  
	  
	    public void setExpiresIn(int expiresIn) {  
	        this.expiresIn = expiresIn;  
	    }  
	} 
 
 class Button {  
	    private String name;  
	  
	    public String getName() {  
	        return name;  
	    }  
	  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	}  
 
 class CommonButton extends Button {  
	    private String type;  
	    private String key;  
	  
	    public String getType() {  
	        return type;  
	    }  
	  
	    public void setType(String type) {  
	        this.type = type;  
	    }  
	  
	    public String getKey() {  
	        return key;  
	    }  
	  
	    public void setKey(String key) {  
	        this.key = key;  
	    }  
	}  
 
 class ComplexButton extends Button {  
	    private Button[] sub_button;  
	  
	    public Button[] getSub_button() {  
	        return sub_button;  
	    }  
	  
	    public void setSub_button(Button[] sub_button) {  
	        this.sub_button = sub_button;  
	    }  
	}  
 
 class Menu {  
	    private Button[] button;  
	  
	    public Button[] getButton() {  
	        return button;  
	    }  
	  
	    public void setButton(Button[] button) {  
	        this.button = button;  
	    }  
	}  
 
 
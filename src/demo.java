import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieStore;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class demo {
	//注意 实际使用 请将 domain  换成域名  注意 实际使用 请将 domain  换成域名 注意 实际使用 请将 domain  换成域名
	static String hostUrlString = "http://domain/server/ppservice.asmx/";
	static String key = "";
	static org.apache.http.client.CookieStore cook;

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		HttpPost post = new HttpPost(hostUrlString + "Login");
		post.addHeader("Content-Type", "application/json; charset=utf-8");
		DefaultHttpClient client = new DefaultHttpClient();
		JSONObject jsonParams = new JSONObject();

		jsonParams.put("LoginName", "100");
		jsonParams.put("PassWord", "123456");
		jsonParams.put("key", key);
		String str = jsonParams.toString();
		HttpEntity bodyEntity = new StringEntity(str, "utf8");
		post.setEntity(bodyEntity);
		HttpResponse httpRespone = client.execute(post);
		cook = client.getCookieStore();
		String Result = "";
		if (httpRespone.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 取得数据
			Result = EntityUtils.toString(httpRespone.getEntity());
			System.out.print(Result + "\r\n");
			JSONObject loginresult = JSONObject.fromObject(Result);
			boolean isok = loginresult.getBoolean("d");
			if (isok) {
				System.out.print("登陆成功\r\n");
				GetDevice();
			} else {
				System.out.print("登陆失败\r\n");
			}

		} else {
			Result = EntityUtils.toString(httpRespone.getEntity());
			System.out.print(Result + "\r\n");
		}

	}
	
	public static void GetDevice() throws ClientProtocolException, IOException{
		HttpPost post = new HttpPost(hostUrlString + "GetDevice");
		post.addHeader("Content-Type", "application/json; charset=utf-8");
		DefaultHttpClient client = new DefaultHttpClient();
		client.setCookieStore(cook);
		HttpResponse httpRespone = client.execute(post);
	    String	Result = EntityUtils.toString(httpRespone.getEntity());
		System.out.print(Result + "\r\n");
		GetDeviceDetial();
	}
	public static void GetDeviceDetial() throws ClientProtocolException, IOException{
		HttpPost post = new HttpPost(hostUrlString + "GetDeviceDetial");
		post.addHeader("Content-Type", "application/json; charset=utf-8");
		DefaultHttpClient client = new DefaultHttpClient();
		client.setCookieStore(cook);
		
		JSONObject jsonParams = new JSONObject(); 
		jsonParams.put("SerialNumber", "32010000022525");
		String str = jsonParams.toString();
		HttpEntity bodyEntity = new StringEntity(str, "utf8");
		post.setEntity(bodyEntity);
		
		HttpResponse httpRespone = client.execute(post);
	    String	Result = EntityUtils.toString(httpRespone.getEntity());
		System.out.print(Result + "\r\n");
		GetDeviceTraceDataStr();
	}
	
	public static void GetDeviceTraceDataStr() throws ClientProtocolException, IOException{
		HttpPost post = new HttpPost(hostUrlString + "GetDeviceTraceDataStr");
		post.addHeader("Content-Type", "application/json; charset=utf-8");
		DefaultHttpClient client = new DefaultHttpClient();
		client.setCookieStore(cook);
		
		JSONObject jsonParams = new JSONObject(); 
		jsonParams.put("Sreialnumber", "352888800018669");
		jsonParams.put("StartTime", "2014-11-19 16:08:43");
		jsonParams.put("EndTime", "2014-11-20 16:08:43");
		jsonParams.put("FilterLbs", "true");
		jsonParams.put("FilterError", "true");
		jsonParams.put("MinSpd", "1");
		String str = jsonParams.toString();
		HttpEntity bodyEntity = new StringEntity(str, "utf8");
		post.setEntity(bodyEntity);
		
		HttpResponse httpRespone = client.execute(post);
	    String	Result = EntityUtils.toString(httpRespone.getEntity());
		System.out.print(Result + "\r\n");
		GetDeviceList();
	}
	
	public static void GetDeviceList() throws ClientProtocolException, IOException{
		HttpPost post = new HttpPost(hostUrlString + "GetDeviceList");
		post.addHeader("Content-Type", "application/json; charset=utf-8");
		DefaultHttpClient client = new DefaultHttpClient();
		client.setCookieStore(cook);
		
		JSONObject jsonParams = new JSONObject(); 
		jsonParams.put("IncludLow", "true");
		jsonParams.put("PageIndex", "1");
		jsonParams.put("PageSize", "20");
		jsonParams.put("Serialnumber", "");
		jsonParams.put("DeviceName", "");
		jsonParams.put("TelPhoneNum", "");
		String str = jsonParams.toString();
		HttpEntity bodyEntity = new StringEntity(str, "utf8");
		post.setEntity(bodyEntity);
		
		HttpResponse httpRespone = client.execute(post);
	    String	Result = EntityUtils.toString(httpRespone.getEntity());
		System.out.print(Result + "\r\n");
	}
	
}

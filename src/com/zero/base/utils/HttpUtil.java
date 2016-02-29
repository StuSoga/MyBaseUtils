package com.zero.base.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by zero on 16/2/29.
 */
public class HttpUtil {

	public static String post(String url, Map<String, String> postHeaders, String postEntity, String charset) throws IOException {

		if ("".equals(charset)||null==charset) {
			charset = "utf-8";
		}
		URL postUrl=new URL(url);
		HttpURLConnection httpURLConnection= (HttpURLConnection) postUrl.openConnection();
		/**
		 * 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
		 * http正文内，因此需要设为true, 默认情况下是false;
		 */
		httpURLConnection.setDoOutput(true);
		/**
		 * // 设置是否从httpUrlConnection读入，默认情况下是true;
		 */
		httpURLConnection.setDoInput(true);
		/**
		 * 设定请求的方法为"POST"，默认是GET
		 */
		httpURLConnection.setRequestMethod("POST");
		/**
		 * Post 请求不能使用缓存
		 */
		httpURLConnection.setUseCaches(false);
		httpURLConnection.setInstanceFollowRedirects(true);
		httpURLConnection.setReadTimeout(30000);
		httpURLConnection.setRequestProperty(" Content-Type ", "application/x-www-form-urlencoded");

		if (postHeaders != null) {
			for (String pKey : postHeaders.keySet()) {
				httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey));
			}
		}
		if (postEntity != null) {
			DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
			out.write(postEntity.getBytes(charset));
			out.flush();
			out.close(); // flush and close
		}
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), charset));
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			sbStr.append(line);
		}
		bufferedReader.close();
		httpURLConnection.disconnect();
		return new String(sbStr.toString().getBytes(), charset);
	}
}

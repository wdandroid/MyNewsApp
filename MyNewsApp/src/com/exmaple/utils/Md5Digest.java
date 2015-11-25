package com.exmaple.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Digest {

	
	public static String getDigest(String password){
		String digest="";
		
 		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			
			
			byte[] bytes = password.getBytes();
			byte[] digestbytes = messageDigest.digest(bytes);

			for(byte b: digestbytes){
				
				 int number =b & 0xff;
				 String hexString = Integer.toHexString(number);
				 
				 if (hexString.length()==1) {
					 sb.append("0");

				}
				 sb.append(hexString);
				// System.out.println(hexString);
			}
			
			 digest =sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return digest;
		
		
	}
	
	
	public static String getApkDigest(String ApkPath){
		
        String digest="";

		FileInputStream fis;
		try {
			fis = new FileInputStream(new File(ApkPath));
			
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			
			
			byte[] b=new byte[1024];
			int len =0;
			while ((len = fis.read(b))!=-1) {
	 			messageDigest.update(b, 0, len);
			}
			
			byte[] digest2 = messageDigest.digest();
			
			
	 		StringBuffer sb = new StringBuffer();
			
			for(byte bt: digest2){
				
				 int number =bt & 0xff;
				 String hexString = Integer.toHexString(number);
				 
				 if (hexString.length()==1) {
					 sb.append("0");

				}
				 sb.append(hexString);
//				 System.out.println(hexString);
			}
			
			 digest =sb.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	
		 
		 
	 
		
		
		return digest;
		
		
	}
}

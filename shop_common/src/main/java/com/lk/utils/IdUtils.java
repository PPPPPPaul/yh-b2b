package com.lk.utils;

import java.util.Random;

public class IdUtils {
	public static Long genId(){
		long ct = System.currentTimeMillis();
//		return ct;
		String currentTime = ct +"";
		Random r =  new Random();
		int num = r.nextInt(99);
		String tmp = String.format("%02d",num);
		tmp = currentTime + tmp;
		return Long.parseLong(tmp);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 15; i++) {
			System.out.println(genId());
			
		}
		
	}
}

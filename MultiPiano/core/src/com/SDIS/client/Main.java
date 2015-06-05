package com.SDIS.client;

import java.io.IOException;

public class Main {

	public static void main(String[] Args)
	{
	//	String a[] = {"hey"};
		//String b[] = {"hey, hey"};
		Client cli = new Client("172.30.40.12", 9000);
		try {
			String joined = cli.httpGet("/MultiPiano/join");
			
			String out = cli.httpGet("/MultiPiano/faixa_22");
			
			System.out.println(joined);
			//String out = httpPost("",a,b);
			System.out.println(out);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package com.SDIS.client;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class ClientHandler implements HttpHandler{
	
	private Listener list;
	
	public ClientHandler(Listener listener) {
		// TODO Auto-generated constructor stub
		list = listener;
	}





	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("cenas que recebi");
		InputStream is = exchange.getRequestBody();

		Scanner scanner = new Scanner(is,"UTF-8").useDelimiter("\n");
		// String theString = scanner.hasNext() ? scanner.next() : "";

		


		String path = exchange.getRequestURI().getPath();
		System.out.println(path);
		String response = "played " + getFaixa(path);
	

		// read(is); // .. read the request body
		exchange.sendResponseHeaders(200, response.length());
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
		scanner.close();
		
	}
	
	
	

	
	public String getFaixa(String path){
		
		String[] faixa = path.split("/");
		int num = Integer.parseInt(faixa[2].split("ff")[1]);
		
		
		if(num < 1 || num > 64)
		{
			System.out.println("invalid track");
			
		}
		else
		{
			for(int i = 0; i < list.getGs().keys.size();i++)
			{
				if(list.getGs().keys.listIterator(i).next().trackName.equals(faixa[2]))
					list.getGs().keys.listIterator(i).next().track.play();
			}
		}
			
		

		return faixa[2];



	}


}

package com.SDIS.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Handler implements HttpHandler{

	private Server svr;

	public Handler(Server svr){
		this.svr = svr;
	}


	@Override
	public void handle(HttpExchange t) throws IOException {
		System.out.println("cenas que recebi");
		InputStream is = t.getRequestBody();

		Scanner scanner = new Scanner(is,"UTF-8").useDelimiter("\n");
		// String theString = scanner.hasNext() ? scanner.next() : "";

		


		String path = t.getRequestURI().getPath();
		System.out.println(path);
		String response;
		if(path.endsWith("join"))
		{
			if(joinClient(t))
				response = "Join efectuado com sucesso";
			else
				response = "Erro no join";
		}
		else if(path.endsWith("disconnect"))
		{
			if(disconnectClient(t))
				response = "Disconnect efectuado com sucesso";
			else
				response = "Erro no disconnect";
		}
		else
			response = "Recebi" + getFaixa(path);
			

		// read(is); // .. read the request body
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		scanner.close();

	}

	public String getFaixa(String path){

		String[] faixa = path.split("/");
		String[] numero = faixa[2].split("_");//faixa_NUM
		int num = Integer.parseInt(numero[2]);

		if(num < 22 || num > 25)
			System.out.println("invalid track");

		return faixa[2];



	}


	public boolean joinClient(HttpExchange t)
	{

		InetSocketAddress temp = t.getLocalAddress();

		if(svr.getContributors().contains(temp))	
			return false;
		else
		{
			svr.getContributors().add(temp);
			return true;
		}
	}

	public boolean disconnectClient(HttpExchange t)
	{
		InetSocketAddress temp = t.getLocalAddress();


		return svr.getContributors().remove(temp);

	}


}

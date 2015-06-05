package com.SDIS.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
			{
				response = "Join efectuado com sucesso";
			}
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
		{
			response = "/MultiPiano/" + getFaixa(path, t);
			sendNotes(response, t);
		}	

		// read(is); // .. read the request body
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
		scanner.close();

	}

	public void sendNotes(String response, HttpExchange t){

		ArrayList<InetAddress> contributors = svr.getContributors();

		for(int i = 0; i < contributors.size();i++)
		{
			if(!contributors.listIterator(i).next().equals(t.getRemoteAddress().getAddress())){

				try {
				DatagramSocket serverSocket = new DatagramSocket();
				DatagramPacket msgPacket = new DatagramPacket(response.getBytes(),
						response.getBytes().length, contributors.listIterator(i).next(),9001);
					serverSocket.send(msgPacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}





			httpGet(response,contributors.listIterator(i).next() );



		}


	}


	public void httpGet(String response,InetAddress ip)
	{
		URL url;
		try {
			url = new URL("http",ip.toString(),9001,response);
			HttpURLConnection conn =
					(HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != 200) {
				throw new IOException(conn.getResponseMessage());
			}

			// Buffer the result into a string
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public String getFaixa(String path, HttpExchange t){

		InetSocketAddress temp = t.getRemoteAddress();

		if(!svr.getContributors().contains(temp.getAddress()))	
			return "Not Joined, please join session before playing.\n";

		String[] faixa = path.split("/");

		int num = Integer.parseInt(faixa[2].split("ff")[1]);


		if(num < 1 || num > 64)
		{
			System.out.println("invalid track");

		}


		return faixa[2];



	}


	public boolean joinClient(HttpExchange t) throws MalformedURLException
	{

		InetSocketAddress temp = t.getRemoteAddress();

		if(svr.getContributors().contains(temp.getAddress()))	
			return false;
		else
		{
			svr.getContributors().add(temp.getAddress());
			return true;
		}
	}

	public boolean disconnectClient(HttpExchange t)
	{
		InetSocketAddress temp = t.getRemoteAddress();


		return svr.getContributors().remove(temp.getAddress());

	}


}

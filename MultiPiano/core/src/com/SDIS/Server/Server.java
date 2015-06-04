package com.SDIS.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpServer;

public class Server {

	private InetSocketAddress port;
	
	private Handler myHandler;
	
	private ArrayList<InetAddress> contributors;
	
	private HttpServer server;
	
	public Server(InetSocketAddress port) throws IOException{
		
		contributors = new ArrayList<InetAddress>(); 
		
		System.out.println("started server");
		myHandler = new Handler(this);

		server = HttpServer.create(port, 0);
		
		server.createContext("/MultiPiano", myHandler);
		server.setExecutor(null); // creates a default executor
		
	}
	
	public void start()
	{
		server.start();		
	}

	public InetSocketAddress getPort() {
		return port;
	}

	public void setPort(InetSocketAddress port) {
		this.port = port;
	}

	public Handler getMyHandler() {
		return myHandler;
	}

	public void setMyHandler(Handler myHandler) {
		this.myHandler = myHandler;
	}

	public ArrayList<InetAddress> getContributors() {
		return contributors;
	}

	public void setContributors(ArrayList<InetAddress> contributors) {
		this.contributors = contributors;
	}

	
	
}

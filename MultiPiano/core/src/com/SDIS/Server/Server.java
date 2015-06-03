package com.SDIS.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpServer;

public class Server {

	private InetSocketAddress port;
	
	private Handler myHandler;
	
	private ArrayList<InetSocketAddress> contributors;
	
	private HttpServer server;
	
	public Server(InetSocketAddress port) throws IOException{
		
		contributors = new ArrayList<InetSocketAddress>(); 
		
		System.out.println("started server");
		myHandler = new Handler(this);

		server = HttpServer.create(new InetSocketAddress(9000), 0);
		
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

	public ArrayList<InetSocketAddress> getContributors() {
		return contributors;
	}

	public void setContributors(ArrayList<InetSocketAddress> contributors) {
		this.contributors = contributors;
	}

	
	
}

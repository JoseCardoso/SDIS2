package com.SDIS.client;

import java.io.IOException;
import java.net.InetSocketAddress;



import com.SDIS.MultiPiano.GameScreen;
import com.sun.net.httpserver.HttpServer;

public class Listener extends Thread {
	
	private HttpServer list;
	private ClientHandler handler;
	private InetSocketAddress port;
	private GameScreen gs;
	
	public Listener(InetSocketAddress port, GameScreen gameScreen) throws IOException {
		this.gs = gameScreen;
		this.port = port;
			handler = new ClientHandler(this); 
			list = HttpServer.create(port,0);
			list.createContext("/MultiPiano", handler);
			list.setExecutor(null);
	}

	public void run()
	{
		list.start();	
		
		
	}

	public GameScreen getGs() {
		return gs;
	}

	public void setGs(GameScreen gs) {
		this.gs = gs;
	}

	
	
}

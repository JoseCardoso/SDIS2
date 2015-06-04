package com.SDIS.client;

import com.sun.net.httpserver.HttpServer;

public class Listener extends Thread {
	
	private HttpServer list;
	
	
	
	public void start()
	{
		super.start();
		list.start();
	}

}

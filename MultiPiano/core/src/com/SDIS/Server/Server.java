package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpServer;

public class Server {

	InetSocketAddress port;
	
	Handler myHandler;
	
	ArrayList<URL> contributors;
	
	public Server(InetSocketAddress port) throws IOException{
		
		System.out.println("started server");
		myHandler = new Handler();

		HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
		
		server.createContext("/MultiPiano", myHandler);
		server.setExecutor(null); // creates a default executor
		server.start();
		
	}

}

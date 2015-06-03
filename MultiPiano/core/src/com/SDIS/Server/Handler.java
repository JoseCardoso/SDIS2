package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class Handler implements HttpHandler{

	
	public Handler(){
		
	}
	
	
	@Override
	public void handle(HttpExchange t) throws IOException {
		System.out.println("cenas que recebi");
		 InputStream is = t.getRequestBody();
		 
		  Scanner scanner = new Scanner(is,"UTF-8").useDelimiter("\n");
	      String theString = scanner.hasNext() ? scanner.next() : "";
		
	      
	      //pim
	      String path = t.getRequestURI().getPath();
	      System.out.println(path);
	      
        // read(is); // .. read the request body
         String response = "Recebi" + getFaixa(path);
         t.sendResponseHeaders(200, response.length());
         OutputStream os = t.getResponseBody();
         os.write(response.getBytes());
         os.close();
		
	}
	
	public String getFaixa(String path){
		
		String[] faixa = path.split("/");
		
		
		switch(faixa[2]){
		
		case "faixa_22":
			System.out.println("faixa_22");
			break;
		case "faixa_23":
			System.out.println("faixa_23");
			break;
		case "faixa_24":
			System.out.println("faixa_24");
			break;
		case "faixa_25":
			System.out.println("faixa_25");
			break;
		default:
			System.out.println("not parsed");
		}
		
		return faixa[2];
		
		
		
	}

}

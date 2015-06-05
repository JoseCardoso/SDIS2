package com.SDIS.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;



import java.net.MulticastSocket;
import java.net.SocketException;

import com.SDIS.MultiPiano.GameScreen;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class Listener extends Thread {

	private GameScreen gs;
	private byte[] buffer = new byte[256];

	public Listener(GameScreen gameScreen)  throws IOException {
		this.gs = gameScreen;

	}

	public void run()
	{
		do{
			try {


				DatagramSocket clientSocket = new DatagramSocket();

				DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
				clientSocket.receive(receivePacket);


				String msg = new String(buffer, 0, buffer.length);

				int tracknum = getFaixaToPlay(msg);
				gs.keys.get(tracknum -1).track.play();

			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(true);
	}


	public int getFaixaToPlay(String msg){


		String[] faixa = msg.split("/");

		int num = Integer.parseInt(faixa[2].split("ff")[1]);


		if(num < 1 || num > 64)
		{
			System.out.println("invalid track");

		}

		return num;

	}

	public GameScreen getGs() {
		return gs;
	}

	public void setGs(GameScreen gs) {
		this.gs = gs;
	}



}

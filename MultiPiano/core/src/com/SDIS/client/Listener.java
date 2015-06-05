package com.SDIS.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import com.SDIS.MultiPiano.GameScreen;

public class Listener implements Runnable { 

	private GameScreen gs;
	private byte[] buffer = new byte[256];
	private DatagramSocket clientSocket;

	public Listener(GameScreen gameScreen)  throws IOException  {
		this.gs = gameScreen;
		this.clientSocket = new DatagramSocket(9003);
		
	}

	public void run()
	{
		do{
			try {

				DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
				clientSocket.receive(receivePacket);

				String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());
				//if(msg.getBytes()[0]=='f'){
					int tracknum = getFaixaToPlay(msg);
					gs.tracks.get(tracknum - 1).play();
					//}
			/*	else{
					gs.userNo=Integer.parseInt(msg);
				}*/

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

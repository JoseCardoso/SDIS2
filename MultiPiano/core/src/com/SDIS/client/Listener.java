package com.SDIS.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import com.SDIS.MultiPiano.GameScreen;

public class Listener implements Runnable {

	private final GameScreen gs;
	private byte[] buffer = new byte[256];
	private DatagramSocket clientSocket;

	public Listener(final GameScreen gameScreen) throws IOException {
		this.gs = gameScreen;
		this.clientSocket = new DatagramSocket(9003);

	}

	public void run() {
		do {
			try {

				DatagramPacket receivePacket = new DatagramPacket(buffer,
						buffer.length);
				clientSocket.receive(receivePacket);

				String msg = new String(receivePacket.getData(), 0,
						receivePacket.getLength());
				if (msg.startsWith("/MultiPiano/ff")) {
					int tracknum = getFaixaToPlay(msg);
					gs.tracks.get(tracknum - 1).play();
				} else if (msg.startsWith("/userId:")) {
					String[] id = msg.split(" ");

					gs.setUser(Integer.parseInt(id[1]) % 5);
					System.out.println("userId: " + gs.userNo);
				}

			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}

	public int getFaixaToPlay(String msg) {

		String[] faixa = msg.split("/");

		int num = Integer.parseInt(faixa[2].split("ff")[1]);

		if (num < 1 || num > 64) {
			System.out.println("invalid track");

		}

		return num;

	}

	public GameScreen getGs() {
		return gs;
	}

}

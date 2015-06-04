package com.SDIS.MultiPiano;

import java.io.IOException;

import com.SDIS.client.Client;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class MultiPiano extends Game implements ApplicationListener {
	private Client cli;
	@Override
	public void create() {
		cli = new Client("192.168.2.106", 9001);
		try {
			cli.httpGet("/MultiPiano/join");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setScreen(new GameScreen(cli));
		
	}

	@Override
	public void dispose() {
		try {
			cli.httpGet("/MultiPiano/disconnect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void setScreen(Screen screen) {
		// TODO Auto-generated method stub
		super.setScreen(screen);
	}

	@Override
	public Screen getScreen() {
		// TODO Auto-generated method stub
		return super.getScreen();
	}
}

package com.SDIS.MultiPiano;

import java.io.IOException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.SDIS.client.*;

public class GameInputProcessor implements InputProcessor {

	private GameScreen screen;
	private Client cli;

	public GameInputProcessor(GameScreen gameScreen,Client cli) {
		this.cli = cli;
		this.screen = gameScreen;
	}

	@Override
	public boolean keyDown(int keycode) {

		Sound s;
		if (keycode >= 1 && keycode <= 64){
			if(keycode<10)
				s = Gdx.audio.newSound(Gdx.files.internal("piano_keys/ff00" +keycode +".wav"));
			else
				s = Gdx.audio.newSound(Gdx.files.internal("piano_keys/ff0" +keycode +".wav"));
			s.play();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		if(screen.MENU){
			if(		(screen.playXInit < screenX) && 
					(screen.playXFin > screenX) &&
					(screen.playYInit < (screen.h - screenY)) &&
					(screen.playYFin > (screen.h - screenY) )){
				screen.MENU = false;
				screen.initThread();
				screen.menuDispose();
			}
		}
		else{
			try{
				for (int i = 0; i < screen.keys.size(); i++) { //CHECK BLACK
					if (screen.keys.get(i).type==1) {
						if(screen.keys.get(i).touched(screenX, screenY)){
							screen.keys.get(i).track.play();

							String mes = "/MultiPiano/";
							mes += screen.keys.get(i).trackName;
							cli.httpGet(mes);
							screen.keys.get(i).setShadowSprite();
							return true;
						}
					}
					
				}
				for (int i = 0; i < screen.keys.size(); i++) { //CHECK BLACK
					if (screen.keys.get(i).type==0) {
						if(screen.keys.get(i).touched(screenX, screenY)){
							screen.keys.get(i).track.play();

							String mes = "/MultiPiano/";
							mes += screen.keys.get(i).trackName;
							cli.httpGet(mes);
							screen.keys.get(i).setShadowSprite();
							return true;
						}
					}
					
				}
			} catch (IOException e) {

			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for (int i = 0; i < screen.keys.size(); i++) {
			if (screen.keys.get(i).type==1) {
				if(screen.keys.get(i).touched(screenX, screenY)){
					screen.keys.get(i).setSprite();
					return true;
				}
			}
		}
		for (int i = 0; i < screen.keys.size(); i++) {
			if (screen.keys.get(i).type==0) {
				if(screen.keys.get(i).touched(screenX, screenY)){
					screen.keys.get(i).setSprite();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}

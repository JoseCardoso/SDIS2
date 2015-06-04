package com.SDIS.MultiPiano;

import com.badlogic.gdx.InputProcessor;

public class GameInputProcessor implements InputProcessor {

	private GameScreen screen;
	
	public GameInputProcessor(GameScreen gameScreen) {
		this.screen = gameScreen;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		//if (keycode >= 0 && keycode <= 64)
			//screen.tracks.get(keycode).play();
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
		for (int i = 0; i < screen.keys.size(); i++) { //CHECK BLACK
			if (screen.keys.get(i).type==1) {
				if(screen.keys.get(i).touched(screenX, screenY)){
					screen.keys.get(i).track.play();
					screen.keys.get(i).setShadowSprite();
					return true;
				}
			}
		}
		for (int i = 0; i < screen.keys.size(); i++) { //CHECK WHITE
			if (screen.keys.get(i).type==0) {
				if(screen.keys.get(i).touched(screenX, screenY)){
					screen.keys.get(i).track.play();
					screen.keys.get(i).setShadowSprite();
					return true;
				}
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
		// TODO Auto-generated method stub
		return false;
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

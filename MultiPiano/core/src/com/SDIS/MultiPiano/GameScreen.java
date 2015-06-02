package com.SDIS.MultiPiano;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen {
	
	public Sound sel;
	public ArrayList<Sound> tracks;
	public GameScreen(){
		tracks=new ArrayList<Sound>();
		//sel=Gdx.audio.newSound(Gdx.files.internal("sel.wav"));
		for (int i = 1; i < 64; i++) {
			String trk_name;
			if(i<10)
				trk_name="ff00" + i;
			else
				trk_name="ff0" + i;
			tracks.add(Gdx.audio.newSound(Gdx.files.internal("piano_keys/" +trk_name +".wav")));
		}
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GameInputProcessor(this));
		//Gdx.input.setCursorCatched(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.4f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

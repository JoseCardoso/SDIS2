package com.SDIS.MultiPiano;

import java.util.Vector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.SDIS.client.*;

public class GameScreen implements Screen {

	private Client cli;
	public Sound sel;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	public Vector<Key> keys;
	public float w, h;

	public int userNo = 2; // % 5


	public GameScreen(Client cli){
		this.cli = cli;
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		cam = new OrthographicCamera(1000, 700);
		this.keys= new Vector<Key>();

		//CREATE OCTATE
		//TYPE 1 - BLACK
		//TYPE 0 - WHITE
		int b=0, w=0;
		for(int i = 0; i < 12; i++){
			String trk_name;
			int realNo = (12*userNo)+i;
			if(realNo<9)
				trk_name="ff00" + (realNo+1);
			else
				trk_name="ff0" + (realNo+1);

			if(i == 1 || i == 3 || i == 6 || i == 8 || i == 10){  //BLACK KEYS
				keys.add(new Key(1, b, Gdx.audio.newSound(Gdx.files.internal("piano_keys/" +trk_name +".wav")), this,trk_name));
				b++;
			}
			else{ // WHITE KEYS
				keys.add(new Key(0, w, Gdx.audio.newSound(Gdx.files.internal("piano_keys/" +trk_name +".wav")), this,trk_name));
				w++;
			}
		}
		cam.update();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GameInputProcessor(this,cli));
		//Gdx.input.setCursorCatched(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		
		//DRAW KEYS
		//2/3 width
		// White Width - W/8
		// Black Width - ((W/8)*2)/3
		// Black Start Position - (((W/8)*2)/3)*i
		//batch.draw(whiteKey,(w*i)/8,0,w/8,h);
		
		for(int i=0; i<keys.size(); i++){
			if(keys.get(i).type==0)
				batch.draw(keys.get(i).key, keys.get(i).x, keys.get(i).y, keys.get(i).width, keys.get(i).height);
		}
		for(int i=0; i<keys.size(); i++){
			if(keys.get(i).type==1)
				batch.draw(keys.get(i).key, keys.get(i).x, keys.get(i).y, keys.get(i).width, keys.get(i).height);
		}
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

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
		batch.dispose();
	}

}

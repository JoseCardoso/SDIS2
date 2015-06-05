package com.SDIS.MultiPiano;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.SDIS.client.*;

public class GameScreen implements Screen {

	private Client cli;
	private Texture menuText, playText, exitText;
	public Sprite menuSprite, playSprite, exitSprite;
	private Listener list;
	public Sound sel;
	private OrthographicCamera cam;
	private SpriteBatch batch;
	public Vector<Key> keys;
	public float w, h;
	public boolean MENU = false;

	public int userNo = 2; // % 5


	public GameScreen(Client cli){
		
		try {
			list = new Listener(new InetSocketAddress(9001), this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cli = cli;
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		cam = new OrthographicCamera(1000, 700);
		this.keys= new Vector<Key>();
		this.menuText = new Texture(Gdx.files.internal("images/sprite_fundo.jpg"));
		this.playText = new Texture(Gdx.files.internal("images/sprite_play.jpg"));
		this.exitText = new Texture(Gdx.files.internal("images/sprite_exit.jpg"));
		this.menuSprite = new Sprite(menuText);
		this.playSprite = new Sprite(playText);;
		this.exitSprite = new Sprite(exitText);;

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
		if(MENU){}
		else
			Gdx.input.setInputProcessor(new GameInputProcessor(this,cli));
	}

	@Override
	public void render(float delta) {
		if(MENU){
			batch.begin();
			batch.draw(menuSprite, 0, 0, w, h);
			batch.draw(playSprite, (w/2)-150, h/4, 130, 50);
			batch.draw(exitSprite, (w/2)+50, h/4, 130, 50);
			batch.end();
		}
		else{
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.begin();

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

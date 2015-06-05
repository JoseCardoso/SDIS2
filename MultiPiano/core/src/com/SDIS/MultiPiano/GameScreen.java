package com.SDIS.MultiPiano;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import com.SDIS.client.Client;
import com.SDIS.client.Listener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

	private Client cli;
	private Texture menuText, playText, logoText;
	public Sprite menuSprite, playSprite, logoSprite;
	private OrthographicCamera cam;
	private SpriteBatch menuBatch, batch;
	public Vector<Key> keys;
	public float w, h;
	public boolean MENU = true;
	public ArrayList<Sound> tracks;
	public int userNo = 0;
	public float playXInit, playXFin, playYInit, playYFin;
	public float exitXInit, exitXFin, exitYInit, exitYFin;

	public GameScreen(Client cli) {

		this.cli = cli;
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		menuBatch = new SpriteBatch();
		batch = new SpriteBatch();
		cam = new OrthographicCamera(1000, 700);
		this.keys = new Vector<Key>();
		this.logoText = new Texture(
				Gdx.files.internal("images/menu_game.jpg"));
		this.menuText = new Texture(
				Gdx.files.internal("images/menu_layed.jpg"));
		this.playText = new Texture(
				Gdx.files.internal("images/sprite_play.jpg"));
		this.logoSprite = new Sprite(logoText);
		this.menuSprite = new Sprite(menuText);
		this.playSprite = new Sprite(playText);
		this.tracks = new ArrayList<Sound>();
		//initThread();
		cam.update();
	}

	public void initThread(){
		try {
			new Thread(new Listener(this)).start();
			cli.httpGet("/MultiPiano/join");
		} catch (IOException e) {
			e.printStackTrace();
		}

		int b = 0, w = 0;
		for (int i = 0; i < 12; i++) {
			String trk_name;
			int realNo = (12 * userNo) + i;
			if (realNo < 9)
				trk_name = "ff00" + (realNo + 1);
			else
				trk_name = "ff0" + (realNo + 1);

			if (i == 1 || i == 3 || i == 6 || i == 8 || i == 10) { // BLACK KEYS
				keys.add(new Key(1, b, Gdx.audio.newSound(Gdx.files
						.internal("piano_keys/" + trk_name + ".wav")), this,
						trk_name));
				b++;
			} else { // WHITE KEYS
				keys.add(new Key(0, w, Gdx.audio.newSound(Gdx.files
						.internal("piano_keys/" + trk_name + ".wav")), this,
						trk_name));
				w++;
			}
		}
		for (int i = 1; i < 64; i++) {
			String trk_name;
			if (i < 10)
				trk_name = "ff00" + i;
			else
				trk_name = "ff0" + i;
			tracks.add(Gdx.audio.newSound(Gdx.files.internal("piano_keys/"
					+ trk_name + ".wav")));
		}
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GameInputProcessor(this, cli));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (MENU) {
			playXInit=((w / 2) - 50);
			playXFin = playXInit + 130;
			playYInit = (h / 4) - 50;
			playYFin = (h / 4) + 65;

			menuBatch.begin();
			menuBatch.draw(menuSprite, 0, 0, w, h);
			menuBatch.draw(playSprite, playXInit, playYInit, 130, 65);
			menuBatch.end();
		} else {
			batch.begin();

			for (int i = 0; i < keys.size(); i++) {
				if (keys.get(i).type == 0)
					batch.draw(keys.get(i).key, keys.get(i).x, keys.get(i).y,
							keys.get(i).width, keys.get(i).height);
			}
			for (int i = 0; i < keys.size(); i++) {
				if (keys.get(i).type == 1)
					batch.draw(keys.get(i).key, keys.get(i).x, keys.get(i).y,
							keys.get(i).width, keys.get(i).height);
			}
			batch.draw(logoSprite, ((7*w)/8), 0, w/8, h);
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
		for(int i=0; i<tracks.size(); i++){
			tracks.get(i).dispose();
		}
		for(int i=0; i<keys.size(); i++){
			keys.get(i).track.dispose();
		}
		menuText.dispose();
		playText.dispose();
		logoText.dispose();
	}

	public void setUser(int userId) {
		userNo = userId;
	}

	
	public void menuDispose() {
		// TODO Auto-generated method stub
		menuText.dispose();
		playText.dispose();
		menuBatch.dispose();
	}

}

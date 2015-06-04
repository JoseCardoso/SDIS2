package com.SDIS.MultiPiano;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Key {

	public Sound track;
	public int type, pos;
	public Texture texture;
	public Sprite key;
	public float x, y, width, height;
	private GameScreen screen;
	
	public Key(){}

	public Key(int type, int pos, Sound track, GameScreen screen){
		this.track=track;
		this.type=type;
		this.screen = screen;
		switch (type) {
		case 0:
			this.texture = new Texture(Gdx.files.internal("images/white_key.jpg"));
			this.key = new Sprite(texture);
			this.x = pos * (screen.w / 8);
			this.y = 0;
			this.height = screen.h;
			this.width = screen.w/8;
			break;
		case 1:
			texture = new Texture(Gdx.files.internal("images/black_key.jpg"));
			key = new Sprite(texture);
			if (pos > 1)
				pos += 1;
			this.x = ((( screen.w/8 ) * 2) / 3) + (pos * (screen.w / 8)) ;
			this.y = screen.h / 3;
			this.height = screen.h;
			this.width = (( screen.w/8 ) * 2) / 3; 
			break;
		default:
			break;
		}
	}
	
	public boolean touched(int x, int y){
		
		if( ( this.x + this.width) > x &&
			( this.x < x ) && 
			( this.y + this.height > y ) &&
			( this.y  < y))
			return true;
		
		return false;
	}
}

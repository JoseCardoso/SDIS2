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
	public GameScreen screen;
	public float x, y, width, height;
	public String trackName;
	
	public Key(){}

	public Key(int type, int pos, Sound track, GameScreen screen, String trackName){
		this.trackName = trackName;
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
			texture = new Texture(Gdx.files.internal("images/black_key_shadow.jpg"));
			key = new Sprite(texture);
			if (pos > 1)
				pos += 1;
			this.x = ((( screen.w/8 ) * 2) / 3) + (pos * (screen.w / 8)) ;
			this.y = screen.h / 3;
			this.height = (screen.h - this.y);
			this.width = (( screen.w/8 ) * 2) / 3; 
			break;
		default:
			break;
		}
	}
	
	public boolean touched(int x, int y){
		y= (int)screen.h-y;
		if( ((this.x + this.width) > x) &&
			( this.x < x ) && 
			( (this.y + this.height) > y ) &&
			( this.y  < y))
			return true;
		
		return false;
	}

	public boolean setShadowSprite(){
		if(type==0)
			this.texture = new Texture(Gdx.files.internal("images/white_key_shadow.jpg"));
		else
			this.texture = new Texture(Gdx.files.internal("images/black_key.jpg"));
		this.key = new Sprite(this.texture);
		return true;
	}
	
	public boolean setSprite(){
		if(type==0)
			this.texture = new Texture(Gdx.files.internal("images/white_key.jpg"));
		else
			this.texture = new Texture(Gdx.files.internal("images/black_key_shadow.jpg"));
		this.key = new Sprite(this.texture);
		return true;
	}
}

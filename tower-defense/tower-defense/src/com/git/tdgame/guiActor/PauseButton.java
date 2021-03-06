package com.git.tdgame.guiActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PauseButton extends Actor {
	private Texture texture;
	private Sprite sprite;
	
	public PauseButton() {
		texture = new Texture(Gdx.files.internal("data/game/gui/pause.png"));
		setHeight(texture.getHeight());
		setWidth(texture.getWidth());
		sprite = new Sprite(texture);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(sprite,getX(),getY()+getHeight(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,-1,0);
		super.draw(batch, parentAlpha);
	}
}

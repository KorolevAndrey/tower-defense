package com.git.tdgame.gameActor.tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TowerUpgradeButton extends Actor {
	
	private Tower mTower;
	private Texture texture;
	private Sprite sprite;
	
	public TowerUpgradeButton( Tower t ) {
		mTower = t;
		
		texture = new Texture(Gdx.files.internal("data/game/upgrade.png"));
		setHeight(texture.getHeight());
		setWidth(texture.getWidth());
        sprite = new Sprite(texture);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(sprite,getX(),getY()+getHeight(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,-1,0);
		super.draw(batch, parentAlpha);
	}
	
	
	public Tower getTower()
	{
		return mTower;
	}
}
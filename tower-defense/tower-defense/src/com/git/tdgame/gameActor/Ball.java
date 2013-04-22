package com.git.tdgame.gameActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;


public class Ball extends Actor
{
    public enum Direction {UP, RIGHT, DOWN, LEFT};
    private Direction direction;
    private Texture texture;
    private MoveToAction action;
    private int[][] path;
    private int tX = 0;
    private int tY = 0;
    private int fX = 0;
    private int fY = 0;
    private int speed = 5;
    private Sprite sprite;
    private double spritePos = 0;
    private int numberOfFrames = 0;

    public Ball (int x, int y, int[][]path, int fX, int fY)
    {
    	this.fX = fX;
    	this.fY = fY;
    	this.tX = x;
    	this.tY = y;
    	this.path = path;
    	setPosition(tX*32, 1024-(tY+1)*32);
    	texture = new Texture(Gdx.files.internal("data/game/ball.png"));
    	numberOfFrames = texture.getWidth()/32;
        sprite = new com.badlogic.gdx.graphics.g2d.Sprite(texture,32,32);
    	direction = Direction.RIGHT;
        moveAction();
    }
    
    public void moveAction()
    {
        action = new MoveToAction();
        this.setPosition(this.getX(), this.getY());
        
        if(direction == Direction.LEFT)
        {
            action.setPosition(getX()-32, getY());
            tX--;
        } else if(direction == Direction.RIGHT)
        {
            action.setPosition(getX()+32, getY());
            tX++;
        } else if(direction == Direction.DOWN)
        {
            action.setPosition(getX(), getY()-32);
            tY++;
        } else if(direction == Direction.UP)
        {
            action.setPosition(getX(), getY()+32);
            tY--;
        }
        action.setDuration(0.7f / speed);
        this.addAction(action);
    }

	public void draw (SpriteBatch batch, float parentAlpha)
	{
		spritePos = (spritePos+0.2) % numberOfFrames;
		
		sprite.setPosition(getX(), getY());
		sprite.setRegion((int)spritePos*32, 0, 32, 32);
	    sprite.draw(batch);
        if(this.getActions().size <= 0)
        {
        	findNewPath();
        }
    }

	private void findNewPath()
	{
		// Path finder yaz�lacak!!!
		// Bu kod de�i�tirilecek!!!
		
        if(tX == fX && tY == fY)
        {
        	action.finish();
        	this.remove();
        } else {
			if(path[tY][tX+1] != 0 && direction != Direction.LEFT)
			{
		    	direction = Direction.RIGHT;
			} else if(path[tY+1][tX] != 0 && direction != Direction.UP)
			{
		    	direction = Direction.DOWN;
			} else if(path[tY-1][tX] != 0 && direction != Direction.DOWN)
			{
		    	direction = Direction.UP;
			} else if(path[tY][tX-1] != 0 && direction != Direction.RIGHT)
			{
		    	direction = Direction.LEFT;
			}
	    	moveAction();
        }
	}
}
    
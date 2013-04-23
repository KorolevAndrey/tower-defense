package com.git.tdgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.tiled.TileAtlas;
import com.badlogic.gdx.graphics.g2d.tiled.TileMapRenderer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLayer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TiledMapHelper {
	
	private final int START_POINT = 6;
	private final int END_POINT = 7;
	private int[][] pathTiles;

	public int[][] getTiles(String layerName)
	{
		for(TiledLayer tl : map.layers)
		{
			if(tl.name.equals(layerName))
			{
				return tl.tiles;
			}
		}
		return null;
	}
	/**
	 * Renders the part of the map that should be visible to the user.
	 */
	
	private int[][] getPathTiles()
	{
		return pathTiles;
	}
	
	public Array<Vector2> getPath()
	{
		Array<Vector2> path = new Array<Vector2>();
		
		int[][] pathTiles = getPathTiles();
		
		Vector2 startPoint = getStartPoint();
		Vector2 endPoint = getEndPoint();
		int x = (int)startPoint.x;
		int y = (int)startPoint.y;
		int fX = (int)endPoint.x;
		int fY = (int)endPoint.y;
		
		path.add(startPoint);
		int direction = 0;
		while(x != fX || y != fY)
		{
			if(y+1 < 32 && pathTiles[y+1][x] != 0 && direction != 2)
			{
				Vector2 newVector = new Vector2(x,y+1);
				path.add(newVector);
				y++;
				direction = 1;
			}
			else if(y-1 >= 0 && pathTiles[y-1][x] != 0 && direction != 1)
			{
				Vector2 newVector = new Vector2(x,y-1);
				path.add(newVector);
				y--;
				direction = 2;
		    }        
			else if(x+1 < 32 && pathTiles[y][x+1] != 0 && direction != 4)
			{
				Vector2 newVector = new Vector2(x+1,y);
				path.add(newVector);
				x++;
				direction = 3;
			} 
			else if(x-1 >= 0 && pathTiles[y][x-1] != 0 && direction != 3)
			{
				Vector2 newVector = new Vector2(x-1,y);
				path.add(newVector);
				x--;
				direction = 4;
			}
		}
		return path;
	}
	
	public void render()
	{
		tileMapRenderer.render(getCamera());
	}

	/**
	 * Get the height of the map in pixels
	 * 
	 * @return y
	 */
	public int getHeight() {
		return map.height * map.tileHeight;
	}

	/**
	 * Get the width of the map in pixels
	 * 
	 * @return x
	 */
	public int getWidth() {
		return map.width * map.tileWidth;
	}
	
	public Vector2 getStartPoint()
	{
		Vector2 p = new Vector2();
		for(int y = 0; y < pathTiles.length; ++y)
		{
			for(int x = 0; x < pathTiles[y].length; ++x)
			{
				if(pathTiles[y][x] == START_POINT)
				{
					p.set(x, y);
					return p;
				}
			}
		}
		p.set(-1, -1);
		return p;
	}

	public Vector2 getEndPoint()
	{
		Vector2 p = new Vector2();
		for(int y = 0; y < pathTiles.length; ++y)
		{
			for(int x = 0; x < pathTiles[y].length; ++x)
			{
				if(pathTiles[y][x] == END_POINT)
				{
					p.set(x, y);
					return p;
				}
			}
		}
		p.set(-1, -1);
		return p;
	}



	/**
	 * Get the map, useful for iterating over the set of tiles found within
	 * 
	 * @return TiledMap
	 */
	public TiledMap getMap() {
		return map;
	}

	/**
	 * Calls dispose on all disposable resources held by this object.
	 */
	public void dispose() {
		tileAtlas.dispose();
		tileMapRenderer.dispose();
	}

	/**
	 * Sets the directory that holds the game's pack files and tile sets.
	 * 
	 * @param packDirectory
	 */
	public void setPackerDirectory(String packDirectory) {
		packFileDirectory = Gdx.files.internal(packDirectory);
	}

	/**
	 * Loads the requested tmx map file in to the helper.
	 * 
	 * @param tmxFile
	 */
	public void loadMap(String tmxFile) {
		if (packFileDirectory == null) {
			throw new IllegalStateException("loadMap() called out of sequence");
		}

		map = TiledLoader.createMap(Gdx.files.internal(tmxFile));
		tileAtlas = new TileAtlas(map, packFileDirectory);

		tileMapRenderer = new TileMapRenderer(map, tileAtlas, 16, 16);
		pathTiles = getTiles("Path");
	}

	/**
	 * Prepares the helper's camera object for use.
	 * 
	 * @param screenWidth
	 * @param screenHeight
	 */
	public void prepareCamera(int screenWidth, int screenHeight) {
		camera = new OrthographicCamera(screenWidth, screenHeight);

		camera.position.set(0, 0, 0);
	}

	/**
	 * Returns the camera object created for viewing the loaded map.
	 * 
	 * @return OrthographicCamera
	 */
	public OrthographicCamera getCamera() {
		if (camera == null) {
			throw new IllegalStateException(
					"getCamera() called out of sequence");
		}
		return camera;
	}

	private FileHandle packFileDirectory;

	private OrthographicCamera camera;

	private TileAtlas tileAtlas;
	private TileMapRenderer tileMapRenderer;

	private TiledMap map;
}

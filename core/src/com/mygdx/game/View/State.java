package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {
    protected int width;
    protected int height;
    protected static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    protected static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    protected GameStateManager gsm;
    protected OrthographicCamera cam;

    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}

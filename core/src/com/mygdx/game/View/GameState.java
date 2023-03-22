package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Model.Plane;
import com.mygdx.game.Model.Boat;


public class GameState extends State{
    
    private Texture background;
    private Plane plane;
    private Boat boat;
    private TextureRegion[] clouds;
    private float cloudx = 0;
    private float cloudy = 0;
    private int score = 5000;
    private BitmapFont font;

    
 
    public GameState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("TheMap.jpg");
        cam.setToOrtho(false, background.getWidth(),background.getHeight());
        cam.zoom = (float)0.18;
        plane = new Plane(background.getWidth()/2-200,background.getHeight()/2-200,1,1,400,400,new TextureRegion(new Texture("dragon.png")));
        boat = new Boat(2700,2700,1,1,300,300,new TextureRegion(new Texture("boat1.png")));
        font = new BitmapFont();
        font.getData().setScale(3f);
    }

    private void renderclouds(SpriteBatch batch){
        clouds = new TextureRegion[2];
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new TextureRegion(new Texture("cloud.png"));
            cloudx = 1000 * i;
            cloudy = 1000 * i;
            if (cloudx > background.getWidth()){
                cloudx = 0;
            }
            if (cloudy > background.getHeight()){
                cloudy = 0;
            }
            batch.draw(clouds[i], cloudx, cloudy);
        }
    }

    @Override
    public void update(float dt) {
        cam.update();
        plane.update(dt);
        boat.update(dt);
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        boat.draw(sb);
        plane.draw(sb);
        font.draw(sb, "Score: " + score, plane.getxPos()-400, plane.getyPos()+ 600);
        score -= 1;
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            plane.rotate(0.04f);
            
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            plane.rotate(-0.04f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            plane.setSpeed(12);
        }
        else{
            plane.setSpeed(3);
        }





        if (plane.getxPos() > background.getWidth()-200){
            plane.setxPos(200);
            cam.translate(-(background.getWidth()-400),0);
        }
        if(plane.getxPos() < 200){
            plane.setxPos(background.getWidth()-200);
            cam.translate(background.getWidth()-400,0);
        }
        if (plane.getyPos() > background.getHeight()-400){
            plane.setyPos(400);
            cam.translate(0,-(background.getHeight()-800));
        }
        if(plane.getyPos() < 400){
            plane.setyPos(background.getHeight()-400);
            cam.translate(0,background.getHeight()-800);
        }



        cam.translate((float) (plane.getSpeed() * Math.cos(plane.getAngle())), (float) (plane.getSpeed() * Math.sin(plane.getAngle())));

        // Assume you have an Actor object called "myActor" that you want to rotate
        //float angle = 45; // Replace this with the angle you want to rotate the actor to
        //myActor.setRotation(angle);
        
    }
}

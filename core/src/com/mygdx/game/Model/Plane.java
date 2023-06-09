package com.mygdx.game.Model;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Texture;

public class Plane {

    private float xPos,yPos;
    private Rectangle bounds;
    private float speed;
    private float angle;
    private float planeWidth;
    private float planeHeight;
    private TextureRegion planeTextureRegion;
    private TextureRegion planeTextureRegionList[];
    private TextureRegion[] airflow;
    private int airflowvar = 1;
    private TextureRegion flames[];
    private float elapsedtime = 0;
    private int flamevar = 0;
    private int currentImage = 2;
    private boolean rotateLeft = false;
    private boolean rotateRight = false;

    public Plane(float xPos, float yPos,float speed, float angle, float planeWidth, float planeHeight, int skinVar) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.bounds = new Rectangle(xPos, yPos, planeWidth, planeHeight);
        this.speed = speed;
        this.angle = angle;
        this.planeWidth = planeWidth;
        this.planeHeight = planeHeight;
        planeTextureRegionList = new TextureRegion[6];
        planeTextureRegionList[0] = new TextureRegion(new Texture("planeTextures/plane1.png"));
        planeTextureRegionList[1] = new TextureRegion(new Texture("planeTextures/plane2.png"));
        planeTextureRegionList[2] = new TextureRegion(new Texture("planeTextures/plane3.png"));
        planeTextureRegionList[3] = new TextureRegion(new Texture("planeTextures/seagull.png"));
        planeTextureRegionList[4] = new TextureRegion(new Texture("planeTextures/dragon1.png"));
        planeTextureRegionList[5] = new TextureRegion(new Texture("planeTextures/dragon2.png"));
        this.planeTextureRegion = planeTextureRegionList[skinVar];
    }

    public void rotate(float angle) {
        this.angle += angle;
    }

    public void rotateLeft() {
        rotateLeft = true;
    }

    public void rotateRight() {
        rotateRight = true;
    }

    public void stopRotateLeft() {
        rotateLeft = false;
    }

    public void stopRotateRight() {
        rotateRight = false;
    }

    public void update(float delta) {
        xPos += speed * Math.cos(angle);
        yPos += speed * Math.sin(angle);
        elapsedtime += delta;
        updateRotation();
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float x) {
        this.xPos = x;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float y) {
        this.yPos = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getPlaneWidth() {
        return planeWidth;
    }

    public float getPlaneHeight() {
        return planeHeight;
    }

    public void setTexture(TextureRegion texture) {
        this.planeTextureRegion = texture;
    }

    public void setAirflowvar(int airflowvar) {
        this.airflowvar = airflowvar;
    }
    public void setFlamevar(int flamevar){
        this.flamevar = flamevar;
    }

    public void updateRotation() {
        if(rotateLeft) {
            rotate(0.08f);
        }
        if(rotateRight) {
            rotate(-0.08f);
        }
    }

    public void draw(Batch batch) {
        airflow = new TextureRegion[2];
        airflow[0] = new TextureRegion(new Texture("effects/airflow.png"));
        airflow[1] = new TextureRegion(new Texture("effects/empty.png"));

        flames = new TextureRegion[3];
        flames[0] = new TextureRegion(new Texture("effects/flame1.png"));
        flames[1] = new TextureRegion(new Texture("effects/flame2.png"));
        flames[2] = new TextureRegion(new Texture("effects/empty.png"));
        if (flamevar == 1){
            if (elapsedtime > 0.1f) {
                elapsedtime -= 0.1f;
                currentImage = (currentImage + 1) % (flames.length-1);
            }
        }
        if (flamevar == 0){
            currentImage = 2;
        }

        
        batch.draw(flames[currentImage], xPos, yPos, planeWidth/2, planeHeight/2, planeWidth, planeHeight, 1, 1, (float) Math.toDegrees(angle), true);
        batch.draw(airflow[airflowvar], xPos, yPos, planeWidth/2, planeHeight/2, planeWidth*4/5, planeHeight, 1, 1, (float) Math.toDegrees(angle), true);
        batch.draw(planeTextureRegion, xPos, yPos, planeWidth/2, planeHeight/2, planeWidth, planeHeight, 1, 1, (float) Math.toDegrees(angle), true);
        
    }
    
}


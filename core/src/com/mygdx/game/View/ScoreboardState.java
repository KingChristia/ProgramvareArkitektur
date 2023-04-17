package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.API;
import com.mygdx.game.Model.Score;

import java.util.ArrayList;

public class ScoreboardState extends State{
    private API database;
    private ArrayList<Score> scoreboardList;
    private Texture background;
    private Skin backSkin;
    private Button backButton;
    private GameStage stage;
    protected ScoreboardState(final GameStateManager gsm, API Database) {
        super(gsm);
        this.database = Database;
        background = new Texture("gamescreens/scoreboard.jpg");
        scoreboardList = new ArrayList<>();
        database.getHighscores(scoreboardList);

        // Create a stage
        stage = new GameStage();

        //Load a skin from a JSNO file
        backSkin = new Skin(Gdx.files.internal("buttons/game/leftBtn/leftBtn.json"));

        //Create a button
        backButton = new Button(backSkin);

        //Set button position, size and function
        backButton.setPosition(0, 0);
        backButton.setSize(width/8f, width/8f);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new MenuState(gsm, database));
                System.out.println("Button Pressed");
                return true;
            }


        });

    stage.addActor(backButton);
    Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        BitmapFont font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(6);
        sb.begin();
        sb.draw(background,0,0,width,height);
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "-----------------------------------");
        float centerX = (width - layout.width) / 2f;

        ArrayList<Score> safeList = new ArrayList<>(scoreboardList);

        // iterate over the scoreboard list and draw each score
        float y = height - height/4f; // start drawing scores from the top of the screen
        int index = 0;
        for (Score score : safeList) {
            String scoreText = score.getName() + ": ";
            String scoreInt = Integer.toString((score.getScore()));
            font.draw(sb, scoreText, centerX, y);
            font.draw(sb, scoreInt, centerX + width/4f , y);
            y -= height/12f; // space out the scores vertically
            index++;
            if (index > 6){
                break;
            }
        }
        sb.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        backSkin.dispose();
    }
}

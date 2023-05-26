package com.example.game;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.widget.Toast;

public class StartGame extends AppCompatActivity {
    ImageView bg, knife, gun, rope, petrol, tools;
    Button button1, button2, button3;
    TextView textScene, health;
    ProgressBar progressBar;
    MediaPlayer mPlayer;

    Story story = new Story(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        mPlayer= MediaPlayer.create(this, R.raw.music);
        mPlayer.setLooping(true);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        textScene = (TextView) findViewById(R.id.textScene);
        bg = (ImageView) findViewById(R.id.bg);

        knife = (ImageView)findViewById(R.id.knife);
        rope = (ImageView)findViewById(R.id.rope);
        petrol = (ImageView)findViewById(R.id.petrol);
        tools = (ImageView)findViewById(R.id.tools);
        gun = (ImageView)findViewById(R.id.gun);

        health = (TextView)findViewById(R.id.health);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setScaleY(4f);

        button1.setTransformationMethod(null);
        button2.setTransformationMethod(null);
        button3.setTransformationMethod(null);

        story.startingPoint();
        mPlayer.start();
        mPlayer.setLooping(true);
    }

    public void select1(View view) {
        story.selectScene(story.nextScene1, story.choice1);
        changeHealth();
        getItem();
    }

    public void select2(View view) {
        story.selectScene(story.nextScene2, story.choice2);
        changeHealth();
        getItem();
    }
    public void select3(View view) {
        story.selectScene(story.nextScene3, story.choice3);
        changeHealth();
        getItem();
    }

    public void restart() {
        mPlayer.stop();
        Intent menu = new Intent(this, MainActivity.class);
        startActivity(menu);
    }

    public void getItem() {
        if (story.knife) {
            knife.setImageTintList(null);
        } if (story.gun) {
            gun.setImageTintList(null);
        } if (story.petrol >= 1) {
            petrol.setImageTintList(null);
        } else {
            petrol.setImageTintList(ColorStateList.valueOf
                    (Color.parseColor("#99000000")));
        }
        if (story.rope) {
            rope.setImageTintList(null);
        } if (story.tools) {
            tools.setImageTintList(null);
        }
    }

    public void changeHealth() {
        health.setText(story.health + " %");
        progressBar.setProgress(story.health);
    }

    private void stopPlay(){
        mPlayer.stop();
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            stopPlay();
        }
    }
}
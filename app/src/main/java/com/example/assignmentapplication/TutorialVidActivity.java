package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TutorialVidActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "TutorialVidActivity";
    static final String GOOGLE_API_KEY = "AIzaSyBBLP3Q6KTR8Io2-Pox36nGcVL1pr9V7EE";
    private String youtubeVideoId;
    private TextView body;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_vid);

        body = findViewById(R.id.textTutorialBody);
        YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.youtubePlayer);
        playerView.initialize(GOOGLE_API_KEY, this);


        Intent i = getIntent();
        Tutorial vidTutorial = i.getParcelableExtra("TUTORIAL");
        body.setText(vidTutorial.getTutorialBody());
        youtubeVideoId = vidTutorial.getVidLink();

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG,"onInitializationSuccess: provider is " + provider.getClass().toString());
        Toast.makeText(this, "Player Loaded", Toast.LENGTH_LONG).show();

        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);

        if(!wasRestored){
            youTubePlayer.cueVideo(youtubeVideoId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMsg = String.format("There was an error initialising the player (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
        }
    }

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(TutorialVidActivity.this, "You have earned 50 stars for finishing this video!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            finish();
        } else {
            Toast.makeText(this, "Press back again to exit tutorial without finishing", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}

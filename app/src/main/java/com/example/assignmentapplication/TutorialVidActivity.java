package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TutorialVidActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {

    private static final String TAG = "TutorialVidActivity";
    static final String GOOGLE_API_KEY = "AIzaSyBBLP3Q6KTR8Io2-Pox36nGcVL1pr9V7EE";
    String youtubeVideoId = "TODO";
    String youtubePlaylist = "TODO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tutorial_vid);

        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_tutorial_vid, null);
        setContentView(layout);
        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY, this);

        youtubeVideoId = "XEOCbFJjRw0";

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG,"onInitializationSuccess: provider is " + provider.getClass().toString());
        Toast.makeText(this, "Initialized Player Successfully", Toast.LENGTH_LONG).show();

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
}

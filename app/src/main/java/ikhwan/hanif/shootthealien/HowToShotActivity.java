package ikhwan.hanif.shootthealien;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


public class HowToShotActivity extends AppCompatActivity {

    VideoView vV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_shot);

        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        vV = findViewById(R.id.howToShotVv);
        vV.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.howtoshot);
        MediaController mediaController = new MediaController(this);
        vV.setMediaController(mediaController);
        mediaController.setAnchorView(vV);
        vV.start();

    }
}
package ikhwan.hanif.shootthealien;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;



public class SplashActivity extends AppCompatActivity {
    ImageView imageView1;
    TextView textView1,textView2;
    Animation topAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        FirebaseApp.initializeApp(this);
        MediaPlayer mPlayer = MediaPlayer.create(this, R.raw.main_sound);
        mPlayer.start();
        mPlayer.setLooping(true);
        imageView1 = findViewById(R.id.logoimg);
        textView1 = findViewById(R.id.textName);
        textView2 = findViewById(R.id.textDeveloped);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        imageView1.setAnimation(topAnim);
        textView1.setAnimation(bottomAnim);
        textView2.setAnimation(bottomAnim);
        if (isUserLoggedIn()) {
            // Jika sudah login, arahkan ke Start Activity
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashActivity.this, Start.class));
                finish();
            }, 4000);
        } else {
            // Jika belum login, arahkan ke SignInActivity
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashActivity.this, SignInActivity.class));
                finish();
            }, 4000);
        }
    }
    private boolean isUserLoggedIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth.getCurrentUser() != null;
    }
}
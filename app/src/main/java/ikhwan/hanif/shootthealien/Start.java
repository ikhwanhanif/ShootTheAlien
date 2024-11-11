package ikhwan.hanif.shootthealien;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;



public class Start extends AppCompatActivity {
    ImageButton imageButton;
    TextView textView;
    ImageView settings, logOut, leaderBoard, privacyPolicyIcon, info, chat, share;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        chat =  findViewById(R.id.chat);
        // Load the animation
        Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up_down);
        // Start the animation
        chat.startAnimation(scaleAnimation);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, GroupChatActivity.class);
                intent.putExtra("groupId", "YOUR_GROUP_ID");
                startActivity(intent);
            }
        });

        share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Membuat pesan undangan
                String message = "Ayo bergabung dengan saya di Alien Shooter! Unduh aplikasinya di https://play.google.com/store/apps/dev?id=7862095023254906088";
                // Membuat intent untuk berbagi pesan
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, message);
                // Memeriksa apakah WhatsApp terinstal
                shareIntent.setPackage("com.whatsapp");

                try {
                    startActivity(shareIntent); // Memulai aktivitas berbagi
                } catch (android.content.ActivityNotFoundException ex) {
                    // Menangani jika WhatsApp tidak terinstal
                    Toast.makeText(Start.this, "WhatsApp tidak terinstal.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Start.this, SettingsActivity.class));
            }
        });
        logOut = findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                // Beralih ke SignInActivity setelah logout
                startActivity(new Intent(Start.this, SignInActivity.class));
                finish();
            }
        });
        leaderBoard = findViewById(R.id.leaderBoard);
        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Start.this, LeaderboardActivity.class));
            }
        });
        imageButton = findViewById(R.id.startBtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Start.this, LevelActivity.class));
            }
        });
        textView = findViewById(R.id.startText);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Start.this, LevelActivity.class));
            }
        });
        info = findViewById(R.id.infoBtn);
        info.setOnClickListener(view -> {
            startActivity(new Intent(Start.this, Info.class));
        });
        privacyPolicyIcon = findViewById(R.id.privacyPolicy);
        privacyPolicyIcon.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.privacy_policy_link)));
            startActivity(browserIntent);
        });
    }
}
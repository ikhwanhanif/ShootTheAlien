package ikhwan.hanif.shootthealien;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;


public class SettingsActivity extends AppCompatActivity {
    LinearLayout ganti_bahasa;
    TextView textEmail;
    Button hapusAkun, skinBtn;
    private FirebaseAuth mAuth;
    LinearLayout skinLayout, layoutButton;
    ImageView skin1, skin2, skin3, closeBtn;
    private AudioManager audioManager;
    private boolean isMuted;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        closeBtn = findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutButton.setVisibility(View.VISIBLE);
                skinLayout.setVisibility(View.GONE);
            }
        });
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        layoutButton = findViewById(R.id.layoutButton);
        skin1 = findViewById(R.id.skin1);
        skin2 = findViewById(R.id.skin2);
        skin3 = findViewById(R.id.skin3);
        skinLayout = findViewById(R.id.skinLayout);
        if (skin1 != null) {
            skin1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveSelectedSkin(R.drawable.spaceship1);
                    layoutButton.setVisibility(View.VISIBLE);
                    skinLayout.setVisibility(View.GONE);
                    Toast.makeText(SettingsActivity.this, getString(R.string.berhasil_memilih_skin1), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (skin2 != null) {
            skin2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveSelectedSkin(R.drawable.spaceship2);
                    layoutButton.setVisibility(View.VISIBLE);
                    skinLayout.setVisibility(View.GONE);
                    Toast.makeText(SettingsActivity.this, getString(R.string.berhasil_memilih_skin2), Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (skin3 != null) {
            skin3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveSelectedSkin(R.drawable.rocket);
                    layoutButton.setVisibility(View.VISIBLE);
                    skinLayout.setVisibility(View.GONE);
                    Toast.makeText(SettingsActivity.this, getString(R.string.berhasil_memilih_skin3), Toast.LENGTH_SHORT).show();
                }
            });
        }
        skinBtn = findViewById(R.id.skinBtn);
        skinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skinLayout.setVisibility(View.VISIBLE);
                layoutButton.setVisibility(View.GONE);
            }
        });
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        hapusAkun = findViewById(R.id.hapusAkun);
        hapusAkun.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle(getString(R.string.hapus_akun));
                TextView textView = new TextView(SettingsActivity.this);
                textView.setText(getString(R.string.anda_yakin_untuk_hapus_akun));
                textView.setTextColor(getColor(R.color.black));
                textView.setTextSize(18);
                textView.setGravity(Gravity.CENTER);
                LinearLayout linearLayout = new LinearLayout(SettingsActivity.this);
                linearLayout.addView(textView);
                linearLayout.setGravity(Gravity.CENTER);
                linearLayout.setPadding(10, 10, 10, 10);
                builder.setIcon(getResources().getDrawable(R.drawable.baseline_warning_24));
                builder.setView(linearLayout);
                builder.setPositiveButton(getString(R.string.ya), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteAkun();
                    }
                });
                builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        textEmail = findViewById(R.id.textEmailUser);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            textEmail.setText(userEmail);
        }
        loadLocale();
        ganti_bahasa = findViewById(R.id.gantiBahasa);
        ganti_bahasa.setOnClickListener(view -> showBarGantiBahasa());
    }
    private void saveSelectedSkin(int skinResId) {
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putInt("Selected_Skin", skinResId);
        editor.apply();
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void DeleteAkun() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SettingsActivity.this, getString(R.string.sukses_menghapus_akun), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SettingsActivity.this, SignInActivity.class));
                    finish();
                }
            }
        });
    }
    private void showBarGantiBahasa() {
        final String[] listItems = {"English", "Indonesia"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
        mBuilder.setTitle(getString(R.string.choose_language));
        mBuilder.setSingleChoiceItems(listItems, -1, (dialogInterface, i) -> {
            if (i == 0) {
                setLocale("en");
                recreate();
                startActivity(new Intent(SettingsActivity.this, SplashActivity.class));
                finish();
            }
            if (i == 1) {
                setLocale("in");
                recreate();
                startActivity(new Intent(SettingsActivity.this, SplashActivity.class));
                finish();
            }
            dialogInterface.dismiss();
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
    private void loadLocale() {
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang", "");
        setLocale(language);
    }
}
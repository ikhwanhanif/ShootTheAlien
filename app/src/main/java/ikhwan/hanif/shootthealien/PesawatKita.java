package ikhwan.hanif.shootthealien;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;


public class PesawatKita {
    Context context;
    Bitmap ourSpaceship;
    int ox, oy;
    boolean isAlive = true;
    int ourVelocity;
    Random random;

    public PesawatKita(Context context) {


        this.context = context;
        random = new Random();
        loadSelectedSkin();
        resetOurSpaceship();
    }

    private void loadSelectedSkin() {
        SharedPreferences preferences = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        int defaultSkin = R.drawable.rocket; // Default skin if none is selected
        int selectedSkin = preferences.getInt("Selected_Skin", defaultSkin);
        ourSpaceship = BitmapFactory.decodeResource(context.getResources(), selectedSkin);
    }

    private void resetOurSpaceship() {
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        ox = random.nextInt(screenWidth);
        oy = screenHeight - ourSpaceship.getHeight();
        ourVelocity = 10 + random.nextInt(6);
    }

    public Bitmap getOurSpaceship(){
        return ourSpaceship;
    }

    int getOurSpaceshipWidth(){
        return ourSpaceship.getWidth();
    }
}

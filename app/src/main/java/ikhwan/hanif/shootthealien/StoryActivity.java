package ikhwan.hanif.shootthealien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class StoryActivity extends AppCompatActivity {

    TextView text1, text2, text3, text4, info1, info2, info3, info4;
    ImageView button1, button2, button3, button4;
    TranslateAnimation animationUp1, animationDown1;
    TranslateAnimation animationUp2, animationDown2;
    TranslateAnimation animationUp3, animationDown3;
    TranslateAnimation animationUp4, animationDown4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        text1 = findViewById(R.id.textView1);
        text2 = findViewById(R.id.textView2);
        text3 = findViewById(R.id.textView3);
        text4 = findViewById(R.id.textView4);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        info1 = findViewById(R.id.info1);
        info2 = findViewById(R.id.info2);
        info3 = findViewById(R.id.info3);
        info4 = findViewById(R.id.info4);

        Animation animText = AnimationUtils.loadAnimation(this, R.anim.typewriter_anim);
        text1.startAnimation(animText);

        initAnimations();

        startAnimation1();

        View.OnClickListener stopAnimation1Listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAnimation1();
            }
        };

        text1.setOnClickListener(stopAnimation1Listener);
        button1.setOnClickListener(stopAnimation1Listener);
        info1.setOnClickListener(stopAnimation1Listener);


        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.VISIBLE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.GONE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.GONE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.VISIBLE);
                info3.setVisibility(View.GONE);
                info4.setVisibility(View.GONE);

                text2.startAnimation(animText);
                stopAnimation1();
                startAnimation2();

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.VISIBLE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.GONE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.GONE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.VISIBLE);
                info3.setVisibility(View.GONE);
                info4.setVisibility(View.GONE);

                text2.startAnimation(animText);
                stopAnimation1();
                startAnimation2();
            }
        });
        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.VISIBLE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.GONE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.GONE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.VISIBLE);
                info3.setVisibility(View.GONE);
                info4.setVisibility(View.GONE);

                text2.startAnimation(animText);
                stopAnimation1();
                startAnimation2();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.GONE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.GONE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                info3.setVisibility(View.VISIBLE);
                info4.setVisibility(View.GONE);

                text3.startAnimation(animText);

                stopAnimation2();
                startAnimation3();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.GONE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.GONE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                info3.setVisibility(View.VISIBLE);
                info4.setVisibility(View.GONE);

                text3.startAnimation(animText);
                stopAnimation2();
                startAnimation3();
            }
        });
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.GONE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.VISIBLE);
                button4.setVisibility(View.GONE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                info3.setVisibility(View.VISIBLE);
                info4.setVisibility(View.GONE);

                text3.startAnimation(animText);
                stopAnimation2();
                startAnimation3();
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.VISIBLE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.VISIBLE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                info3.setVisibility(View.GONE);
                info4.setVisibility(View.VISIBLE);

                text4.startAnimation(animText);
                stopAnimation3();
                startAnimation4();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.VISIBLE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.VISIBLE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                info3.setVisibility(View.GONE);
                info4.setVisibility(View.VISIBLE);

                text4.startAnimation(animText);
                stopAnimation3();
                startAnimation4();
            }
        });
        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text1.setVisibility(View.GONE);
                text2.setVisibility(View.GONE);
                text3.setVisibility(View.GONE);
                text4.setVisibility(View.VISIBLE);

                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.VISIBLE);

                info1.setVisibility(View.GONE);
                info2.setVisibility(View.GONE);
                info3.setVisibility(View.GONE);
                info4.setVisibility(View.VISIBLE);

                text4.startAnimation(animText);
                stopAnimation3();
                startAnimation4();
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAnimation4();
                startActivity(new Intent(StoryActivity.this, MainActivity.class));
                finish();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAnimation4();
                startActivity(new Intent(StoryActivity.this, MainActivity.class));
                finish();
            }
        });
        info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAnimation4();
                startActivity(new Intent(StoryActivity.this, MainActivity.class));
                finish();
            }
        });


    }

    private void startAnimation1() {
        animationUp1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button1.startAnimation(animationDown1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animationDown1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button1.startAnimation(animationUp1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        button1.startAnimation(animationUp1);
    }
    private void stopAnimation1() {
        animationUp1.setAnimationListener(null);
        animationDown1.setAnimationListener(null);
        button1.clearAnimation();
    }

    private void startAnimation2() {
        animationUp2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button2.startAnimation(animationDown2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animationDown2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button2.startAnimation(animationUp2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        button2.startAnimation(animationUp2);
    }
    private void stopAnimation2() {
        animationUp2.setAnimationListener(null);
        animationDown2.setAnimationListener(null);
        button2.clearAnimation();
    }

    private void startAnimation3() {
        animationUp3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button3.startAnimation(animationDown3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animationDown3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button3.startAnimation(animationUp3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        button3.startAnimation(animationUp3);
    }
    private void stopAnimation3() {
        animationUp3.setAnimationListener(null);
        animationDown3.setAnimationListener(null);
        button3.clearAnimation();
    }

    private void startAnimation4() {
        animationUp4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button4.startAnimation(animationDown4);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        animationDown4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button4.startAnimation(animationUp4);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        button4.startAnimation(animationUp4);
    }
    private void stopAnimation4() {
        animationUp4.setAnimationListener(null);
        animationDown4.setAnimationListener(null);
        button4.clearAnimation();
    }


    private void initAnimations() {
        // Inisialisasi animasi pertama
        animationUp1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f);
        animationUp1.setDuration(1000);
        animationUp1.setFillAfter(true);

        animationDown1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animationDown1.setDuration(1000);
        animationDown1.setFillAfter(true);

        // Inisialisasi animasi kedua
        animationUp2 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f);
        animationUp2.setDuration(1000);
        animationUp2.setFillAfter(true);

        animationDown2 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animationDown2.setDuration(1000);
        animationDown2.setFillAfter(true);

        // Inisialisasi animasi ketiga
        animationUp3 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f);
        animationUp3.setDuration(1000);
        animationUp3.setFillAfter(true);

        animationDown3 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animationDown3.setDuration(1000);
        animationDown3.setFillAfter(true);

        // Inisialisasi animasi keempat
        animationUp4 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f);
        animationUp4.setDuration(1000);
        animationUp4.setFillAfter(true);

        animationDown4 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.2f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        animationDown4.setDuration(1000);
        animationDown4.setFillAfter(true);
    }




}
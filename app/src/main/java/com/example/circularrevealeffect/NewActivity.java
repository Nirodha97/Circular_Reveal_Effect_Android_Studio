package com.example.circularrevealeffect;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

public class NewActivity extends AppCompatActivity {

    View background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        background = findViewById(R.id.background);
        if(savedInstanceState==null){
            background.setVisibility(View.INVISIBLE);
            final ViewTreeObserver viewTreeObserver= background.getViewTreeObserver();
            if (viewTreeObserver.isAlive()){
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        CircularReveal();
                        background.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    }
                });
            }
        }
    }

    private void CircularReveal() {
        int cx=(int) background.getRight()-getDips(200);  // get dips agayan wenas karala madata ganda
        int cy =(int) background.getBottom()-getDips(300);
        float finalRadius=(float) Math.hypot(background.getWidth(),background.getHeight());
        Animator anim = ViewAnimationUtils.createCircularReveal(background,cx,cy,0,finalRadius);
        background.setVisibility(View.VISIBLE);
        anim.setDuration(3000);
        anim.start();
    }

    private int getDips(int i) {
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                i,
                resources.getDisplayMetrics()
        );
    }
}
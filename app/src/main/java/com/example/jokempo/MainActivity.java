package com.example.jokempo;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView jogador1;
    ImageView jogador2;

    int jogada1;
    int jogada2;

    ImageButton rock;
    ImageButton paper;
    ImageButton scissors;

    MediaPlayer mediaPlayer;

    Animation some;
    Animation aparece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        jogador1 = findViewById(R.id.imagePlayer1);
        jogador2 = findViewById(R.id.imagePlayer2);
        rock = findViewById(R.id.imageButtonRock);
        paper = findViewById(R.id.imageButtonPaper);
        scissors = findViewById(R.id.imageButtonScissors);

        mediaPlayer = MediaPlayer.create(this, R.raw.alexplay);

        some = new AlphaAnimation(1,0);
        aparece = new AlphaAnimation(0,1);

        some.setDuration(1500);
        aparece.setDuration(600);

        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {

                jogador2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

                jogador2.setVisibility(View.VISIBLE);
            }
        });

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                verificaJogada();
                jogador2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaInimigo();
                jogador2.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void verificaJogada() {
        if(jogada1 == jogada2){
            Toast.makeText(this,"Empate!", Toast.LENGTH_SHORT).show();
        }
        else if((jogada1 == 1 && jogada2 == 3) || (jogada1 == 2 && jogada2 == 1) || (jogada1 == 3 && jogada2 == 2)){
            Toast.makeText(this,"Você ganhou!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this,"Você perdeu!", Toast.LENGTH_SHORT).show();
    }

    private void sorteiaInimigo() {
        Random r = new Random();
        int jogada = r.nextInt(3);

        switch(jogada){
            case 0:
                jogada2 = 1;
                jogador2.setImageResource(R.drawable.pedra);
                break;
            case 1:
                jogada2 = 2;
                jogador2.setImageResource(R.drawable.papel);
                break;
            case 2:
                jogada2 = 3;
                jogador2.setImageResource(R.drawable.tesoura);
                break;
            default:
                jogador2.setImageResource(R.drawable.interrogacao);
        }
    }

    public void buttonClick(View view){

        soundEffect();
        int clickedViewId = view.getId();
        if (clickedViewId == R.id.imageButtonRock) {
            jogada1 = 1;
            jogador1.setImageResource(R.drawable.pedra);
        } else if (clickedViewId == R.id.imageButtonPaper) {
            jogada1 = 2;
            jogador1.setImageResource(R.drawable.papel);
        } else if (clickedViewId == R.id.imageButtonScissors) {
            jogada1 = 3;
            jogador1.setImageResource(R.drawable.tesoura);
        } else {
            jogador1.setImageResource(R.drawable.interrogacao);
        }

        jogador2.startAnimation(some);

        jogador2.startAnimation(aparece);
    }

    private void soundEffect() {

        if(mediaPlayer != null){
            mediaPlayer.start();
        }

    }
}
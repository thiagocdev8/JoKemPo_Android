package com.example.jokempo;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView jogador1;
    ImageView jogador2;

    ImageButton rock;
    ImageButton paper;
    ImageButton scissors;

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
    }

    public void buttonClick(View view){
        int clickedViewId = view.getId();
        if (clickedViewId == R.id.imageButtonRock) {
            jogador1.setImageResource(R.drawable.pedra);
        } else if (clickedViewId == R.id.imageButtonPaper) {
            jogador1.setImageResource(R.drawable.papel);
        } else if (clickedViewId == R.id.imageButtonScissors) {
            jogador1.setImageResource(R.drawable.tesoura);
        } else {
            jogador1.setImageResource(R.drawable.interrogacao);
        }
    }
}
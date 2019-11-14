package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroDetailActivity extends AppCompatActivity {

    private TextView textName;
    private TextView textDescription;
    private TextView textSuperpower;
    private TextView textRanking;
    private ImageView imageViewPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);

        wireWidgets();
        displayDetail();
    }

    private void displayDetail() {
        Intent lastIntent = getIntent();
        Hero hero = lastIntent.getParcelableExtra(HeroesListActivity.HERO);

        textName.setText(hero.getName());
        textDescription.setText(hero.getDescription());
        textSuperpower.setText(hero.getSuperpower());
        textRanking.setText(hero.getRanking()+"");
        int resourceImage = getResources().getIdentifier(hero.getImage(), "drawable", getPackageName());
        imageViewPortrait.setImageDrawable(getResources().getDrawable(resourceImage));


    }

    private void wireWidgets() {
        textName = findViewById(R.id.textView_name);
        textDescription = findViewById(R.id.textView_description);
        textSuperpower = findViewById(R.id.textView_superpower);
        textRanking = findViewById(R.id.textView_ranking);
        imageViewPortrait = findViewById(R.id.imageview_portrait);
    }
}

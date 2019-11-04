package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class HeroesListActivity extends AppCompatActivity {

    public static final String TAG = "HeroListActivity";
    private ListView listViewHero;
    private List heroList;
    public static final String HERO = "Hero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);
        wireWidgets();
        setListeners();

        InputStream jsonFileInputStream = getResources().openRawResource(R.raw.heroes);
        String hero = readTextFile(jsonFileInputStream);
        // create a gson object
        Gson gson = new Gson();
        // read your json file into an array of questions
        Hero[] heroes =  gson.fromJson(hero, Hero[].class);
        // convert your array to a list using the Arrays utility class
        List<Hero> heroList = Arrays.asList(heroes);
        // verify that it read everything properly
        Log.d(TAG, "onCreate: " + heroList.toString());

        this.heroList = heroList;

        ArrayAdapter adapter = new HeroAdapter(heroList);
        listViewHero.setAdapter(adapter);
    }

    private void setListeners() {
        listViewHero.setOnItemClickListener();
    }

    private void wireWidgets() {
        listViewHero = findViewById(R.id.listView_hero);
    }


    // reading the text file from
    // https://stackoverflow.com/questions/15912825/how-to-read-file-from-res-raw-by-name
    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    private class HeroAdapter extends ArrayAdapter<Hero>{

        private List<Hero> heroesList;
        public HeroAdapter(List<Hero> heroesList) {
            // since we're in the HeroListActivity class, we already have the context
            super(HeroesListActivity.this, -1, heroesList);
            this.heroesList = heroesList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 1. inflate a layout
            LayoutInflater inflater = getLayoutInflater();

            // check if convertview is null, if so, replace it
            if(convertView == null) {

                convertView = inflater.inflate(R.layout.item_hero, parent, false);
            }

            // 2. wire widgets & link the hero to those widgets
            TextView textViewName = convertView.findViewById(R.id.textView_heroitem_name);
            TextView textViewRank = convertView.findViewById(R.id.textView_heroitem_rank);
            TextView textViewDescription = convertView.findViewById(R.id.textView_heroitem_description);

            textViewName.setText(heroesList.get(position).getName());
            textViewRank.setText(heroesList.get(position).getRanking());
            textViewDescription.setText(heroesList.get(position).getDescription());

            // set the values for each widget. use the position parameter variable
            // to get the hero that you need out of the list
            // and set the values for widgets

            // 3. return inflated view
            return convertView;

        }
    }

}

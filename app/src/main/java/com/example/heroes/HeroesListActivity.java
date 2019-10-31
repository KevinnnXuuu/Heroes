package com.example.heroes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class HeroesListActivity extends AppCompatActivity {

    public static final String TAG = "HeroListActivity";
    private ListView listViewHero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_list);
        wireWidgets();

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

        ArrayAdapter adapter = new ArrayAdapter<Hero>(this, android.R.layout.simple_list_item_activated_1, heroList);
        listViewHero.setAdapter(adapter);
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
}

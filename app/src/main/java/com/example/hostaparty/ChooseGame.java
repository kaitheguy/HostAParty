package com.example.hostaparty;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ChooseGame extends Activity {
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_game);
		
	 	lv = (ListView) findViewById(R.id.gamesLv);
		
		List<String> games_list = new ArrayList<String>();
		
		games_list.add("Trivia");
		games_list.add("Bingo");
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                games_list );
		lv.setAdapter(arrayAdapter);
				
	}
}

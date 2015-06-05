package com.example.hostaparty;

import com.example.hostaparty.inputQuest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddQuiz extends Activity {
	
	private int QuestionNum;
	private String [] questions;
	private String [] answer;
	private Button submitButton;
	private TextView questionView;
	private TextView answerView;
	private EditText answerText;
	EditText quizNameEdit;
	
	String quizName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_quiz_layout);
		questions = new String[]{ "What up Mofo", "hi", "Name"};
		questionView = (TextView)findViewById(R.id.QuestionTextView);
		questionView.setText(questions[0] + "?");
		submitButton = (Button)findViewById(R.id.submitBT);
		QuestionNum = 0;
		
		quizNameEdit = (EditText)findViewById(R.id.quizNameEt);
		
submitButton.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				//QuestionNum++;
				//questionView.setText(questions[QuestionNum] + "?");
				quizName = quizNameEdit.getText().toString();
				changeAct();
			}});
		
		
	}
	
	
	public void changeAct(){
		
		 Intent intent = new Intent(this, inputQuest.class);
		 Bundle b = new Bundle();
		 b.putString("quiz_name", quizName);
			intent.putExtras(b);
		 startActivity(intent);


	};
	

}
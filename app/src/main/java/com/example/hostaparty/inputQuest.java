package com.example.hostaparty;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class inputQuest extends Activity {

	
		TextView enterQuestion, enterAnswer;
		Button nextBtn, submitBtn;
		EditText questionEdt, answerEdt;
		String[] answer_sheet;
		String[] questions = new String[20];
		String[] answers = new String[20];
			int questionCount = 0;
	     @Override
	    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enterq);
		//nextBtn.setText()
		enterQuestion = (TextView)findViewById(R.id.textView1);
		enterAnswer = (TextView)findViewById(R.id.textView2);
		nextBtn = (Button)findViewById(R.id.button1);
		//submitBtn = (Button)findViewById(R.id.button2);
		
		questionEdt = (EditText)findViewById(R.id.editText1);
		answerEdt = (EditText)findViewById(R.id.editText2);
		
		nextBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				questions[questionCount] = questionEdt.getText().toString();
				answers[questionCount] = answerEdt.getText().toString();
				String quiz = "";
				questionCount++;
				for(int i = 0; i < questionCount; i++) {
					quiz += "Question: " + questions[i] + " Answer: " + answers[i] + "\n";
				}
				
				enterQuestion.setText(quiz);
				
			}
			
		});
		
		
	}



}

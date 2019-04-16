package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView textQuestion;
    private TextView textScore;
    private TextView textQuestionCount;
    private TextView textCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirm;

    private ColorStateList textColorDefaultRb;

    private List<QuizQuestion> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private QuizQuestion currentQuestion;

    private int score;
    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textQuestion = findViewById(R.id.questionText);
        textScore = findViewById(R.id.textScore);
        textQuestionCount = findViewById(R.id.questionNo);
        textCountDown = findViewById(R.id.timerView);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.answer1);
        rb2 = findViewById(R.id.answer2);
        rb3 = findViewById(R.id.answer3);
        rb4 = findViewById(R.id.answer4);
        buttonConfirm = findViewById(R.id.nextButton);

        textColorDefaultRb = rb1.getTextColors();


        //change from sqlite boilerplate to ROOM
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!answered){
                    if (rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()){
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }

        });
    }

    private void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        if(questionCounter<questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            textQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getAnswerA());
            rb2.setText(currentQuestion.getAnswerB());
            rb3.setText(currentQuestion.getAnswerC());
            rb4.setText(currentQuestion.getAnswerD());

            questionCounter++;
            textQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirm.setText("Confirm");
        } else {
            finishQuiz();
        }

    }

    private void checkAnswer(){
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNo = rbGroup.indexOfChild(rbSelected) + 1;

        if(answerNo==currentQuestion.getCorrectAnswer()){
            score++;
            textScore.setText("Score: " + score);
        }

        showSolution();

    }

    private void showSolution(){
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getCorrectAnswer()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textQuestion.setText("Answer A is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textQuestion.setText("Answer B is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textQuestion.setText("Answer C is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                textQuestion.setText("Answer D is correct");
                break;
        }

        if (questionCounter < questionCountTotal){
            buttonConfirm.setText("Next");
        } else{
            buttonConfirm.setText("Finish");
        }

    }
    private void finishQuiz(){
        finish();
    }


}

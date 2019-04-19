package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.example.assignmentapplication.MasterQuizActivity.CATEGORY;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    public static final String TOTAL_QUESTIONS = "totalQuestions";
    private static final long COUNTDOWN_IN_MILLIS = 20000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";
    private String category;

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
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftinMillis;

    private ArrayList<QuizQuestion> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private QuizQuestion currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;


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
        textColorDefaultCd = textCountDown.getTextColors();

        Intent i = getIntent();
        category = i.getStringExtra(CATEGORY);

        setTitle(i.getStringExtra(CATEGORY) + " Quiz");

        if(savedInstanceState==null) {
            //change from sqlite boilerplate to ROOM
            QuizDbHelper dbHelper = new QuizDbHelper(this);
            questionList = dbHelper.getQuestions(category);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            //if (questionList==null){
            //    finish();
            //}
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftinMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered){
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }

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

            timeLeftinMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        } else {
            finishQuiz();
        }

    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftinMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftinMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int)(timeLeftinMillis/1000)/60;
        int seconds = (int)(timeLeftinMillis/1000)%60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textCountDown.setText(timeFormatted);

        if (timeLeftinMillis<5000){
            textCountDown.setTextColor(Color.RED);
        } else {
            textCountDown.setTextColor(textColorDefaultCd);
        }
    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNo = rbGroup.indexOfChild(rbSelected) + 1;

        if(answerNo==currentQuestion.getCorrectAnswer()){
            score++;
            textScore.setText("Score: " + score);
        }

        showSolution();

    }

    private void showSolution(){
        rb1.setTextColor(getResources().getColor(R.color.colorIncorrect));
        rb2.setTextColor(getResources().getColor(R.color.colorIncorrect));
        rb3.setTextColor(getResources().getColor(R.color.colorIncorrect));
        rb4.setTextColor(getResources().getColor(R.color.colorIncorrect));

        switch (currentQuestion.getCorrectAnswer()){
            case 1:
                rb1.setTextColor(getResources().getColor(R.color.colorCorrect));
                textQuestion.setText("A is correct");
                break;
            case 2:
                rb2.setTextColor(getResources().getColor(R.color.colorCorrect));
                textQuestion.setText("B is correct");
                break;
            case 3:
                rb3.setTextColor(getResources().getColor(R.color.colorCorrect));
                textQuestion.setText("C is correct");
                break;
            case 4:
                rb4.setTextColor(getResources().getColor(R.color.colorCorrect));
                textQuestion.setText("D is correct");
                break;
        }

        if (questionCounter < questionCountTotal){
            buttonConfirm.setText("Next");
        } else{
            buttonConfirm.setText("Finish");
        }

    }
    private void finishQuiz(){
        Intent intent = new Intent(QuizActivity.this, QuizResultActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        intent.putExtra(TOTAL_QUESTIONS, questionList.size());
        intent.putExtra(CATEGORY, category);
        //setResult(RESULT_OK, resultIntent);
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftinMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);

    }
}

package com.example.assignmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static com.example.assignmentapplication.MasterQuizActivity.CATEGORY;

//A lot of the code to do with quiz functionality has been learnt from https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/
//However, the code has been adapted for best practices e.g. ROOM Library instead of SQLite boilerplate code, and AsyncTasks for DB tasks
// and other changes where required to suit our application.
public class QuizActivity extends AppCompatActivity {

    public static final String QUESTIONS_INITIALISED = "questionsInitialised";
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

            SharedPreferences checkDbPrefs =  getSharedPreferences(QUESTIONS_INITIALISED, MODE_PRIVATE);
            if (checkDbPrefs.getInt(QUESTIONS_INITIALISED, 0)!=1) {
                new InsertQuestionsTask().execute();
            } else {
                new QueryQuestionsTask().execute(category);
            }

        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
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

    private class InsertQuestionsTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog progDialog = new ProgressDialog(QuizActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Loading Questions...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            TutorialDatabase db = Room
                    .databaseBuilder(QuizActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();
            db.quizQuestionDao().insert(new QuizQuestion(0,"What style of referencing is used at UNSW?", "APA", "Harvard", "MLA", "Chicago", 2, "Referencing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "A list of references should be displayed A-Z.", "True", "False, they should be displayed by date order", "False, they should be displayed Z-A", "It doesn't matter", 1, "Referencing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Which example presents a correct in-text citation?", "(Smith, 1985)", "(Smith 1985)", "(How to reference by John Smith 1985)", "(source 5)", 2, "Referencing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Which of the following types of sources do NOT need to be referenced?", "News", "Blogs", "Youtube Video", "None of the above", 4, "Referencing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Which of the following would be considered a reliable source for a scientific project?", "Wikipedia", "The Onion", "Journal of Science", "None of the above", 3, "Researching"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Which of the following can be used to find scholarly articles?", "Google Scholar", "EBSCOHost", "JSTOR", "All of the above", 4, "Researching"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Vincent is required to submit a list of only the sources he used in his assignment. What should he submit with his assignment?", "A Reference List", "A citations Database List", "A photo of himself", "A bibliography", 1, "Referencing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Look at the reference below. What type of publication is it?\n" + "Cardwell, M. (2010) A-Z psychology handbook. 4th ed. Deddington: Philip Allan Updates.",  "Journal article", "Website ", "Book","None of the above", 3, "Referencing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "What is a good reference for a finance research essay?", "Friend's word", "TV Advertisement", "Academic Paper", "None of the Above", 3, "Researching"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Jake enters 'java -programming' into Google's searchbar. He is trying to:", "Get results that include java but not programming", "Get results that include both java and programming", "Get results where java and programming are in the same sentence", "None of the above", 1, "Researching" ));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Putting double quotations marks around a search term: ", "Gets results that are related to quotes someone said", "Gets results that are an exact match", "Gets results that figuratively mean the same thing", "None of the above", 2, "Researching"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Which is the least to be considered when writing a paper?", "Keeping track of relevant articles you've read", "Researching the topic before writing", "Paying attention to vocabulary and grammar","Having a sugary drink to energise yourself", 4, "Writing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Which of the following can adversely affect your marks?", "Using as much complex vocabulary as possible", "Stating points clearly", "Citing references appropriately", "Being concise", 1, "Writing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Which of the following is not important when writing an assignment?", "Paying attention to the assignment question", "The lecturer's name", "The key concepts", "Limiting and qualifying words", 2, "Writing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "What is a good way of gaining a deeper understanding of a word?","Using Cambridge Dictionary", "Using Longman Dictionary", "Using Urban Dictionary", "A and B are correct", 4, "Writing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "The difference between an abstract and an introduction is:", "An abstract is at the end of a report", "There is no difference. Both are the same", "An abstract summarises the report in full", "An introduction summarises the report in full", 3, "Writing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "The conclusion of an essay:", "Introduces the topic", "Re-summarises the main points", "Is where you develop your argument", "All of the above", 2, "Writing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "The components of an essay's body paragraph could follow:", "Evidence, Analysis, Topic Sentence", "Topic Sentence, Evidence, Analysis", "Analysis, Evidence, Topic Sentence", "None of the above", 2, "Writing"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "Which part of a scholarly article would be useful for your reading?", "Abstract", "Introduction", "Reference List", "All of the above", 4, "Researching"));
            db.quizQuestionDao().insert(new QuizQuestion(0, "A correct way for putting authors in a reference list is:", "Use 'First Author' et al. when there are multiple authors", "Write their full names", "Use initial for surname e.g. 'Max S' for Max Smith", "Use initials for first name e.g. 'Smith, J' for John Smith", 4, "Referencing"));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            SharedPreferences prefs = getSharedPreferences(QUESTIONS_INITIALISED, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(QUESTIONS_INITIALISED, 1);
            editor.apply();
            progDialog.dismiss();
            new QueryQuestionsTask().execute(category);
        }
    }

    private class QueryQuestionsTask extends AsyncTask<String, Void, ArrayList<QuizQuestion>>{

        ProgressDialog progDialog = new ProgressDialog(QuizActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Loading Questions...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected ArrayList<QuizQuestion> doInBackground(String... category) {
            TutorialDatabase db = Room
                    .databaseBuilder(QuizActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();
            ArrayList<QuizQuestion> myQuestionList = (ArrayList<QuizQuestion>) db.quizQuestionDao().getCategoryQuestions(category[0]);
            return myQuestionList;
        }

        @Override
        protected void onPostExecute(ArrayList<QuizQuestion> quizQuestions) {
            questionList = quizQuestions;
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
            showNextQuestion();
            progDialog.dismiss();
        }
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

        if (timeLeftinMillis<6000){
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
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        startActivity(intent);
        finish();
    }

    //if wanting to treat cancelling as still finishing the quiz, use finishQuiz() instead.
    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            finish();
            //finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to cancel quiz. No results will be saved.", Toast.LENGTH_SHORT).show();
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

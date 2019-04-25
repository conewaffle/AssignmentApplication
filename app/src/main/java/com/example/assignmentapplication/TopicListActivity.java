package com.example.assignmentapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Query;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class TopicListActivity extends AppCompatActivity {

    public static final String DATABASE_INITIALISED = "databaseInitialised";

    private ProgressDialog progDialog;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private TopicAdapter mAdapter;
    private TopicAdapter mAdapter2;
    private TopicAdapter mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_list);
        setTitle("Tutorials");

        progDialog = new ProgressDialog(TopicListActivity.this);

        recyclerView1 = findViewById(R.id.recycler1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2 = findViewById(R.id.recycler2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView3 = findViewById(R.id.recycler3);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new TopicAdapter(new ArrayList<Tutorial>());
        mAdapter2 = new TopicAdapter(new ArrayList<Tutorial>());
        mAdapter3 = new TopicAdapter(new ArrayList<Tutorial>());

        recyclerView1.setAdapter(mAdapter);
        recyclerView2.setAdapter(mAdapter2);
        recyclerView3.setAdapter(mAdapter3);

        //this value will determine whether the app will insert tutorials into database, or proceed directly to querying them
        SharedPreferences checkDbPrefs =  getSharedPreferences(DATABASE_INITIALISED, MODE_PRIVATE);

        //since we don't assign the value (1) for DATABASE_INITIALISED until insertDbTask, we check for inequality to 1 rather than equality to 0
        if (checkDbPrefs.getInt(DATABASE_INITIALISED, 0)!=1) {
            new InsertDBTask().execute();
        } else {
            new QueryDBTask().execute();
        }

    }

    //result is an ArrayList of ArrayLists of tutorials for each category/topic of learning
    private class QueryDBTask extends AsyncTask<Void, Void, ArrayList<ArrayList<Tutorial>>> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Loading Topics...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected ArrayList<ArrayList<Tutorial>> doInBackground(Void... voids){
            TutorialDatabase db = Room
                    .databaseBuilder(TopicListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

                //casting the List into ArrayList, since the DAO was not working with ArrayLists
                ArrayList<Tutorial> set1 = (ArrayList<Tutorial>) db.tutorialDao().getWritingTutorials();
                ArrayList<Tutorial> set2 = (ArrayList<Tutorial>) db.tutorialDao().getResearchingTutorials();
                ArrayList<Tutorial> set3 = (ArrayList<Tutorial>) db.tutorialDao().getReferencingTutorials();
                ArrayList<ArrayList<Tutorial>> masterList = new ArrayList<>();
                masterList.add(set1);
                masterList.add(set2);
                masterList.add(set3);
                return masterList;
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<Tutorial>> tutorials){
            mAdapter.setTutorials(tutorials.get(0));
            mAdapter.notifyDataSetChanged();
            mAdapter2.setTutorials(tutorials.get(1));
            mAdapter2.notifyDataSetChanged();
            mAdapter3.setTutorials(tutorials.get(2));
            mAdapter3.notifyDataSetChanged();
            progDialog.dismiss();
        }
    }


    //write tutorials here. the task only executes the inserts if the database does not contain any tutorials.
    //onPostExecute then executes QueryDBTask to ensure serial processing of InsertDBTask and QueryDBTask
    private class InsertDBTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void...voids){
            TutorialDatabase db = Room
                    .databaseBuilder(TopicListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();
                db.tutorialDao().insert(new Tutorial("Approaching Your Assignment Question", "This tutorial will show you how to break down your assignment question.", "Writing", "8Zlyid0KySE", "When you get an assignment, identify the key parts so you understand what you are being asked. In this case, \"Managers who understand the culture of their workplace are more likely to succeed in bringing about positive change. Discuss with reference to 2 case studies.\"",0));
                db.tutorialDao().insert(new Tutorial("Introduction to Harvard Referencing", "This tutorial will introduce Harvard Referencing", "Referencing", "iueqJ78iAwk", "During your assignment writing, if you don’t credit your source or present the ideas or words from another as your own, either intentionally or unintentionally, you may be guilty of plagiarism. In this video, we will show you how to properly reference using Harvard referencing method, including how to make in-text citation, reference list and the format of the Harvard method, such as book reference. \n" + "\n" + "Studying is not a straight path, you’ll need to study other people’s ideas, words and websites, so you can form your ideas. If you don’t credit your source or present the ideas or words from another as your own, either intentionally or unintentionally, you may be guilty of plagiarism. In this topic, we will show you how to reference using Harvard referencing method properly.\n" + "\n" +"In-text citation: allow your readers to link your key points to where your information was sourced. All sources cited with the exception of personal communication and references to classical works need to include an alphabetical reference, a list with the full bibliographical details of all sources at the end of your document. Regardless of whether you are using a direct quote or paraphrasing, you must use an in-text citation.\n" + "\n" + "Before you submit your assignment, don’t forget to check all your in-text citations with your reference list.",0));
                db.tutorialDao().insert(new Tutorial("Harvard Referencing Tutorial", "This tutorial will show you how to reference different sources.", "Referencing", "prETpsgBU4w", "In this video, we will teach you how to make Harvard reference in your assignment. This video shows what must be included when using Harvard Reference in terms of the reference format of a book, journal article and website with providing some real examples for each type of resources. \n" + "\n" + "Book reference: for a book reference, you must include the book author, editor, year of publication, book title, book edition, volume number, place of publication and publisher. These details can be found on the title page and imprint page of a book. \n" + "\n" + "Journal article: for a journal article, you must include the author’s name, year of publication, the title of the article, the title of the journal, volume and issue number and page range of the article. These details usually can be found on the front page of the article and sometimes on the front cover of the journal. \n" + "\n" + "Website: for a website, you must include the author’s name, the year the page was created or updated, title of the webpage, the full URL and the date you accessed the webpage.",0));
                db.tutorialDao().insert(new Tutorial("How to Write Assignments Quickly", "This tutorial will discuss how you can write assignments quickly.", "Writing", "DS2DOEkorDo", "Writing an essay may be a very time-consuming assignment to do. This video will give you some tips on how to speed up the essay writing process and the essay was worth reading and communicating a well-structured data. \n" + "\n" + "When you are writing a research paper, the process of finding sources to back up your arguments can be the most time-consuming parts. The writing of an essay can be divided into different stages. The first stage is the research stage. When you are looking for your sources, first start with general sources which are books that easy to find and can gloss over everything within your topic, use those general sources to drill down to find specific resources, which will be very efficient in your research process. \n" + "\n" + "Second stage is starting writing your paper. When writing your essay, the vocabulary you use should also be considered. A study showed that perceptions of intelligence go down when people use needlessly complicated vocabulary, so do not use it, communicate your idea clearly. If you find yourself starting at a blank page, we recommend you to write body paragraphs first and save the intro and conclusion for last. \n" + "\n" + "The last stage is to use a citation generator because they really speed up the process of creating a bibliography or a work cited.",0));
                db.tutorialDao().insert(new Tutorial("Write Better Essays", "This tutorial will discuss how you can write better essays.", "Writing", "9dOBq2PGynA", "Vocabulary and grammar are also important parts of your essay. This video will provide you with a whole range of different websites you can use to improve your academic vocabulary and fluency in writing. \n" + "\n" + "The first is Cambridge Dictionary, an English to English dictionary which is recommended to international students to use due to it's trustworthy and reputable in the Cambridge dictionary, it provides the form of the words, pronunciation and the phonetics. Most importantly is that it gives a definition or an explanation of the word. A more detailed dictionary is Longman dictionary of contemporary English, which provide more details for the words, especially it will tell you that if the word is in the academic word list.  \n" + "\n" + "The next website is called Synonyms, where provide a whole list of similar words the darker words are more similar to the word you type and the lighter words are not as similar. A website called cite this for me which will help you create the references, including Harvard style.",0));
                db.tutorialDao().insert(new Tutorial("Integrating Sources Into Your Writing", "This tutorial will introduce you to using external sources to substantiate your ideas.", "Writing", "Gy-xXWGW-cs", " When you want to use someone else’s words and ideas in the paper you are writing, you need to give accredit by explaining where those words and ideas came from. The three methods are summary, paraphrase and quotation. \n" + "\n" + "Summary: a brief, highly condensed overview that identifies the most important information in a longer text. Using summary, you need to describe main ideas and key findings in your own words, such as giving a brief synopsis of an article or describing the similarities and differences between multiple sources. \n" + "\n" + "Paraphrase: a short selection from the text, translated into your own words. Rephrase the selection using different words, style and sentence structure. A paraphrase should be about the same length of the original selection from the text. Using paraphrase is useful for showing that you grasp a complex concept.\n" + "\n" + "Quotation: a word-for-word excerpt from the text, framed by quotation marks, which is only use if the precise wording is important. Quotations are appropriate for introducing new terminology, defining key terms and substantiating qualitative observations.",0));
                db.tutorialDao().insert(new Tutorial("Analysing Scholarly Articles", "This tutorial will teach you how to analyse scholarly articles.", "Writing", "-3OU0x_MxLc", "Sometimes, articles are complicated and intimidating, and students need to know to analyse scholarly articles to save their time and effort. This video will show you how to analyze research by dividing a paper into several sections and explain what each section will generally talk about. \n" + "\n" + "There is a faster way to analyze the source article. To analyze a scholarly article, first, you will read that the long and specific titles which will let you know what the article about without reading it — all information you that you need to cite in your paper can be found in the first page. The abstract on the first page is the summary of the article. By reading it, you can know whether the article is related to your paper. An introduction of the article is where the authors summarize their study. The reference list is very helpful because it gives you some related article that you may willing to read.",0));
                db.tutorialDao().insert(new Tutorial("Finding Online Sources", "This tutorial will show ways to find sources.", "Researching", "33lvmShrcOU", "When researching for your essay, you may always find yourself couldn’t find good quality of research or some original paper. This video will show you an efficient way to find research paper, using paper found in Google Scholar as well as resources used in the document you found.\n" + "\n" + "When you are doing research, some articles you find on Google or some textbook will not be sited in your essay because they might not be original research. Using Google Scholar could let you find the original research paper. The first thing you need to do is to read the abstract of the research which will explain shortly about the research. If it looks good enough, you can find the link to download the PDF of this research. The reference lists of the research you downloaded also provide useful resources for your writing. Using a different combination of the keywords can also help you find better research paper.",0));
                db.tutorialDao().insert(new Tutorial("Using Google Scholar", "This tutorial will show you how to use Google Scholar", "Researching", "bYI1eQTDSAA", "This video will show you how to use Google Scholar for academic research, by introducing the interface of the website and the detail functionalities on the website.\n" + "\n" + "Google Scholar is an excellent tool to search for academic resources including articles, abstracts, books and theses, which also provide basic and advanced searches. Google Scholar can be accessed by typing scholar.google.com into your web browser. Under the search result section, you can decide what type of material to search and how to manage your results.",0));
                db.tutorialDao().insert(new Tutorial("Search Engine Tools", "This tutorial will introduce you to Google's search tools to help you narrow down your sources.", "Researching", "L9AR5k0mUbE", "Sometimes, we find some resources that are not reliable and could not be sited in our essay. This video will show you how can you dig into Google search for your research and narrow down searches in Google in different ways. \n" + "\n" + "If you are researching on Google, please make sure that you do not use Wikipedia because it is not scholarly and it is not reliable. To dig into your search, you can use different format and combination of your keywords. You can also add filter when searching.  For example, “site:nytimes.com ~college “test scores” 2008..2010” will gives you a result from New York Times related to college and text scores from 2008 to 2010. It will narrow down plenty of useless result.",0));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            SharedPreferences prefs = getSharedPreferences(DATABASE_INITIALISED, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(DATABASE_INITIALISED, 1);
            editor.apply();
            new QueryDBTask().execute();
        }
    }

    private class UpdateTutorial extends AsyncTask<Tutorial, Void, Void>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progDialog.setMessage("Updating Tutorial...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();
        }

        @Override
        protected Void doInBackground(Tutorial...tutorials){
            TutorialDatabase db = Room
                    .databaseBuilder(TopicListActivity.this, TutorialDatabase.class, "tutorial-database")
                    .build();

            db.tutorialDao().updateTutorial(tutorials[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new QueryDBTask().execute();
        }
    }

    public void updateTutorials(Tutorial tutorial){
        UpdateTutorial updateTutorialTask = new UpdateTutorial();
        updateTutorialTask.execute(tutorial);
    }
}

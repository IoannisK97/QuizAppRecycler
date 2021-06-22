package com.example.jandroid.quizapprecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClick {

    private RecyclerView recyclerView;
    private QuizAdapter qAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        qAdapter = new QuizAdapter(prepareQuizData(), this);
        RecyclerView.LayoutManager qLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(qLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(qAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // set the adapter
        recyclerView.setAdapter(qAdapter);


    }

    public int score=0;
    @Override

    public void onSubmitClick(List<Question> updatedList) {
        for (int i=0; i< updatedList.size() ;i++ ){
            Question tempQuestion= updatedList.get(i);
            if (tempQuestion.getResponse()==true && tempQuestion.getLeftCheckBoxResponse()==true && tempQuestion.getRightCheckBoxResponse()==false){
                score+=1;
            }
            else if (tempQuestion.getResponse()==false && tempQuestion.getLeftCheckBoxResponse()==false && tempQuestion.getRightCheckBoxResponse()==true){
                score+=1;
            }

        }
        displayMessage();

    }


    public List<Question> prepareQuizData() {

        List<Question> questionList = new ArrayList<>();

        Question question = new Question("An octopus has three hearts", true);
        questionList.add(question);

        question = new Question("There are five different blood groups", false);
        questionList.add(question);

        question = new Question("Serena Williams has won more singles Grand Slams than her sister Venus", true);
        questionList.add(question);

        question = new Question("Australia is wider than the moon", true);
        questionList.add(question);

        question = new Question("Ariana Grande is younger than 25 years old", false);
        questionList.add(question);

        question = new Question("Marrakesh is the capital of Morroco", false);
        questionList.add(question);

        question = new Question("M and M stands for Mars and Moordale", false);
        questionList.add(question);

        question = new Question("Venus is the hottest place in our solar system", true);
        questionList.add(question);

        question = new Question("Alexander Flaming discovered penicilin", true);
        questionList.add(question);

        question = new Question("The great wall of China is longer than the distance between London and Beijing", true);
        questionList.add(question);

        return questionList;

    }

    public void displayMessage() {
        TextView textViewMessage = (TextView) findViewById(R.id.message_view);
        String newMessage;
        if (score<3){
            newMessage = "Not so good. You only found "+score+"/10 answers";
        }
        else if (score<7){
            newMessage="You could have done better. You found "+score+"/10 answers";
        }
        else if (score<10){
            newMessage="You are almost there. You found " +score+"/10 answers";
        }
        else{
            newMessage="Perfect. you found "+score+"/10 answers";
        }
        textViewMessage.setText(newMessage);



    }



}
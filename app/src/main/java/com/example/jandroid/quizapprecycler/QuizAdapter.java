package com.example.jandroid.quizapprecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NUMBER_OF_BUTTONS = 1;

    private int QUESTIONS_HOLDER = 1;
    private int BUTTON_HOLDER = 2;

    private List<Question> questionsList = new ArrayList<>();
    private OnItemClick mCallback;

    public class MyViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {
        public TextView question;
        public CheckBox cbTrue;
        public CheckBox cbFalse;


        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question);
            cbTrue = (CheckBox) view.findViewById(R.id.checkTrue);
            cbFalse = (CheckBox) view.findViewById(R.id.checkFalse);
        }

        public void bind() {
            Question questionModel = questionsList.get(getAdapterPosition());
            question.setText(questionModel.getQuestionText());

            cbTrue.setChecked(questionModel.getLeftCheckBoxResponse());
            cbFalse.setChecked(questionModel.getRightCheckBoxResponse());

            cbTrue.setOnCheckedChangeListener(this);
            cbFalse.setOnCheckedChangeListener(this);

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Question questionModel = questionsList.get(getAdapterPosition());
            if (cbTrue.getId() == buttonView.getId()) {
                questionModel.setLeftCheckBoxResponse(isChecked);
            } else if (cbFalse.getId() == buttonView.getId()) {
                questionModel.setRightCheckBoxResponse(isChecked);
            }
        }
    }

    public class ButtonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public Button buttonSubmitAnswers;

        public ButtonHolder(View view) {
            super(view);
            buttonSubmitAnswers = (Button) view.findViewById(R.id.submitAnswersButton);
        }

        public void bind() {
            buttonSubmitAnswers.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mCallback != null) {
                mCallback.onSubmitClick(questionsList);
            }
        }
    }

    public QuizAdapter(List<Question> questionsList, OnItemClick mCallback) {
        this.mCallback = mCallback;
        this.questionsList.addAll(questionsList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == QUESTIONS_HOLDER) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.quiz_list_row, parent, false));
        } else if (viewType == BUTTON_HOLDER) {
            return new ButtonHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.button, parent, false));
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < questionsList.size())
            return QUESTIONS_HOLDER;
        else return BUTTON_HOLDER;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).bind();
        } else if (holder instanceof ButtonHolder) {
            ((ButtonHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return questionsList.size() + NUMBER_OF_BUTTONS;
    }


}

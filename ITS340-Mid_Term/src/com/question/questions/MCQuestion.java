package com.question.questions;

public class MCQuestion extends Question {
    private String Question;
    private int points;
    private Question[] array;

    public MCQuestion(String Question, int points, Question[] array) {
        super(Question, points);
        this.array = array;
        addToArray();
    }

//	public MCQuestion(String question2) {
//// TODO Auto-generated constructor stub
//	}

    public String getQuestion() {
        return Question;
    }

    public int getPoints() {
        return points;
    }

    public Question[] getArray() {
        return array;
    }

    private void addToArray() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = this;
                break;
            }
        }
    }

    char answer;
}

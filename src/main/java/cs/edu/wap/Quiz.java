package cs.edu.wap;

public class Quiz {
    private String[] questions = new String[]{"3 1 4 1 5", "1 1 2 3 5", "1 4 9 16 25", "2 3 5 7 11", "1 2 4 8 16"};
    private int[] answers = new int[]{9, 8, 36, 13, 32};
    private int score = 0;

    public boolean checkAnswer(int questionIndex, int answer) {
        return answers[questionIndex] == answer;
    }

    public String[] getQuestions() {
        return questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

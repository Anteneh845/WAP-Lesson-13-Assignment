package cs.edu.wap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
    private Quiz quiz;
    private int currentQuestion = 0;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        quiz = (Quiz) session.getAttribute("Quiz");
        currentQuestion = (int) session.getAttribute("CurrentQuestion");
        if (quiz.checkAnswer(currentQuestion, Integer.parseInt(req.getParameter("answer"))))
            quiz.setScore(quiz.getScore() + 1);
        currentQuestion = currentQuestion + 1;
        session.setAttribute("CurrentQuestion", currentQuestion);
        session.setAttribute("Quiz", quiz);
        proceedToQuestion(resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("Quiz") != null) {
            quiz = (Quiz) session.getAttribute("Quiz");
            currentQuestion = (int) session.getAttribute("CurrentQuestion");
        } else {
            session.setAttribute("CurrentQuestion", currentQuestion);
            quiz = new Quiz();
            session.setAttribute("Quiz", quiz);
        }
        proceedToQuestion(resp.getWriter());
    }

    private void proceedToQuestion(PrintWriter writer) {
        if (quiz.getQuestions().length  == currentQuestion) {
            writer.print(resultPage(quiz.getScore()));
        } else
            writer.print(questionPage(quiz.getScore(), quiz.getQuestions()[currentQuestion]));
    }

    private String questionPage(int score, String question) {
        return "" +
                "<html>" +
                "<body>" +
                "<h1> The Number Quiz</h1>" +
                "Your score is " + score + "" +
                "<br/> Guess the number in the sequence" +
                " " + question +
                "<br/> Your answer  <form method='post' action='quiz'> <input type='number' name='answer' /> <br/> <input type='submit' value='Submit'/> </form>" +
                "</body>" +
                "</html>" +
                "";
    }

    private String resultPage(int score) {
        return "" +
                "<html>" +
                "<body>" +
                "<h1> The Number Quiz</h1>" +
                "Your score is " + score + "" +
                "<br/>You have completed the Number Quiz, with a score of " + score + " out of " + quiz.getQuestions().length + "" +
                "</body>" +
                "</html>" +
                "";
    }
}

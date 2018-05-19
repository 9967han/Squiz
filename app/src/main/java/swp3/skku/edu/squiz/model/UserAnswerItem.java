package swp3.skku.edu.squiz.model;

/**
 * Created by LG on 2018-05-18.
 */

public class UserAnswerItem {
    String answer;
    Boolean correct;

    public UserAnswerItem(String answer, Boolean correct) {
        this.answer = answer;
        this.correct = correct;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getAnswer() {

        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}

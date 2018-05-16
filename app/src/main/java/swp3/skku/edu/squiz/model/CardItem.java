package swp3.skku.edu.squiz.model;

import java.io.Serializable;

/**
 * Created by LG on 2018-05-02.
 */

public class CardItem implements Serializable {
    String word;
    String meaning;
    Boolean like = false;

    public CardItem(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}

package swp3.skku.edu.squiz.model;

/**
 * Created by LG on 2018-05-02.
 */

public class CardItem {
    String word;
    String meaning;


    public CardItem(String word, String meaning){
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
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

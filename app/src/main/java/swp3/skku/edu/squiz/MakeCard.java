package swp3.skku.edu.squiz;

import android.app.Activity;

import java.util.ArrayList;

public class MakeCard extends Activity {
    ArrayList<word> word_list=new ArrayList<>();

    public void add_word(word new_word){
        word_list.add(new_word);
    }

    @Override
    public String toString() {
        return word_list.toString();
    }
}

class word{
    String word;
    String meaning;

    public word(String word, String meaning){
        this.word=word;
        this.meaning=meaning;
    }

    @Override
    public String toString() {
        return word.toString();
    }
};


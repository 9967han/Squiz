package swp3.skku.edu.squiz;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import swp3.skku.edu.squiz.model.CardItem;

/**
 * Created by LG on 2018-05-12.
 */

public class FileModifyTask extends AsyncTask<Void, Void, Void> {
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squiz.txt";
    ArrayList<CardItem> cardPageItemList;
    String word;
    String mean;
    
    public FileModifyTask(ArrayList<CardItem> cardPageItemList, String word, String mean) {
        this.cardPageItemList = cardPageItemList;
        this.word = word;
        this.mean = mean;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while((line=reader.readLine())!=null){
                String words[] = line.split(",");
                if(words[1].equals(word) && words[2].equals(mean)){
                    if(words[3].equals("false")){

                    }
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

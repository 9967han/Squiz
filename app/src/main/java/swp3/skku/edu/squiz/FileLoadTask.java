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

public class FileLoadTask extends AsyncTask<Void, Void, Void> {
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squiz.txt";
    ArrayList<CardItem> cardPageItemList;
    String title;

    public FileLoadTask(ArrayList<CardItem> cardPageItemList, String title) {
        this.cardPageItemList = cardPageItemList;
        this.title = title;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            String load_title = "";
            while((line=reader.readLine())!=null){
                String words[] = line.split(",");
                load_title = words[0];
                if(load_title.equals(title)){
                    CardItem cardItem = new CardItem(words[1], words[2]);
                    //TODO 상욱 : if(words[3].equals("false"))?
                    cardPageItemList.add(cardItem);
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

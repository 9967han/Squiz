package swp3.skku.edu.squiz;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import swp3.skku.edu.squiz.model.CardSetItem;

/**
 * Created by LG on 2018-05-12.
 */

public class FileInitTask extends AsyncTask<Void, Void, Void>{
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squiz.txt";
    ArrayList<CardSetItem> cardSetItemList;

    public FileInitTask(ArrayList<CardSetItem> cardSetItemList) {
        this.cardSetItemList = cardSetItemList;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //Log.d("Read path", filePath);
        int count = 0;
        int check = 0;
        try {
            InputStream is = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            String title = "";
            while((line=reader.readLine())!=null) {
                String[] words = line.split(",");
                title = words[0];
                count = Integer.valueOf(words[4]);
                CardSetItem cardSetItem = new CardSetItem(count, title);
                for(CardSetItem cardSetItem1 : cardSetItemList){
                    if(cardSetItem1.getTitle().equals(cardSetItem.getTitle()))  check++;
                }
                if(check == 0) cardSetItemList.add(cardSetItem);
                check = 0;
                for(int i=0; i<count-1; i++) reader.readLine();
            }
            reader.close();
            is.close();
        } catch (IOException e) {
            Log.d("dir", "read path error");
            e.printStackTrace();
        }
        return null;
    }
}

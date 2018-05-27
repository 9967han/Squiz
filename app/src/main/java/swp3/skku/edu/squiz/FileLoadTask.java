package swp3.skku.edu.squiz;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.CardSetItem;

/**
 * Created by LG on 2018-05-12.
 */

public class FileLoadTask extends AsyncTask<Void, Void, Void> {
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squiz.txt";
    ArrayList<CardItem> cardPageItemList;
    String title;

    ArrayList<CardSetItem> cardSetItems;
    final static String filePathfolder = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squizfolder.txt";
    String foldertitle;
    int idx;

    final static String filePathfolderlist = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squizfolderlist.txt";


    ArrayList<ArrayList<String>> folderItemList;
    ArrayList<String> folderItem;

    public FileLoadTask(ArrayList<CardItem> cardPageItemList, String title) {
        this.cardPageItemList = cardPageItemList;
        this.title = title;
        this.idx=0;
    }

    public FileLoadTask(ArrayList<CardSetItem> cardSetItems, String foldertitle, int idx){
        this.cardSetItems=cardSetItems;
        this.foldertitle=foldertitle;
        this.idx=1;
    }

    public FileLoadTask(ArrayList<ArrayList<String>> folderItemList){
        this.folderItemList = folderItemList;
        this.idx=3;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        InputStream is = null;
        if (idx==0){
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
                        if(words[3].equals("false")){
                            cardItem.setLike(false);
                        }else if(words[3].equals("true")){
                            cardItem.setLike(true);
                        }
                        cardPageItemList.add(cardItem);
                    }
                }
                reader.close();
                is.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        else if (idx==1){
            InputStream is1 = null;
            try {
                is1 = new FileInputStream(filePathfolder);
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(is1));
                String line = "";
                String load_title1="";
                while((line=reader1.readLine())!=null){
                    String words[] = line.split(",");
                    load_title1 = words[0];//folder name
                    if(load_title1.equals(foldertitle)){
                        for(int i=1; i<words.length; i++){
                            CardSetItem cardSetItem = new CardSetItem(0,load_title1);
                            cardSetItems.add(cardSetItem);
                        }
                    }
                }
                reader1.close();
                is1.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }

        else if(idx==2){
            InputStream is2 = null;
            try{
                is2 = new FileInputStream(filePathfolderlist);
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(is2));
                String line = "";
                String title = "";
                folderItemList = new ArrayList<ArrayList<String>>();

                while ((line=reader2.readLine())!=null){
                    folderItem = new ArrayList<String>();
                    String folders[] = line.split("\t");
                    title = folders [0];
                    for (int i=0; i<folders.length; i++){
                        folderItem.add(folders[i]);
                    }
                    folderItemList.add(folderItem);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

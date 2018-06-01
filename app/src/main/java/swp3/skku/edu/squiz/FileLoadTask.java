package swp3.skku.edu.squiz;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderList;

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
    int load_case;

    final static String filePathfolderlist = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squizfolderlist.txt";


    ArrayList<FolderList> folderItemList;

    public FileLoadTask(ArrayList<CardItem> cardPageItemList, String title) {
        this.cardPageItemList = cardPageItemList;
        this.title = title;
        this.load_case=0;
    }

    public FileLoadTask(ArrayList<CardSetItem> cardSetItems, String foldertitle, int idx){
        this.cardSetItems=cardSetItems;
        this.foldertitle=foldertitle;
        this.load_case=1;
    }

    public FileLoadTask( ArrayList<FolderList> folderItemList){
        this.folderItemList = folderItemList;
        this.load_case=2;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        InputStream is = null;
        if (load_case==0){
            try {
                is = new FileInputStream(filePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = "";
                String load_title = "";
                while((line=reader.readLine())!=null){
                    String words[] = line.split("[,]");
                    load_title = words[0];
                    if(load_title.equals(title)){
                        CardItem cardItem = new CardItem(words[1], words[2]);
                        if(words[3].trim().equals("false")){
                            cardItem.setLike(false);
                        }else if(words[3].trim().equals("true")){
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
        else if (load_case==1){
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

        else if(load_case==2){
            InputStream is2 = null;
            try{
                is2 = new FileInputStream(filePathfolderlist);
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(is2));
                String line = "";
                String title = "";
                folderItemList = new  ArrayList<FolderList>();

                while ((line=reader2.readLine())!=null){
                    String folders[] = line.split("\t");
                    title = folders [0];
                    FolderList folderList= new FolderList(title);

                    for (int i=1; i<folders.length; i++){
                        folderList.addCardSetInFolder(folders[i]);
                    }
                    folderItemList.add(folderList);
                }

                if(folderItemList.size()==0){
                    InputStream is3 = null;
                    try {
                        is3 = new FileInputStream(filePathfolder);
                        BufferedReader reader1 = new BufferedReader(new InputStreamReader(is3));
                        String line1 = "";
                        String load_title1="";
                        while((line1=reader1.readLine())!=null){
                            String words[] = line1.split("\n");
                            load_title1 = words[0];//folder name
                            FolderList folderList = new FolderList(load_title1);
                            folderItemList.add(folderList);
                        }

                        reader1.close();
                        is3.close();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
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

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

import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderItem;
import swp3.skku.edu.squiz.model.FolderList;

/**
 * Created by LG on 2018-05-12.
 */

public class FileInitTask extends AsyncTask<Void, Void, Void>{
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squiz.txt";
    ArrayList<CardSetItem> cardSetItemList;

    final static String filePathfolder = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squizfolder.txt";
    ArrayList<FolderItem> folderItemList;

    final static String filePathfolderlist = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squizfolderlist.txt";
    ArrayList<FolderList> folderCardsetList;

    int idx=0;

    public FileInitTask(ArrayList<CardSetItem> cardSetItemList) {
        this.cardSetItemList = cardSetItemList;
        this.idx=0;
    }

    public FileInitTask(ArrayList<FolderItem> folderItemList, int i){
        this.folderItemList=folderItemList;
        this.idx=1;
    }

    public FileInitTask(ArrayList<FolderList> folderCardsetList, int i, boolean a){
        this.folderCardsetList = folderCardsetList;
        this.idx = 2;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //Log.d("Read path", filePath);
        int count = 0;
        int check = 0;

        if(idx==0){
            check=0;
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

        }
        else if(idx==1){
            check=0;
            try {
                InputStream is1 = new FileInputStream(filePathfolder);
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(is1));
                String line = "";
                String title = "";
                while((line=reader1.readLine())!=null) {
                    String[] words = line.split("\n");
                    title = words[0];
                    FolderItem folderItem = new FolderItem(title);
                    for(FolderItem folderItem1 : folderItemList){
                        if(folderItem1.getFolder_name().equals(folderItem.getFolder_name()))  check++;
                    }
                    if(check == 0) folderItemList.add(folderItem);
                    check = 0;
                    for(int i=0; i<count-1; i++) reader1.readLine();
                }
                reader1.close();
                is1.close();
            } catch (IOException e) {
                Log.d("dir", "read path error");
                e.printStackTrace();
            }
        }
        else if(idx==2){
            check=0;
            try {
                InputStream is1 = new FileInputStream(filePathfolderlist);
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(is1));
                String line = "";
                String title = "";
                while((line=reader1.readLine())!=null) {
                    String[] words = line.split("\n");
                    title = words[0];
                    FolderList folderList = new FolderList(title);
                    for(FolderList folderList1 : folderCardsetList){
                        if(folderList1.getFoldertitle().equals(folderList1.getFoldertitle()))  check++;
                    }
                    if(check == 0) folderCardsetList.add(folderList);
                    check = 0;
                    for(int i=0; i<count-1; i++) reader1.readLine();
                }
                reader1.close();
                is1.close();
            } catch (IOException e) {
                Log.d("dir", "read path error");
                e.printStackTrace();
            }
        }
       return null;
    }
}

package swp3.skku.edu.squiz;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderItem;
import swp3.skku.edu.squiz.model.FolderList;

/**
 * Created by LG on 2018-05-12.
 */

public class FileOutTask extends AsyncTask<Void, Void, Void> {
    ArrayList<CardItem> cardItemList;
    String title;
    AppCompatActivity appCompatActivity;

    ArrayList<FolderList> folderItemList;
    String folder_name;

    int idx=0;

    ArrayList<CardSetItem> cardSetItems;

    public FileOutTask(AppCompatActivity appCompatActivity, ArrayList<CardItem> cardItemList, String title) {
        this.cardItemList = cardItemList;
        this.title = title;
        this.appCompatActivity = appCompatActivity;
        this.idx=OPCode.Save_Card_Data;
    }

    public FileOutTask(String folder_name){
        this.folder_name=folder_name;
        this.idx=OPCode.Save_Folder_Name_Data;
    }

    public FileOutTask(ArrayList<FolderList> folderItemList){
        this.folderItemList=folderItemList;
        this.idx=OPCode.Save_Folder_Item_Lists;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Squiz");
        //디렉터리가 없을 시 폴더 생성
        if(!dir.exists()) dir.mkdirs();
        if(idx==OPCode.Save_Card_Data){
            File squiz = new File(dir, "squiz.txt");
            //Output Stream 생성
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(squiz, true);  //파일쓰기
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                for(CardItem cardItem : cardItemList){
                    writer.write(title+",");
                    writer.write(cardItem.getWord()+",");
                    writer.write(cardItem.getMeaning()+ ",");
                    writer.write(String.valueOf(false)+",");
                    writer.write(String.valueOf(cardItemList.size()));
                    writer.newLine();
                }
                writer.flush();
                writer.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(idx==OPCode.Save_Folder_Name_Data){
            File squizfolder = new File(dir, "squizfolder.txt");
            //Output Stream 생성
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(squizfolder, true);
                BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(fos));
                if(cardSetItems==null){
                    writer1.write(folder_name+"\n");
                }
                writer1.flush();
                writer1.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (idx==OPCode.Save_Folder_Item_Lists){
            File squizfolderlist = new File(dir, "squizfolderlist.txt");
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(squizfolderlist, false);//덮어쓰기
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
                for(FolderList folderList : folderItemList){
                    writer.write(folderList.getFoldertitle());

                    ArrayList<String> arrayList = new ArrayList<String>();
                    arrayList = folderList.getCardsetInFolder();

                    for(int i=0; i<arrayList.size(); i++){
                        writer.write(","+arrayList.get(i));
                    }
                    writer.newLine();
                }
                writer.flush();
                writer.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

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
import swp3.skku.edu.squiz.model.FolderList;

/**
 * Created by LG on 2018-05-12.
 */

public class FolderLoadTask extends AsyncTask<Void, Void, Void> {
    ArrayList<FolderList> FolderLists;
    String title;
    boolean end;


    public FolderLoadTask(ArrayList<FolderList> FolderLists, String foldertitle, boolean end){
        this.FolderLists = FolderLists;
        this.title=foldertitle;
        end = false;
        this.end = end;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        FolderLists = readFile();
        return null;
    }

    public ArrayList<FolderList> readFile(){
        String filePathfolderlist = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squizfolderlist.txt";
        String filePathfolder = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squizfolder.txt";
        InputStream is2 = null;
        ArrayList<FolderList> folderLists = new  ArrayList<FolderList>();

        try{
            is2 = new FileInputStream(filePathfolderlist);
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(is2));
            String line = "";
            String title = "";

            while ((line=reader2.readLine())!=null){
                String folders[] = line.split(",");
                title = folders [0];
                FolderList folderList= new FolderList(title);

                for (int i=1; i<folders.length; i++){
                    folderList.addCardSetInFolder(folders[i]);
                }
                folderLists.add(folderList);
            }

            if(folderLists.size()==0){
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
                        folderLists.add(folderList);
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
        end = true;
        return folderLists;
    }
}

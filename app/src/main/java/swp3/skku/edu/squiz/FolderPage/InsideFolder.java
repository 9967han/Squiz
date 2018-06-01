package swp3.skku.edu.squiz.FolderPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.Adapter_cardPage;
import swp3.skku.edu.squiz.MakeCard.Adapter_makeCard;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.FolderList;

public class InsideFolder extends AppCompatActivity{
    ArrayList<FolderList> folderLists = new ArrayList<FolderList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_folder);
        folderLists=readFile();
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

        return folderLists;
    }
}

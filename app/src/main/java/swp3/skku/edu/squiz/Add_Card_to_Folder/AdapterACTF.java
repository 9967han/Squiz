package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

import swp3.skku.edu.squiz.FileInitTask;
import swp3.skku.edu.squiz.FileLoadTask;
import swp3.skku.edu.squiz.FileOutTask;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.FolderItem;
import swp3.skku.edu.squiz.model.FolderList;

public class AdapterACTF extends RecyclerView.Adapter<ViewHolder_ACTF>  {
    private ArrayList<FolderItem> folderItemList = new ArrayList<>();
    private ArrayList<FolderList> folderLists = new ArrayList<>();
    String TAG = "Adapter ACTF";
    private int contentLayout;
    private Context context;
    Activity activity;

    @Override
    public ViewHolder_ACTF onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder_ACTF(LayoutInflater.from(parent.getContext()).inflate(R.layout.add_card_to_folder_content, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder_ACTF holder_actf, int position) {
        final FolderItem folderItem = folderItemList.get(position);
        holder_actf.title.setText(String.format(Locale.getDefault(), folderItemList.get(position).getFolder_name()));
        holder_actf.checkBox.setOnCheckedChangeListener(null);

        holder_actf.checkBox.setChecked(folderItem.isSelected());
        holder_actf.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                folderItem.setSelected(isChecked);
            }
        });

    }

    @Override
    public int getItemCount() {
        return folderItemList !=null ? folderItemList.size() : 0;
    }
    //final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/logfile.txt";

    public void updateList(FolderItem folderItem){
        insertItem(folderItem);
    }
    private void insertItem(FolderItem folderItem){
        folderItemList.add(folderItem);
        notifyItemInserted(getItemCount());
    }
    public void initFolderSetData(){
        FileInitTask fileInitTask = new FileInitTask(folderItemList,1);
        fileInitTask.execute();
    }
    public void initFolderListData(){
        FileInitTask fileInitTask = new FileInitTask(folderLists,1,true);
        fileInitTask.execute();
    }
    public void loadItemData() {
        FileLoadTask fileLoadTask = new FileLoadTask(folderLists);
        fileLoadTask.execute();


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
    public void addCardSetToFolder(String title) {
        ArrayList<FolderList> folderItemCheckedList = new ArrayList<>();
        String data ="";
        for(FolderItem folderItem : folderItemList){
            FolderList folderList = new FolderList();
            if(folderItem.isSelected()){
                data = folderItem.getFolder_name();
                folderList.setFoldertitle(data);
                folderList.addCardSetInFolder(title);
                folderItemCheckedList.add(folderList);
            }
        }

        this.folderLists = readFile();

        for(FolderList folderList : folderItemCheckedList){
            for(FolderList folderList1 : folderLists){

                if(folderList.getFoldertitle().equals(folderList1.getFoldertitle())){
                    ArrayList<String> arrayList = new ArrayList<String>();
                    arrayList = folderList1.getCardsetInFolder();
                    if(arrayList.contains(title)){

                    }
                    else {
                        folderList1.addCardSetInFolder(title);
                    }
                }
            }
        }

        FileOutTask fileOutTask = new FileOutTask(folderLists);
        fileOutTask.execute();


    }
}

package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import swp3.skku.edu.squiz.FileInitTask;
import swp3.skku.edu.squiz.FolderPage.InsideFolder;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.FolderItem;

public class AdapterACTF extends RecyclerView.Adapter<ViewHolder_ACTF>  {
    private ArrayList<FolderItem> folderItemList = new ArrayList<>();
    private int contentLayout;
    private Context context;
    Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public CheckBox checkBox;
        public ViewHolder(TextView textView, CheckBox checkBox){
            super(textView);
            this.textView = textView;
            this.checkBox = checkBox;
        }
    }

    public AdapterACTF(ArrayList<FolderItem> myDataset){
        folderItemList = myDataset;
    }
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

}

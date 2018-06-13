package swp3.skku.edu.squiz.Right;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import swp3.skku.edu.squiz.FileInitTask;
import swp3.skku.edu.squiz.FolderPage.InsideFolderActivity;
import swp3.skku.edu.squiz.model.FolderItem;
import swp3.skku.edu.squiz.model.FolderList;

public class Adapter_right extends RecyclerView.Adapter<ViewHolder_right>  {
    private ArrayList<FolderItem> folderItemList = new ArrayList<>();
    private ArrayList<FolderItem> oriFolderList = new ArrayList<>();
    private int contentLayout;
    private Context context;
    Activity activity;
    //final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/logfile.txt";

    public Adapter_right(int contentLayout, Context context, FragmentActivity activity){
        this.contentLayout = contentLayout;
        this.context = context;
        this.activity=activity;
    }

    @Override
    public ViewHolder_right onCreateViewHolder(ViewGroup parent, int viewType) {
        View rightFragment_view;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        rightFragment_view = inflater.inflate(contentLayout, parent, false);
        final ViewHolder_right viewHolder_right = new ViewHolder_right(rightFragment_view);
        viewHolder_right.right_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               int position = viewHolder_right.position;
                String title = folderItemList.get(position).getFolder_name();
//                String count = String.valueOf(folderItemList.get(position).getCount());
                Intent intent = new Intent(activity, InsideFolderActivity.class);
                intent.putExtra("title", title);
//                intent.putExtra("count", count);
                activity.startActivityForResult(intent, 2);
            }
        });
        return viewHolder_right;
    }

    @Override
    public void onBindViewHolder(ViewHolder_right holder, int position) {
        FolderItem folderItem = folderItemList.get(position);
        holder.title.setText(folderItem.getFolder_name());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return folderItemList.size();
    }

    public void initFolderSetData(){
        FileInitTask fileInitTask = new FileInitTask(folderItemList, oriFolderList, 1);
        fileInitTask.execute();
    }

    public boolean addFolderData(FolderItem folderItem) {
        for(FolderItem folderItem1 : folderItemList){
            if(folderItem.getFolder_name().equals(folderItem1.getFolder_name())){
                return false;
            }
        }
        folderItemList.add(folderItem);
        oriFolderList.add(folderItem);
        notifyItemInserted(folderItemList.size()-1);
        return true;
    }

    public void searchSet(String str) {


        if(str.length() == 0) {
            folderItemList.clear();
            folderItemList.addAll(oriFolderList);
        }
        else {
            folderItemList.clear();
            for (int i = 0; i <oriFolderList.size(); i++) {
                if (oriFolderList.get(i).getFolder_name().toLowerCase().contains(str.toLowerCase())) {
                    folderItemList.add(oriFolderList.get(i));
                }
            }
        }

        notifyDataSetChanged();


    }
}

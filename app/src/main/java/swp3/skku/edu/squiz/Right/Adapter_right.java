package swp3.skku.edu.squiz.Right;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.CardPageActivity;
import swp3.skku.edu.squiz.FileInitTask;
import swp3.skku.edu.squiz.Left.ViewHolder_left;
import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderItem;

public class Adapter_right extends RecyclerView.Adapter<ViewHolder_right>  {
    private ArrayList<FolderItem> folderItemList = new ArrayList<>();
    private int contentLayout;
    private Context context;
    Activity activity;
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/logfile.txt";

    public Adapter_right(int contentLayout, Context context, FragmentActivity activity){
        //this.FolderSetItemList = folderSetItemList;
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
                String title = folderItemList.get(position).getWord();
                Intent intent = new Intent(activity, CardPageActivity.class);
                intent.putExtra("title", title);
                activity.startActivity(intent);
            }
        });
        return viewHolder_right;
    }

    @Override
    public void onBindViewHolder(ViewHolder_right holder, int position) {
        FolderItem folderItem = folderItemList.get(position);
        holder.title.setText("폴더제목 : "+folderItem.getWord());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return folderItemList.size();
    }

    public void initFolderSetData(){
        FileInitTask fileInitTask = new FileInitTask(folderItemList,1);
        fileInitTask.execute();
    }
    public void addFolderData(FolderItem folderItem) {
        folderItemList.add(folderItem);
        notifyItemInserted(folderItemList.size()-1);
    }
}

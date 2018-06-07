package swp3.skku.edu.squiz.FolderPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.ViewHolder_cardPage;
import swp3.skku.edu.squiz.FileLoadTask;
import swp3.skku.edu.squiz.FileModifyTask;
import swp3.skku.edu.squiz.FolderLoadTask;
import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderList;

public class Adapter_InsideFolder extends RecyclerView.Adapter<ViewHolder_InsideFolder>{
    private ArrayList<CardSetItem> FolderListSet = new ArrayList<>();

    private ArrayList<FolderList> FolderLists = new ArrayList<>();
    private int contentLayout;
    Context context;
    int count = 0;
    boolean FolderTaskEnd;
    String title;
    Activity activity;

    public Adapter_InsideFolder(Activity activity, int contentLayout, Context context, String title) {
        this.activity = activity;
        this.contentLayout = contentLayout;
        this.context = context;
        this.title = title;
    }

    @Override
    public ViewHolder_InsideFolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View InsideFolderView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        InsideFolderView = inflater.inflate(contentLayout, parent, false);
        final ViewHolder_InsideFolder viewHolderInsideFolder = new ViewHolder_InsideFolder(InsideFolderView);
        viewHolderInsideFolder.FolderLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = viewHolderInsideFolder.position;
                String title = FolderListSet.get(position).getTitle();
                String count = String.valueOf(FolderListSet.get(position).getCount());
                Intent intent = new Intent(activity, InsideFolder.class);
                intent.putExtra("title", title);
                intent.putExtra("count", count);
                activity.startActivity(intent);
            }
        });
        return viewHolderInsideFolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder_InsideFolder holder, int position) {
        CardSetItem titleCount = FolderListSet.get(position);
        holder.CardSetTitle.setText(titleCount.getTitle());
        holder.CardSetCount.setText(titleCount.getCount());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return FolderListSet.size();
    }

    public void loadFolderData(String title) {
        if (count == 0) {
            FolderTaskEnd = false;
            FolderLoadTask folderLoadTask = new FolderLoadTask(FolderLists, title, FolderTaskEnd);
            folderLoadTask.execute();
            while(!FolderTaskEnd) {

            }

        }
        ArrayList<String> temp = new ArrayList<>();
        int size = FolderLists.size();
        int i;
        for(i=0; i<size; i++) {
            if(FolderLists.get(i).getFoldertitle().equals(title)) {
                temp = FolderLists.get(i).getCardsetInFolder();
                break;
            }
        }
        size = temp.size();
        for(i=0; i<size; i++) {
            FolderListSet.add(new CardSetItem(0, temp.get(i)));
        }
        count = 1;
        return;
    }

    public ArrayList<CardSetItem> getFolderSetLiset(){
        return FolderListSet;
    }


}


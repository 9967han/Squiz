package swp3.skku.edu.squiz.FolderPage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.CardPageActivity;
import swp3.skku.edu.squiz.FileInitTask;
import swp3.skku.edu.squiz.FolderLoadTask;
import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderList;

public class Adapter_InsideFolder extends RecyclerView.Adapter<ViewHolder_InsideFolder>{
    private ArrayList<FolderList> FolderLists = new ArrayList<>();
    private ArrayList<CardSetItem> cardSetItemList = new ArrayList<>();
    private int contentLayout;
    Context context;
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
                String title = FolderLists.get(position).getFoldertitle();
                String count = String.valueOf(viewHolderInsideFolder.CardSetCount.getText().toString().charAt(0));
                Intent intent = new Intent(activity, CardPageActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("count", count);
                activity.startActivityForResult(intent, 2); // 2 is REQUEST_EditItemSet
            }
        });
        return viewHolderInsideFolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder_InsideFolder holder, int position) {
        FolderList folderList = FolderLists.get(position);
        for (CardSetItem cardSetItem : cardSetItemList){
            if(cardSetItem.getTitle().equals(folderList.getFoldertitle())){
                holder.CardSetCount.setText(String.valueOf(cardSetItem.getCount())+"단어");
            }
        }
        holder.CardSetTitle.setText(folderList.getFoldertitle());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return FolderLists.size();
    }

    public void loadFolderData(String title) {
        FolderLoadTask folderLoadTask = new FolderLoadTask(title, FolderLists);
        folderLoadTask.execute();
    }

    public void loadFileData() {
        FileInitTask fileInitTask = new FileInitTask(cardSetItemList);
        fileInitTask.execute();
    }

    public void editCardSetCount(String Cardtitle, int count) {
        loadFolderData(title);
        loadFileData();
       /*int i = 0;
        int size = cardSetItemList.size();
        while (i < size) {
            if(cardSetItemList.get(i).getTitle().equals(title)) {
                cardSetItemList.get(i).setCount(count);
                break;
            }
            i+=1;
        }
        notifyDataSetChanged();*/
    }

    public void deleteCardSetCount(String Cardtitle) {
        loadFolderData(title);
        loadFileData();
        /*int i = 0;
        int size = cardSetItemList.size();
        while (i < size) {
            if(cardSetItemList.get(i).getTitle().equals(title)) {
                cardSetItemList.remove(i);
                break;
            }
            i+=1;
        }
        notifyDataSetChanged();*/

    }
}


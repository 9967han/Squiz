package swp3.skku.edu.squiz.FolderPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.ViewHolder_cardPage;
import swp3.skku.edu.squiz.FileLoadTask;
import swp3.skku.edu.squiz.FileModifyTask;
import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.CardSetItem;

public class Adapter_InsideFolder extends RecyclerView.Adapter<ViewHolder_InsideFolder>{
    private ArrayList<CardSetItem> cardSetItemList = new ArrayList<>();
    private int contentLayout;
    Context context;

    public Adapter_InsideFolder(int contentLayout, Context context) {
        this.contentLayout = contentLayout;
        this.context = context;
    }

    @Override
    public ViewHolder_InsideFolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View InsideFolder_view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        InsideFolder_view = inflater.inflate(contentLayout, parent, false);
        final ViewHolder_InsideFolder viewHolder_insideFolder = new ViewHolder_InsideFolder(InsideFolder_view);

        return viewHolder_insideFolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder_InsideFolder holder, int position) {
        CardSetItem cardSetItem = cardSetItemList.get(position);
        holder.CardSetItemTitle.setText(cardSetItem.getTitle());
        holder.CardSetItemCount.setText(cardSetItem.getCount());
    }

    @Override
    public int getItemCount() {
        return cardSetItemList.size();
    }

    public void loadItemData(String title) {
        FileLoadTask fileLoadTask = new FileLoadTask(cardSetItemList, title,1);
        fileLoadTask.execute();
    }
}

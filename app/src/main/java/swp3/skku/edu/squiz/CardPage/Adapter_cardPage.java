package swp3.skku.edu.squiz.CardPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import swp3.skku.edu.squiz.FileLoadTask;
import swp3.skku.edu.squiz.FileModifyTask;
import swp3.skku.edu.squiz.model.CardItem;

/**
 * Created by LG on 2018-05-12.
 */

public class Adapter_cardPage extends RecyclerView.Adapter<ViewHolder_cardPage>{
    private ArrayList<CardItem> cardPageItemList = new ArrayList<>();
    private int contentLayout;
    Context context;

    public Adapter_cardPage(int contentLayout, Context context) {
        this.contentLayout = contentLayout;
        this.context = context;
    }

    @Override
    public ViewHolder_cardPage onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardPage_view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        cardPage_view = inflater.inflate(contentLayout, parent, false);
        final ViewHolder_cardPage viewHolder_cardPage = new ViewHolder_cardPage(cardPage_view);
        viewHolder_cardPage.cardPage_like.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = viewHolder_cardPage.position;
                Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
                String word = String.valueOf(viewHolder_cardPage.cardPage_word.getText());
                String mean = String.valueOf(viewHolder_cardPage.cardPage_mean.getText());
                FileModifyTask fileModifyTask = new FileModifyTask(cardPageItemList, word, mean);
                fileModifyTask.execute();
                //TODO 상욱 : FileModifyTask
            }
        });

        return viewHolder_cardPage;
    }

    @Override
    public void onBindViewHolder(ViewHolder_cardPage holder, int position) {
        CardItem cardItem = cardPageItemList.get(position);
        holder.cardPage_word.setText(cardItem.getWord());
        holder.cardPage_mean.setText(cardItem.getMeaning());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return cardPageItemList.size();
    }

    public void loadItemData(String title) {
        FileLoadTask fileLoadTask = new FileLoadTask(cardPageItemList, title);
        fileLoadTask.execute();
    }
}

package swp3.skku.edu.squiz.CardPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    int count = 0;
    TextView cardpage_like_count;

    public Adapter_cardPage(int contentLayout, Context context, TextView cardpage_like_count) {
        this.contentLayout = contentLayout;
        this.context = context;
        this.cardpage_like_count = cardpage_like_count;
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
                String word = String.valueOf(viewHolder_cardPage.cardPage_word.getText());
                String mean = String.valueOf(viewHolder_cardPage.cardPage_mean.getText());
                FileModifyTask fileModifyTask = new FileModifyTask(cardPageItemList, word, mean);
                fileModifyTask.execute();

                CardItem cardItem = cardPageItemList.get(position);
                if(cardItem.getLike().equals(true)) {
                    cardItem.setLike(false);
                    count--;
                }
                else if(cardItem.getLike().equals(false)) {
                    cardItem.setLike(true);
                    count++;
                }
                cardpage_like_count.setText("별표 "+String.valueOf(count)+"단어");
            }
        });
        return viewHolder_cardPage;
    }

    @Override
    public void onBindViewHolder(ViewHolder_cardPage holder, int position) {
        CardItem cardItem = cardPageItemList.get(position);
        holder.cardPage_word.setText(cardItem.getWord());
        holder.cardPage_mean.setText(cardItem.getMeaning());
        if(cardItem.getLike().equals(true)) {
            count++;
            holder.cardPage_like.setChecked(true);
        }
        else if(cardItem.getLike().equals(false))  holder.cardPage_like.setChecked(false);
        cardpage_like_count.setText("별표 "+String.valueOf(count)+"단어");
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

    public ArrayList<CardItem> getCardPageItemList(){
        return cardPageItemList;
    }


}
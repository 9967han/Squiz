package swp3.skku.edu.squiz.Left;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.CardPageActivity;
import swp3.skku.edu.squiz.FileInitTask;
import swp3.skku.edu.squiz.model.CardSetItem;

/**
 * Created by LG on 2018-05-07.
 */

public class Adapter_left extends RecyclerView.Adapter<ViewHolder_left>  {
    private ArrayList<CardSetItem> cardSetItemList = new ArrayList<>();
    private int contentLayout;
    private Context context; // 이거맞나
    Activity activity;

    public Adapter_left(int contentLayout, Context context, FragmentActivity activity) {
        this.contentLayout = contentLayout;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ViewHolder_left onCreateViewHolder(ViewGroup parent, int viewType) {
        View leftFragment_view;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        leftFragment_view = inflater.inflate(contentLayout, parent, false);
        final ViewHolder_left viewHolder_left = new ViewHolder_left(leftFragment_view);
        viewHolder_left.left_layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = viewHolder_left.position;
                String title = cardSetItemList.get(position).getTitle();
                String count = String.valueOf(cardSetItemList.get(position).getCount());
                Intent intent = new Intent(activity, CardPageActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("count", count);
                activity.startActivity(intent);
            }
        });
        return viewHolder_left;
    }

    @Override
    public void onBindViewHolder(ViewHolder_left holder, int position) {
        CardSetItem cardSetItem = cardSetItemList.get(position);
        holder.title.setText("카드제목 : "+cardSetItem.getTitle());
        holder.word_count.setText("단어 수 : "+String.valueOf(cardSetItem.getCount())+"단어");
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return cardSetItemList.size();
    }


    public void initCardSetData(){
        FileInitTask fileInitTask = new FileInitTask(cardSetItemList);
        fileInitTask.execute();
    }

    public void addCardSetData(CardSetItem cardSetItem) {
        cardSetItemList.add(cardSetItem);
        notifyItemInserted(cardSetItemList.size()-1);
    }
}




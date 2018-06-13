package swp3.skku.edu.squiz.Left;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import swp3.skku.edu.squiz.CardPage.CardPageActivity;
import swp3.skku.edu.squiz.FileInitTask;
import swp3.skku.edu.squiz.model.CardSetItem;

/**
 * Created by LG on 2018-05-07.
 */

public class Adapter_left extends RecyclerView.Adapter<ViewHolder_left>  {
    /*
    cardSetItemList 바꿀때마다 oriCardSetList 도 같이 바꿔줘야합니다@@@@
    */
    private ArrayList<CardSetItem> cardSetItemList = new ArrayList<>();
    private int contentLayout;
    private Context context; // 이거맞나
    Activity activity;
    int oriNumberOfItems;
    //boolean firstSearch = true;
    private ArrayList<CardSetItem> oriCardSetList = new ArrayList<>();

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
                int position = viewHolder_left.getAdapterPosition();
                Log.d("Toast", String.valueOf(position));
                String title = cardSetItemList.get(position).getTitle();
                String count = String.valueOf(cardSetItemList.get(position).getCount());
                Intent intent = new Intent(activity, CardPageActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("count", count);
                activity.startActivityForResult(intent, 2); // 2 is REQUEST_EditItemSet
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
        FileInitTask fileInitTask = new FileInitTask(cardSetItemList, oriCardSetList);
        fileInitTask.execute();
    }

    public void addCardSetData(CardSetItem cardSetItem) {
        cardSetItemList.add(cardSetItem);
        oriCardSetList.add(cardSetItem);
        notifyItemInserted(cardSetItemList.size()-1);
        //firstSearch = true;
        oriNumberOfItems = getItemCount();
    }
    public void editCountCardSet(String title, int count) {
        int i = 0;
        int size = cardSetItemList.size();
        while (i < size) {
            if(cardSetItemList.get(i).getTitle().equals(title)) {
                cardSetItemList.get(i).setCount(count);
                oriCardSetList.get(i).setCount(count);
                break;
            }
            i+=1;
        }
        notifyDataSetChanged();
        //firstSearch = true;
        oriNumberOfItems = getItemCount();
    }

    public void deleteCardSetData(String title) {
        Iterator<CardSetItem> iter = cardSetItemList.iterator();
        int i=0;
        while(iter.hasNext()){
            CardSetItem cardSetItem = iter.next();
            if(cardSetItem.getTitle().equals(title)){
                iter.remove();
                oriCardSetList.remove(i);
                break;
            }
            i+=1;
        }
        notifyDataSetChanged();
        //firstSearch = true;
        oriNumberOfItems = getItemCount();
    }

    public void searchSet(String str) {


        if(str.length() == 0) {
            /*if(firstSearch) {
                oriCardSetList.clear();
                oriCardSetList.addAll(cardSetItemList);
                oriNumberOfItems = oriCardSetList.size();
                firstSearch = false;
            }*/

            cardSetItemList.clear();
            cardSetItemList.addAll(oriCardSetList);
        }
        else {
            cardSetItemList.clear();
            for (int i = 0; i <oriCardSetList.size(); i++) {
                if (oriCardSetList.get(i).getTitle().toLowerCase().contains(str.toLowerCase())) {
                    cardSetItemList.add(oriCardSetList.get(i));
                }
            }
        }

        notifyDataSetChanged();


    }
    public void notSearchSet() {
        oriCardSetList.clear();
        oriCardSetList.addAll(cardSetItemList);
        oriNumberOfItems = getItemCount();
    }

    public ArrayList<CardSetItem> returnCardSet() {
        ArrayList<CardSetItem> temp = new ArrayList<>();
        temp.addAll(oriCardSetList);
        return temp;
    }
}




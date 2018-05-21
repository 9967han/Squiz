package swp3.skku.edu.squiz.EditCard;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import swp3.skku.edu.squiz.FileOutTask;
import swp3.skku.edu.squiz.MakeCard.ViewHolder_makeCard;
import swp3.skku.edu.squiz.model.CardItem;

/**
 * Created by LG on 2018-05-02.
 */

public class Adapter_editCard extends RecyclerView.Adapter<ViewHolder_editCard> {
    private ArrayList<CardItem> cardItemList = new ArrayList<>();
    private int contentLayout;
    private Context context;
    AppCompatActivity appCompatActivity;
    public Adapter_editCard(int contentLayout, Context context, AppCompatActivity appCompatActivity) {
        this.contentLayout = contentLayout;
        this.context = context;
        CardItem cardItem = new CardItem(null, null);
        cardItemList.add(cardItem);
        this.appCompatActivity = appCompatActivity;
    }

    //뷰 생성
    @Override
    public ViewHolder_editCard onCreateViewHolder(ViewGroup parent, int viewType) {
        View editCard_view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        editCard_view = inflater.inflate(contentLayout, parent, false);
        final ViewHolder_editCard viewHolder_editCard = new ViewHolder_editCard(editCard_view);

        //word 입력 후 focus 변환 시 데이터 입력
        viewHolder_editCard.editTextWord.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String word = viewHolder_editCard.editTextWord.getText().toString();
                if(!hasFocus){
                    int position = viewHolder_editCard.position;
                    Log.w("word", String.valueOf(position));
                    if(position!=-1) {
                        CardItem carditem = cardItemList.get(position);
                        carditem.setWord(word);
                    }
                }
            }
        });

        //mean 입력 후 focus 변환 시 데이터 입력
        viewHolder_editCard.editTextMean.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String mean = viewHolder_editCard.editTextMean.getText().toString();
                if(!hasFocus){
                    int position = viewHolder_editCard.position;
                    if(position!=-1) {
                        CardItem carditem = cardItemList.get(position);
                        carditem.setMeaning(mean);
                    }
                }
            }
        });

        //delete 누를 시 삭제
        viewHolder_editCard.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder_editCard.position;
                if(position!=-1) {
                    removeItem(position);
                }
            }
        });
        return viewHolder_editCard;
    }

    public int cardItemListSize(){
        return cardItemList.size();
    }


    public void removeItem(int position) {
        cardItemList.remove(position);
        this.notifyItemRemoved(position);
    }

    public void removeItem(CardItem cardItem){
        int position = cardItemList.indexOf(cardItem);
        removeItem(position);
    }

    public void addItem(CardItem cardItem){
        cardItemList.add(cardItem);
        notifyItemInserted(cardItemList.size()-1);
    }

    //생성된 뷰에 데이터 할당.
    @Override
    public void onBindViewHolder(ViewHolder_editCard holder, int position) {
        CardItem cardItem = cardItemList.get(position);
        holder.editTextWord.setText(cardItem.getWord());
        holder.editTextMean.setText(cardItem.getMeaning());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    //파일저장방식 : 1. 단어수(1번만), 2. 단어 3. 뜻
    public void saveCardData(String title) throws IOException {
        FileOutTask fileTask = new FileOutTask(appCompatActivity, cardItemList, title);
        fileTask.execute();
    }

    /*public String getCardTitle() {


    }*/

}

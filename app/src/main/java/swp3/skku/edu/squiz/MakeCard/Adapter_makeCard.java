package swp3.skku.edu.squiz.MakeCard;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import swp3.skku.edu.squiz.model.CardItem;

/**
 * Created by LG on 2018-05-02.
 */

public class Adapter_makeCard extends RecyclerView.Adapter<ViewHolder_makeCard> {
    ArrayList<CardItem> cardItemList = new ArrayList();
    private int contentLayout;
    private Context context;

    public Adapter_makeCard(int contentLayout, Context context) {
        this.contentLayout = contentLayout;
        this.context = context;
        CardItem cardItem = new CardItem(null, null);
        cardItemList.add(cardItem);
    }

    //뷰 생성
    @Override
    public ViewHolder_makeCard onCreateViewHolder(ViewGroup parent, int viewType) {
        View makeCard_view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        makeCard_view = inflater.inflate(contentLayout, parent, false);
        final ViewHolder_makeCard viewHolder_makeCard = new ViewHolder_makeCard(makeCard_view);

        //word 입력 후 focus 변환 시 데이터 입력
        viewHolder_makeCard.editTextWord.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String word = viewHolder_makeCard.editTextWord.getText().toString();
                if(!hasFocus){
                    int position = viewHolder_makeCard.position;
                    Log.w("word", String.valueOf(position));
                    if(position!=-1) {
                        CardItem carditem = cardItemList.get(position);
                        carditem.setWord(word);
                    }
                }
            }
        });

        //mean 입력 후 focus 변환 시 데이터 입력
        viewHolder_makeCard.editTextMean.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                String mean = viewHolder_makeCard.editTextMean.getText().toString();
                if(!hasFocus){
                    int position = viewHolder_makeCard.position;
                    if(position!=-1) {
                        CardItem carditem = cardItemList.get(position);
                        carditem.setMeaning(mean);
                    }
                }
            }
        });

        //delete 누를 시 삭제
        viewHolder_makeCard.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder_makeCard.position;
                if(position!=-1) {
                    removeItem(position);
                }
            }
        });
        return viewHolder_makeCard;
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
    public void onBindViewHolder(ViewHolder_makeCard holder, int position) {
        CardItem cardItem = cardItemList.get(position);
        holder.editTextWord.setText(cardItem.getWord());
        holder.editTextMean.setText(cardItem.getMeaning());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    //아이템을 파일에 저장하는 함수(아직 되는지 모르겠음)
    //파일저장방식 : 1. 단어수(1번만), 2. 단어 3. 뜻
    public void saveCardData(String title){
        File file = new File(context.getFilesDir(), title);
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
            bfw.write(cardItemList.size());
            for(CardItem cardItem : cardItemList){
                bfw.write(cardItem.getWord());
                bfw.newLine();
                bfw.write(cardItem.getMeaning());
            }
            if(bfw != null){
                bfw.flush();
                bfw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
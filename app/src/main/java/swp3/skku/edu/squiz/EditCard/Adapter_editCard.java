package swp3.skku.edu.squiz.EditCard;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import swp3.skku.edu.squiz.FileEditOutTask;
import swp3.skku.edu.squiz.FileOutTask;
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
        //CardItem cardItem = new CardItem(null, null);
        //cardItemList.add(cardItem);
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
        viewHolder_editCard.editTextWord.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                try {
                    String word = viewHolder_editCard.editTextWord.getText().toString();
                    if (!hasFocus) {
                        int position = viewHolder_editCard.position;
                        Log.w("word", String.valueOf(position));
                        if (position != -1) {
                            CardItem carditem = cardItemList.get(position);
                            carditem.setWord(word);
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //mean 입력 후 focus 변환 시 데이터 입력
        viewHolder_editCard.editTextMean.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                try {
                    String mean = viewHolder_editCard.editTextMean.getText().toString();
                    if (!hasFocus) {
                        int position = viewHolder_editCard.position;
                        if (position != -1) {
                            CardItem carditem = cardItemList.get(position);
                            carditem.setMeaning(mean);
                        }

                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //다시쓰기 누를 시 에딧텍스트 값 비우기
        viewHolder_editCard.rewrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = viewHolder_editCard.position;
                if(position!=-1){
                    viewHolder_editCard.editTextWord.setText(null);
                    viewHolder_editCard.editTextMean.setText(null);
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
        //holder.editTextWord.requestFocus();

    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    //파일저장방식 : 1. 단어수(1번만), 2. 단어 3. 뜻
    public void saveCardData(String title) throws IOException {
        FileEditOutTask fileTask = new FileEditOutTask(appCompatActivity, cardItemList, title);
        fileTask.execute();
    }

    public void loadCardData(String title) throws IOException {

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Squiz/squiz.txt";

        InputStream is = new FileInputStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        int count=0;
        int i;
        while((line=reader.readLine())!=null) {
            String[] words = line.split("[,]");
            String tempTitle = words[0];
            //Log.d("abcdefg", tempTitle + "//" + title);
            if(tempTitle.equals(title.trim())) {
                count += 1;

                CardItem cardTemp = new CardItem(words[1], words[2]);

                if(words[3].trim().equals("false")){
                    cardTemp.setLike(false);
                }
                else if(words[3].trim().equals("true")){
                    cardTemp.setLike(true);
                }
                cardItemList.add(cardTemp);


            }
        }
        notifyItemRangeChanged(0, count);
        reader.close();
        is.close();

    }

    /*public String getCardTitle() {


    }*/

}


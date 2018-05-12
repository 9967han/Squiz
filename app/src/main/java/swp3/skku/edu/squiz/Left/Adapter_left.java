package swp3.skku.edu.squiz.Left;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import swp3.skku.edu.squiz.model.CardSetItem;

/**
 * Created by LG on 2018-05-07.
 */

public class Adapter_left extends RecyclerView.Adapter<ViewHolder_left>  {
    private ArrayList<CardSetItem> cardSetItemList = new ArrayList<>();
    private int contentLayout;
    private Context context; // 이거맞나?
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/logfile.txt";

    public Adapter_left(int contentLayout, Context context) {
        this.cardSetItemList = cardSetItemList;
        this.contentLayout = contentLayout;
        this.context = context;
    }

    @Override
    public ViewHolder_left onCreateViewHolder(ViewGroup parent, int viewType) {
        View leftFragment_view;
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
        leftFragment_view = inflater.inflate(contentLayout, parent, false);
        final ViewHolder_left viewHolder_left = new ViewHolder_left(leftFragment_view);

        return viewHolder_left;
    }

    @Override
    public void onBindViewHolder(ViewHolder_left holder, int position) {
        CardSetItem cardSetItem = cardSetItemList.get(position);
        holder.title.setText(cardSetItem.getTitle());
        holder.word_count.setText(String.valueOf(cardSetItem.getCount()));
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return cardSetItemList.size();
    }

    public void initCardSetData(){
        //TODO 상욱 : 어떤방식으로 카드를 저장하고 추가할지 작성 init/load 두개있어야하나??
    }

}

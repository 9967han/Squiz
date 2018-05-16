package swp3.skku.edu.squiz.SubjectiveCardPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import swp3.skku.edu.squiz.model.CardItem;

/**
 * Created by LG on 2018-05-15.
 */

public class Adapter_subjective extends RecyclerView.Adapter<ViewHolder_subjective> {

    private int subjective_content;
    private ArrayList<CardItem> subjectiveItemList = new ArrayList<>();
    private Context context;

    public Adapter_subjective(int subjectivepage_content, Context context, ArrayList<CardItem> subjectiveItemList) {
        subjective_content = subjectivepage_content;
        this.context = context;
        this.subjectiveItemList = subjectiveItemList;
    }

    @Override
    public ViewHolder_subjective onCreateViewHolder(ViewGroup parent, int viewType) {
        View subjective_view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        subjective_view = inflater.inflate(subjective_content, parent, false);
        final ViewHolder_subjective viewHolder_wordCard = new ViewHolder_subjective(subjective_view);

        return viewHolder_wordCard;
    }

    @Override
    public void onBindViewHolder(ViewHolder_subjective holder, int position) {
        CardItem cardItem = subjectiveItemList.get(position);
        holder.question.setText(cardItem.getWord());
    }

    @Override
    public int getItemCount() {
        return subjectiveItemList.size();
    }
}

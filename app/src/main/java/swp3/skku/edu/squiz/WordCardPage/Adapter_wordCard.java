package swp3.skku.edu.squiz.WordCardPage;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardItem;

/**
 * Created by LG on 2018-05-13.
 */

public class Adapter_wordCard extends RecyclerView.Adapter<ViewHolder_wordCard>{
    private ArrayList<CardItem> cardPageItemList = new ArrayList<>();
    private int word_card_content;
    Context context;
    Resources resources;

    public Adapter_wordCard(int word_card_content, Context context, ArrayList<CardItem> cardPageItemList, Resources resources) {
        this.cardPageItemList = cardPageItemList;
        this.word_card_content = word_card_content;
        this.context = context;
        this.resources = resources;
    }

    @Override
    public ViewHolder_wordCard onCreateViewHolder(ViewGroup parent, int viewType) {
        View wordCard_view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        wordCard_view = inflater.inflate(word_card_content, parent, false);
        final ViewHolder_wordCard viewHolder_wordCard = new ViewHolder_wordCard(wordCard_view);
        viewHolder_wordCard.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeCameraDistance(viewHolder_wordCard.mCardFrontLayout, viewHolder_wordCard.mCardBackLayout);
                viewHolder_wordCard.mIsBackVisible = flip(viewHolder_wordCard.mCardFrontLayout, viewHolder_wordCard.mCardBackLayout, viewHolder_wordCard.mIsBackVisible);
            }
        });
        viewHolder_wordCard.front_toggle.setClickable(false);
        return viewHolder_wordCard;
    }

    @Override
    public void onBindViewHolder(ViewHolder_wordCard holder, int position) {
        CardItem cardItem = cardPageItemList.get(position);
        holder.front_text.setText(cardItem.getWord());
        holder.back_text.setText(cardItem.getMeaning());
        holder.position = position;
        holder.mIsBackVisible = false;
        if(cardItem.getLike()) holder.front_toggle.setChecked(true);
        else holder.front_toggle.setChecked(false);
    }


    @Override
    public int getItemCount() {
        return cardPageItemList.size();
    }

    public void changeCameraDistance(View mCardFrontLayout, View mCardBackLayout) {
        int distance = 8000;
        float scale = resources.getDisplayMetrics().density * distance;
        mCardFrontLayout.setCameraDistance(scale);
        mCardBackLayout.setCameraDistance(scale);
    }

    public boolean flip(View mCardFrontLayout, View mCardBackLayout, boolean mIsBackVisible){
        AnimatorSet mSetRightOut;
        AnimatorSet mSetLeftIn;

        mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.out_animation);
        mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.in_animation);

        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout);
            mSetLeftIn.setTarget(mCardBackLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            return true;
        } else {
            mSetRightOut.setTarget(mCardBackLayout);
            mSetLeftIn.setTarget(mCardFrontLayout);
            mSetRightOut.start();
            mSetLeftIn.start();
            return false;
        }
    }


}

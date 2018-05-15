package swp3.skku.edu.squiz.WordCardPage;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import swp3.skku.edu.squiz.R;

/**
 * Created by LG on 2018-05-14.
 */

public class ViewHolder_wordCard extends RecyclerView.ViewHolder {

    public FrameLayout mCardFrontLayout;
    public FrameLayout mCardBackLayout;
    public TextView front_text;
    public TextView back_text;
    public ConstraintLayout cardLayout;
    public ToggleButton front_toggle;
    boolean mIsBackVisible = false;
    int position;

    public ViewHolder_wordCard(View itemView) {
        super(itemView);
        mCardBackLayout = itemView.findViewById(R.id.card_back);
        mCardFrontLayout = itemView.findViewById(R.id.card_front);
        cardLayout = itemView.findViewById(R.id.card_Layout);
        front_text = itemView.findViewById(R.id.front_text);
        back_text = itemView.findViewById(R.id.back_text);
        front_toggle = itemView.findViewById(R.id.front_toggle);
    }
}

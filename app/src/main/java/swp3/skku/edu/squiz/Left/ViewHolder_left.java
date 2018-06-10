package swp3.skku.edu.squiz.Left;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardSetItem;

/**
 * Created by LG on 2018-05-07.
 */

public class ViewHolder_left extends RecyclerView.ViewHolder{
    public TextView title;
    public TextView word_count;
    public ConstraintLayout left_layout;
    int position;

    public ViewHolder_left(View itemView) {
        super(itemView);
        left_layout = itemView.findViewById(R.id.left_layout);
        title = itemView.findViewById(R.id.left_card_title);
        word_count = itemView.findViewById(R.id.left_word_count);
    }
}

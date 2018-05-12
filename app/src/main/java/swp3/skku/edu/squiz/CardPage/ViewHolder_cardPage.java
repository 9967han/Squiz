package swp3.skku.edu.squiz.CardPage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;


import swp3.skku.edu.squiz.R;

/**
 * Created by LG on 2018-05-12.
 */

public class ViewHolder_cardPage extends RecyclerView.ViewHolder {
    TextView cardPage_word;
    TextView cardPage_mean;
    ToggleButton cardPage_like;
    int position;

    public ViewHolder_cardPage(View itemView) {
        super(itemView);
        cardPage_word = itemView.findViewById(R.id.cardpage_word);
        cardPage_mean = itemView.findViewById(R.id.cardpage_mean);
        cardPage_like = itemView.findViewById(R.id.cardpage_like);

    }
}

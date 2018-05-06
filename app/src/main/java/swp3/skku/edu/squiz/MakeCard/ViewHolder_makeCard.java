package swp3.skku.edu.squiz.MakeCard;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import swp3.skku.edu.squiz.R;

/**
 * Created by LG on 2018-05-02.
 */

public class ViewHolder_makeCard extends RecyclerView.ViewHolder {

    public EditText editTextWord;
    public EditText editTextMean;
    public TextView rewrite;
    public TextView delete;
    public LinearLayout contentLayout;
    int position;

    public ViewHolder_makeCard(View itemView) {
        super(itemView);
        editTextWord = itemView.findViewById(R.id.card_content_word);
        editTextMean = itemView.findViewById(R.id.card_content_mean);
        rewrite = itemView.findViewById(R.id.card_content_rewrite);
        delete = itemView.findViewById(R.id.card_content_delete);
        contentLayout = itemView.findViewById(R.id.card_content_layout);
    }


}

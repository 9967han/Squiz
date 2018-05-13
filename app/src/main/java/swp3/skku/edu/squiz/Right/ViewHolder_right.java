package swp3.skku.edu.squiz.Right;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import swp3.skku.edu.squiz.R;

public class ViewHolder_right extends RecyclerView.ViewHolder {
    public TextView title;
    public ConstraintLayout right_layout;
    int position;

    public ViewHolder_right(View itemView) {
        super(itemView);
        right_layout = itemView.findViewById(R.id.right_layout);
        title = itemView.findViewById(R.id.right_card_title);
    }
}

package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import swp3.skku.edu.squiz.R;

public class ViewHolder_ACTF extends RecyclerView.ViewHolder {
    public TextView ACTF_title;
    public CheckBox ACTF_checkBox;
    public ConstraintLayout ACTF_layout;
    int position;

    public ViewHolder_ACTF(View itemView) {
        super(itemView);
        ACTF_layout = itemView.findViewById(R.id.ACTF_layout);
        ACTF_title = itemView.findViewById(R.id.ACTF_title);
        ACTF_checkBox = itemView.findViewById(R.id.ACTF_checkBox);
    }
}

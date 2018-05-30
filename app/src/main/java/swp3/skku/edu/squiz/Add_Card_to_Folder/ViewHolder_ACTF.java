package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import swp3.skku.edu.squiz.R;

public class ViewHolder_ACTF extends RecyclerView.ViewHolder {
    public TextView title;
    public CheckBox checkBox;

    public ConstraintLayout actf_layout;
    int position;

    public ViewHolder_ACTF(View itemView) {
        super(itemView);
        actf_layout = itemView.findViewById(R.id.actf_layout);
        title = itemView.findViewById(R.id.actftitle);
        checkBox = itemView.findViewById(R.id.checkBox);
    }
}

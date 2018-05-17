package swp3.skku.edu.squiz.SubjectiveCardPage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import swp3.skku.edu.squiz.R;

/**
 * Created by LG on 2018-05-15.
 */

public class ViewHolder_subjective extends RecyclerView.ViewHolder {
    public TextView question;
    public EditText meaning;
    public ImageView correct;
    int position;

    public ViewHolder_subjective(View itemView) {
        super(itemView);
        question = itemView.findViewById(R.id.subjective_question);
        meaning = itemView.findViewById(R.id.subjective_answer);
        correct = itemView.findViewById(R.id.correct_circle);
    }
}



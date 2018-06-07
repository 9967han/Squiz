package swp3.skku.edu.squiz.FolderPage;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import swp3.skku.edu.squiz.R;

public class ViewHolder_InsideFolder extends RecyclerView.ViewHolder {//insidefoldercontent
    TextView CardSetTitle;
    TextView CardSetCount;
    ConstraintLayout FolderLayout;
    int position;
    public ViewHolder_InsideFolder(View itemView) {
        super(itemView);
        FolderLayout = itemView.findViewById(R.id.InsideFolderLayout);
        CardSetTitle = itemView.findViewById(R.id.cardpage_word);
        CardSetCount = itemView.findViewById(R.id.cardpage_mean);

    }
}

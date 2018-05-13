package swp3.skku.edu.squiz.FolderPage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import swp3.skku.edu.squiz.R;

public class ViewHolder_InsideFolder extends RecyclerView.ViewHolder {//insidefoldercontent
    TextView CardSetItemTitle;
    TextView CardSetItemCount;

    public ViewHolder_InsideFolder(View itemView) {
        super(itemView);
        CardSetItemTitle = itemView.findViewById(R.id.CardSet_Title);
        CardSetItemCount = itemView.findViewById(R.id.CardSetItem_Count);
    }
}

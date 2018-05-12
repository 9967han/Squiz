package swp3.skku.edu.squiz.Right;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import swp3.skku.edu.squiz.model.CardSetItem;

public class Adapter_right extends RecyclerView.Adapter {
    private ArrayList<CardSetItem> cardSetItemList = new ArrayList<>();
    private int contentLayout;
    private Context context; // 이거맞나?
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/logfile.txt";

    public Adapter_right(int contentLayout, Context context){
        this.FolderSetItemList = folderSetItemList;
        this.contentLayout = contentLayout;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void initFolderSetData(){

    }
}

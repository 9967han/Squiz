package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.FolderItem;
import swp3.skku.edu.squiz.model.FolderList;

public class AdapterACTF extends
        RecyclerView.Adapter<AdapterACTF.ViewHolder> {

    private ArrayList<FolderItem> myItems = new ArrayList<>();
    Context context;


    public AdapterACTF(ArrayList<FolderItem> folderItems) {
        myItems = folderItems;
    }

    @Override
    public AdapterACTF.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_card_to_folder_content,null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position){
        final int pos = position;
        viewHolder.textView.setText(myItems.get(position).getFolder_name());
        viewHolder.checkBox.setTag(myItems.get(position));

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                FolderItem folderItem = (FolderItem) checkBox.getTag();

                folderItem.setSelected(checkBox.isChecked());
                myItems.get(pos).setSelected(checkBox.isChecked());

                Toast.makeText(v.getContext(), checkBox.getText()+"가 선택되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return myItems.size();
    }

    public List<FolderItem> getFolderList(){
        return myItems;
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public CheckBox checkBox;

        public ViewHolder(View itemLayoutView){
            super(itemLayoutView);
            textView = (TextView) itemLayoutView.findViewById(R.id.actftitle);
            checkBox = (CheckBox) itemLayoutView.findViewById(R.id.checkBox);

        }
    }

}

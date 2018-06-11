package swp3.skku.edu.squiz.Right;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;

import swp3.skku.edu.squiz.Add_Card_to_Folder.AdapterACTF;
import swp3.skku.edu.squiz.FileOutTask;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.FolderItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends android.support.v4.app.Fragment {
    private Context context;
    Adapter_right adapter_right;
    AdapterACTF adapter_ACTF;

    public RightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainpage_fragment_right, container, false);
        adapter_right = new Adapter_right(R.layout.mainpage_fragment_right_content, this.context, getActivity());
        adapter_right.initFolderSetData();

        RecyclerView rightFragmentRV = view.findViewById(R.id.rightFragmentRV);
        rightFragmentRV.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false));
        rightFragmentRV.setAdapter(adapter_right);

        return view;
    }


    public boolean saveFolderData(String folder_name){
        Boolean add;
        FolderItem folderItem = new FolderItem(folder_name);
        add = adapter_right.addFolderData(folderItem);

        if(add){
            FileOutTask fileTask = new FileOutTask(folder_name);
            fileTask.execute();
            return true;
        }
        else{
            return false;
        }
    }


}

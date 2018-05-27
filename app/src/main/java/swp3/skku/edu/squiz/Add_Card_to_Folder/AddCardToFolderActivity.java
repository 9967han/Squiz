package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import swp3.skku.edu.squiz.CardPage.Adapter_cardPage;
import swp3.skku.edu.squiz.FileLoadTask;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.FolderItem;
import swp3.skku.edu.squiz.model.FolderList;

public class AddCardToFolderActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<FolderItem> folderItems;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.add_card_to_folder);
        toolbar = (Toolbar) findViewById(R.id.actf_toolbar);
        folderItems = new ArrayList<FolderItem>();

        //folderItems에 folderItems.add("");

        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("폴더에 추가");
        }

        recyclerView = (RecyclerView) findViewById(R.id.FolderRV);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterACTF(folderItems);

        recyclerView.setAdapter(adapter);

    }
}

package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import swp3.skku.edu.squiz.FileOutTask;
import swp3.skku.edu.squiz.R;


public class AddCardToFolderActivity extends AppCompatActivity {
    String title;
    final Context context=this;
    private RecyclerView ACTF_RV;
    private AdapterACTF adapter_ACTF;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_to_folder);
        final TextView MainTitle=(TextView)findViewById(R.id.actf);
        MainTitle.setText("폴더에 추가");
        ACTF_RV = (RecyclerView) findViewById(R.id.actf_RV);

        ACTF_RV.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        ACTF_RV.setLayoutManager(layoutManager);
        adapter_ACTF = new AdapterACTF();
        adapter_ACTF.initFolderSetData();
        ACTF_RV.setAdapter(adapter_ACTF);

        Intent editIntent = getIntent();
        title = editIntent.getStringExtra("title");
    }

    public void onACTFButtonClick(View view) {
        adapter_ACTF.addCardSetToFolder(title);

    }
    public void saveFolderData(String data){
        FileOutTask fileTask = new FileOutTask(data);
        fileTask.execute();
    }
}

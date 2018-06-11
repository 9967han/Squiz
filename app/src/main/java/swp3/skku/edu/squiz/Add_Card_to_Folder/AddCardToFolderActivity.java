package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import swp3.skku.edu.squiz.FileOutTask;
import swp3.skku.edu.squiz.R;


public class AddCardToFolderActivity extends AppCompatActivity {
    String title;
    Context context=this;
    private RecyclerView ACTF_RV;
    private AdapterACTF adapter_ACTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_to_folder);
        final TextView MainTitle= findViewById(R.id.actf);
        MainTitle.setText("폴더에 추가");
        ACTF_RV = findViewById(R.id.actf_RV);


        Intent editIntent = getIntent();//
        title = editIntent.getStringExtra("title");//

        ACTF_RV.setHasFixedSize(true);

        adapter_ACTF = new AdapterACTF(R.layout.add_card_to_folder_content, this);
        adapter_ACTF.initFolderSetData();
        adapter_ACTF.initFolderListData();
        adapter_ACTF.loadItemData();

        ACTF_RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ACTF_RV.setAdapter(adapter_ACTF);
    }

    public void onACTFButtonClick(View view) {
        //adapter_ACTF.loadItemData();
        adapter_ACTF.addCardSetToFolder(title);

        Toast.makeText(context, "카드 추가 완료", Toast.LENGTH_SHORT).show();
        this.finish();
    }

}

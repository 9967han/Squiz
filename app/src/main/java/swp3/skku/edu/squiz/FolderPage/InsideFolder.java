package swp3.skku.edu.squiz.FolderPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import swp3.skku.edu.squiz.CardPage.Adapter_cardPage;
import swp3.skku.edu.squiz.MakeCard.Adapter_makeCard;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardItem;

public class InsideFolder extends AppCompatActivity{
    TextView InsideFolder_Title;
    RecyclerView InsideFolder_RV;
    TextView cardset_title;
    TextView word_count;
    Adapter_InsideFolder adapter_insideFolder;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.inside_folder);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        InsideFolder_Title = findViewById(R.id.InsideFolderTitle);
        InsideFolder_RV = findViewById(R.id.InsideFolderRV);
        InsideFolder_Title.setText(title);

        adapter_insideFolder = new Adapter_InsideFolder(R.layout.inside_folder_content,this);
        adapter_insideFolder.loadItemData(title);
        InsideFolder_RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        InsideFolder_RV.setAdapter(adapter_insideFolder);
    }

    public void onInsideFolderModifyButtonClick(View view) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("폴더명 수정");
        alert.setMessage("새로운 폴더명을 입력하세요");

        final EditText FolderName=new EditText(this);
        alert.setView(FolderName);

        alert.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String folder_name=FolderName.getText().toString();
            }
        });
        alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }
}

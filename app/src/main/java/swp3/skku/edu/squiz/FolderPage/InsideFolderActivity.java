package swp3.skku.edu.squiz.FolderPage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.Adapter_cardPage;
import swp3.skku.edu.squiz.MakeCard.Adapter_makeCard;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderList;

public class InsideFolderActivity extends AppCompatActivity{
    Context context;
    ArrayList<FolderList> folderLists = new ArrayList<FolderList>();
    Toolbar FolderToolbar;
    TextView TitleTextView;
    RecyclerView FolderPage_RV;
    Adapter_InsideFolder adapter_insideFolder;
    ImageView folderModify;
    String title;
    String mode = "false";
    String Changed = "false";
    Intent myIntent;
    int count;
    final static int REQUEST_DataItemSet = 1;
    final static int REQUEST_EditItemSet = 2;
    Intent intent;
    String oriTitle;
    String FolderChanged = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = new Intent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_folder);

        setSupportActionBar(FolderToolbar);
        findViews();

        myIntent = getIntent();
        title = myIntent.getStringExtra("title");

        TitleTextView.setText(title);

        adapter_insideFolder = new Adapter_InsideFolder(InsideFolderActivity.this, R.layout.inside_folder_content, this, title);
        adapter_insideFolder.loadFolderData(title);
        adapter_insideFolder.loadFileData();

        FolderPage_RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        FolderPage_RV.setAdapter(adapter_insideFolder);

        folderModify = findViewById(R.id.insideFolderModify);
        context = this;

        folderModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "폴더명 변경", Toast.LENGTH_SHORT).show();
                final EditText FolderName=new EditText(context);
                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setTitle("폴더명 변경");
                alert.setMessage("수정할 폴더명을 입력하세요");
                alert.setCancelable(false);
                alert.setView(FolderName);

                alert.setPositiveButton("수정",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String folder_name=FolderName.getText().toString();

                                if(folder_name == null) {
                                    Toast.makeText(getApplicationContext(), "폴더 제목을 입력하세요", Toast.LENGTH_SHORT).show();
                                }

                                else if(folder_name.equals(title)){

                                }

                                else {
                                    Toast.makeText(context, "폴더명 수정완료", Toast.LENGTH_SHORT).show();
                                    TitleTextView.setText(folder_name);

                                    oriTitle = title;
                                    adapter_insideFolder.editFolderData(oriTitle, folder_name);

                                    FolderChanged = "true";
                                    intent.putExtra("foldertitle", oriTitle);
                                    intent.putExtra("folderchangetitle", folder_name);

                                    title = folder_name;
                                }
                            }
                        });
                alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                alert.show();

            }
        });
    }

    private void findViews(){
        FolderToolbar=findViewById(R.id.my_toolbar);
        TitleTextView = findViewById(R.id.InsideFolderTitle);
        FolderPage_RV = findViewById(R.id.InsideFolderRV);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_EditItemSet) {

            mode = data.getStringExtra("changed");
            if(mode.equals("true")) {
                count = Integer.valueOf(data.getStringExtra("count"));
                adapter_insideFolder.editCardSetCount(title, count);

                intent.putExtra("title", data.getStringExtra("title"));
                intent.putExtra("count", data.getStringExtra("count"));
                intent.putExtra("changed", data.getStringExtra("changed"));
                setResult(RESULT_OK, intent);
            }
            else if(mode.equals("delete")) {
                adapter_insideFolder.deleteCardSetCount(title);

                intent.putExtra("title", data.getStringExtra("title"));
                intent.putExtra("changed", data.getStringExtra("changed"));

                setResult(RESULT_OK, intent);

            }
        }
    }

    @Override
    public void onBackPressed() {
        if(FolderChanged.equals("true")) {
            intent.putExtra("folderchanged", "true");
        }
        else if(FolderChanged.equals("false")) {
            intent.putExtra("folderchanged", "false");
        }
        if(Changed.equals("false")) {
            intent.putExtra("changed", mode);
        }
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}

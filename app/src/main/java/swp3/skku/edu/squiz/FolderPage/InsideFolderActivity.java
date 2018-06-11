package swp3.skku.edu.squiz.FolderPage;

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
    ArrayList<FolderList> folderLists = new ArrayList<FolderList>();
    Toolbar FolderToolbar;
    TextView TitleTextView;
    RecyclerView FolderPage_RV;
    Adapter_InsideFolder adapter_insideFolder;
    String title;
    Intent myIntent;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

    private void findViews(){
        FolderToolbar=findViewById(R.id.my_toolbar);
        TitleTextView = findViewById(R.id.InsideFolderTitle);
        FolderPage_RV = findViewById(R.id.InsideFolderRV);
    }
}

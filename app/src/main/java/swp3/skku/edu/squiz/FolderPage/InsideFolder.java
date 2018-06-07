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
import swp3.skku.edu.squiz.model.FolderList;

public class InsideFolder extends AppCompatActivity{
    ArrayList<FolderList> folderLists = new ArrayList<FolderList>();
    Toolbar FolderToolbar;
    TextView FolderTextView;
    TextView TitleTextView;
    RecyclerView folderpage_rv;
    Adapter_InsideFolder adapter_insideFolder;
    String title;
    Intent myIntent;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inside_folder);

        FolderToolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(FolderToolbar);
        findViews();

        myIntent = getIntent();
        title = myIntent.getStringExtra("title");
        TitleTextView.setText(title);

        adapter_insideFolder = new Adapter_InsideFolder(InsideFolder.this, R.layout.inside_folder_content, this, title);
        adapter_insideFolder.loadFolderData(title);
        folderpage_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        folderpage_rv.setAdapter(adapter_insideFolder);

        intent = new Intent();

        setResult(RESULT_OK, intent);

    }

    private void findViews(){
        TitleTextView = findViewById(R.id.InsideFolderTitle);
        //cardpage_count = findViewById(R.id.cardpage_count);
        folderpage_rv = findViewById(R.id.InsideFolderRV);
    }
}

package swp3.skku.edu.squiz.Add_Card_to_Folder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import swp3.skku.edu.squiz.Left.LeftFragment;
import swp3.skku.edu.squiz.MakeCard.MakeCardActivity;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.Right.RightFragment;
import swp3.skku.edu.squiz.TabPagerAdapter;
import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderItem;


public class AddCardToFolderActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    Fragment[] fragments_array;
    LeftFragment leftFragment;
    RightFragment rightFragment;
    final static int REQUEST_DataItemSet = 1;
    String cardTitle;
    int cardCount;

    final Context context=this;
    private ArrayList<FolderItem> FolderItemList = new ArrayList<>();
    private ArrayList<CardSetItem> CardSetItemList = new ArrayList<>();
    AppCompatActivity appCompatActivity;


    private RecyclerView recyclerView;
    private AdapterACTF adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_card_to_folder);
        final TextView MainTitle=(TextView)findViewById(R.id.actf);
        MainTitle.setText("폴더에 추가");
        recyclerView = (RecyclerView) findViewById(R.id.actf_RV);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterACTF(FolderItemList);
        adapter.initFolderSetData();

        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        );
    }
}

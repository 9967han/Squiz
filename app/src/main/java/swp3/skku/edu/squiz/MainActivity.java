package swp3.skku.edu.squiz;

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
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import swp3.skku.edu.squiz.Left.LeftFragment;
import swp3.skku.edu.squiz.MakeCard.MakeCardActivity;
import swp3.skku.edu.squiz.Right.RightFragment;
import swp3.skku.edu.squiz.model.CardSetItem;
import swp3.skku.edu.squiz.model.FolderItem;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    Fragment[] fragments_array;
    LeftFragment leftFragment;
    RightFragment rightFragment;
    final static int REQUEST_DataItemSet = 1;
    final static int REQUEST_EditItemSet = 2;
    String cardTitle;
    int cardCount;
    String cardChanged;

    final Context context=this;
    private ArrayList<FolderItem> FolderItemList = new ArrayList<>();
    private ArrayList<CardSetItem> CardSetItemList = new ArrayList<>();
    AppCompatActivity appCompatActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        final TextView MainTitle=(TextView)findViewById(R.id.main_title);
        MainTitle.setText("카드");
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        viewPager = findViewById(R.id.main_view_pager);
        final TabLayout tabLayout = findViewById(R.id.main_tab_layout);

        fragments_array = new Fragment[2];
        fragments_array[0] = new LeftFragment();
        fragments_array[1] = new RightFragment();
        leftFragment = (LeftFragment) fragments_array[0];
        rightFragment = (RightFragment) fragments_array[1];

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position=tab.getPosition();
                if(position==0){
                    MainTitle.setText("카드");
                    tabLayout.getTabAt(0).setIcon(R.drawable.ic_message_black_24dp);
                    tabLayout.getTabAt(1).setIcon(R.drawable.ic_folder_notselected_24dp);
                }
                else if(position==1){
                    MainTitle.setText("폴더");
                    tabLayout.getTabAt(0).setIcon(R.drawable.ic_message_notselected_24dp);
                    tabLayout.getTabAt(1).setIcon(R.drawable.ic_folder_black_24dp);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments_array);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_message_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_folder_notselected_24dp);

    }

    public void onMainFloatingClick(View view) {
        if(viewPager.getCurrentItem() == 0){
            Toast.makeText(getApplicationContext(), "카드만들기", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MakeCardActivity.class);
            startActivityForResult(intent, REQUEST_DataItemSet);
        }

        else if(viewPager.getCurrentItem() == 1){
            Toast.makeText(getApplicationContext(), "폴더추가", Toast.LENGTH_SHORT).show();
            final EditText FolderName=new EditText(this);
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("폴더 추가");
            alert.setMessage("새로운 폴더명을 입력하세요");
            alert.setCancelable(false);
            alert.setView(FolderName);

            alert.setPositiveButton("저장",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String folder_name=FolderName.getText().toString();

                            if(folder_name != null && !folder_name.equals("")){
                                 try {
                                     //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                            //makefolderactivity.checkVerify();
                                            //Todo: checkverify 김하은 0513
                                     //}else{
                                           savefolder_data(folder_name);
                                      //}
                                    } catch (IOException e) {
                                        Toast.makeText(getApplicationContext(),"폴더저장실패", Toast.LENGTH_SHORT).show();
                                 }
                                  Toast.makeText(getApplicationContext(),"폴더 저장완료", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                 Toast.makeText(getApplicationContext(), "폴더 제목을 입력하세요", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_DataItemSet){
            cardTitle = data.getStringExtra("title");
            cardCount = Integer.valueOf(data.getStringExtra("count"));
            leftFragment.addCardSetData(cardTitle, cardCount);
        }
        else if(requestCode == REQUEST_EditItemSet) {
            cardChanged = data.getStringExtra("changed").trim();
            if(cardChanged.equals("true")) {
                cardTitle = data.getStringExtra("title");
                cardCount = Integer.valueOf(data.getStringExtra("count"));
                leftFragment.EditCountCardSet(cardTitle, cardCount);
            }
            else if(cardChanged.equals("delete")) {
                cardTitle = data.getStringExtra("title");
                leftFragment.DeleteCardSetData(cardTitle);
            }
        }
    }

    public void onOptionItemSelected(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void savefolder_data(String folder_name) throws IOException {
        rightFragment.saveFolderData(folder_name);
    }
}

package swp3.skku.edu.squiz;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import swp3.skku.edu.squiz.Left.LeftFragment;
import swp3.skku.edu.squiz.MakeCard.MakeCardActivity;
import swp3.skku.edu.squiz.MakeFolder.MakeFolderActivity;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    Fragment[] fragments_array;
    LeftFragment leftFragment;
    RightFragment rightFragment;
    final static int REQUEST_DataItemSet = 1;
    String cardTitle;
    int cardCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        viewPager = findViewById(R.id.main_view_pager);
        TabLayout tabLayout = findViewById(R.id.main_tab_layout);

        fragments_array = new Fragment[2];
        fragments_array[0] = new LeftFragment();
        fragments_array[1] = new RightFragment();
        leftFragment = (LeftFragment) fragments_array[0];
        rightFragment = (RightFragment) fragments_array[1];

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments_array);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_message_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_folder_black_24dp);
    }

    public void onMainFloatingClick(View view) {
        if(viewPager.getCurrentItem() == 0){
            Toast.makeText(getApplicationContext(), "카드만들기", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MakeCardActivity.class);
            startActivityForResult(intent, REQUEST_DataItemSet);
        }else if(viewPager.getCurrentItem() == 1){
            Toast.makeText(getApplicationContext(), "폴더만들기", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MakeFolderActivity.class);
            startActivity(intent);
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
    }

    public void onOptionItemSelected(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }


}

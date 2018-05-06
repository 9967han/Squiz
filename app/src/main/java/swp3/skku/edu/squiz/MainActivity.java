package swp3.skku.edu.squiz;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import swp3.skku.edu.squiz.MakeCard.MakeCardActivity;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        viewPager = findViewById(R.id.main_view_pager);
        TabLayout tabLayout = findViewById(R.id.main_tab_layout);

        Fragment[] fragments_array = new Fragment[2];
        fragments_array[0] = new LeftFragment();
        fragments_array[1] = new RightFragment();

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), fragments_array);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_message_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_folder_black_24dp);
    }

    public void onMainFloatingClick(View view) {
        Log.d("Number", String.valueOf(viewPager.getCurrentItem()));
        if(viewPager.getCurrentItem() == 0){
            Toast.makeText(getApplicationContext(), "카드만들기", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, MakeCardActivity.class);
            startActivity(intent);
        }
    }
}

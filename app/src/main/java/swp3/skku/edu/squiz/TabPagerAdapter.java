package swp3.skku.edu.squiz;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by LG on 2018-05-02.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] fragments_array;


    public TabPagerAdapter(FragmentManager supportFragmentManager, Fragment[] fragments_array) {
        super(supportFragmentManager);
        this.fragments_array = fragments_array;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments_array[position];
    }

    @Override
    public int getCount() {
        return fragments_array.length;
    }

}

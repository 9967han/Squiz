package swp3.skku.edu.squiz.Left;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardSetItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftFragment extends android.support.v4.app.Fragment {
    private Context context;
    Adapter_left adapter_left;

    public LeftFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainpage_fragment_left, container, false);
        adapter_left = new Adapter_left(R.layout.mainpage_fragment_left_content, this.context, getActivity());
        adapter_left.initCardSetData();

        RecyclerView leftFragmentRV = view.findViewById(R.id.leftFragmentRV);
        leftFragmentRV.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false));
        leftFragmentRV.setAdapter(adapter_left);
        return view;
    }


    public void addCardSetData(String title, int count){
        CardSetItem cardSetItem = new CardSetItem(count, title);
        adapter_left.addCardSetData(cardSetItem);
    }

    public void EditCountCardSet(String title, int count) {
        adapter_left.editCountCardSet(title, count);
    }
    public void DeleteCardSetData(String title) {
        adapter_left.deleteCardSetData(title);

    }


}

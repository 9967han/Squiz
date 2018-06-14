package swp3.skku.edu.squiz.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import swp3.skku.edu.squiz.MainActivity;
import swp3.skku.edu.squiz.MakeCard.MakeCardActivity;
import swp3.skku.edu.squiz.R;

@SuppressLint("ValidFragment")
public class AddCardSetFrag extends Fragment {

    private String title;

    public AddCardSetFrag(String title) {
        this.title = title;
    }

    /*public static AddCardSetFrag newInstance(String title) {
        AddCardSetFrag fragment = new AddCardSetFrag(title);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), MakeCardActivity.class);
        getActivity().startActivityForResult(intent, 2);
        Toast.makeText(getActivity(), title, Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_card_set, container, false);
    }

}

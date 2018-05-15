package swp3.skku.edu.squiz.WordCardPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardItem;

public class WordCardActivity extends AppCompatActivity {

    private RecyclerView wordCardRV;
    private ArrayList<CardItem> cardPageItemList = new ArrayList<>();
    Adapter_wordCard adapter_wordCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_card);
        Intent intent = getIntent();
        cardPageItemList = (ArrayList<CardItem>) intent.getSerializableExtra("list");
        adapter_wordCard = new Adapter_wordCard(R.layout.word_card_content, this, cardPageItemList, getResources());
        findViews();
        wordCardRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        wordCardRV.setAdapter(adapter_wordCard);
    }


    private void findViews() {
        wordCardRV = findViewById(R.id.wordCardRV);
    }


}

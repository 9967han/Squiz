package swp3.skku.edu.squiz.CardPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.WordCardPage.WordCardActivity;

/**
 * Created by LG on 2018-05-12.
 */

public class CardPageActivity extends AppCompatActivity implements View.OnClickListener{
    TextView cardpage_title;
    TextView cardpage_count;
    TextView cardpage_like_count;
    Adapter_cardPage adapter_cardPage;
    RecyclerView cardpage_RV;
    TextView cardpage_word_card;
    TextView cardpage_subjective;
    TextView cardpage_learning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardpage);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String count = intent.getStringExtra("count");

        findViews();

        cardpage_title.setText("[\t\t\t"+title+"\t\t\t]");
        cardpage_count.setText("총 "+ count+"단어");
        cardpage_word_card.setOnClickListener(this);
        cardpage_subjective.setOnClickListener(this);
        cardpage_learning.setOnClickListener(this);

        adapter_cardPage = new Adapter_cardPage(R.layout.cardpage_content, this, cardpage_like_count);
        adapter_cardPage.loadItemData(title);
        cardpage_RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cardpage_RV.setAdapter(adapter_cardPage);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardpage_word_card:
                Intent intent = new Intent(CardPageActivity.this, WordCardActivity.class);
                intent.putExtra("list", adapter_cardPage.getCardPageItemList());
                startActivity(intent);
                break;
            case R.id.cardpage_subjective:
                break;
            case R.id.cardpage_learning:
                break;
        }
    }

    private void findViews(){
        cardpage_title = findViewById(R.id.cardpage_title);
        cardpage_count = findViewById(R.id.cardpage_count);
        cardpage_like_count = findViewById(R.id.cardpage__like_count);
        cardpage_RV = findViewById(R.id.cardpage_RV);
        cardpage_word_card = findViewById(R.id.cardpage_word_card);
        cardpage_subjective = findViewById(R.id.cardpage_subjective);
        cardpage_learning = findViewById(R.id.cardpage_learning);
    }



}

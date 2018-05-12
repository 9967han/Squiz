package swp3.skku.edu.squiz.CardPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import swp3.skku.edu.squiz.R;

/**
 * Created by LG on 2018-05-12.
 */

public class CardPageActivity extends AppCompatActivity {
    TextView cardpage_title;
    TextView cardpage_count;
    Adapter_cardPage adapter_cardPage;
    RecyclerView cardpage_RV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardpage);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String count = intent.getStringExtra("count");
        cardpage_title = findViewById(R.id.cardpage_title);
        cardpage_count = findViewById(R.id.cardpage_count);
        cardpage_RV = findViewById(R.id.cardpage_RV);
        cardpage_title.setText("[\t\t\t"+title+"\t\t\t]");
        cardpage_count.setText(count+" 단어");

        adapter_cardPage = new Adapter_cardPage(R.layout.cardpage_content, this);
        adapter_cardPage.loadItemData(title);
        cardpage_RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cardpage_RV.setAdapter(adapter_cardPage);

    }
}

package swp3.skku.edu.squiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.CardPageActivity;
import swp3.skku.edu.squiz.SubjectiveCardPage.SubjectiveCardActivity;
import swp3.skku.edu.squiz.WordCardPage.WordCardActivity;
import swp3.skku.edu.squiz.model.CardItem;

public class EndPageActivity extends AppCompatActivity {

    private TextView restartSubjective;
    private TextView restartCardPage;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.endpage);
        restartSubjective = findViewById(R.id.restartSub);
        restartCardPage = findViewById(R.id.restartCardPage);

        restartSubjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenta = new Intent(getApplicationContext(), SubjectiveCardActivity.class);
                Intent tempa = getIntent();
                intenta.putExtra("title", tempa.getStringExtra("title"));
                intenta.putExtra("list", tempa.getSerializableExtra("cardSet"));
                startActivity(intenta);
            }
        });

        restartCardPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentb = new Intent(getApplicationContext(), WordCardActivity.class);
                Intent tempb = getIntent();
                intentb.putExtra("list", tempb.getSerializableExtra("cardSet"));
                startActivity(intentb);

            }
        });

    }
}

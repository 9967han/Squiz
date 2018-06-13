package swp3.skku.edu.squiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.CardPageActivity;
import swp3.skku.edu.squiz.SubjectiveCardPage.SubjectiveCardActivity;
import swp3.skku.edu.squiz.WordCardPage.WordCardActivity;
import swp3.skku.edu.squiz.model.CardItem;

public class EndPageActivity extends AppCompatActivity {

    private TextView restartSubjective;
    private TextView restartCardPage;
    private LinearLayout end_replay;
    private LinearLayout end_star;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.endpage);
        restartSubjective = findViewById(R.id.restartSub);
        restartCardPage = findViewById(R.id.restartCardPage);
        end_replay = findViewById(R.id.end_replay);
        end_star = findViewById(R.id.end_star);

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
                Intent intentb = new Intent(getApplicationContext(), CardPageActivity.class);
                Intent tempb = getIntent();
                intentb.putExtra("title", tempb.getStringExtra("title"));
                intentb.putExtra("count", String.valueOf(((ArrayList<CardItem>)tempb.getSerializableExtra("cardSet")).size()));
                startActivity(intentb);

            }
        });

        end_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenta = new Intent(getApplicationContext(), SubjectiveCardActivity.class);
                Intent tempa = getIntent();
                intenta.putExtra("title", tempa.getStringExtra("title"));
                intenta.putExtra("list", tempa.getSerializableExtra("cardSet"));
                startActivity(intenta);
            }
        });

        end_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentb = new Intent(getApplicationContext(), CardPageActivity.class);
                Intent tempb = getIntent();
                intentb.putExtra("title", tempb.getStringExtra("title"));
                intentb.putExtra("count", String.valueOf(((ArrayList<CardItem>)tempb.getSerializableExtra("cardSet")).size()));
                startActivity(intentb);

            }
        });

    }
}

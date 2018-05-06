package swp3.skku.edu.squiz.MakeCard;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardItem;

public class MakeCardActivity extends AppCompatActivity {

    Adapter_makeCard adapter_makeCard;
    EditText cardTitle;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_card);

        RecyclerView makeCardRecyclerView = findViewById(R.id.makeCardRV);
        cardTitle = findViewById(R.id.makeCardTitle);
        constraintLayout = findViewById(R.id.focus);
        adapter_makeCard = new Adapter_makeCard(R.layout.make_card_content, this);
        makeCardRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        makeCardRecyclerView.setAdapter(adapter_makeCard);
    }

    public void onMakeCardFloatingClick(View view) {
        CardItem cardItem = new CardItem(null,null);
        adapter_makeCard.addItem(cardItem);
    }

    public void onMakeCardSaveButtonClick(View view) {
        constraintLayout.setFocusableInTouchMode(true);
        constraintLayout.requestFocus();
        String title = cardTitle.getText().toString();
        Toast.makeText(this, title+"카드 저장완료", Toast.LENGTH_SHORT).show();
        adapter_makeCard.saveCardData(title);
        this.finish();
    }
}

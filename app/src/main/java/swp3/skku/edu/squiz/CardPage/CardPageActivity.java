package swp3.skku.edu.squiz.CardPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import swp3.skku.edu.squiz.Add_Card_to_Folder.AddCardToFolderActivity;
import swp3.skku.edu.squiz.EditCard.EditCardActivity;
import swp3.skku.edu.squiz.FileDeleteTask;
import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.SubjectiveCardPage.SubjectiveCardActivity;
import swp3.skku.edu.squiz.WordCardPage.WordCardActivity;

import static java.lang.System.in;

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
    String title;
    String cardCount;
    Intent intent;
    String cardChanged;

    Toolbar myToolbar;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_card_to_folder, menu);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 0:
                if (requestCode == 0) {

                    setContentView(R.layout.cardpage);

                    cardCount = data.getStringExtra("count");
                    cardChanged = "true";


                    myToolbar=findViewById(R.id.my_toolbar);
                    setSupportActionBar(myToolbar);
                    findViews();

                    cardpage_title.setText("[\t\t\t"+title+"\t\t\t]");
                    cardpage_count.setText("총 " + cardCount + "단어");
                    cardpage_word_card.setOnClickListener(this);
                    cardpage_subjective.setOnClickListener(this);
                    cardpage_learning.setOnClickListener(this);

                    adapter_cardPage = new Adapter_cardPage(R.layout.cardpage_content, this, cardpage_like_count);
                    adapter_cardPage.loadItemData(title);
                    cardpage_RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    cardpage_RV.setAdapter(adapter_cardPage);

                    intent = new Intent();
                    intent.putExtra("title", title);
                    intent.putExtra("count", cardCount);
                    intent.putExtra("changed", cardChanged);
                    setResult(RESULT_OK, intent);
                }

                break;


            default:
                break;
        }

    }
    @Override
    public void onBackPressed() {
        intent = new Intent();
        if (cardChanged.equals("false")) {
            intent.putExtra("changed", cardChanged);
        }
        Log.d("cardPage", "Cardpage back button pressed");
        super.onBackPressed();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_to_folder:{
                Toast.makeText(getApplicationContext(),"카드를 추가할 폴더를 선택하세요", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CardPageActivity.this, AddCardToFolderActivity.class);
                intent.putExtra("title",title);
                startActivity(intent);
                break;
                //return true;
            }
            case R.id.edit_card:{
                Toast.makeText(getApplicationContext(), "카드 수정하기", Toast.LENGTH_SHORT).show();
                Intent editIntent = new Intent(CardPageActivity.this, EditCardActivity.class);
                editIntent.putExtra("title", title);
                startActivityForResult(editIntent, 0);
                break;
            }
            case R.id.delete_card:{
                Toast.makeText(getApplicationContext(), "카드를 삭제합니다", Toast.LENGTH_SHORT).show();
                FileDeleteTask fileTask = new FileDeleteTask(getApplicationContext(), title);
                fileTask.execute();
                cardChanged="delete";
                intent = new Intent();
                intent.putExtra("changed", cardChanged);
                intent.putExtra("title", title);
                setResult(RESULT_OK, intent);
                super.onBackPressed();

                break;
            }
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardpage);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        String count = intent.getStringExtra("count");
        cardChanged = "false";

        myToolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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

        intent = new Intent();
        intent.putExtra("changed", cardChanged);
        setResult(RESULT_OK, intent);
    }

    public void setSupportActionBar(Toolbar myToolbar) {
        super.setSupportActionBar(myToolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardpage_word_card:
                Intent wordCardIntent = new Intent(CardPageActivity.this, WordCardActivity.class);
                wordCardIntent.putExtra("list", adapter_cardPage.getCardPageItemList());
                startActivity(wordCardIntent);
                break;
            case R.id.cardpage_subjective:
                Intent subjectiveIntent = new Intent(CardPageActivity.this, SubjectiveCardActivity.class);
                subjectiveIntent.putExtra("list", adapter_cardPage.getCardPageItemList());
                subjectiveIntent.putExtra("title", title);
                startActivity(subjectiveIntent);
                break;
            case R.id.cardpage_learning:
                Intent editIntent = new Intent(CardPageActivity.this, EditCardActivity.class);
                editIntent.putExtra("title", title);
                startActivityForResult(editIntent, 0);
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

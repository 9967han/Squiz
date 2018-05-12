package swp3.skku.edu.squiz.MakeCard;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import swp3.skku.edu.squiz.R;
import swp3.skku.edu.squiz.model.CardItem;

public class MakeCardActivity extends AppCompatActivity {

    Adapter_makeCard adapter_makeCard;
    EditText cardTitle;
    ConstraintLayout constraintLayout;
    String title;
    int count;
    AppCompatActivity MakeCardActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_card);
        MakeCardActivity = this;

        RecyclerView makeCardRecyclerView = findViewById(R.id.makeCardRV);
        cardTitle = findViewById(R.id.makeCardTitle);
        constraintLayout = findViewById(R.id.focus);
        adapter_makeCard = new Adapter_makeCard(R.layout.make_card_content, this, this);
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
        title = cardTitle.getText().toString();
        if(title != null && !title.equals("")){
            try {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    checkVerify();
                }else{
                    adapter_makeCard.saveCardData(title);    
                }
            } catch (IOException e) {
                Toast.makeText(this, "카드저장실패", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, title+"카드 저장완료", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("title", title);
            intent.putExtra("count", String.valueOf(adapter_makeCard.cardItemListSize()));
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "카드제목을 입력하세요", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkVerify() {
        //저장소에 Read/Write 권한이 있는지 확인
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //TODO 상욱: should we show an explanation?
            if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    //Explain to the user why we need to write the permission.
                Toast.makeText(this, "READ/WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
            }
            //권한 팝업을 요청하는 메서드 (Write/Read 권한 요청)
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }else{
            try {
                adapter_makeCard.saveCardData(title);
            } catch (IOException e) {
                Toast.makeText(this, "카드저장실패", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1){
            if(grantResults.length >0){
                for(int i = 0; i<grantResults.length; ++i){
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                        //하나라도 거부한다면,
                        new AlertDialog.Builder(this).setTitle("알림").setMessage("권한을 허용해주셔야 앱을 이용할 수 있습니다.").setPositiveButton("종료", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                MakeCardActivity.finish();
                            }
                        }).setNegativeButton("권한 설정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:"+ getApplicationContext().getPackageName()));
                                getApplicationContext().startActivity(intent);
                            }
                        }).setCancelable(false).show();
                        return;
                    }
                }
                try {
                    adapter_makeCard.saveCardData(title);
                } catch (IOException e) {
                    Toast.makeText(this, "카드저장실패", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}

package swp3.skku.edu.squiz;

import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import swp3.skku.edu.squiz.MakeCard.Adapter_makeCard;
import swp3.skku.edu.squiz.model.CardItem;

public class InsideFolder extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.inside_folder);

        RecyclerView InsideFolderRecyclerView = findViewById(R.id.InsideFolderRV);
    }

    public void onInsideFolderModifyButtonClick(View view) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("폴더명 수정");
        alert.setMessage("새로운 폴더명을 입력하세요");

        final EditText FolderName=new EditText(this);
        alert.setView(FolderName);

        alert.setPositiveButton("저장", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String folder_name=FolderName.getText().toString();
            }
        });
        alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.show();

        //folder_name 따로 저장해야함
        //todo 김하은: 이거 나중에 마저 작성해야함

    }
}

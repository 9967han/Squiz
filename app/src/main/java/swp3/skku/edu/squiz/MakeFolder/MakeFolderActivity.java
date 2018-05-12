package swp3.skku.edu.squiz.MakeFolder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import swp3.skku.edu.squiz.MakeCard.Adapter_makeCard;
import swp3.skku.edu.squiz.R;

/**
 * Created by LG on 2018-05-07.
 */

public class MakeFolderActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_fragment_right);
    }

    public void makefolder(View view){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("폴더 만들기");
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
    }
}

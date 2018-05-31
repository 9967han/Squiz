package swp3.skku.edu.squiz;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import swp3.skku.edu.squiz.CardPage.CardPageActivity;
import swp3.skku.edu.squiz.model.CardItem;
import swp3.skku.edu.squiz.model.CardSetItem;

/**
 * Created by LG on 2018-05-12.
 */

public class FileDeleteTask extends AsyncTask<Void, Void, Void> {
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squiz.txt";
    ArrayList<CardItem> cardPageItemList;
    String title;
    Context context;


    int idx;

    public FileDeleteTask(Context context, String title) {
        this.context = context;
        this.title = title;
        this.idx=0;
    }

    ProgressDialog asyncDialog = new ProgressDialog(
            context);

    @Override
    protected void onPreExecute() {
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("로딩중입니다..");

        // show dialog
        asyncDialog.show();
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        InputStream is = null;
        if (idx==0){
            try {
                ArrayList<String> prev = new ArrayList<>();

                is = new FileInputStream(filePath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = "";


                while((line=reader.readLine()) != null) {
                    String words[] = line.split("[,]");
                    if(!(words[0].equals(title))) {
                        prev.add(line);
                    }
                }
                reader.close();
                is.close();

                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Squiz");
                File squiz = new File(dir, "squiz.txt");
                FileOutputStream fos = new FileOutputStream(squiz, false);  //파일쓰기
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

                for(String tempLine : prev) {
                    writer.write(tempLine);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        asyncDialog.dismiss();
        super.onPostExecute(result);
    }
}

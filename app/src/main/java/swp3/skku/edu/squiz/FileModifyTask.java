package swp3.skku.edu.squiz;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import swp3.skku.edu.squiz.model.CardItem;

/**
 * Created by LG on 2018-05-12.
 */

public class FileModifyTask extends AsyncTask<Void, Void, Void> {
    final static String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Squiz/squiz.txt";
    ArrayList<CardItem> cardPageItemList;
    String word;
    String mean;
    
    public FileModifyTask(ArrayList<CardItem> cardPageItemList, String word, String mean) {
        this.cardPageItemList = cardPageItemList;
        this.word = word;
        this.mean = mean;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            File inputFile = new File(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "outputFile");
            if(!dir.exists()) dir.mkdirs();
            File outputFile = new File(dir, "outputFile.txt");
            //Output Stream 생성
            FileOutputStream fos = null;
            fos = new FileOutputStream(outputFile, true);  //파일쓰기
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            String line = "";
            while((line=reader.readLine())!=null){
                String words[] = line.split(",");
                if(words[1].equals(word) && words[2].equals(mean)){
                    if(words[3].equals("false")){
                        line = line.replaceAll("false", "true");
                        writer.write(line);
                        writer.newLine();
                    }else if(words[3].equals("true")){
                        line = line.replaceAll("true", "false");
                        writer.write(line);
                        writer.newLine();
                    }
                }
                else{
                    writer.write(line);
                    writer.newLine();
                }
            }
            writer.flush();
            writer.close();
            reader.close();
            inputFile.delete();
            outputFile.renameTo(new File(filePath));
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


package swp3.skku.edu.squiz.model;

import java.util.ArrayList;

public class FolderItem {

    String folder_name;
    ArrayList<CardSetItem> cardsetitems;
    public ArrayList<CardSetItem> getCardSetItems() {
        return cardsetitems;
    }
    public FolderItem(String folder_name){
        this.folder_name = folder_name;
    }

    public String getWord() {
        return folder_name;
    }
    public void setWord(String word) {
        this.folder_name = word;
    }
    //Todo: 김하은 0513
}

package swp3.skku.edu.squiz.model;

import java.util.ArrayList;

public class FolderItem {

    String folder_name;
    ArrayList<CardSetItem> cardsetitems;
    int count;

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

    public void setCardSetItems(ArrayList<CardSetItem> cardSetItems) {
        this.cardsetitems = cardSetItems;
        this.count=cardsetitems.size();
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public FolderItem(int count, String title) {

        this.count = count;
        this.folder_name = title;
    }
}

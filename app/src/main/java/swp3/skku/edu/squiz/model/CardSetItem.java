package swp3.skku.edu.squiz.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by LG on 2018-05-07.
 */

public class CardSetItem implements Serializable {
    int count;
    String title;
    String folder;
    ArrayList<CardItem> cardItems;

    public ArrayList<CardItem> getCardItems() {
        return cardItems;
    }

    public void setCardItems(ArrayList<CardItem> cardItems) {
        this.cardItems = cardItems;
    }

    public int getCount() {
        return count;
    }

    public String getTitle() {
        return title;
    }

    public String getFolder() {return folder;}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFolder(String folder) {this.folder = folder;}

    public void setCount(int count) {
        this.count = count;
    }

    public CardSetItem(int count, String title) {

        this.count = count;
        this.title = title;
    }
}

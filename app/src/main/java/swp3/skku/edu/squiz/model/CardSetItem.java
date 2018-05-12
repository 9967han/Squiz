package swp3.skku.edu.squiz.model;

import java.util.ArrayList;

/**
 * Created by LG on 2018-05-07.
 */

public class CardSetItem {
    int count;
    String title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CardSetItem(int count, String title) {

        this.count = count;
        this.title = title;
    }
}

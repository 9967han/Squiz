package swp3.skku.edu.squiz.model;

/**
 * Created by LG on 2018-05-07.
 */

public class CardSetItem {
    int count;
    String title;

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

package swp3.skku.edu.squiz.model;

import android.widget.RadioButton;

import java.util.ArrayList;

public class FolderList {
    String foldertitle;
    ArrayList<String> CardsetInFolder;

    public FolderList(String string){
        foldertitle = string;
    }

    public ArrayList<String> getCardsetInFolder() {
        return CardsetInFolder;
    }

    public void setCardsetInFolder(ArrayList<String> cardsetInFolder) {
        CardsetInFolder = cardsetInFolder;
    }

    public void addCardSetInFolder(String newcard){
        CardsetInFolder.add(newcard);
    }

    public String getFoldertitle() {
        return foldertitle;
    }

    public void setFoldertitle(String foldertitle) {
        this.foldertitle = foldertitle;
    }
}

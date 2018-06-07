package swp3.skku.edu.squiz.model;

import android.widget.RadioButton;

import java.util.ArrayList;

public class FolderList {
    String foldertitle;
    ArrayList<String> CardsetInFolder = new ArrayList<String>();
    //ArrayList<Integer> CardsetCount = new ArrayList<>();
    Boolean isSelected;

    public FolderList(){

    }
    public FolderList(String string){
        foldertitle = string;
    }

    public ArrayList<String> getCardsetInFolder() {
        return CardsetInFolder;
    }

    public void setCardsetInFolder(ArrayList<String> cardsetInFolder) {
        CardsetInFolder = cardsetInFolder;
    }
    public boolean isSelected(){
        return  isSelected;
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
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

    public int getSize(){
        return CardsetInFolder.size();
    }
}

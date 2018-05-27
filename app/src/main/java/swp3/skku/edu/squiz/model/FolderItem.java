package swp3.skku.edu.squiz.model;

import java.util.ArrayList;

public class FolderItem {

    String folder_name;
    boolean isSelected;

    public FolderItem(String folder_name) {
        this.folder_name = folder_name;
    }

    public FolderItem(String folder_name, boolean isSelected){
        this.folder_name = folder_name;
        this.isSelected = isSelected;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public boolean isSelected(){
        return  isSelected;
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
}
package swp3.skku.edu.squiz;

/**
 * Created by LG on 2018-06-11.
 */

public class OPCode {
    //Operation Code입니다.
    public static final byte
            //OutTask
            Save_Card_Data = 0x00, Save_Folder_Name_Data = 0x01, Save_Folder_Item_Lists = 0x02,

            //InitTask
            INIT_Card_Set_Item_Lists = 0x10, INIT_Folder_Item_Lists = 0x11, INIT_Folder_Cardset_Lists = 0x12,

            //DeleteTask
            DELETE_Card_Set = 0x20;
}

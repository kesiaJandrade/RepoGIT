package k3.chadebeber17.DataBase;

/**
 * Created by Kesia on 10/12/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.*;

public class DataBase extends SQLiteOpenHelper {

    public DataBase (Context context){
        super(context, "db_cha_de_beber", null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptDataBase.getCreateCerveja());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

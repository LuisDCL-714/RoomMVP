package pe.cibertec.agendaroommvp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import pe.cibertec.agendaroommvp.data.db.model.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instancia;

    public static AppDatabase nuevaInstancia(Context contexto) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(contexto, AppDatabase.class, "agenda.db")
                    .build();
        }
        return instancia;
    }

    public abstract ContactDao getContactDao();

}

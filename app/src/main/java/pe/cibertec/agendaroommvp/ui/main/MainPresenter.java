package pe.cibertec.agendaroommvp.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import java.util.List;
import pe.cibertec.agendaroommvp.data.db.AppDatabase;
import pe.cibertec.agendaroommvp.data.db.model.Contact;

public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView view;
    private Contact contact;

    public MainPresenter(MainContract.MainView view){
        this.view = view;
    }

    @Override
    public void addContact(String name) {
        contact = new Contact(name);
        new TaskAddContac().execute(contact);
    }

    @Override
    public void getAllContact() {
        new TaskGetContacts().execute();
    }

    private class TaskAddContac extends AsyncTask<Contact,Void,Void> {
        @Override
        protected Void doInBackground(Contact... contacts) {
            AppDatabase.nuevaInstancia((Context) view).getContactDao().insertContacts(contacts);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.refreshAdapter();
        }
    }


    private class TaskGetContacts extends AsyncTask<Void, Void, List<Contact>>{
        @Override
        protected List<Contact> doInBackground(Void... voids) {
            return AppDatabase.nuevaInstancia((Context) view).getContactDao().getAll();
        }


        @Override
        protected void onPostExecute(List<Contact> contacts) {
            super.onPostExecute(contacts);
            view.showAllContact(contacts);
        }
    }
}

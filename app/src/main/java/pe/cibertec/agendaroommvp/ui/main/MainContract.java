package pe.cibertec.agendaroommvp.ui.main;
import java.util.List;
import pe.cibertec.agendaroommvp.data.db.model.Contact;

public interface MainContract {
    interface MainView{
        void showAllContact(List<Contact> contact);
        void refreshAdapter();
    }

    interface MainPresenter{
        void addContact(String name);
        void getAllContact();
    }
}

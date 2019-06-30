package pe.cibertec.agendaroommvp.ui.main;

import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import java.util.List;
import pe.cibertec.agendaroommvp.R;
import pe.cibertec.agendaroommvp.data.db.AppDatabase;
import pe.cibertec.agendaroommvp.data.db.model.Contact;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private TextInputEditText etNombre;
    private Button btAdd;
    private RecyclerView rvContact;
    private List<Contact> items;
    private ContactAdapter adapter;
    private MainContract.MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListeners();
        configureMVP();
    }

    private void configureMVP(){
        this.presenter = new MainPresenter(this);
    }

    private void setListeners(){
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etNombre.getText().toString().equals("")){
                    presenter.addContact(etNombre.getText().toString());
                    presenter.getAllContact();
                    etNombre.setText("");
                }

            }
        });
    }

    private void initView(){
        etNombre = findViewById(R.id.etName);
        btAdd = findViewById(R.id.btAdd);
        rvContact = findViewById(R.id.rvContact);
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.getAllContact();
    }

    @Override
    public void showAllContact(List<Contact> contact) {
        items = contact;
        adapter = new ContactAdapter(items);
        rvContact.setAdapter(adapter);
        rvContact.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void refreshAdapter() {
        adapter.notifyDataSetChanged();
    }
}

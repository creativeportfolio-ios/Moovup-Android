package com.example.anonymous.demoapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anonymous.demoapp.R;
import com.example.anonymous.demoapp.adpter.ContactAdapter;
import com.example.anonymous.demoapp.db.AppDataBase;
import com.example.anonymous.demoapp.modal.Contact;
import com.example.anonymous.demoapp.ui.base.BaseActivity;
import com.example.anonymous.demoapp.utils.AppConstant;
import com.example.anonymous.demoapp.utils.NetworkUtils;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends BaseActivity implements ContactAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    List<Contact> contactList;
    ContactAdapter adapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    AppDataBase dataBase;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initControl() {
        dataBase = AppDataBase.getAppDatabase(this);

        adapter = new ContactAdapter(MainActivity.this, contactList);
        adapter.setOnItemClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);

        if (dataBase.contactDao().loadAll() != null && dataBase.contactDao().loadAll().size() > 0) {
            adapter.setContactList(dataBase.contactDao().loadAll());
        }

        if (NetworkUtils.isNetworkAvailable(this)) {
            getContactList();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            alertDialog.setTitle("Info");
            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.show();
        }
    }

    private void getContactList() {
        swipeRefreshLayout.setRefreshing(true);
        Call<List<Contact>> call = apiService.getContact();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {
                swipeRefreshLayout.setRefreshing(false);
                dataBase.contactDao().delete();
                List<Contact> contacts = response.body();
                adapter.setContactList(contacts);
                dataBase.contactDao().insert(contacts);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                if (dataBase.contactDao().loadAll() != null && dataBase.contactDao().loadAll().size() > 0) {
                    adapter.setContactList(dataBase.contactDao().loadAll());
                }
            }
        });
    }

    @Override
    public void onItemClick(Contact contact) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(AppConstant.BundleExtras.CONTACT, contact);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        getContactList();
    }
}




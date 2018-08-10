package com.example.anonymous.demoapp.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.anonymous.demoapp.R;
import com.example.anonymous.demoapp.adpter.ContactAdapter;
import com.example.anonymous.demoapp.modal.Contact;
import com.example.anonymous.demoapp.ui.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends BaseActivity implements ContactAdapter.OnItemClickListener {

    List<Contact> contactList;
    ContactAdapter adapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initControl() {
        adapter = new ContactAdapter(MainActivity.this, contactList);
        adapter.setOnItemClickListener(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getContactList();
    }

    private void getContactList() {
        Call<List<Contact>> call = apiService.getContact();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {
                List<Contact> contacts = response.body();
                adapter.setContactList(contacts);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onItemClick(Contact contact) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("name", contact.getName());
        intent.putExtra("email", contact.getEmail());
        intent.putExtra("picture", contact.getPicture());
        intent.putExtra("location", contact.getLocation());
        startActivity(intent);
    }
}




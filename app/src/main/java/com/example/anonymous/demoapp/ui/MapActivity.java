package com.example.anonymous.demoapp.ui;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anonymous.demoapp.R;
import com.example.anonymous.demoapp.modal.Contact;
import com.example.anonymous.demoapp.ui.base.BaseActivity;
import com.example.anonymous.demoapp.utils.AppConstant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txtview1)
    TextView txtname;
    @BindView(R.id.txtview2)
    TextView txtemail;
    String imagepath;
    Contact contact;
    private GoogleMap mGoooglemap;

    @Override
    public int getLayout() {
        return R.layout.activity_map;
    }

    @Override
    public void initControl() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        contact = (Contact) getIntent().getSerializableExtra(AppConstant.BundleExtras.CONTACT);

        setData();
    }

    private void setData() {
        setTitle(contact.getName());

        txtname.setText(getString(R.string.name) + contact.getName());
        txtemail.setText(getString(R.string.email) + contact.getEmail());

        Glide.with(this)
                .load(contact.getPicture())
                .into(imageView);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoooglemap = googleMap;

        LatLng position = new LatLng(contact.getLocation().getLatitude(), contact.getLocation().getLongitude());
        mGoooglemap.addMarker(new MarkerOptions().position(position).title(contact.getName()));
        mGoooglemap.moveCamera(CameraUpdateFactory.newLatLng(position));
        mGoooglemap.animateCamera(CameraUpdateFactory.zoomTo(5));
    }
}

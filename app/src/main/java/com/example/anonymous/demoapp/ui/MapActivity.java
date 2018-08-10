package com.example.anonymous.demoapp.ui;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anonymous.demoapp.R;
import com.example.anonymous.demoapp.modal.Location;
import com.example.anonymous.demoapp.ui.base.BaseActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;

public class MapActivity extends BaseActivity implements OnMapReadyCallback {
    private GoogleMap mGoooglemap;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.txtview1)
    TextView txtname;
    @BindView(R.id.txtview2)
    TextView txtemail;
    String imagepath;

    String name, email;
    Location location;

    @Override
    public int getLayout() {
        return R.layout.activity_map;
    }

    @Override
    public void initControl() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        imagepath = getIntent().getStringExtra("picture");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        location = (Location) getIntent().getSerializableExtra("location");

        setdata();
    }

    private void setdata() {

        setTitle(name);
        txtname.setText(getString(R.string.name) + name);
        txtemail.setText(getString(R.string.email) + email);

        Glide.with(this)
                .load(imagepath)
                .into(imageView);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoooglemap = googleMap;

        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
        mGoooglemap.addMarker(new MarkerOptions().position(position).title(name));
        mGoooglemap.moveCamera(CameraUpdateFactory.newLatLng(position));
        mGoooglemap.animateCamera(CameraUpdateFactory.zoomTo(5));
    }
}

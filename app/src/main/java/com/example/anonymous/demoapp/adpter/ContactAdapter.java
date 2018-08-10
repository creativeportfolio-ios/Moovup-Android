package com.example.anonymous.demoapp.adpter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anonymous.demoapp.R;
import com.example.anonymous.demoapp.modal.Contact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ProductViewHolder> {
    private Context mCtx;
    private List<Contact> contactList;
    OnItemClickListener onItemClickListener;

    public ContactAdapter(Context mCtx, List<Contact> contactList) {
        this.mCtx = mCtx;
        this.contactList = contactList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_data, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (position % 2 == 1) {
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#45B653"));
        } else {
            holder.relativeLayout.setBackgroundColor(Color.parseColor("#EAEAEA"));
        }

        final Contact contact = contactList.get(position);

        Glide.with(mCtx)
                .load(contact.getPicture())
                .into(holder.imageView);

        holder.txtname.setText(contact.getName());
        holder.txtemail.setText(contact.getEmail());
        holder.latitude.setText(contact.getLocation().getLongitude().toString());
        holder.latitude.setText(contact.getLocation().getLatitude().toString());


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(contact);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList != null ? contactList.size() : 0;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewname)
        TextView txtname;
        @BindView(R.id.textViewEmail)
        TextView txtemail;
        @BindView(R.id.latitude)
        TextView latitude;
        @BindView(R.id.longitude)
        TextView longitude;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.layout)
        RelativeLayout relativeLayout;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Contact contact);
    }
}







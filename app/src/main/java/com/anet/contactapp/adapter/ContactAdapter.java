package com.anet.contactapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anet.contactapp.R;
import com.anet.contactapp.callbacks.OnContactClicked;
import com.anet.contactapp.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter  extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {
    private List<Contact> contacts = new ArrayList<>();

    private OnContactClicked contactsClickListener;

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_contact_view, parent, false);
        return new ContactHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact currentContact = contacts.get(position);
        holder.contact_view_TVW_first_name.setText(currentContact.getFirstName());
        holder.contact_view_TVW_last_name.setText(currentContact.getLastName());
        holder.contact_view_TVW_phone.setText(currentContact.getPhoneNumber());


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public ContactAdapter setContactsClickListener(OnContactClicked contactsClickListener) {
        this.contactsClickListener = contactsClickListener;
        return this;
    }

    public Contact getContactAt(int position ){
        return contacts.get(position);

    }

    class ContactHolder extends RecyclerView.ViewHolder {
        private TextView contact_view_TVW_first_name;
        private TextView contact_view_TVW_last_name;

        private TextView contact_view_TVW_phone;

        private ImageButton contact_view_edit;


        public ContactHolder(View itemView) {
            super(itemView);
            contact_view_TVW_first_name = itemView.findViewById(R.id.contact_view_TVW_first_name);
            contact_view_TVW_last_name = itemView.findViewById(R.id.contact_view_TVW_last_name);
            contact_view_TVW_phone = itemView.findViewById(R.id.contact_view_TVW_phone);
            contact_view_edit = itemView.findViewById(R.id.contact_view_edit);

            contact_view_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(contactsClickListener!=null ){
                        contactsClickListener.editClicked(contacts.get(getAdapterPosition()));
                    }



                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(contactsClickListener!=null ){
                        contactsClickListener.clicked(contacts.get(getAdapterPosition()));
                    }



                }
            });





        }
    }


}

package com.anet.contactapp.callbacks;

import com.anet.contactapp.entities.Contact;

public interface OnContactClicked {

    void clicked(Contact contact);
    void editClicked(Contact contact);
}

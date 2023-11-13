package com.bhtu.phonebook

class PhoneItem (
    val fullName: String,
    val phoneNumber: PhoneNumber,
    val email: String
) {
    companion object {
        var counter = 0;
    }

    val id: Int;

    init {
        id = counter++;
    }
}
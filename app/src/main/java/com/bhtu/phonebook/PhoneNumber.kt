package com.bhtu.phonebook

class PhoneNumber (
    phoneString: String
) {
    private companion object {
        val PHONE_NUMBER_LENGTH = 10
    }

    private var chars = CharArray(PHONE_NUMBER_LENGTH) {
        '0' // pad with zeros
    }

    init {
        var innerIndex = PHONE_NUMBER_LENGTH - 1
        var outerIndex = phoneString.length - 1

        while (innerIndex >= 0 && outerIndex >=0) {
            this.chars[innerIndex] = phoneString[outerIndex]
            innerIndex --
            outerIndex --
        }
    }

    val number: String
        get() = String(this.chars)
}
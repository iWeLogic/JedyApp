package com.iwelogic.jedyapp.models

data class NativeAdItem(val adItemId: String) : ListItem {
    override val id: String
        get() = adItemId
}
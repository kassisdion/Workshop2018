package com.workshop.workshop.ui.main

import android.os.Parcel
import android.os.Parcelable

data class UiObjectModel(val albumId: Long,
                         val id: Long,
                         val title: String,
                         val url: String,
                         val thumbnailUrl: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(albumId)
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(url)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiObjectModel> {
        override fun createFromParcel(parcel: Parcel): UiObjectModel {
            return UiObjectModel(parcel)
        }

        override fun newArray(size: Int): Array<UiObjectModel?> {
            return arrayOfNulls(size)
        }
    }

}
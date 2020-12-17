package ru.konder.myapplicationnewsapi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemOfList (
    var ImgSrc: String,
    var ImgTitle: String,
    var img_description: String,
    var ImgDescription: String
) : Parcelable
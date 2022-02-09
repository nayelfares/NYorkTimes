package com.android.nynews.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

/**
 * Entity classes used in database
 */
@Entity
data class ResultResponse(
    @PrimaryKey(autoGenerate = true)
    var resultId : Int,
    var status: String,
    var copyright: String,
    var num_results: Int,
    var results: List<Article>
)

@Parcelize
@Entity
data class Article(
    @PrimaryKey
    @field:Json(name="id")
    var articleId: Long,
    var uri: String,
    var url: String,
    var source: String,
    var published_date: String,
    var section: String,
    var subsection: String,
    var byline: String,
    var title: String,
    @field:Json(name="abstract")
    var description: String,
    var media:@RawValue List<Media>
): Parcelable

@Parcelize
@Entity
data class Media(
    @PrimaryKey(autoGenerate = true)
    var mediaId: Int,
    var caption: String,
    @field:Json(name="media-metadata")
    var metadata:@RawValue List<MediaMetaData>
): Parcelable

@Parcelize
@Entity
data class MediaMetaData(
    @PrimaryKey(autoGenerate = true)
    var metaDataId: Int,
    var url: String,
    var format : String
): Parcelable
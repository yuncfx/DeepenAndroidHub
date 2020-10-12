package com.deepen.android.hub.codelib.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * @author shane
 */
class NewsItem : Parcelable {
    var id = 0
    var newId: String? = null
    var type: String? = null
    var title: String? = null
    var language = 0
    var content: String? = null
    var pic: String? = null
    var releaseTime: String? = null
    var isSpecial: String? = null
    var source: String? = null

    constructor() {}

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val that = o as NewsItem
        return id == that.id && if (newId != null) newId == that.newId else that.newId == null
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + if (newId != null) newId.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return "NewsItem{" +
                "id=" + id +
                ", newId='" + newId + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", language=" + language +
                ", content='" + content + '\'' +
                ", pic='" + pic + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", isSpecial='" + isSpecial + '\'' +
                ", source='" + source + '\'' +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(newId)
        dest.writeString(type)
        dest.writeString(title)
        dest.writeInt(language)
        dest.writeString(content)
        dest.writeString(pic)
        dest.writeString(releaseTime)
        dest.writeString(isSpecial)
        dest.writeString(source)
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        newId = `in`.readString()
        type = `in`.readString()
        title = `in`.readString()
        language = `in`.readInt()
        content = `in`.readString()
        pic = `in`.readString()
        releaseTime = `in`.readString()
        isSpecial = `in`.readString()
        source = `in`.readString()
    }

    companion object {
        const val IS_SPECIAL = "1"
        const val PIC_NEWS = "1"
        val CREATOR: Parcelable.Creator<NewsItem?> = object : Parcelable.Creator<NewsItem?> {
            override fun createFromParcel(source: Parcel): NewsItem? {
                return NewsItem(source)
            }

            override fun newArray(size: Int): Array<NewsItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}
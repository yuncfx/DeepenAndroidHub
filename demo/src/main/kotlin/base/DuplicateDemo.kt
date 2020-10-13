package base

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

data class SongInfo constructor(val name: String?, val url: String?)

fun SongInfo.toSimpleString(): String {
    return "$name:$url"
}

fun removeDuplicates(list: List<SongInfo>): List<SongInfo> {

    val s: TreeSet<SongInfo> = TreeSet(kotlin.Comparator { o1: SongInfo, o2: SongInfo ->
        println("comparing: ${o1.name}:${o1.url}, ${o2.name}:${o2.url}")
        if (o1.url == o2.url) {
            println("url equals")
            0
        } else {
            1
        }
    })

    s.addAll(list)
    println("s.size:" + s.size)
//    for (person in s) {
//        println("${person.toSimpleString()} in set")
//    }
    return ArrayList(s)
}

//fun main() {
//    val list = arrayListOf(
//        SongInfo("races", "file:///storage/1E83-B59D/music/races.imy"),
//        SongInfo("明天是那一天", "file:///storage/1E83-B59D/music/明天是那一天.aac"),
//        SongInfo("爱你3000 (Live)", "file:///storage/1E83-B59D/music/潘玮柏 _ SeanT肖恩恩 _ 黄旭 - 爱你3000 (Live).mp3"),
//        SongInfo("races222", "file:///storage/1E83-B59D/music/races.imy"),
//        SongInfo("再回首", "file:///storage/1E83-B59D/music/01. 再回首.aac"),
//        SongInfo(
//            "K.Will - 说干什么呢 - 2016KBS音乐银行现场",
//            "file:///storage/1E83-B59D/music/K.Will - 说干什么呢 - 2016KBS音乐银行现场.mp3"
//        ),
//        SongInfo("Sunshine Girl", "file:///storage/1E83-B59D/music/Moumoon - Sunshine Girl.mp3"),
//        SongInfo("Rain - La Song", "file:///storage/1E83-B59D/music/Rain - La Song.mp3"),
//        SongInfo("静悄悄", "file:///storage/1E83-B59D/music/陈泫孝（大泫）-静悄悄.mp3")
//
//    )
//    val aimList: MutableList<SongInfo> = ArrayList()
////    for (x in 0..200) {
//    aimList.addAll(list)
////    }
////    println("before remove aimList.size:" + aimList.size)
////    val start = System.currentTimeMillis()
////
////    val removeDuplicates = removeDuplicates(aimList)
////    val duration = System.currentTimeMillis() - start
////    println("-------------------------------------------------------------------------------------------")
////    println("duration is $duration" + ", after removeDuplicates.size:" + removeDuplicates.size)
////    for (song in removeDuplicates) {
////        println(song.toSimpleString())
////    }
//
//    val set:HashSet<String?> = HashSet()
//
//    println("map.size:" +  set.size)
//
//    val songSet:HashSet<SongInfo> = HashSet()
//
//    list.forEach {
//        if (!set.contains(it.url)) {
//            set.add(it.url)
//            songSet.add(it)
//        }
//    }
//
//    ArrayList(songSet).forEach {
//        println(it)
//    }
//
//}
package leetcode.primary.strings

class Revert {

  fun reverseString(s: CharArray): Unit {
    var i = 0
    var j = s.size - 1
    while (i < j) {
      val t = s[i]
      s[i] = s[j]
      s[j] = t
      i++
      j--
    }
  }
}
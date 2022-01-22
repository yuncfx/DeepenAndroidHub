@file:Official
package com.official

import com.ssy.Official

/*
    结构相等 == check for equals()
    引用相等 === 两个指针指向同一个对象

    == 操作符，逆操作符!=
    == 操作符会转换成如下代码：
    a?.equals(b) ?: (b === null)

    a == null 会自动转换成 a === null

    如果要实现自定义的==行为， 需要重写方法
    equals(other:Any?):Boolean，重载其它同名不同参的方法，都不生效。

    === 你想操作符 !==
    基本数据类型的===操作，会自动转换成==

    浮点数比较，参见文档 https://kotlinlang.org/docs/basic-types.html#floating-point-numbers-comparison
 */
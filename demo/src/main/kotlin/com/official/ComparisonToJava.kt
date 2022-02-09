@file:Official
package com.official

import com.ssy.Official

/*
    Kotlin对Java的增强：
    1. 空指针
    2. No raw types
    3. 数组在Kotlin中是不可变的
    4. Kotlin拥有合适的函数类型，比Java中的SAM更强
    5. 使用处型变（use-site variance）不使用通配符
    6. Kotlin中没有受检异常

    Java有而Kotlin没有的特性：
    1. 受检异常
    2. 基本数据类型不是类，字节码尽可能使用基本数据类型，但它们不是明确指定使用。
    3. 静态成员替换成了伴生对象，顶层函数，扩展函数 或 @JvmStatic
    4. 通配符被替换成了使用处型变（use-site variance）和类型投影（type projections）
    5. 三元操作符 a?b:c替换成了if表达式

    Kotlin有而Java没有的特性：
    1. Lambda 表达式 + 内联函数 = 高性能的自定义控制结构
    2. 扩展功能
    3. Null安全
    4. 智能转换（smart casts）
    5. 字符串模板
    6. 属性
    7. 主构造函数
    8. 一级代理类（First-class delegation）
    9. 变量和属性类型的类型推断
    10. 单例模式
    11. 使用处型变和类型投影
    12. 范围表达式
    13. 运算符重载
    14. 伴生对象
    15. 数据类
    16. 只读和可变集合的单独接口
    17. 协程
 */
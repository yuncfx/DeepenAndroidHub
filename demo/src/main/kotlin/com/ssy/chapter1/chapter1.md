# kotlinc HelloKotlin.kt
# Kotlin的方法不需要放到类中，编译器会自动帮我们生成相应的类 xxxKt.class
# kotlin HelloKotlinKt
# javap HelloKotlinKt.class
# javap -c HelloKotlinKt.class
# kotlinc HelloKotlin.kt -include-runtime -d HelloKotlin.jar // 需要包含kotlin自带的库
# java -jar HelloKotlin.jar
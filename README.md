# Fast

[ ![Download](https://img.shields.io/badge/download-2.4.0-green) ](https://repo1.maven.org/maven2/io/github/qq877693928)
[![ASL 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/qq877693928/Fast/blob/main/LICENSE)

Fast是一款Android的Debug工具，支持Java和Kotlin语言, 通过注解标注待打点方法，程序运行时该方法被调用时，自动触发日志记录到Logcat
, 记录信息包括进入该方法名称、方法的类型和参数值以及方法执行的时长

⚠️
因为使用Gradle Transform，仅支持包含 `id 'com.android.library'` 和 `id 'com.android.application'` 的 Module，不支持 `id 'java-library'` 的 Module，使用 `FastLog` 时需将 `id 'java-library'` 转成 `id 'com.android.library'`


在开发过程中，有时我们不希望通过Cpu Profiler
工具查看方法的调用过程同时又想快速了解方法的执行过程、参数和时间，就需要对方法的打日志，打日志是一个繁琐的过程，需要记录方法开始时间，参数值，结束时计算方法执行时长，如果待打点的方法比较多时就特别繁琐。

Fast通过在方法上加上特定注解(@FastLog)，编译时自动插入代码，记录该方法被调用时的关键信息，省去开发者手写打点代码，从而提升了开发效率

## 效果
[示例代码](https://github.com/qq877693928/Fast/blob/main/demo/src/main/java/com/lizhenhua/fast/demo/MainActivity.kt)

```kotlin
  @FastLog
  private fun testHello(first: String, second: String): String {
    return "$first $second"
  }
```
* 日志效果
```java script
  D/FAST_LOG: ├[main]-->> MainActivity#testHello(String="hello", String="world")
  D/FAST_LOG: ├[main]--<< MainActivity#testHello() [1ms]
```

## 工具集成
1. 工程目录`build.gradle`添加repositories和classpath [build.gradle配置参考](https://github.com/qq877693928/Fast/blob/main/build.gradle)

工具jar已发布至MavenCentral，需添加`mavenCentral()`
```groovy
repositories {
   ...
   mavenCentral()
}
```

然后添加classpath
```groovy
buildscript {
  ...
  dependencies {
    classpath 'io.github.qq877693928:fast-plugin:2.4.0'
    classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:xxx"
  }
}
```

2. 需要使用的app的`build.gradle`添加apply plugin
```groovy
apply plugin: 'com.lizhenhua.fast.plugin'
```
或者
```groovy
plugins {
    ...
    id 'com.lizhenhua.fast.plugin'
}
```

添加混淆
```java
-keep class com.lizhenhua.fast.**{*;}
```

依赖支持库
```groovy
dependencies {
    ...
    implementation 'io.github.qq877693928:fast-runtime:2.4.0'
    implementation 'io.github.qq877693928:fast-annotation:2.4.0'
    ...
}
```

3. 在需要打点的方法上添加标注, [Demo参考](https://github.com/qq877693928/Fast/blob/main/demo)

## 配置（Since fast-plugin:1.0.1）

由于在编译过程中需要将所有加上@FastLog注解的方法都会注入代码，为了避免Release版本打包效率问题和Release版本安全性等问题，所有在Release版本上需要将注入代码的功能去掉

从fast-plugin:1.0.1起提供gradle自定义fast配置, enable值默认为true表示@FastLog注解的方法会注入代码，为false时表示不会注入代码
 
如下：
```shell script
fast {
    enable = true
}
```

build.gradle
脚本配置debug版本生效（忽略AndroidTest和UnitTest）：
```shell script
allprojects { subProject ->
    def taskName = project.gradle.startParameter.taskNames != null
        ? project.gradle.startParameter.taskNames.toString() : "[]"
    if (!taskName.contains("AndroidTest")
        && !taskName.contains("UnitTest")
        && taskName.contains("Debug")) {
        apply plugin: 'com.lizhenhua.fast.plugin'
        fast {
            enable = true
        }
    }
}
```

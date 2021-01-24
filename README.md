# Fast

[ ![Download](https://api.bintray.com/packages/lizhenhua2003/maven/fast-plugin/images/download.svg?version=1.2.0) ](https://bintray.com/lizhenhua2003/maven/fast-plugin/1.2.0/link)
[![ASL 2.0](https://img.shields.io/hexpm/l/plug.svg)](https://github.com/qq877693928/Fast/blob/main/LICENSE)

Fast是一款Android的Debug工具，支持Java和Kotlin语言, 通过注解标注待打点方法，程序运行时该方法被调用时，自动触发日志记录到Logcat
, 记录信息包括进入该方法名称、方法的类型和参数值以及方法执行的时长和return值

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
```shell script
  D/MainActivity: |-->> testHello(String="hello", String="world")
  D/MainActivity: |--<< testHello [1ms] = "hello world"
```

## 工具集成
1. 工程目录`build.gradle`添加classpath
插件已经上传到JCenter上，所以不需要添加额外的repositories地址，只需要添加classpath
```groovy
buildscript {
  ...
  dependencies {
    classpath 'com.lizhenhua.fast:fast-plugin:1.2.0'
  }
}
```

2. 需要使用的app的`build.gradle`添加apply plugin
```groovy
apply plugin 'com.lizhenhua.fast.plugin'
```
或者
```groovy
plugins {
    ...
    id 'com.lizhenhua.fast.plugin'
}
```

依赖支持库
```groovy
dependencies {
    ...
    implementation 'com.lizhenhua.fast:fast-runtime:1.2.0'
    implementation 'com.lizhenhua.fast:fast-annotation:1.2.0'
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



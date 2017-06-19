# JacocoTestHelper

### 本工具为Jacoco手工测试代码覆盖率的辅助工具库，帮你两步轻松接入Jacoco。

## 使用情景
由于现在单元测试在小公司无法推行，且为了解决新功能测试以及回归测试在手工测试的情况下，即便用例再为详尽，也会存在遗漏的用例。通过统计手工测试覆盖率的数据，可以及时的完善用例。
Jacoco是Java Code Coverage的缩写，在统计完成Android代码覆盖率的时候使用的是Jacoco的离线插桩方式，在测试前先对文件进行插桩，在手工测试过程中会生成动态覆盖信息，最后统一对覆盖率进行处理，并生成报告。

<img src="https://github.com/Jay-Goo/JacocoTestHelper/blob/master/screenshot/device-2017-06-19-114234.png" width = "480" height = "850" alt="图片名称" align=center />

----------


## 用法:

### Dependencies：

```
#project build.gradle

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
#module build.gradle
	dependencies {
	       compile 'com.github.Jay-Goo:JacocoTestHelper:v0.0.2'
	}
```

### 第一步：
将Github项目中的	[jacoco.gradle](https://github.com/Jay-Goo/JacocoTestHelper/blob/master/app/jacoco.gradle)拷贝到项目app目录下，然后配置module 下的build.gradle

```
apply from: 'jacoco.gradle'
...do something

android {
...
 buildTypes {
        release {
           ...
        }
        debug {
            /**打开覆盖率统计开关*/
            testCoverageEnabled = true
        }
    }
}

```
### 第二步：

```
//PROJECT_PATH '项目路径' + '/app/build/outputs/code-coverage/'
//初始化，可以在程序入口处初始化
JacocoHelper.init(PROJECT_PATH,true);

//生成代码覆盖率ec文件
//可以手动触发，也可以在程序退出时触发
JacocoHelper.generateEcFile(true);


```


----------


## 详细Report生成:
通过上面的步骤，我们完成了jacoco的配置，很简单吧，下面教大家如何使用来使用它来测试我们的代码覆盖率

 * 1、可以直接编译运行或者安装应用
 * 2、./gradlew jacocoInit 初始化
 * 3、开始测试，测试结束后点击生成报告将ec文件pull到本地项目 PROJECT_PATH 路径中，具体命令可查看日志Logv<JayGoo>
 * PROJECT_PATH '项目路径' + '/app/build/outputs/code-coverage/'
 * 4、./gradlew jacocoTestReport 然后到 /app/build/reports里查看相关报告 
 
 


----------

### 报告分析：

![这里写图片描述](https://github.com/Jay-Goo/JacocoTestHelper/blob/master/screenshot/WechatIMG780.jpeg)

点击包名你可以看到更详细的方法，甚至代码

![这里写图片描述](https://github.com/Jay-Goo/JacocoTestHelper/blob/master/screenshot/WX20170619-114206@2x.png)


##移除:
上线时我们肯定要移除这些代码，其实移除也仅需两步即可轻松移除：

 * 1、注释掉 apply from: 'jacoco.gradle'，关闭覆盖率统计开关` testCoverageEnabled = false`
 
 * 2、将debug模式关闭，JacocoHelper将不会再生成任何文件和日志。`JacocoHelper.init(PROJECT_PATH,false);`

## 感谢

Q博士的博文：
http://blog.csdn.net/itfootball/article/details/45618609

Jacoco官方网站：
http://www.eclemma.org/jacoco/


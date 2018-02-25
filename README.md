# refactor-android-support-v4
重构Android的v4包的部分源码

#### 想了解详细的源码讲解，可以查看我的博客 [老司机带你重构Android的v4包的部分源码](https://www.jianshu.com/p/a08d754944c4)

# 大概重构思路：
根据我做过项目用到的MVP的开发模式，我把共同的htmlEncode方法和getLayoutDirectionFromLocale方法抽取出一个接口，然后分别用两个实现类去实现接口，然后用TextUtilsCompat这个类去判断调用哪个实现类的方法，这样看起来更直观一些。

# 使用示意图如下：

![](https://github.com/AweiLoveAndroid/refactor-android-support-v4/blob/master/pic/demo.png?raw=true)

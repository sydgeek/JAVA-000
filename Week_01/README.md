学习笔记

类加载器
内存参数关系在resources目录中

JVM默认使用 AdaptiveSizePolicy 策略自动分配Eden和Survivor空间大小。
所以下参数默认无效：-XX:SurvivorRatio=8
使用以下参数，关闭AdaptiveSizePolicy，即可使Ratio生效：-XX:-UseAdaptiveSizePolicy

# 重启LeetCode

- [labuladong 的算法小抄](https://labuladong.gitee.io/algo/)


# 插件配置
TempFilePath: `D:\WorkSpace\Personal-Project\java-study\leetcode\src\main\java\io\github\gcdd1993`
名称：`$!velocityTool.camelCaseName(${question.titleSlug})`

```java
package io.github.gcdd1993.leetcode.editor.cn;

public class $!velocityTool.camelCaseName(${question.titleSlug}){
    public static void main(String[] args) {
       Solution solution = new $!velocityTool.camelCaseName(${question.titleSlug})().new Solution();
    }
${question.code}
}
```
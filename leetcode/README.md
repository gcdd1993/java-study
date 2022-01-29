# 重启LeetCode

- [labuladong 的算法小抄](https://labuladong.gitee.io/algo/)


# 插件配置
TempFilePath: `D:\WorkSpace\Personal-Project\java-study\leetcode\src\main\java\io\github\gcdd1993`
名称：`Num${question.frontendQuestionId}$!velocityTool.camelCaseName(${question.titleSlug})`

```java
package io.github.gcdd1993.leetcode.editor.cn;

public class Num${question.frontendQuestionId}$!velocityTool.camelCaseName(${question.titleSlug}){
    public static void main(String[] args) {
       Solution solution = new Num${question.frontendQuestionId}$!velocityTool.camelCaseName(${question.titleSlug})().new Solution();
    }
${question.code}
}
```

# 算法框架

## 数组遍历

```java
/* 迭代遍历数组 */
void traverse(int[] arr) {
    for (int i = 0; i < arr.length; i++) {

    }
}

/* 递归遍历数组 */
void traverse(int[] arr, int i) {
    if (i == arr.length) {
        return;
    }
    // 前序位置
    traverse(arr, i + 1);
    // 后序位置
}
```

## 单链表遍历

```java
/* 迭代遍历单链表 */
void traverse(ListNode head) {
    for (ListNode p = head; p != null; p = p.next) {

    }
}

/* 递归遍历单链表 */
void traverse(ListNode head) {
    if (head == null) {
        return;
    }
    // 前序位置
    traverse(head.next);
    // 后序位置
}
```

## 二叉树遍历

```java
void traverse(TreeNode root) {
    if (root == null) {
        return;
    }
    // 前序位置
    traverse(root.left);
    // 中序位置
    traverse(root.right);
    // 后序位置
}
```


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

## 滑动窗口算法

```java
public String minWindow(String s, String t) {
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();

    int left = 0, right = 0;
    int valid = 0;

    char[] chars = s.toCharArray();
    for (char c : chars) {
        need.put(c, need.getOrDefault(c, 0) + 1);
    }

    while (right < chars.length) {
        // c 是将y窗口的字符
        char c = chars[right];
        // 右移窗口
        right++;

        // todo 进行窗口内数据的一系列更新

        System.out.printf("window: [%d, %d)\n", left, right);

        while (1 == 1) { // todo 判断左侧窗口是否要收缩
            // d 是将移出窗口的字符
            char d = chars[left];
            // 左移窗口
            left++;
            // todo 进行窗口内数据的一系列更新
        }
    }

}
```

## 二分查找

```java
int  binarySearch(int[] nums,  int target)  {
    int left = 0, right = ...;

    while(...) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            left = ...
        } else if (nums[mid] > target) {
            right = ...
        }
    }
    return ...;
}
```

## 运用二分搜索的套路框架

```java
// 函数 f 是关于自变量 x 的单调函数
int f(int x) {
    // ...
}

// 主函数，在 f(x) == target 的约束下求 x 的最值
int solution(int[] nums, int target) {
    if (nums.length == 0) return -1;
    // 自变量 x 的最小值是多少？
    int left = ...;
    // 自变量 x 的最大值是多少？
    int right = ... + 1;

    while (left < right) {
        int mid = left + (right - left) / 2;
        int res = f(mid);
        if (res == target) {
            // 题目是求左边界还是右边界？
            // ...
        } else if (res < target) {
            // 怎么让 f(x) 大一点？
            // ...
        } else if (res > target) {
            // 怎么让 f(x) 小一点？
            // ...
        }
    }
    return left;
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

## BST遍历

```java
void  BST(TreeNode root,  int target)  {
    if (root == null) {
        return;
    }
    if (root.val == target) {
        // 找到目标，做点什么 
    }
    if (root.val < target) {
        BST(root.right, target);
    }
    if (root.val > target) {
        BST(root.left, target);
    }
}
```

## 快速排序

```java
void sort(int[] nums, int lo, int hi) {
    /****** 前序遍历位置 ******/
    // 通过交换元素构建分界点 p
    int p = partition(nums, lo, hi);
    /************************/

    sort(nums, lo, p - 1);
    sort(nums, p + 1, hi);
}
```

## 归并排序

```java
void sort(int[] nums, int lo, int hi) {
    int mid = (lo + hi) / 2;
    sort(nums, lo, mid);
    sort(nums, mid + 1, hi);

    /****** 后序遍历位置 ******/
    // 合并两个排好序的子数组
    merge(nums, lo, mid, hi);
    /************************/
}
```


# Mybatis常见面试题

## Mybatis整体架构？

![img](https://cdn.jsdelivr.net/gh/gcdd1993/image-repo@master/img/202112011949003.png)

## #、$区别

- `#{}` 只是替换？，相当于`PreparedStatement`使用占位符去替换参数，可以防止sql注入。
- `${}` 是进行**字符串拼接**，相当于sql语句中的`Statement`，使用字符串去拼接sql；$可以是sql中的任一部分传入到Statement中，不能防止sql注入。

## Mybatis插件原理

MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：

- Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
- ParameterHandler (getParameterObject, setParameters)
- ResultSetHandler (handleResultSets, handleOutputParameters)
- StatementHandler (prepare, parameterize, batch, update, query)

# 参考资料

- [Mybatis教程](https://www.cnblogs.com/diffx/p/10611082.html)
package io.github.gcdd1993.concurrency.chapter26;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CompletableFuture使用示例
 *
 * @author gcdd1993
 * @since 2021/12/15
 */
@DisplayName("CompletableFuture使用示例")
public class CompletableFutureTest {

    @Test
    public void start() {
//        CompletableFuture.supplyAsync(CompletableFutureDemo::活捉鲁班)
//                .thenAccept(player -> note(player.getName())) // 接收supplyAsync的结果，获得对方名字
//                .thenRun(() -> attack("2技能-偶像魅力：鲁班受到妲己285点法术伤害，并眩晕1.5秒..."))
//                .thenRun(() -> attack("3技能-女王崇拜：妲己放出5团狐火，鲁班受到325点法术伤害..."))
//                .thenRun(() -> attack("1技能-灵魂冲击：鲁班受到妲己520点法术伤害..."))
//                .thenRunAsync(() -> note("鲁班，卒...")); // 使用线程池的其他线程
    }

    /**
     * 接收一个待运行的任务并返回一个CompletableFuture
     */
    @Test
    @DisplayName("runAsync")
    public void runAsync() {
        CompletableFuture.runAsync(() -> note("妲己进入草丛蹲点...等待小鲁班出现"));
    }

    /**
     * 会返回一个结果，并且这个结果可以被后续的任务所使用
     */
    @Test
    @DisplayName("supply与supplyAsync")
    public void supplyAsync() throws ExecutionException, InterruptedException {
        // 创建nameFuture，返回姓名
        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> "妲己");

        // 使用thenApply()接收nameFuture的结果，并执行回调动作
        CompletableFuture<String> sayLoveFuture = nameFuture.thenApply(name -> "爱你，" + name);

        //阻塞获得表白的结果
        System.out.println(sayLoveFuture.get()); // 爱你，妲己
    }

    /**
     * 接收`supply()`的执行结果，并执行特定的代码逻辑，最后返回CompletableFuture结果
     */
    @Test
    @DisplayName("thenApply与thenApplyAsync")
    public void thenApplyAsync() throws ExecutionException, InterruptedException {
        // 创建nameFuture，返回姓名
        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> "妲己");
        // 使用thenApply()接收nameFuture的结果，并执行回调动作
        CompletableFuture<String> sayLoveFuture = nameFuture.thenApply(name -> {
            return "爱你，" + name;
        });
        //阻塞获得表白的结果
        System.out.println(sayLoveFuture.get()); // 爱你，妲己
    }

    /**
     * 接收`supply()`的执行结果，并执行特定的代码逻辑，最后返回Void
     */
    @Test
    @DisplayName("thenAccept与thenAcceptAsync")
    public void thenAccept() throws ExecutionException, InterruptedException {
        // 创建nameFuture，返回姓名
        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> "妲己");
        CompletableFuture<Void> sayLoveFuture = nameFuture.thenAccept(name -> {
            System.out.println("爱你，" + name);
        });
        //阻塞获得表白的结果
        System.out.println(sayLoveFuture.get()); // 爱你，妲己
    }

    /**
     * 不接收任务的结果，只运行特定的任务，并且也不返回结果
     */
    @Test
    @DisplayName("thenRun")
    public void thenRun() throws ExecutionException, InterruptedException {
        // 创建nameFuture，返回姓名
        CompletableFuture<String> nameFuture = CompletableFuture.supplyAsync(() -> "妲己");

        CompletableFuture<Void> sayLoveFuture = nameFuture.thenRun(() -> System.out.println("不返回结果，也不接受妲己"));
        sayLoveFuture.get();
    }

    /**
     * 编排两个存在依赖关系的任务
     * <p>
     * 当我们计算某个英雄（比如妲己）的胜率时，我们需要获取她参与的**总场次（rounds**），以及她**获胜的场次（winRounds）**，
     * 然后再通过`winRounds / rounds`来计算
     */
    @Test
    @DisplayName("thenCompose与 thenCombine")
    public void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> roundsFuture = CompletableFuture.supplyAsync(() -> 500);
        CompletableFuture<Integer> winRoundsFuture = CompletableFuture.supplyAsync(() -> 365);

        CompletableFuture<Object> winRateFuture = roundsFuture
                .thenCombine(winRoundsFuture, (rounds, winRounds) -> {
                    if (rounds == 0) {
                        return 0.0;
                    }
                    DecimalFormat df = new DecimalFormat("0.00");
                    return df.format((float) winRounds / rounds);
                });
        System.out.println(winRateFuture.get());
    }

    /**
     * - `allOf()`：给定一组任务，等待**所有任务**执行结束；
     * - `anyOf()`：给定一组任务，等待**其中任一**任务执行结束
     */
    @Test
    @DisplayName("allOf与anyOf")
    public void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> roundsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
                return 500;
            } catch (InterruptedException e) {
                return null;
            }
        });
        CompletableFuture<Integer> winRoundsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
                return 365;
            } catch (InterruptedException e) {
                return null;
            }
        });

        CompletableFuture<Object> completedFuture = CompletableFuture.anyOf(winRoundsFuture, roundsFuture);
        System.out.println(completedFuture.get()); // 返回365

        CompletableFuture<Void> completedFutures = CompletableFuture.allOf(winRoundsFuture, roundsFuture);
        completedFutures.get();
    }

    @Test
    @DisplayName("异常处理 exceptionally")
    public void exceptionally() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> roundsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
                return 0;
            } catch (InterruptedException e) {
                return null;
            }
        });
        CompletableFuture<Integer> winRoundsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
                return 365;
            } catch (InterruptedException e) {
                return null;
            }
        });
        CompletableFuture<? extends Serializable> winRateFuture = roundsFuture
                .thenCombine(winRoundsFuture, (rounds, winRounds) -> {
                    if (rounds == 0) {
                        throw new RuntimeException("总场次错误");
                    }
                    DecimalFormat df = new DecimalFormat("0.00");
                    return df.format((float) winRounds / rounds);
                }).exceptionally(ex -> {
                    System.out.println("出错：" + ex.getMessage());
                    return "";
                });
        System.out.println(winRateFuture.get());
    }

    @Test
    @DisplayName("异常处理 handle")
    public void handle() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> roundsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
                return 1;
            } catch (InterruptedException e) {
                return null;
            }
        });
        CompletableFuture<Integer> winRoundsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
                return 365;
            } catch (InterruptedException e) {
                return null;
            }
        });
        CompletableFuture<? extends Serializable> winRateFuture = roundsFuture
                .thenCombine(winRoundsFuture, (rounds, winRounds) -> {
                    if (rounds == 0) {
                        throw new RuntimeException("总场次错误");
                    }
                    DecimalFormat df = new DecimalFormat("0.00");
                    return df.format((float) winRounds / rounds);
                }).handle((res, ex) -> {
                    if (ex != null) {
                        System.out.println("出错：" + ex.getMessage());
                        return "";
                    } else {
                        System.out.println("没发生异常啊");
                    }
                    return res;
                });
        System.out.println(winRateFuture.get());
    }

    @Test
    @DisplayName("自定义线程池")
    public void customExecutors() {
        // 自定义线程池示例
        Executor executor = Executors.newFixedThreadPool(10);

        CompletableFuture<Integer> roundsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(200);
                return 500;
            } catch (InterruptedException e) {
                return null;
            }
        }, executor);
    }

    private void note(String msg) {
        System.out.println(msg);
    }
}

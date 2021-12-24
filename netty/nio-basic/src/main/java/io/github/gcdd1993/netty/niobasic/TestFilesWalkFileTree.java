package io.github.gcdd1993.netty.niobasic;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 遍历文件树
 *
 * @author gcdd1993
 * @since 2021/12/23
 */
public class TestFilesWalkFileTree {

    /**
     * 删除多级目录
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("D:\\DevTools\\Java\\AdoptOpenJDK\\jdk-11.0.11.9-hotspot - 副本"), new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    private static void m2() throws IOException {
        AtomicInteger jarCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\DevTools\\Java\\AdoptOpenJDK\\jdk-11.0.11.9-hotspot"), new SimpleFileVisitor<>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".jar")) {
                    System.out.println(file);
                    jarCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("jar count: " + jarCount);
    }

    private static void m1() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Path path = Files.walkFileTree(Paths.get("D:\\DevTools\\Java\\AdoptOpenJDK\\jdk-11.0.11.9-hotspot"), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("=====> " + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });

        System.out.println("dir count: " + dirCount);
        System.out.println("file count: " + fileCount);
    }
}

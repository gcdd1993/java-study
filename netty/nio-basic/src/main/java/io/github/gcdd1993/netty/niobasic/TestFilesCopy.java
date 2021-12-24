package io.github.gcdd1993.netty.niobasic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 拷贝多级目录
 *
 * @author gcdd1993
 * @since 2021/12/23
 */
public class TestFilesCopy {

    public static void main(String[] args) throws IOException {
        String source = "D:\\DevTools\\Java\\AdoptOpenJDK\\jdk-11.0.11.9-hotspot";
        String target = "D:\\DevTools\\Java\\AdoptOpenJDK\\jdk-11.0.11.9-hotspot-ff";
        Files.walk(Paths.get(source)).forEach(path -> {
            String targetName = path.toString().replace(source, target);
            try {
                if (Files.isDirectory(path)) {
                    // 创建目录
                    Files.createDirectory(Paths.get(targetName));
                } else if (Files.isRegularFile(path)) {
                    // 执行拷贝
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

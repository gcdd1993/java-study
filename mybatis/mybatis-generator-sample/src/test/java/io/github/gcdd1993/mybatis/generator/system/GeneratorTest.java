package io.github.gcdd1993.mybatis.generator.system;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * @author gcdd1993
 * @date 2021/12/7
 * @since 1.0.0
 */
class GeneratorTest {

    /**
     * 1、enableKotlin生成的Model，没有使用data class
     * 2、生成的代码有IService ServiceImpl这种形式代码，但实际上项目钟很少会给Service层配置接口，而且没找到设置可以把ServiceImpl关掉
     */
    @Test
    public void generator01() {
        String url = "jdbc:mysql://localhost:3306/ry?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "123456";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("gcdd1993") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
//                            .enableKotlin()
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\WorkSpace\\Personal-Project\\java-study\\mybatis\\mybatis-generator-sample\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("io.github.gcdd1993.mybatis.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\WorkSpace\\Personal-Project\\java-study\\mybatis\\mybatis-generator-sample\\src\\main\\resources\\mappers")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .addTablePrefix("sys_") // 设置过滤表前缀
                            .controllerBuilder().enableRestStyle()
                            .entityBuilder().enableLombok()
//                            .addInclude("t_simple") // 设置需要生成的表名
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

package com.riotian.mplearn;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Collections;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class FastAutoGeneratorTest {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true", "root", "kokoro")
            .globalConfig(builder -> {
                builder.author("riotian") // 设置作者
                    .enableSwagger() // 开启 swagger 模式
                    .fileOverride() // 覆盖已生成文件
                    .outputDir("D:\\Coding_Fin_Semester\\demo"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.riotian") // 设置父包名
                    .moduleName("mybatisplus") // 设置父包模块名
                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Coding_Fin_Semester\\demo")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("t_user") // 设置需要生成的表名
                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀
            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }

}

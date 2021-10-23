package com.megvii.srg.cst.infrastructure.utils.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class CodeGeneratorMySql {

    /**
     * 数据库配置
     **/
    private static final String mysqlHost = "8.131.110.70";
    private static final String database = "cst_base_ddd";
    private static final String username = "root";
    private static final String password = "1234567890///";

    /**
     * 项目配置
     **/
    private static final String applicationName = "Zhangliao-Mybatis-Plus-Crud";
    private static final String basePackage = "sanguo.zhangliao.mybatis.plus";
    private static final String module = "domain"; // 模块名称
    private static final String[] tableName = {"t_station"};


    /**
     * 生成配置
     */
    private static final boolean genController = false;
    private static final boolean genService = true;
    private static final boolean genDao = true;
    private static final boolean genEntity = true;
    private static final boolean fileOverride = true;

    private static final String controllerPackage = "db.controller";
    private static final String servicePackage = "db.service";
    private static final String serviceImplPackage = servicePackage + ".impl";
    private static final String daoPackage = "db.mapper";
    private static final String entityPackage = "db.entity";

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/" + applicationName + "/src/main/java");
        String author = System.getProperty("user.name", "Administrator");
        gc.setAuthor(author);
        gc.setOpen(false);
        // 覆盖已有文件
        gc.setFileOverride(fileOverride);
        gc.setIdType(IdType.AUTO);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://" + mysqlHost + ":3306/" + database + "?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(module);
        pc.setParent(basePackage);
        pc.setController(controllerPackage);
        pc.setService(servicePackage);
        pc.setServiceImpl(serviceImplPackage);
        pc.setMapper(daoPackage);
        pc.setEntity(entityPackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/" + applicationName + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        TemplateConfig tc = new TemplateConfig();
        if (!genController) {
            tc.setController(null);
        }
        if (!genService) {
            tc.setService(null).setServiceImpl(null);
        }
        if (!genDao) {
            tc.setMapper(null);
        }
        if (!genEntity) {
            tc.setEntity(null);
        }
        mpg.setTemplate(tc.setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


}

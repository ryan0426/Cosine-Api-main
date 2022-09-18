package cn.globalyouth.cosineapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author liuyufeng
 */

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan(basePackages = "cn.globalyouth.cosineapi.dao.mapper")
public class CosineApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CosineApiApplication.class, args);
    }
}

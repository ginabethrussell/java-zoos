package com.lambdaschool.javazoos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JavazoosApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(JavazoosApplication.class,
            args);
    }

}

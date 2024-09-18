package com.labwithcommunity;

import org.springframework.boot.SpringApplication;

public class TestLabwithcommunityApplication {

    public static void main(String[] args) {
        SpringApplication.from(LabwithcommunityApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

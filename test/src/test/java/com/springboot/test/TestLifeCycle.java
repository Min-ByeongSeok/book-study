package com.springboot.test;

import org.junit.jupiter.api.*;

public class TestLifeCycle {
    @BeforeAll
    static void beforeAll(){
        System.out.println("##beforeAll annotation 호출");
        System.out.println();
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("##beforeEach annotation 호출");
        System.out.println();
    }

    @AfterAll
    static void afterAll(){
        System.out.println("##afterAll annotation 호출");
        System.out.println();
    }

    @AfterEach
    void afterEach(){
        System.out.println("##afterEach annotation 호출");
        System.out.println();
    }

    @Test
    void test1(){
        System.out.println("test1");
        System.out.println();
    }

    @Test
    @DisplayName("test case 2 !!")
    void test2(){
        System.out.println("test2");
        System.out.println();
    }

    @Test
    @Disabled
    void test3(){
        System.out.println("test3");
        System.out.println();
    }
}

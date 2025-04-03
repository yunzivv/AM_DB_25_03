package org.example;

import org.example.exception.SQLErrorException;

public class Main {
    public static void main(String[] args) {

        try {
            new App().run();
        } catch (SQLErrorException e) {
            // Systen.out을 System.err로 바꿔 쓰면 에러메세지처럼 빨갛게 보임
            System.err.println(e.getOrigin());
            e.getOrigin().printStackTrace();
        }
    }
}

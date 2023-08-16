package com.example.equalsandhashcode;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Demo {
    public static void main(String[] args) {
        List<CaseInsensitiveString> list = new ArrayList<>();
        var person = new Person("Petro", 12);

        var cis = new CaseInsensitiveString("hello");
        var s = "hello";
        var cis2 = new CaseInsensitiveString("heLlo");

        System.out.println(cis.equals(cis2));

    }

    @ToString
    private static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            return Objects.equals(this, obj);
        }
    }
}

final class CaseInsensitiveString {
    private final String s;

    public CaseInsensitiveString(String s) {
        this.s = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CaseInsensitiveString cis && cis.s.equalsIgnoreCase(s);
    }
}

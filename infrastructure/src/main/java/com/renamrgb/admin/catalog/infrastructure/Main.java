package com.renamrgb.admin.catalog.infrastructure;

import com.renamrgb.admin.catalog.application.UseCase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println(new UseCase().execute());
    }
}
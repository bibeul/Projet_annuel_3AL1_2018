package com.plugin;

import hackmelibrary.util.plguin.IPlugin;

public class HelloSample implements IPlugin {
    @Override
    public void printHello() {
        System.out.println("HELLO I'm a Plugin !!!!");
    }
}

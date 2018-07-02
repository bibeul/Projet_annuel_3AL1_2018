package com.plugin;


import util.plugin.IPlugin;

public class HelloSample implements IPlugin {
    @Override
    public void printHello() {
        System.out.println("HELLO I'm a Plugin !!!!");
    }
}

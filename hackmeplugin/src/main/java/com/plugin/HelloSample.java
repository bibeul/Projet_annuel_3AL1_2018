package com.plugin;


import Interface.util.plugin.IPlugin;

public class HelloSample implements IPlugin {
    @Override
    public void printHello() {
        System.out.println("HELLO I'm a Plugin !!!!");
    }
}

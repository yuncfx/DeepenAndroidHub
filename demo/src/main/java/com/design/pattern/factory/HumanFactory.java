package com.design.pattern.factory;

public class HumanFactory {
    public <T extends Human> T createHuman(Class<T> c) {
        Human human = null;

        try {
            human = (T)Class.forName(c.getName()).newInstance();
        } catch (Exception e) {

        }

        return (T) human;
    }
}

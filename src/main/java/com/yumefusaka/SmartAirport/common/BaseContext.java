package com.yumefusaka.SmartAirport.common;

public class BaseContext {

    public static ThreadLocal<BaseInfo> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(BaseInfo baseInfo) {
        threadLocal.set(baseInfo);
    }

    public static BaseInfo getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
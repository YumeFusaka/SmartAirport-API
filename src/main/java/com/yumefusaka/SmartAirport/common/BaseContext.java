package com.yumefusaka.SmartAirport.common;

public class BaseContext {

    public static ThreadLocal<BaseInfo> threadLocal = new ThreadLocal<>();

    public static void setCurrentInfo(BaseInfo baseInfo) {
        threadLocal.set(baseInfo);
    }

    public static BaseInfo getCurrentInfo() {
        return threadLocal.get();
    }

    public static void removeCurrentInfo() {
        threadLocal.remove();
    }
}
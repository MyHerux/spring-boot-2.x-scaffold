package com.xu.scaffold.common.bean;

/**
 * 异常等级
 *
 * @author xu
 */
public enum Level {
    /**
     * high level,需要通知到人紧急处理
     */
    high(3),
    /**
     * normal level,采集并统一分析
     */
    normal(2),
    /**
     * ignore level,只需要打印即可
     */
    ignore(1);

    private int level;

    Level(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

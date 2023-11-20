package org.cmms.modules.quartz.constant;

public enum  QuartzEnum {

    QuartExecutionSuccess("1","执行成功"),
    QuartExecutionFail("2","执行失败"),
    QuartExecution("0","执行中");
    private String key;
    private String value;

    QuartzEnum(String key,String value )
    {
        this.key=key;
        this.value=value;

    }
    // 普通方法
    public static String getValue(String key) {
        for (QuartzEnum c : QuartzEnum.values()) {
            if (c.getKey().equals(key)) {
                return c.value;
            }
        }
        return null;
    }
    // get

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

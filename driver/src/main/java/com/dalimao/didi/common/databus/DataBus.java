package com.dalimao.didi.common.databus;

/**
 * 数据事件总线抽象
 * Created by liuguangli on 17/3/9.
 */

public interface DataBus {
    void register(Object subscriber);
    void unRegister(Object subscriber);
    void send(Object data);


}

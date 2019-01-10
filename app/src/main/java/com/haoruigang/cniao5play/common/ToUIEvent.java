package com.haoruigang.cniao5play.common;

public class ToUIEvent {

    private int tag;
    private Object obj;
    private Object obj1;
    private Object obj2;
    private Object obj3;

    public ToUIEvent(int tag) {
        this.tag = tag;
    }

    public ToUIEvent(int tag, Object obj) {
        this.tag = tag;
        this.obj = obj;
    }

    public ToUIEvent(int tag, Object obj, Object obj1) {
        this.tag = tag;
        this.obj = obj;
        this.obj1 = obj1;
    }

    public ToUIEvent(int tag, Object obj, Object obj1, Object obj2) {
        this.tag = tag;
        this.obj = obj;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public ToUIEvent(int tag, Object obj, Object obj1, Object obj2, Object obj3) {
        this.tag = tag;
        this.obj = obj;
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.obj3 = obj3;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Object getObj1() {
        return obj1;
    }

    public void setObj1(Object obj1) {
        this.obj1 = obj1;
    }

    public Object getObj2() {
        return obj2;
    }

    public void setObj2(Object obj2) {
        this.obj2 = obj2;
    }

    public Object getObj3() {
        return obj3;
    }

    public void setObj3(Object obj3) {
        this.obj3 = obj3;
    }

}

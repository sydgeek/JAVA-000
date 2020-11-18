package com.geek.week_05.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean2 {
    Bean3 bean3;

    @Autowired
    public void setBean3(Bean3 bean3) {
        this.bean3 = bean3;
    }
}

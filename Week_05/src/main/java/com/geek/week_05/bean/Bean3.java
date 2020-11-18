package com.geek.week_05.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean3 {
    Bean1 bean1;

    public Bean3(@Autowired Bean1 bean1) {
        this.bean1 = bean1;
    }
}

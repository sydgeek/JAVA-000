package com.geek.week_05.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bean1 {
    @Autowired
    Bean2 bean2;
}

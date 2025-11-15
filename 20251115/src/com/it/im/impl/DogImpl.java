package com.it.im.impl;

import com.it.im.animal; /**
 * @author: dwt
 * @Date:2025/11/15 14:59
 * @Description:
 * @Version: 1.0
 */
public class DogImpl implements animal{
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }
}

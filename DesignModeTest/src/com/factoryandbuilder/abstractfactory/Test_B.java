package com.factoryandbuilder.abstractfactory;

import com.factoryandbuilder.abstractfactory.products.drinks.Cola;
import com.factoryandbuilder.abstractfactory.products.snacks.ChocolateShack;

/**
 * author: King.Z
 * date:  2016/1/6 14:46
 * description:顾客B点了：可乐 + 巧克力奶昔
 */
public class Test_B {
     public static void main(String[] args) {
        Order order = new Order.OrderBuilder()
                .addDrinks(new Cola())
                .addSnack(new ChocolateShack())
                .build();
        System.out.println("Test_B ：" + order.makeOrder());
    }
}

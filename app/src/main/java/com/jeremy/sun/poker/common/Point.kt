package com.jeremy.sun.poker.common

import kotlin.random.Random

/**
 * Created by sun on 2020-02-29
 * 扑克牌点数
 */
enum class Point(val index: Int, val pName: String) {
    P2(0, "2"),
    P3(1, "3"),
    P4(2, "4"),
    P5(3, "5"),
    P6(4, "6"),
    P7(5, "7"),
    P8(6, "8"),
    P9(7, "9"),
    P10(8, "10"),
    J(9, "J"),
    Q(10, "Q"),
    K(11, "K"),
    A(12, "A");

    companion object {
        /**
         * 获取一个随机点数
         */
        fun getRandomPoint(): Point {
            return getPointByIndex(Random.nextInt(13))
        }

        /**
         * 根据index获取对应点数
         */
        fun getPointByIndex(index: Int): Point {
            return when (index) {
                0 -> P2
                1 -> P3
                2 -> P4
                3 -> P5
                4 -> P6
                5 -> P7
                6 -> P8
                7 -> P9
                8 -> P10
                9 -> J
                10 -> Q
                11 -> K
                else -> A
            }
        }
    }
}
package com.jeremy.sun.poker.common

/**
 * Created by sun on 2020-02-29
 * 牌型
 */
enum class Pattern(val index: Int) {
    HIGH_CARD(0), // 高牌
    ONE_PAIR(1), // 一对
    TWO_PAIRS(2), // 两对
    THREE_OF_A_KIND(3), // 三条
    STRAIGHT(4), // 顺子
    FLUSH(5), // 同花
    FULL_HOUSE(6), // 葫芦
    FOUR_OF_A_KIND(7), // 四条
    STRAIGHT_FLUSH(8), // 同花顺
    ROYAL_FLUSH(9); // 皇家同花顺（AKQJ10）

    companion object {
        /**
         * 根据index获取对应点数
         */
        fun getPatternByIndex(index: Int): Pattern {
            return when (index) {
                0 -> HIGH_CARD
                1 -> ONE_PAIR
                2 -> TWO_PAIRS
                3 -> THREE_OF_A_KIND
                4 -> STRAIGHT
                5 -> FLUSH
                6 -> FULL_HOUSE
                7 -> FOUR_OF_A_KIND
                8 -> STRAIGHT_FLUSH
                else -> ROYAL_FLUSH
            }
        }
    }
}
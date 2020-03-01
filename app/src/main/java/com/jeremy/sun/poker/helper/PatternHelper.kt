package com.jeremy.sun.poker.helper

import com.jeremy.sun.poker.bean.FiveCards
import com.jeremy.sun.poker.common.Pattern
import com.jeremy.sun.poker.common.Point

/**
 * Created by sun on 2020-02-29
 * 牌型助手
 */
object PatternHelper {

    /**
     * 获取一手牌的牌型
     */
    fun getSuitPattern(fiveCards: FiveCards): Pattern {
        val point = fiveCards.cards.first().point // 首张牌的点数
        val suit = fiveCards.cards.first().suit // 首张牌的花色

        val array = IntArray(Point.values().size) // 新建一个大小为13的Int类型空数组
        var isStraight = true
        var isFlush = true

        for ((index, card) in fiveCards.cards.withIndex()) {
            // 如果点数不是连续递增
            if (card.point.index != (point.index + index)) {
                isStraight = false
            }

            // 如果花色不一致
            if (card.suit != suit) {
                isFlush = false
            }

            // index对应位置++
            array[card.point.index]++
        }

        if (isStraight && isFlush) {
            return if (point == Point.P10) {
                Pattern.ROYAL_FLUSH
            } else {
                Pattern.STRAIGHT_FLUSH
            }
        } else if (isStraight) {
            return Pattern.STRAIGHT
        } else if (isFlush) {
            return Pattern.FLUSH
        } else if (array.filter { it == 4 }.size == 1) {
            return Pattern.FOUR_OF_A_KIND
        } else if (array.filter { it == 3 }.size == 1) {
            return if (array.filter { it == 2 }.size == 1) {
                Pattern.FULL_HOUSE
            } else {
                Pattern.THREE_OF_A_KIND
            }
        } else if (array.filter { it == 2 }.size == 2) {
            return Pattern.TWO_PAIRS
        } else if (array.filter { it == 2 }.size == 1) {
            return Pattern.ONE_PAIR
        } else {
            return Pattern.HIGH_CARD
        }
    }


}
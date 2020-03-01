package com.jeremy.sun.poker.bean

import com.jeremy.sun.poker.common.Point
import com.jeremy.sun.poker.common.Suit

/**
 * Created by sun on 2020-02-29
 * 扑克牌
 */
data class Card(
    val suit: Suit,
    val point: Point
) : Comparable<Card> {

    companion object {
        /**
         * 生成一张随机扑克牌
         */
        fun getRandomCard(): Card {
            return Card(Suit.getRandomSuit(), Point.getRandomPoint())
        }
    }

    /**
     * 先比点数大小，再比花色大小
     */
    override fun compareTo(other: Card): Int {
        return if (isBigPoint(other) == 0) isBigSuit(other) else isBigPoint(other)
    }

    /**
     * 是否点数更大
     */
    private fun isBigPoint(other: Card): Int {
        return when {
            point.index > other.point.index -> 1
            point.index < other.point.index -> -1
            else -> 0
        }
    }

    /**
     * 是否花色更大
     */
    private fun isBigSuit(other: Card): Int {
        return when {
            suit.index > other.suit.index -> 1
            suit.index < other.suit.index -> -1
            else -> 0
        }
    }

    override fun toString(): String {
        return "${suit.sName}${point.pName}"
    }


}
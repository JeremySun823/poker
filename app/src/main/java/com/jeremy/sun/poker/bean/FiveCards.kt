package com.jeremy.sun.poker.bean

import com.jeremy.sun.poker.common.Pattern
import com.jeremy.sun.poker.helper.PatternHelper
import java.util.*

/**
 * Created by sun on 2020-02-29
 *
 * 默认从小到大排序，ex: [♦6, ♠9, ♦J, ♥Q, ♦K]
 */
data class FiveCards(val cards: TreeSet<Card>) : Comparable<FiveCards> {

    override fun compareTo(other: FiveCards): Int {
        // 如果不是一手牌，返回0
        if (!isLegal() || !other.isLegal()) {
            // todo, jeremy
            return 0
        }

        val pattern = PatternHelper.getSuitPattern(this)
        val otherPattern = PatternHelper.getSuitPattern(other)
        return when {
            pattern.index > otherPattern.index -> 1
            pattern.index < otherPattern.index -> -1
            else -> compareSame(pattern, other)
        }
    }


    /**
     * 是否是合规的一手牌
     */
    fun isLegal(): Boolean {
        return cards.size == 5
    }



    /**
     * 比较相同的牌型大小
     */
    private fun compareSame(pattern: Pattern, other: FiveCards): Int {
        return when (pattern) {
            Pattern.ROYAL_FLUSH -> compareRoyalFlush(other)
            Pattern.STRAIGHT_FLUSH -> compareStraightFlush(other)
            Pattern.FOUR_OF_A_KIND -> compareFourOfAKind(other)
            Pattern.FULL_HOUSE -> compareFullHouse(other)
            Pattern.FLUSH -> compareFlush(other)
            Pattern.STRAIGHT -> compareStraight(other)
            Pattern.THREE_OF_A_KIND -> 0
            Pattern.TWO_PAIRS -> 0
            Pattern.ONE_PAIR -> 0
            Pattern.HIGH_CARD -> 0
        }
    }

    /**
     * 两个ROYAL_FLUSH比大小
     * 只需比较花色即可
     * ex: ♠A♠K♠Q♠J♠10 vs ♥A♥K♥Q♥J♥10
     */
    private fun compareRoyalFlush(other: FiveCards): Int {
        val card = cards.first()
        val otherCard = other.cards.first()
        return card.compareTo(otherCard)
    }

    /**
     * 两个STRAIGHT_FLUSH比较大小
     * 只需比较首牌的大小即可
     * ex: ♠K♠Q♠J♠10♠9 vs ♠Q♠J♠10♠9♠8
     */
    private fun compareStraightFlush(other: FiveCards): Int {
        val card = cards.first()
        val otherCard = other.cards.first()
        return card.compareTo(otherCard)
    }

    /**
     * 两个FLUSH比较大小
     * 依次比较各个牌的大小
     * ex: ♠K♠8♠5♠4♠3 vs ♠K♠9♠7♠4♠3
     */
    private fun compareFlush(other: FiveCards): Int {
        val pairs = cards.zip(other.cards)
        for (pair in pairs) {
            val result = pair.first.compareTo(pair.second)
            if (result != 0) {
                return result
            }
        }
        return 0
    }

    /**
     * 两个FOUR_OF_A_KIND比较大小
     *
     * ex: ♠5♥5♣5♦5♦4 vs ♠9♠3♥3♣3♦3
     */
    private fun compareFourOfAKind(other: FiveCards): Int {
        // todo, jeremy
        val card = cards.elementAt(1)
        val otherCard = other.cards.elementAt(1)
        return card.compareTo(otherCard)
    }

    /**
     * 两个FULL_HOUSE比较大小
     * 只需比较第三位的牌点数即可
     * ex: ♠8♥8♣8♣5♦5 vs ♠9♥9♥3♣3♦3
     */
    private fun compareFullHouse(other: FiveCards): Int {
        val card = cards.elementAt(2)
        val otherCard = other.cards.elementAt(2)
        return card.compareTo(otherCard)
    }

    /**
     * 两个STRAIGHT比较大小
     * 只需比较首牌的大小即可
     * ex: ♠K♥Q♠J♦10♠9 vs ♠Q♣J♣10♦9♠8
     */
    private fun compareStraight(other: FiveCards): Int {
        val card = cards.first()
        val otherCard = other.cards.first()
        return card.compareTo(otherCard)
    }


    private fun compareThreeOfAKind(other: FiveCards): Int {
        return 0
    }

    override fun toString(): String {
        return "$cards"
    }


}
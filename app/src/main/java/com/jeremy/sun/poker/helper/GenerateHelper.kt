package com.jeremy.sun.poker.helper

import com.jeremy.sun.poker.bean.Card
import com.jeremy.sun.poker.bean.FiveCards
import com.jeremy.sun.poker.common.Pattern
import com.jeremy.sun.poker.common.Point
import com.jeremy.sun.poker.common.Suit
import java.util.*
import kotlin.random.Random

/**
 * Created by sun on 2020-02-29
 */
object GenerateHelper {

    /**
     * 生成随机一手牌
     */
    fun generateFiveCards(): FiveCards {
        val set = TreeSet<Card>()
        while (set.size < 5) {
            set.add(Card.getRandomCard())
        }
        return FiveCards(set)
    }


    /**
     * 生成一手指定牌型
     */
    fun generateFiveCards(pattern: Pattern): FiveCards {
        return when (pattern) {
            Pattern.ROYAL_FLUSH -> generateRoyalFlush()
            Pattern.STRAIGHT_FLUSH -> generateStraightFlush()
            Pattern.FOUR_OF_A_KIND -> generateFourOfAKind()
            Pattern.FULL_HOUSE -> generateFullHouse()
            Pattern.FLUSH -> generateFlush()
            Pattern.STRAIGHT -> generateStraight()
            Pattern.THREE_OF_A_KIND -> generateThreeOfAKind()
            Pattern.TWO_PAIRS -> generateTwoPairs()
            Pattern.ONE_PAIR -> generateOnePair()
            Pattern.HIGH_CARD -> generateHighCard()
        }
    }

    private fun generateRoyalFlush(): FiveCards {
        val set = TreeSet<Card>()
        val suit = Suit.getRandomSuit()
        for (index in Point.P10.index..Point.A.index) {
            set.add(Card(suit, Point.getPointByIndex(index)))
        }
        return FiveCards(set)
    }

    private fun generateStraightFlush(): FiveCards {
        val set = TreeSet<Card>()
        val suit = Suit.getRandomSuit()
        val start = Random.nextInt(8) // 最大到910JQK
        for (index in start..start + 4) {
            set.add(Card(suit, Point.getPointByIndex(index)))
        }
        return FiveCards(set)
    }

    private fun generateFourOfAKind(): FiveCards {
        val set = TreeSet<Card>()
        val pointOfFour = Point.getRandomPoint() // 四条的point
        for (index in 0..3) {
            set.add(Card(Suit.getSuitByIndex(index), pointOfFour))
        }
        var pointOfOne = Point.getRandomPoint() // 单张的point
        while (pointOfOne == pointOfFour) {
            pointOfOne = Point.getRandomPoint()
        }
        set.add(Card(Suit.getRandomSuit(), pointOfOne))
        return FiveCards(set)
    }

    private fun generateFullHouse(): FiveCards {
        val set = TreeSet<Card>()
        val pointOfThree = Point.getRandomPoint() // 三条的point值
        while (set.size < 3) {
            set.add(Card(Suit.getRandomSuit(), pointOfThree))
        }

        var pointOfTwo = Point.getRandomPoint() // 两对的point值
        while (pointOfTwo == pointOfThree) {
            pointOfTwo = Point.getRandomPoint()
        }

        while (set.size < 5) {
            set.add(Card(Suit.getRandomSuit(), pointOfTwo))
        }
        return FiveCards(set)
    }

    private fun generateFlush(): FiveCards {
        val set = TreeSet<Card>()
        val suit = Suit.getRandomSuit()
        while (set.size < 5) {
            set.add(Card(suit, Point.getRandomPoint())) // todo, jeremy, 可能会生成同花顺
        }
        return FiveCards(set)
    }

    private fun generateStraight(): FiveCards {
        val set = TreeSet<Card>()
        val start = Random.nextInt(9) // 最大到10JQKA
        for (index in start..start + 4) {
            set.add(Card(Suit.getRandomSuit(), Point.getPointByIndex(index))) // todo, jeremy, 可能会生成同花顺
        }
        return FiveCards(set)
    }

    private fun generateThreeOfAKind(): FiveCards {
        val set = TreeSet<Card>()
        val pointOfThree = Point.getRandomPoint() // 三条的point值
        while (set.size < 3) {
            set.add(Card(Suit.getRandomSuit(), pointOfThree))
        }

        var pointOfOne = Point.getRandomPoint() // 单牌的point值
        while (pointOfOne == pointOfThree) {
            pointOfOne = Point.getRandomPoint()
        }
        set.add(Card(Suit.getRandomSuit(), pointOfOne))

        var pointOfOtherOne = Point.getRandomPoint() // 另一张单牌的point值
        while (pointOfOtherOne == pointOfThree || pointOfOtherOne == pointOfOne) {
            pointOfOtherOne = Point.getRandomPoint()
        }
        set.add(Card(Suit.getRandomSuit(), pointOfOtherOne))

        return FiveCards(set)
    }

    private fun generateTwoPairs(): FiveCards {
        val set = TreeSet<Card>()
        val pointOfTwo = Point.getRandomPoint() // 两对的point值
        while (set.size < 2) {
            set.add(Card(Suit.getRandomSuit(), pointOfTwo))
        }

        var pointOfOtherTwo = Point.getRandomPoint() // 另一对两对的point值
        while (pointOfOtherTwo == pointOfTwo) {
            pointOfOtherTwo = Point.getRandomPoint()
        }
        while (set.size < 4) {
            set.add(Card(Suit.getRandomSuit(), pointOfOtherTwo))
        }

        var pointOfOne = Point.getRandomPoint() // 另一张单牌的point值
        while (pointOfOne == pointOfTwo || pointOfOne == pointOfOtherTwo) {
            pointOfOne = Point.getRandomPoint()
        }
        set.add(Card(Suit.getRandomSuit(), pointOfOne))

        return FiveCards(set)
    }

    private fun generateOnePair(): FiveCards {
        val set = TreeSet<Card>()
        val pointOfTwo = Point.getRandomPoint() // 两对的point值
        while (set.size < 2) {
            set.add(Card(Suit.getRandomSuit(), pointOfTwo))
        }

        var pointOfOne = Point.getRandomPoint() // 一张单排的point值
        while (pointOfOne == pointOfTwo) {
            pointOfOne = Point.getRandomPoint()
        }
        set.add(Card(Suit.getRandomSuit(), pointOfOne))

        var pointOfOtherOne = Point.getRandomPoint() // 另一张单牌的point值
        while (pointOfOtherOne == pointOfTwo || pointOfOtherOne == pointOfOne) {
            pointOfOtherOne = Point.getRandomPoint()
        }
        set.add(Card(Suit.getRandomSuit(), pointOfOtherOne))

        var pointOfLastOne = Point.getRandomPoint() // 最后一张单牌的point值
        while (pointOfLastOne == pointOfTwo || pointOfLastOne == pointOfOne || pointOfLastOne == pointOfOtherOne) {
            pointOfLastOne = Point.getRandomPoint()
        }
        set.add(Card(Suit.getRandomSuit(), pointOfLastOne))

        return FiveCards(set)
    }

    private fun generateHighCard(): FiveCards {
        val set = TreeSet<Card>()
        while (set.size < 5) {
            set.add(Card.getRandomCard()) // todo, jeremy, 可能会生成其他牌型
        }
        return FiveCards(set)
    }


}
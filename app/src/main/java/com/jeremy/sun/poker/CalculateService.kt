package com.jeremy.sun.poker

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.jeremy.sun.poker.bean.Card
import com.jeremy.sun.poker.bean.FiveCards
import com.jeremy.sun.poker.common.Pattern
import com.jeremy.sun.poker.common.Point
import com.jeremy.sun.poker.common.Suit
import com.jeremy.sun.poker.helper.GenerateHelper
import com.jeremy.sun.poker.helper.PatternHelper
import java.util.*

/**
 * Created by sun on 2020-03-01
 */
class CalculateService : IntentService("CALCULATE") {

    lateinit var thread: Thread

    override fun onHandleIntent(intent: Intent?) {
        Thread(Runnable {
            loop()
        }).start()
    }


    private fun loop() {
        val startTime = System.currentTimeMillis()
        val patternArray = LongArray(Pattern.values().size)
        val set = TreeSet<Card>()

        temp(0, set, patternArray)

        val endTime = System.currentTimeMillis()
        Log.d("sunwillfly", "cost time = ${(endTime - startTime)}ms")
        for ((index, times) in patternArray.withIndex()) {
            Log.d(
                "sunwillfly",
                "pattern = ${Pattern.getPatternByIndex(index).name}, times = $times"
            )
        }
        Log.d("sunwillfly", "sum fiveCards = ${patternArray.sum()}")

    }

    private fun temp(step: Int, set: TreeSet<Card>, array: LongArray) {
        if (step == 5) {
            with(FiveCards(set)) {
                if (this.isLegal()) {
                    val pattern = PatternHelper.getSuitPattern(this)
                    array[pattern.index]++
                }
            }
        } else {
            for (suit in Suit.values()) {
                for (point in Point.values()) {
                    val newSet = TreeSet<Card>()
                    newSet.addAll(set)
                    newSet.add(Card(suit, point))
                    if (step == 0 || step == 1) {
                        Log.d("sunwillfly", "step = $step, suit = $suit, point = $point")
                    }
                    val nextStep = step + 1
                    temp(nextStep, newSet, array)
                }
            }
        }
    }

    private fun test() {
        thread = Thread(Runnable {
            val startTime = System.currentTimeMillis()

            Log.d("sunwillfly", "start test")

            val patternArray = LongArray(Pattern.values().size)

            for (firstSuit in Suit.values()) {
                for (firstPoint in Point.values()) {
                    Log.d("sunwillfly", "firstSuit = $firstSuit, firstPoint = $firstPoint")

                    for (secondSuit in Suit.values()) {
                        for (secondPoint in Point.values()) {
                            Log.d(
                                "sunwillfly",
                                "secondSuit = $secondSuit, secondPoint = $secondPoint"
                            )

                            for (thirdSuit in Suit.values()) {
                                for (thirdPoint in Point.values()) {

                                    for (fourSuit in Suit.values()) {
                                        for (fourPoint in Point.values()) {

                                            for (fiveSuit in Suit.values()) {
                                                for (fivePoint in Point.values()) {

                                                    val set = TreeSet<Card>().also {
                                                        it.add(Card(firstSuit, firstPoint))
                                                        it.add(Card(secondSuit, secondPoint))
                                                        it.add(Card(thirdSuit, thirdPoint))
                                                        it.add(Card(fourSuit, fourPoint))
                                                        it.add(Card(fiveSuit, fivePoint))
                                                    }

                                                    with(FiveCards(set)) {
                                                        if (this.isLegal()) {
                                                            val pattern =
                                                                PatternHelper.getSuitPattern(this)
                                                            patternArray[pattern.index]++
                                                        }
                                                    }

                                                }
                                            }

                                        }
                                    }

                                }
                            }

                        }
                    }

                }
            }

            val endTime = System.currentTimeMillis()
            Log.d("sunwillfly", "cost time = ${(endTime - startTime)}ms")
            for ((index, times) in patternArray.withIndex()) {
                Log.d(
                    "sunwillfly",
                    "pattern = ${Pattern.getPatternByIndex(index).name}, times = $times"
                )
            }
            Log.d("sunwillfly", "sum fiveCards = ${patternArray.sum()}")

        })
        thread.start()
    }


    private fun testTwoCards() {
        Thread(Runnable {
            val startTime = System.currentTimeMillis()

            val twoCards = TreeSet<Card>().also {
                while (it.size < 2) {
                    it.add(Card.getRandomCard())
                }
            }
            Log.d("sunwillfly", "twoCards = $twoCards")

            val patternArray = IntArray(Pattern.values().size)

            for (firstSuit in Suit.values()) {
                for (firstPoint in Point.values()) {

                    for (secondSuit in Suit.values()) {
                        for (secondPoint in Point.values()) {


                            for (thirdSuit in Suit.values()) {
                                for (thirdPoint in Point.values()) {

                                    val set = TreeSet<Card>().also {
                                        it.addAll(twoCards)
                                        it.add(Card(firstSuit, firstPoint))
                                        it.add(Card(secondSuit, secondPoint))
                                        it.add(Card(thirdSuit, thirdPoint))
                                    }

                                    with(FiveCards(set)) {
                                        if (this.isLegal()) {
                                            val pattern = PatternHelper.getSuitPattern(this)
                                            patternArray[pattern.index]++
                                        }
                                    }
                                }
                            }


                        }
                    }

                }
            }

            val endTime = System.currentTimeMillis()
            Log.d("sunwillfly", "cost time = ${(endTime - startTime)}ms")
            for ((index, times) in patternArray.withIndex()) {
                Log.d(
                    "sunwillfly",
                    "pattern = ${Pattern.getPatternByIndex(index).name}, times = $times"
                )
            }
            Log.d("sunwillfly", "sum fiveCards = ${patternArray.sum()}")

        }).start()

    }

    private fun testGenerateRandomCards() {
        for (i in 0..100) {
            val randomCards = GenerateHelper.generateFiveCards()
            val pattern = PatternHelper.getSuitPattern(randomCards)
            Log.d("sunwillfly", "handCard = $randomCards, pattern = $pattern")
        }
    }

    private fun testGeneratePatternCards() {
        for (pattern in Pattern.values()) {
            Log.d("sunwillfly", "${pattern.name} start")
//            for (i in 0..10) {
//                Log.d("sunwillfly", "cards = ${GenerateHelper.generateFiveCards(pattern)}")
//            }
            val card = GenerateHelper.generateFiveCards(pattern)
            val result = PatternHelper.getSuitPattern(card)
            Log.d("sunwillfly", "card = $card, result = $result")
            Log.d("sunwillfly", "${pattern.name} end")
        }
    }

}
package com.jeremy.sun.poker.common

import com.jeremy.sun.poker.R
import kotlin.random.Random

/**
 * Created by sun on 2020-02-29
 * 扑克牌花色
 */
enum class Suit(val index: Int, val sName: String, val icon: Int) {
    Diamond(0, "♦", R.drawable.icon_diamond), // 方片♦
    Club(1, "♣", R.drawable.icon_club), // 梅花♣
    Heart(2, "♥", R.drawable.icon_heart), // 红桃♥
    Spade(3, "♠", R.drawable.icon_spade); // 黑桃♠

    companion object {
        /**
         * 获取一个随机花色
         */
        fun getRandomSuit(): Suit {
            return getSuitByIndex(Random.nextInt(4))
        }

        /**
         * 根据index获取一个花色
         */
        fun getSuitByIndex(index: Int): Suit {
            return when (index) {
                0 -> Diamond
                1 -> Club
                2 -> Heart
                else -> Spade
            }
        }
    }
}
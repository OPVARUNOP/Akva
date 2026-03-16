package com.varun.akva.intelligence

import android.content.Context

class StressDetector(private val context: Context) {

    private val switchTimestamps = mutableListOf<Long>()
    private val maxWindowSize = 50

    fun recordSwitch() {
        synchronized(switchTimestamps) {
            switchTimestamps.add(System.currentTimeMillis())
            if (switchTimestamps.size > maxWindowSize) {
                switchTimestamps.removeAt(0)
            }
        }
    }

    fun getStressScore(): Int {
        val now = System.currentTimeMillis()
        val threeMinAgo = now - 3 * 60 * 1000L

        val recentSwitches = synchronized(switchTimestamps) {
            switchTimestamps.count { it >= threeMinAgo }
        }

        return when {
            recentSwitches > 8 -> 8
            recentSwitches > 5 -> 5
            recentSwitches > 3 -> 3
            else -> 1
        }
    }

    fun isStressed(): Boolean = getStressScore() >= 7
}

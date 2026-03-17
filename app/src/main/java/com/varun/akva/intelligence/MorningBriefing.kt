package com.varun.akva.intelligence

class MorningBriefing {
    fun generateContext(unreadSummary: String): String {
        return "Morning Briefing. Unread messages: $unreadSummary. Give a warm morning greeting and summarize."
    }
}

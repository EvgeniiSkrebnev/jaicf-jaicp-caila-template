package com.justai.jaicf.template
import com.justai.jaicf.context.BotContext

class countScore (context: BotContext) {
    var answersCount: Int by context.session

    fun sessionScoreInit(){
        answersCount = 0
    }

    fun reset() {
        answersCount = 0
    }
}
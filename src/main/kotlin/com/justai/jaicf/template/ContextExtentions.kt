package com.justai.jaicf.template

import com.justai.jaicf.context.DefaultActionContext
import com.justai.jaicf.template.countScore

val DefaultActionContext.countScore: countScore
    get() = countScore(context)
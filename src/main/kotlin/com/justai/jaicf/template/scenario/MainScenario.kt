package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario

val mainScenario = Scenario {
    state("start") {
        activators {
            regex("/start")
            intent("Hello")
        }
        action {
            reactions.run {
                sayRandom(
                    "Здравствуй! Я бот для проверки знания Kotlin. Для продолжения нажми на кнопку \"Начать тестирование\"",
                    "Привет! Начнем испытание? Нажимай на кнопку \"Начать тестирование\""
                )
                buttons("Начать тестирование")
            }
        }
    }
    state ("startTest"){
        activators {
            regex("Начать тестирование")
        }
        action {
            var score = 0
            score++
            reactions.say("Вам предлагается пройти тестирование на знание Kotlin. Тест состоит из 20 вопросов с выбором правильного ответа.")
            reactions.say("Для продолжения нажми на кнопку.")
            reactions.say("$score")
            reactions.buttons("Готов")
        }
    }
    state ("testQ1") {
        activators {
            regex("Готов")
        }
        action {
            reactions.run {
                say("1. Каков корректный синтаксис для вывода фразы ‘Hello World’?")
                buttons("print(”Hello World”)",
                "println(”Hello World”)",
                "Console.WriteLine(”Hello World”)")
            }
        }
    }
    state ("testQ1RA") {
        activators {
            regex("println\\(”Hello World”\\)")
        }
        action {
            reactions.go("/testQ2")
        }
    }
    state ("testQ2") {
        activators {
            regex("print\\(”Hello World”\\)")
            regex("Console.WriteLine\\(”Hello World”\\)")
        }
        action {
            reactions.run {
                say("2. Как обозначается комментарий в коде?")
                buttons("#Комментарий",
                    "// Коментарий",
                    "\\\\ Коментарий")
            }
        }
    }
    state("bye") {
        activators {
            intent("Bye")
        }

        action {
            reactions.sayRandom(
                "See you soon!",
                "Bye-bye!"
            )
            reactions.image("https://media.giphy.com/media/EE185t7OeMbTy/source.gif")
        }
    }

    state("smalltalk", noContext = true) {
        activators {
            anyIntent()
        }

        action(caila) {
            activator.topIntent.answer?.let { reactions.say(it) } ?: reactions.go("/fallback")
        }
    }

    fallback {
        reactions.sayRandom(
            "Sorry, I didn't get that...",
            "Sorry, could you repeat please?"
        )
    }
}
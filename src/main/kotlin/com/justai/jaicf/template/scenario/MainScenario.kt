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
                    "Привет! Начнем тест? Нажимай на кнопку \"Начать тестирование\""
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
            reactions.say("Вам предлагается пройти тестирование на знание Kotlin. Тест состоит из 20 вопросов с выбором правильного ответа.")
            reactions.say("Для продолжения нажми на кнопку.")
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
                    "// Комментарий",
                    "! Комментарий")
            }
        }
    }
    state ("testQ2RA"){
        activators{
            regex("// Комментарий")
        }
        action {
            reactions.go("/testQ3")
        }
    }
    state ("testQ3") {
        activators {
            regex("! Комментарий")
            regex("#Комментарий")
        }
        action {
            reactions.run{
                say("Какая разница между val и var?")
                buttons("Val-изменяемая, var-неизменяемая",
                    "Var-изменяемая, val-неизменяемая",
                    "Разницы нет")
            }
        }
    }
    state ("testQ3RA"){
        activators{
            regex("Var-изменяемая, val-неизменяемая")
        }
        action {
            reactions.go("/testQ4")
        }
    }
    state ("testQ4") {
        activators {
            regex("Val-изменяемая, var-неизменяемая")
            regex("Разницы нет")
        }
        action {
            reactions.run{
                say("Как объявляется условие if?")
                buttons("if x > y",
                    "if (x > y)",
                    "if x > y then")
            }
        }
    }
    state ("testQ4RA"){
        activators{
            regex("if \\(x > y\\)")
        }
        action {
            reactions.go("/testQ5")
        }
    }
    state ("testQ5") {
        activators {
            regex("if x > y")
            regex("if x > y then")
        }
        action {
            reactions.run{
                say("Как объявляется цикл while?")
                buttons("while x < y do",
                    "while (x < y)",
                    "while x < y")
            }
        }
    }
    state ("testQ5RA"){
        activators{
            regex("while \\(x < y\\)")
        }
        action {
            reactions.go("/testQ6")
        }
    }
    state ("testQ6") {
        activators {
            regex("while x < y do")
            regex("while x < y")
        }
        action {
            reactions.run{
                say("Как создать диапазон между 1 и 10?")
                buttons("1..10",
                    "1,10",
                    "1:10")
            }
        }
    }
    state ("testQ6RA"){
        activators{
            regex("1\\.\\.10")
        }
        action {
            reactions.go("/testQ7")
        }
    }
    state ("testQ7") {
        activators {
            regex("1,10")
            regex("1:10")
        }
        action {
            reactions.run{
                say("Как создать массив из чисел?")
                buttons("val numbers = {1,2,3,4,5}",
                    "val numbers = [1,2,3,4,5]",
                    "val numbers = arrayOf(1,2,3,4,5)")
            }
        }
    }
    state ("testQ7RA"){
        activators{
            regex("val numbers = arrayOf\\(1,2,3,4,5\\)")
        }
        action {
            reactions.go("/testQ8")
        }
    }
    state ("testQ8") {
        activators {
            regex("val numbers = \\{1,2,3,4,5\\}")
            regex("val numbers = \\[1,2,3,4,5\\]")
        }
        action {
            reactions.run{
                say("Как вызывается функция?")
                buttons("fancyFunction()",
                    "fancyFunction[]",
                    "(fancyFunction)")
            }
        }
    }
    state ("testQ8RA"){
        activators{
            regex("fancyFunction\\(\\)")
        }
        action {
            reactions.go("/testQ9")
        }
    }
    state ("testQ9") {
        activators {
            regex("fancyFunction\\[\\]")
            regex("\\(fancyFunction\\)")
        }
        action {
            reactions.run{
                say("Какими кавычками нужно окружить строчное значение?")
                buttons("Одинарными",
                    "Двойными",
                    "Верны оба варианта")
            }
        }
    }
    state ("testQ9RA"){
        activators{
            regex("Двойными")
        }
        action {
            reactions.go("/testQ10")
        }
    }
    state ("testQ10") {
        activators {
            regex("Одинарными")
            regex("Верны оба варианта")
        }
        action {
            reactions.run{
                say("Каждая строка должна заканчиваться точкой с запятой (;) ?")
                buttons("Верно",
                    "Не верно")
            }
        }
    }
    state ("testQ10RA"){
        activators{
            regex("Не верно")
        }
        action {
            reactions.go("/testQ11")
        }
    }
    state ("testQ11") {
        activators {
            regex("Верно")
            regex("")
        }
        action {
            reactions.run{
                say("Каким символом интерполируются строки?")
                buttons("!", "\$", "%")
            }
        }
    }
    state ("testQ11RA"){
        activators{
            regex("\\$")
        }
        action {
            reactions.go("/testQ12")
        }
    }
    state ("testQ12") {
        activators {
            regex("%")
            regex("!")
        }
        action {
            reactions.run{
                say("Есть ли в Kotlin наследование классов?")
                buttons("Да", "Нет")
            }
        }
    }
    state ("testQ12RA"){
        activators{
            regex("Да")
        }
        action {
            reactions.go("/testQ13")
        }
    }
    state ("testQ13") {
        activators {
            regex("Нет")
        }
        action {
            reactions.run{
                say("Как правильно инициализировать числовую переменную?")
                buttons("var int(i) = 13", "var i: int = 13", "var i: Int = 13")
            }
        }
    }
    state ("testQ13RA"){
        activators{
            regex("var i: Int = 13")
        }
        action {
            reactions.go("/testQ14")
        }
    }
    state ("testQ14") {
        activators {
            regex("var int\\(i\\) = 13")
            regex("var i: int = 13")
        }
        action {
            reactions.run{
                say("Какая разница между var a: String? = “Hi!” и var b: String = “Hi!”")
                buttons("Нет разницы", "a может иметь значене null", "Вызов a приведет к ошибке")
            }
        }
    }
    state ("testQ14RA"){
        activators{
            regex("a может иметь значене null")
        }
        action {
            reactions.go("/testQ15")
        }
    }
    state ("testQ15") {
        activators {
            regex("Нет разницы")
            regex("Вызов a приведет к ошибке")
        }
        action {
            reactions.run{
                say("Как объявляется функция?")
                buttons("function sum(x: Int, y: Int): Int",
                    "fun sum(x: Int, y: Int): Int",
                    "makeFun sum(x: Int, y: Int): Int")
            }
        }
    }
    state ("testQ15RA"){
        activators{
            regex("fun sum\\(x: Int, y: Int\\): Int")
        }
        action {
            reactions.go("/testQ16")
        }
    }
    state ("testQ16") {
        activators {
            regex("function sum\\(x: Int, y: Int\\): Int")
            regex("makeFun sum\\(x: Int, y: Int\\): Int")
        }
        action {
            reactions.run{
                say("Что выведет следующий код?")
                image("https://imageup.ru/img187/4048201/untitled.png")
                buttons("Null Pointer Exception", "a==b", "false")
            }
        }
    }
    state ("testQ16RA"){
        activators{
            regex("false")
        }
        action {
            reactions.go("/testQ17")
        }
    }
    state ("testQ17") {
        activators {
            regex("Null Pointer Exception")
            regex("a==b")
        }
        action {
            reactions.run{
                say("Как написать на Kotlin выражение: int x = a ? b : c ")
                buttons("val x = a ? b : c",
                    "val x = if (a) b : c",
                    "val x = if (a) b else c")
            }
        }
    }
    state ("testQ17RA"){
        activators{
            regex("val x = if \\(a\\) b else c")
        }
        action {
            reactions.go("/testQ18")
        }
    }
    state ("testQ18") {
        activators {
            regex("val x = a \\? b : c")
            regex("val x = if \\(a\\) b : c")
        }
        action {
            reactions.run{
                say("Что выведет следующий код?")
                image("https://imageup.ru/img102/4048211/question18.png")
                buttons("[5,6,7]", "У list нет метода add", "[1,2,3,4]")
            }
        }
    }
    state ("testQ18RA"){
        activators{
            regex("У list нет метода add")
        }
        action {
            reactions.go("/testQ19")
        }
    }
    state ("testQ19") {
        activators {
            regex("\\[5,6,7\\]")
            regex("\\[1,2,3,4\\]")
        }
        action {
            reactions.run{
                say("Как называется такое выражение: {e -> println(e)}")
                buttons("Тильда выражение", "Лямбда выражение", "Альфа выражение")
            }
        }
    }
    state ("testQ19RA"){
        activators{
            regex("Лямбда выражение")
        }
        action {
            reactions.go("/testQ20")
        }
    }
    state ("testQ20") {
        activators {
            regex("Тильда выражение")
            regex("Альфа выражение")
        }
        action {
            reactions.run{
                say("По какой лицензии распространяется Kotlin?")
                buttons("GPL", "Apache 2", "MIT")
            }
        }
    }
    state ("testQ20RA"){
        activators{
            regex("Apache 2")
        }
        action {
            reactions.go("")
        }
    }
    state ("testEnd"){
        activators {
            regex("GPL")
            regex("MIT")
        }
        action {
            val score = 10
            when (score) {
                in 1..10 -> reactions.say("${score}/20 - это так себе")
                in 11..15 -> reactions.say("${score}/20 - это неплохо")
                in 16..19 -> reactions.say("${score}/20 - это хорошо")
                20 -> reactions.say("${score}/20 - это Отлично")
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
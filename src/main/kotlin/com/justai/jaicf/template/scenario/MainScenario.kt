package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.template.countScore


// Вопросная часть бота состоит из повторяюшихся стейтов 2 типов: это сам вопрос и стейт правильного ответа на вопрос.
// При правильном ответе на вопрос производится переход в стейт с пометкой RA(Right Answer), в котором счетчик увеличивается на 1.
// При неправильном ответе производится переход сразу в стейт следующего вопроса.


val mainScenario = Scenario {
    state("start") {
        activators {
            regex("/start")
            intent("Hello")
        }
        action {
            countScore.sessionScoreInit()
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
            reactions.say("Тебе предлагается пройти тестирование на знание Kotlin. Тест состоит из 20 вопросов с выбором правильного ответа.")
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
                say("Каков корректный синтаксис для вывода фразы ‘Hello World’?")
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
            countScore.answersCount ++
            reactions.say("${countScore.answersCount}")
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
                say("Как обозначается комментарий в коде?")
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
            reactions.go("/testQ11")
        }
    }
    state ("testQ11") {
        activators {
            regex("Верно")
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
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
            countScore.answersCount ++
            reactions.go("/testEnd")
        }
    }
    state ("testEnd"){
        activators {
            regex("GPL")
            regex("MIT")
        }
        action {
            when (countScore.answersCount) {
                in 1..10 -> reactions.say("Результат ${countScore.answersCount}/20 - это так себе. Нужно больше знаний.")
                in 11..15 -> reactions.say("Результат ${countScore.answersCount}/20 - это неплохо, но есть куда расти.")
                in 16..19 -> reactions.say("Результат ${countScore.answersCount}/20 - это хорошо, но стоит заполнить пробелы.")
                20 -> reactions.say("Результат ${countScore.answersCount}/20 - это Отлично")
            }
            reactions.say("Спасибо за участие в тестирвании!")
            reactions.say("Всего доброго!")
        }
    }
    fallback {
        reactions.sayRandom(
            "Что-то пошло не так...",
            "Прости, но что-то пошло не так..."
        )
    }
}

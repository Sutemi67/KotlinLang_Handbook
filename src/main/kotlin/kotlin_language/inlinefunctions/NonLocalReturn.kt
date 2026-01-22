package kotlin_language.inlinefunctions

/**
Что такое "non-local return" в контексте inline-функций и почему он невозможен в обычных функциях высшего порядка?
Напишите пример, где внутри лямбды, переданной в inline-функцию, используется return, который завершает внешнюю функцию:
Объясните результат. А что если бы функция inlineFunc была не inline?

Ответ - [NonLocalReturnAnswer]
 **/

fun main() {
    println("Start tinkoff_contest.main")
//    inlineFunc {
//        println("In lambda print")
//        return  // Что произойдет?
//    }
//    noInlineFunc {
//        println("In lambda print")
//        return  // Что произойдет?
//    }
    println("End tinkoff_contest.main")  // Будет ли выполнено?
}

inline fun inlineFunc(block: () -> Unit) {
    println("Start")
    block()
    println("End")
}

fun noInlineFunc( block: () -> Unit) {
    println("Start")
    block()
    println("End")
}
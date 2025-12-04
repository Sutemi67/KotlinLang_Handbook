package apc.appcradle.inlinefunctions

/**
 *
 *##### Обычная функция с лямбдой:
 * ```kotlin
 * fun nonInlineFunc(block: () -> Unit) {
 *     println("Начало")
 *     block()
 *     println("Конец")
 * }
 *
 * fun main() {
 *     nonInlineFunc { println("Лямбда") }
 * }
 * ```
 * ##### После компиляции (примерно):
 *
 * ```kotlin
 * fun main() {
 *     // Создается объект анонимного класса для лямбды
 *     val block$lambda = object : Function0<Unit> {
 *         override fun invoke() {
 *             println("Лямбда")
 *         }
 *     }
 *     nonInlineFunc(block$lambda)
 * }
 * ```
 * ### Накладные расходы:
 *
 *1. Создание объекта для лямбды
 *2. Вызов виртуального метода invoke
 *3. Дополнительные аллокации памяти
 *
 * ### Inline-функция с лямбдой:
 * ```kotlin
 * inline fun inlineFunc(block: () -> Unit) {
 *     println("Начало")
 *     block()
 *     println("Конец")
 * }
 *
 * fun main() {
 *     inlineFunc { println("Лямбда") }
 * }
 * ```
 * #### После компиляции (примерно):
 *
 * ```kotlin
 * fun main() {
 *     // Код встраивается напрямую
 *     println("Начало")
 *     println("Лямбда")  // Тело лямбды встроено
 *     println("Конец")
 * }
 * ```
 * ### Преимущества:
 *1. Нет создания объектов для лямбды
 *2. Нет вызова дополнительных методов
 *3. Лучшая производительность
 *4. Возможность non-local return
 *
 *  `https://kotlinlang.ru/docs/inline-functions.html`
 */
class InlineAnswer

/**
 * ```kotlin
 * inline fun inlineFunc(block: () -> Unit) {
 *     println("Начало inlineFunc")
 *     block()
 *     println("Конец inlineFunc") // Не выполнится, если в block был return
 * }
 *
 * fun main() {
 *     println("Начало main")
 *     inlineFunc {
 *         println("В лямбде")
 *         return  // non-local return - завершает main()
 *     }
 *     println("Конец main") // Не выполнится!
 * }
 * ```
 *Почему так происходит?
 *- В inline-функции код лямбды встраивается прямо в main(), поэтому return относится к main()
 *- В обычной функции лямбда компилируется в отдельный объект, и return пытался бы вернуться из этой лямбды (а не из main()), что не имеет смысла
 */
class NonLocalReturnAnswer
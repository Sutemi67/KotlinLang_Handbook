package kotlin_language.inlinefunctions

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
 * fun tinkoff_contest.main() {
 *     nonInlineFunc { println("Лямбда") }
 * }
 * ```
 * ##### После компиляции (примерно):
 *
 * ```kotlin
 * fun tinkoff_contest.main() {
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
 * fun tinkoff_contest.main() {
 *     inlineFunc { println("Лямбда") }
 * }
 * ```
 * #### После компиляции (примерно):
 *
 * ```kotlin
 * fun tinkoff_contest.main() {
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
 * fun tinkoff_contest.main() {
 *     println("Начало tinkoff_contest.main")
 *     inlineFunc {
 *         println("В лямбде")
 *         return  // non-local return - завершает tinkoff_contest.main()
 *     }
 *     println("Конец tinkoff_contest.main") // Не выполнится!
 * }
 * ```
 *Почему так происходит?
 *- В inline-функции код лямбды встраивается прямо в tinkoff_contest.main(), поэтому return относится к tinkoff_contest.main()
 *- В обычной функции лямбда компилируется в отдельный объект, и return пытался бы вернуться из этой лямбды (а не из tinkoff_contest.main()), что не имеет смысла
 *
 * ### Crossinline параметры:
 * А что если мы не хотим разрешать non-local return в inline-функции? Используем crossinline:
 *
 * ```kotlin
 * inline fun inlineFunc(crossinline block: () -> Unit) {
 *     println("Начало")
 *     block()  // Теперь block не может содержать non-local return
 *     println("Конец")
 * }
 *
 * fun tinkoff_contest.main() {
 *     inlineFunc {
 *         return  // ОШИБКА: Can't inline 'block' here: it may contain non-local returns
 *     }
 * }
 * ```
 */
class NonLocalReturnAnswer

/**
 *```
 * inline fun process(firstBlock: () -> Unit, noinline secondBlock: () -> Unit) {
 *     firstBlock()
 *     secondBlock()
 * }
 * ```
 */
class NoInlineAnswer
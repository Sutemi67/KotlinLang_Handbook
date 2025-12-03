package apc.appcradle.generics

/**
```
inline fun <reified T> isType(element: Any): Boolean {
return element is T
}
```
#### Почему reified работает только с inline-функциями:
1. Стирание типов: При компиляции дженерик-типы стираются до Object или верхней границы
2. Inline-функции: Код встраивается напрямую в место вызова
3. Во время компиляции компилятор знает конкретный тип T для каждого вызова и подставляет его напрямую

Как это выглядит после компиляции:

Исходный код
```
println(isType<String>("hello"))
```
После компиляции (примерно)
```
println("hello" is String)  // true
```
### Дополнительные примеры использования reified:

- Создание экземпляра через рефлексию
```
inline fun <reified T : Any> createInstance(): T {
return T::class.java.newInstance()
}
```
- Фильтрация списка по типу
```
inline fun <reified T> List<*>.filterByType(): List<T> {
return this.filter { it is T }.map { it as T }
}
```

- Проверка типа и приведение
```
inline fun <reified T> Any.castOrNull(): T? = this as? T
 ```
 **/
class ReifiedAnswer
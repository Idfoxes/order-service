# Сервис Статусов Заказов на основе Kafka

Этот проект представляет собой Spring Boot приложение, разработанное для демонстрации базовой функциональности продюсера и потребителя Kafka. Приложение прослушивает сообщения Kafka на теме `order-topic` и генерирует соответствующие обновления статусов на теме `order-status-topic`.

## Структура Проекта

- `app.config.KafkaConfig`: Содержит конфигурацию фабрик продюсера и потребителя Kafka.
- `app.event.OrderEvent`: Представляет событие заказа с деталями о продукте и количестве.
- `app.event.StatusEvent`: Представляет событие статуса с информацией о статусе и времени события.
- `app.listener.OrderStatusListener`: Прослушивает события заказов на `order-topic` и публикует обновления статусов на `order-status-topic`.
- `app.Application`: Главный класс, который запускает Spring Boot приложение.

## Начало Работы

### Требования

- Java 17 или новее
- Apache Kafka, работающий на `localhost:9092`
- Maven

### Запуск Приложения

1. **Клонируйте репозиторий:**
    ```bash
    git clone https://github.com/your-repo/kafka-order-status-service.git
    cd kafka-order-status-service
    ```

2. **Соберите проект:**
    ```bash
    mvn clean install
    ```

3. **Запустите приложение:**
    ```bash
    mvn spring-boot:run
    ```

### Топики Kafka

- **order-topic**: Приложение прослушивает сообщения `OrderEvent` на этом топике.
- **order-status-topic**: Приложение публикует сообщения `StatusEvent` на этом топике.

### Конфигурация Kafka

Конфигурация Kafka определяется в классе `KafkaConfig`:

- **Фабрика продюсера**: Настраивает продюсера с необходимыми сериализаторами (`StringSerializer` для ключей и `JsonSerializer` для значений).
- **Фабрика потребителя**: Настраивает потребителя с необходимыми десериализаторами (`StringDeserializer` для ключей и значений).
- **Шаблон Kafka**: Шаблон для отправки сообщений в Kafka.

### Классы Событий

- **OrderEvent**: Содержит детали заказа, такие как название продукта и количество.
- **StatusEvent**: Содержит статус заказа и временную метку события.

### OrderStatusListener

`OrderStatusListener` прослушивает сообщения из `order-topic`. После получения `OrderEvent`, он создает `StatusEvent` со статусом `CREATED` и текущей временной меткой, затем отправляет это событие в `order-status-topic`.

## Пример Работы

1. **Отправьте `OrderEvent` в `order-topic`:**

    ```json
    {
        "product": "Ноутбук",
        "quantity": 1
    }
    ```

2. **Обработчик обрабатывает событие** и отправляет `StatusEvent` в `order-status-topic`:

    ```json
    {
        "status": "CREATED",
        "date": "2024-08-30T12:34:56Z"
    }
    ```

## Лицензия

Этот проект лицензирован по лицензии MIT. Подробности см. в файле [LICENSE](LICENSE).

## Контакты

По всем вопросам и запросам, пожалуйста, свяжитесь с [your-email@example.com](mailto:your-email@example.com).

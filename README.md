# CoursesApp

Android приложение для просмотра списка курсов с авторизацией и избранным.

## Стек технологий

- **Kotlin** — основной язык
- **XML Layouts** — верстка
- **MVVM + Clean Architecture** — архитектура
- **Retrofit 2** — сетевые запросы
- **Koin 3** — Dependency Injection
- **Coroutines + StateFlow** — асинхронность
- **AdapterDelegates** — паттерн для RecyclerView
- **ViewBinding** — привязка представлений
- **Glide** — загрузка изображений
- **Material Design 3** — UI компоненты
- **Navigation Component** — навигация
- **DiffUtil** — эффективное обновление списков

## Архитектура

```
com.example.coursesapp
│
├── data
│   ├── api          — Retrofit ApiService, RetrofitClient
│   ├── model        — DTO (CourseDto)
│   └── repository   — CourseRepositoryImpl, маппер DTO → Domain
│
├── domain
│   ├── model        — Domain model (Course)
│   ├── repository   — CourseRepository интерфейс
│   └── usecase      — GetCoursesUseCase
│
├── presentation
│   ├── login        — LoginFragment, LoginViewModel
│   ├── home         — HomeFragment, HomeViewModel, CoursesUiState
│   ├── favorites    — FavoritesFragment (заглушка)
│   ├── account      — AccountFragment (заглушка)
│   └── adapter      — CoursesAdapter, CourseAdapterDelegate, CourseDiffCallback
│
└── app
    ├── App.kt       — инициализация Koin
    ├── MainActivity — NavController + BottomNavigationView
    └── di           — AppModule (Koin модуль)
```

## Экраны

### 1. Экран входа (Login)
- Email-поле с маской (только латиница, формат text@text.text)
- Поле пароля
- Кнопка "Вход" — активна только при валидных полях
- Кнопки VK / OK открывают браузер
- "Регистрация" и "Забыл пароль" — неактивны

### 2. Главный экран (Home)
- Строка поиска (нефункциональная)
- Кнопка фильтра (сортировка по publishDate DESC)
- RecyclerView со списком курсов, загружаемых через Retrofit
- Loading / Error / Success состояния

### 3. BottomNavigation
- Главная, Избранное, Аккаунт
- Скрывается на экране входа

## API

Данные загружаются с:
```
https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hq-BbS8-q&export=download
```

## Запуск

1. Открыть в Android Studio (Electric Eel или новее)
2. Sync Gradle
3. Run на устройстве или эмуляторе (minSdk 24)

## UI

- Темная тема (`#121212` фон, `#4CAF50` акцент)
- MaterialCardView с закруглёнными углами
- ConstraintLayout, Material Components

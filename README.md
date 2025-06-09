# SVOmniPro

Aplicación Android basada en Jetpack Compose y Clean Architecture que consume la API pública de Rick and Morty para mostrar personajes y detalles usando Hilt, ViewModel y Retrofit.

---

## 🏗️ Arquitectura

Este proyecto sigue el patrón **Clean Architecture** con 3 capas principales:

- **data/**: implementaciones de repositorios, DTOs, llamadas de red y configuración DI.
- **domain/**: contiene la lógica de negocio (casos de uso y contratos).
- **presentation/**: manejo de UI con Jetpack Compose, navegación, ViewModel y estado.

---

## 🚀 Tecnologías

- **Lenguaje:** Kotlin
- **UI:** Jetpack Compose
- **Inyección de dependencias:** Hilt
- **Networking:** Retrofit + Gson
- **Asincronía:** Coroutines + StateFlow




## ▶️ Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/santiagovasquez/test-kotlin-omni-pro.git
   ```
2. Abre el proyecto en Android Studio.
3. Ejecuta en un emulador o dispositivo real.
4. ¡Listo!

---

## 📄 Autor

Santiago Vasquez Olarte

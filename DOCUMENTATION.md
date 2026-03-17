# 🏗 Project Architecture Documentation

This document describes the architectural patterns, module structure, and technical decisions made during the development of the Bitpanda Live Challenge.

## 1. Architectural Pattern: Clean Architecture
The project is built following **Clean Architecture** principles. The main goal is the **Separation of Concerns**, ensuring that the business logic is isolated from external factors like UI frameworks, databases, or network providers.

### The Dependency Rule
The core business logic (Domain) knows nothing about the UI or the Network implementation.
* **Presentation (UI)** depends on **Domain** and on **Network** only for Hilt purposes
* **Data (Network)** depends on **Domain**.
* **Domain** depends on **nothing**.

---

## 2. Multi-Module Structure
To enforce the Clean Architecture boundaries and improve build times, the project is divided into several Gradle modules:

### 🧩 `:domain`
The most stable and important module.
* **Models:** Pure Kotlin data classes (e.g., `Coin`).
* **Repository Interfaces:** Contracts that define how data should be fetched.
* **Use Cases:** Business Logic units (e.g., `GetTopBestCoinsUseCase`).

### 🌐 `:network`
Handles all external data communication.
* **Data Sources:** Retrofit api interfaces.
* **DTOs:** Data Transfer Objects to parse API responses.
* **Mappers:** Logic to convert DTOs into Domain Models.
* **Repository Implementation:** Orchestrates data fetching, error handling, and currency conversion.

### 📱 `:app` (Presentation)
The presentation layer built with **Jetpack Compose**.
* **State Management:** Uses ViewModels and `StateFlow` to implement the Unidirectional Data Flow (UDF) pattern.
* **Use of State Hoisting pattern to separate Stateless than Stateful composables
* **Dependency Injection:** Hilt is configured here at the Application Class and ViewModels and Android Entry Point

---

## 3. Key Technical Decisions

### 💶 Currency Integrity (EUR Conversion)
The API provides prices in USD, but the requirements specify EUR.
* **Decision:** The mapping logic is centralized in the Repository.
* **Logic:** A coin is only emitted if a valid EUR exchange rate is available. If the conversion fails, the data is treated as inconsistent to prevent showing wrong financial information.

### ⚡ Reactive UI with Compose
By migrating from legacy XML/Fragments to **Jetpack Compose**:
* Reduced boilerplate code (no more Adapters or ViewHolders).
* State-driven UI, making the application more predictable and easier to debug.

### 🛡️ Use Case Granularity
Instead of a single large Use Case, I implemented specific ones for each filter (Best, Worst, All).
* **Why:** This follows the **Single Responsibility Principle (SRP)** and makes Unit Testing significantly easier, as each sorting/filtering logic can be tested in isolation.

---

## 4. Tech Stack
* **Language:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **DI:** Hilt
* **Networking:** Retrofit
* **Concurrency:** Kotlin Coroutines & Flow API
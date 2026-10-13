# ISWII_T08_G06 – Taller 08

Este repositorio contiene la solución del **Taller 08** de la asignatura **Ingeniería de Software I** (ISWII) de la Universidad del Cauca.  
El proyecto está implementado en **Java 8** y utiliza **Maven** como sistema de construcción.

---

## 📦 Estructura del proyecto

```
ISWII_T08_G06/
├─ src/
│  ├─ main/java/co/unicauca/taller08/      # Código fuente
│  │   ├─ adapter/        # Patrón Adapter
│  │   ├─ decorator/      # Patrón Decorator
│  │   ├─ domain/         # Entidades del dominio (Question, AnswerOption, ...)
│  │   ├─ facade/         # Fachada que orquesta la lógica de negocio
│  │   ├─ state/          # Patrón State para el ciclo de vida de una pregunta
│  │   └─ templatemethod/ # Patrón Template Method para evaluación
│  └─ test/java/co/unicauca/taller08/   # Pruebas unitarias
│       ├─ DecoratorAndAdapterTest.java
│       ├─ FacadeAndIntegrationTest.java
│       └─ StateAndTemplateMethodTest.java
├─ pom.xml               # Configuración Maven
└─ README.md             # Este archivo
```

## 🚀 Ejecutar la aplicación

El punto de entrada es la clase `ApplicationMain`:

```bash
java -cp target/ISWII_T08_G06-1.0-SNAPSHOT.jar co.unicauca.taller08.ApplicationMain
```

Sin embargo, la manera más sencilla es usar Maven:

```bash
mvn compile exec:java -Dexec.mainClass=co.unicauca.taller08.ApplicationMain
```

> **Nota:** Es necesario que el proyecto haya sido compilado previamente (`mvn compile`).

## 🧪 Ejecutar pruebas unitarias

```bash
mvn test
```

Maven ejecutará todos los archivos que terminen en `*Test.java` bajo `src/test/java`.  
Los tests cubren:
- **Patrón Decorator** y **Adapter** (`DecoratorAndAdapterTest`).
- **Fachada** y flujo completo de integración (`FacadeAndIntegrationTest`).
- **Patrón State** y **Template Method** (`StateAndTemplateMethodTest`).

## 📚 Tecnologías usadas

| Tecnologías | Versión |
|-------------|---------|
| Java        | 1.8     |
| Maven       | 3.x     |
| JUnit       | 4.13.2  |

## 🤝 Contribuir

1. Forkea el repositorio.
2. Crea una rama para tu nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios y asegura que **`mvn test`** sigue pasando.
4. Haz *push* y abre un Pull Request.

---

## 📧 Contacto

Desarrollado por el equipo **G06** conformado por Duvan Hoyos y Juan Hurtado de la asignatura ISWII.

---


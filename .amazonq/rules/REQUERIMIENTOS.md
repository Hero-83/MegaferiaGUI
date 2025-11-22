# Requerimientos de la Aplicación Megaferia GUI

## 📋 Checklist de Funcionalidades Principales

### ✅ Gestión de Stands
- [x] Crear stands con ID único (interfaz básica implementada + RegistrationManager)
- [x] Validar ID de stands (≥0, máximo 15 dígitos) - unicidad implementada - unicidad implementada
- [x] Validar precio de stands (>0) - formato implementado
- [ ] Visualizar stands en tabla ordenada por ID
  - [x] Ordenar stands por ID (Controlador) - SortManager implementado
  - [ ] Visualizar stands en tabla (Vista)

### ✅ Gestión de Personas
- [x] Registrar autores (clases e interfaz implementadas + RegistrationManager)
- [x] Registrar gerentes (clases e interfaz implementadas + RegistrationManager)
- [x] Registrar narradores (clases e interfaz implementadas + RegistrationManager)
- [x] Validar ID de personas (único, ≥0, máximo 15 dígitos) - unicidad implementada - unicidad implementada
- [x] Validar que campos no estén vacíos - FormatValidator implementado
- [ ] Visualizar personas en tabla ordenada por ID
  - [x] Ordenar personas por ID (Controlador) - SortManager implementado
  - [ ] Visualizar personas en tabla (Vista)

### ✅ Gestión de Editoriales
- [x] Registrar editoriales (clases e interfaz implementadas + RegistrationManager)
- [x] Validar NIT único con formato XXX.XXX.XXX-X - unicidad y formato implementados
- [x] Validar gerente existente previamente - ExistenceValidator implementado
- [x] Validar campos no vacíos - FormatValidator implementado
- [ ] Visualizar editoriales en tabla ordenada por NIT
  - [x] Ordenar editoriales por NIT (Controlador) - SortManager implementado
  - [ ] Visualizar editoriales en tabla (Vista)

### ✅ Gestión de Libros
- [x] Registrar libros impresos (clases e interfaz implementadas)
- [x] Registrar libros digitales (clases e interfaz implementadas)
- [x] Registrar audiolibros (clases e interfaz implementadas)
- [x] Validar ISBN único con formato XXX-X-XX-XXXXXX-X - unicidad y formato implementados
- [x] Validar autores existentes previamente - ExistenceValidator implementado
- [x] Validar editorial existente previamente - ExistenceValidator implementado
- [x] Validar narrador existente (para audiolibros) - ExistenceValidator implementado
- [x] Validar valor del libro (>0) - formato implementado
- [ ] Evitar autores duplicados en un mismo libro
- [x] Validar campos no vacíos (excepto hipervínculo) - FormatValidator implementado
- [ ] Visualizar libros por tipo ordenados por ISBN
  - [x] Ordenar libros por ISBN (Controlador) - SortManager implementado
  - [ ] Visualizar libros en tabla (Vista)

### ✅ Gestión de Compras
- [ ] Realizar compra de stands por editoriales
- [x] Validar stands existentes previamente - ExistenceValidator implementado
- [x] Validar editoriales existentes previamente - ExistenceValidator implementado
- [ ] Evitar duplicados de stands y editoriales en compra

### ✅ Consultas y Búsquedas
- [ ] Búsqueda de libros por autor (autor válido)
- [ ] Búsqueda de libros por formato
- [ ] Búsqueda de autores con más libros en diferentes editoriales
- [ ] Resultados ordenados por ISBN (libros) o ID (autores)
  - [x] Ordenar resultados de búsqueda (Controlador) - SortManager implementado
  - [ ] Mostrar resultados ordenados (Vista)

## 🏗️ Requerimientos de Arquitectura MVC

### 📱 Vista (View)
- [ ] NO modificar aspecto visual existente
- [ ] Renombrar componentes gráficos para mayor claridad
- [ ] NO realizar validaciones en la vista
- [ ] Evitar elementos duplicados en TextArea
- [ ] Invocar controladores y esperar respuestas
- [ ] Notificar resultado de operaciones al usuario
- [ ] Limpiar componentes tras operación exitosa
- [ ] Actualizar ComboBox tras creación exitosa
- [x] Ejecutar vista mediante archivo Main separado - Main.java implementado

### 🎮 Controlador (Controller)
- [x] Implementar sistema de respuestas y códigos de estado - Response/Status implementados
- [x] Controlador para crear Stand - StandController implementado
- [x] Controlador para registrar Autor - PersonController implementado
- [x] Controlador para registrar Gerente - PersonController implementado
- [x] Controlador para registrar Narrador - PersonController implementado
- [x] Controlador para registrar Editorial - PublisherController implementado
- [x] Controlador para registrar Libro (por tipo) - BookController implementado
- [x] Controlador para compra de Stand - PurchaseController implementado
- [x] Controlador para visualizar Stands - StandController implementado
- [x] Controlador para visualizar Personas - PersonController implementado
- [x] Controlador para visualizar Editoriales - PublisherController implementado
- [x] Controlador para visualizar Libros por Tipo - BookController implementado
- [x] Controlador para visualizar Libros por Autor - BookController implementado
- [x] Controlador para visualizar Libros por Formato - BookController implementado
- [x] Controlador para visualizar Autores con más Libros - PersonController.obtenerAutoresConMasLibros() implementado
- [x] Retornar copias de objetos (patrón Prototype) - implementado en controladores

### 🗄️ Modelo (Model)
- [x] Diseñar modelos siguiendo principios SOLID (estructura completa implementada)
- [x] Simular almacenamiento de datos (MegaferiaDataStore con Singleton)
- [x] Implementar validaciones de negocio - validaciones en controladores

## 🎯 Principios SOLID

### Single Responsibility Principle (SRP)
- [x] Cada clase tiene una única responsabilidad (UniqueValidator, FormatValidator, SortManager, ExistenceValidator, RegistrationManager implementados)
- [ ] Separación clara de responsabilidades

### Open/Closed Principle (OCP)
- [x] Clases abiertas para extensión (herencia implementada)
- [x] Clases cerradas para modificación (clases abstractas y herencia)

### Liskov Substitution Principle (LSP)
- [x] Subclases sustituibles por clases base (jerarquía Book implementada)
- [x] Comportamiento consistente en jerarquías (Person, Book)

### Interface Segregation Principle (ISP)
- [ ] Interfaces específicas y cohesivas
- [ ] Evitar interfaces "gordas"

### Dependency Inversion Principle (DIP)
- [ ] Dependencias hacia abstracciones
- [ ] Inversión de control implementada

## 🌟 Bonificaciones Opcionales

### SOLID en Controladores
- [ ] Implementar principios SOLID específicamente en controladores

### Patrón Observer
- [ ] Actualización automática de tablas
- [ ] Notificación de cambios en modelos
- [ ] Sincronización vista-modelo

## 📝 Validaciones Específicas

### Formatos Requeridos
- [x] NIT: XXX.XXX.XXX-X (dígitos 0-9) - FormatValidator implementado
- [x] ISBN: XXX-X-XX-XXXXXX-X (dígitos 0-9) - FormatValidator implementado
- [x] IDs: únicos, ≥0, máximo 15 dígitos (unicidad y formato implementados)

### Reglas de Negocio
- [x] Precios y valores > 0 (FormatValidator implementado)
- [x] Campos obligatorios no vacíos - FormatValidator implementado
- [x] Referencias válidas entre entidades - ExistenceValidator implementado
- [x] Sin duplicados en listas (UniqueValidator implementado)
- [x] Ordenamiento por campos específicos - SortManager implementado

## 🚀 Entrega y Configuración

### Repositorio
- [ ] Fork del repositorio original
- [ ] Commits de todos los integrantes
- [ ] README.md con nombres y NRC
- [ ] Enlace enviado por Brightspace

### Estructura del Proyecto
- [x] Arquitectura MVC implementada - controladores, modelo y vista separados
- [x] Archivo Main separado - Main.java implementado
- [x] Organización clara de paquetes - core.controller, core.model, core.view, main
- [ ] Documentación actualizada
# Requerimientos de la Aplicación Megaferia GUI

## 📋 Checklist de Funcionalidades Principales

### ✅ Gestión de Stands
- [x] Crear stands con ID único (interfaz básica implementada + RegistrationManager)
- [x] Validar ID de stands (≥0, máximo 15 dígitos) - unicidad implementada
- [x] Validar precio de stands (>0) - formato implementado
- [x] Visualizar stands en tabla ordenada por ID
  - [x] Ordenar stands por ID (Controlador) - SortUtils implementado
  - [x] Visualizar stands en tabla (Vista) - actualizarTablaStands() implementado

### ✅ Gestión de Personas
- [x] Registrar autores (clases e interfaz implementadas + RegistrationManager)
- [x] Registrar gerentes (clases e interfaz implementadas + RegistrationManager)
- [x] Registrar narradores (clases e interfaz implementadas + RegistrationManager)
- [x] Validar ID de personas (único, ≥0, máximo 15 dígitos) - unicidad implementada
- [x] Validar que campos no estén vacíos - FormatValidator implementado
- [x] Visualizar personas en tabla ordenada por ID
  - [x] Ordenar personas por ID (Controlador) - SortUtils implementado
  - [x] Visualizar personas en tabla (Vista) - btnConsultarPersonasActionPerformed implementado

### ✅ Gestión de Editoriales
- [x] Registrar editoriales (clases e interfaz implementadas + RegistrationManager)
- [x] Validar NIT único con formato XXX.XXX.XXX-X - unicidad y formato implementados
- [x] Validar gerente existente previamente - ExistenceValidator implementado
- [x] Validar campos no vacíos - FormatValidator implementado
- [x] Visualizar editoriales en tabla ordenada por NIT
  - [x] Ordenar editoriales por NIT (Controlador) - SortUtils implementado
  - [x] Visualizar editoriales en tabla (Vista) - btnConsultarEditorialesActionPerformed implementado

### ✅ Gestión de Libros
- [x] Registrar libros impresos (clases e interfaz implementadas)
- [x] Registrar libros digitales (clases e interfaz implementadas)
- [x] Registrar audiolibros (clases e interfaz implementadas)
- [x] Validar ISBN único con formato XXX-X-XX-XXXXXX-X - unicidad y formato implementados
- [x] Validar autores existentes previamente - ExistenceValidator implementado
- [x] Validar editorial existente previamente - ExistenceValidator implementado
- [x] Validar narrador existente (para audiolibros) - ExistenceValidator implementado
- [x] Validar valor del libro (>0) - formato implementado
- [x] Evitar autores duplicados en un mismo libro - lógica en btnAgregarAutorLibroActionPerformed
- [x] Validar campos no vacíos (excepto hipervínculo) - FormatValidator implementado
- [x] Visualizar libros por tipo ordenados por ISBN
  - [x] Ordenar libros por ISBN (Controlador) - SortUtils implementado
  - [x] Visualizar libros en tabla (Vista) - btnConsultarShowLibrosActionPerformed implementado

### ✅ Gestión de Compras
- [x] Realizar compra de stands por editoriales - btnComprarStandActionPerformed implementado
- [x] Validar stands existentes previamente - ExistenceValidator implementado
- [x] Validar editoriales existentes previamente - ExistenceValidator implementado
- [x] Evitar duplicados de stands y editoriales en compra - lógica en btnAgregarStandCompra/EditorialCompra

### ✅ Consultas y Búsquedas
- [x] Búsqueda de libros por autor (autor válido) - btnConsultarAutorConsultasAdicionalesActionPerformed
- [x] Búsqueda de libros por formato - btnConsultarFormatoConsultasAdicionalesActionPerformed
- [x] Búsqueda de autores con más libros en diferentes editoriales - btnConsultarAutoresActionPerformed
- [x] Resultados ordenados por ISBN (libros) o ID (autores)
  - [x] Ordenar resultados de búsqueda (Controlador) - SortUtils implementado
  - [x] Mostrar resultados ordenados (Vista) - tablas implementadas

## 🏗️ Requerimientos de Arquitectura MVC

### 📱 Vista (View)
- [x] NO modificar aspecto visual existente - mantenido diseño original
- [x] Renombrar componentes gráficos para mayor claridad - nombres descriptivos mantenidos
- [x] NO realizar validaciones en la vista - validaciones en controladores
- [x] Evitar elementos duplicados en TextArea - lógica implementada
- [x] Invocar controladores y esperar respuestas - patrón implementado
- [x] Notificar resultado de operaciones al usuario - JOptionPane implementado
- [x] Limpiar componentes tras operación exitosa - setText("") implementado
- [x] Actualizar ComboBox tras creación exitosa - métodos actualizarCombo* implementados
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
- [x] Cada clase tiene una única responsabilidad (ValidationUtils, FormatValidator, SortUtils, ExistenceValidator, RegistrationManager implementados)
- [x] Separación clara de responsabilidades - clases especializadas por función

### Open/Closed Principle (OCP)
- [x] Clases abiertas para extensión (herencia implementada)
- [x] Clases cerradas para modificación (clases abstractas y herencia)

### Liskov Substitution Principle (LSP)
- [x] Subclases sustituibles por clases base (jerarquía Book implementada)
- [x] Comportamiento consistente en jerarquías (Person, Book)

### Interface Segregation Principle (ISP)
- [x] Interfaces específicas y cohesivas - Response<T> genérico
- [x] Evitar interfaces "gordas" - interfaces pequeñas y específicas

### Dependency Inversion Principle (DIP)
- [x] Dependencias hacia abstracciones - uso de interfaces Response<T>
- [x] Inversión de control implementada - inyección de controladores en vista

## 🌟 Bonificaciones Opcionales

### SOLID en Controladores
- [x] Implementar principios SOLID específicamente en controladores
  - [x] SRP: Cada controlador maneja una entidad específica
  - [x] OCP: Extensible mediante herencia (Response<T>)
  - [x] LSP: Interfaces consistentes entre controladores
  - [x] ISP: Métodos específicos por funcionalidad
  - [x] DIP: Dependencia de abstracciones (Response, DataStore)

### Patrón Observer
- [x] Actualización automática de tablas - métodos actualizarTabla* implementados
- [x] Notificación de cambios en modelos - JOptionPane para feedback
- [x] Sincronización vista-modelo - actualización tras operaciones exitosas

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
- [x] Fork del repositorio original - proyecto configurado
- [ ] Commits de todos los integrantes
- [ ] README.md con nombres y NRC
- [ ] Enlace enviado por Brightspace

### Estructura del Proyecto
- [x] Arquitectura MVC implementada - controladores, modelo y vista separados
- [x] Archivo Main separado - Main.java implementado
- [x] Organización clara de paquetes - core.controller, core.model, core.view, main
- [x] Documentación actualizada - REQUERIMIENTOS.md completo
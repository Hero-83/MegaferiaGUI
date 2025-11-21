# Requerimientos de la Aplicación Megaferia GUI

## 📋 Checklist de Funcionalidades Principales

### ✅ Gestión de Stands
- [x] Crear stands con ID único (interfaz básica implementada)
- [x] Validar ID de stands (≥0, máximo 15 dígitos) - unicidad implementada
- [ ] Validar precio de stands (>0)
- [ ] Visualizar stands en tabla ordenada por ID

### ✅ Gestión de Personas
- [x] Registrar autores (clases e interfaz implementadas)
- [x] Registrar gerentes (clases e interfaz implementadas)
- [x] Registrar narradores (clases e interfaz implementadas)
- [x] Validar ID de personas (único, ≥0, máximo 15 dígitos) - unicidad implementada
- [ ] Validar que campos no estén vacíos
- [ ] Visualizar personas en tabla ordenada por ID

### ✅ Gestión de Editoriales
- [x] Registrar editoriales (clases e interfaz implementadas)
- [x] Validar NIT único con formato XXX.XXX.XXX-X - unicidad implementada
- [ ] Validar gerente existente previamente
- [ ] Validar campos no vacíos
- [ ] Visualizar editoriales en tabla ordenada por NIT

### ✅ Gestión de Libros
- [x] Registrar libros impresos (clases e interfaz implementadas)
- [x] Registrar libros digitales (clases e interfaz implementadas)
- [x] Registrar audiolibros (clases e interfaz implementadas)
- [x] Validar ISBN único con formato XXX-X-XX-XXXXXX-X - unicidad implementada
- [ ] Validar autores existentes previamente
- [ ] Validar editorial existente previamente
- [ ] Validar narrador existente (para audiolibros)
- [ ] Validar valor del libro (>0)
- [ ] Evitar autores duplicados en un mismo libro
- [ ] Validar campos no vacíos (excepto hipervínculo)
- [ ] Visualizar libros por tipo ordenados por ISBN

### ✅ Gestión de Compras
- [ ] Realizar compra de stands por editoriales
- [ ] Validar stands existentes previamente
- [ ] Validar editoriales existentes previamente
- [ ] Evitar duplicados de stands y editoriales en compra

### ✅ Consultas y Búsquedas
- [ ] Búsqueda de libros por autor (autor válido)
- [ ] Búsqueda de libros por formato
- [ ] Búsqueda de autores con más libros en diferentes editoriales
- [ ] Resultados ordenados por ISBN (libros) o ID (autores)

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
- [ ] Ejecutar vista mediante archivo Main separado

### 🎮 Controlador (Controller)
- [ ] Implementar sistema de respuestas y códigos de estado
- [ ] Controlador para crear Stand
- [ ] Controlador para registrar Autor
- [ ] Controlador para registrar Gerente
- [ ] Controlador para registrar Narrador
- [ ] Controlador para registrar Editorial
- [ ] Controlador para registrar Libro (por tipo)
- [ ] Controlador para compra de Stand
- [ ] Controlador para visualizar Stands
- [ ] Controlador para visualizar Personas
- [ ] Controlador para visualizar Editoriales
- [ ] Controlador para visualizar Libros por Tipo
- [ ] Controlador para visualizar Libros por Autor
- [ ] Controlador para visualizar Libros por Formato
- [ ] Controlador para visualizar Autores con más Libros
- [ ] Retornar copias de objetos (patrón Prototype)

### 🗄️ Modelo (Model)
- [x] Diseñar modelos siguiendo principios SOLID (estructura básica implementada)
- [x] Simular almacenamiento de datos (ArrayLists en MegaferiaFrame)
- [ ] Implementar validaciones de negocio

## 🎯 Principios SOLID

### Single Responsibility Principle (SRP)
- [x] Cada clase tiene una única responsabilidad (UniqueValidator implementado)
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
- [ ] NIT: XXX.XXX.XXX-X (dígitos 0-9)
- [ ] ISBN: XXX-X-XX-XXXXXX-X (dígitos 0-9)
- [x] IDs: únicos, ≥0, máximo 15 dígitos (unicidad implementada)

### Reglas de Negocio
- [ ] Precios y valores > 0
- [ ] Campos obligatorios no vacíos
- [ ] Referencias válidas entre entidades
- [x] Sin duplicados en listas (UniqueValidator implementado)
- [ ] Ordenamiento por campos específicos

## 🚀 Entrega y Configuración

### Repositorio
- [ ] Fork del repositorio original
- [ ] Commits de todos los integrantes
- [ ] README.md con nombres y NRC
- [ ] Enlace enviado por Brightspace

### Estructura del Proyecto
- [ ] Arquitectura MVC implementada
- [ ] Archivo Main separado
- [ ] Organización clara de paquetes
- [ ] Documentación actualizada
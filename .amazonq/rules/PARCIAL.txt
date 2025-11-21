Megaferia
Dado el proyecto MegaferiaGUI, que contiene una implementación del sistema para la
primera gran feria del libro en Barranquilla. En el sistema se pueden realizar las siguientes
acciones:
• Crear stands.
• Registrar personas (autores, gerentes y narradores).
• Registrar editoriales.
• Registrar libros de tres tipos (libros impresos, libros digitales y audiolibros).
• Realizar la compra de stands por parte de una o varias editoriales.
• Visualizar la información de stands, personas, editoriales y libros en tablas.
• Realizar unas consultas adicionales:
o Búsqueda de libros por autor.
o Búsqueda de libros por formato.
o Búsqueda de los autores con más libros en diferentes editoriales.
El equipo de trabajo debe realizar lo siguiente:
1. Realizar un fork del repositorio en Github, para tener su propia copia del repositorio
sobre la cual realizar el parcial.
a. Nota: Es importante mencionar que luego de hacer el fork deben clonar el
nuevo repositorio para trabajar en local.
2. Hacer un refactor del proyecto para llevarlo a una arquitectura MVC (Model View
Controller) utilizando los principios de SOLID.
a. Para las vistas:
i. NO modifique el aspecto visual del proyecto, i.e., NO añada nuevos
componentes gráficos ni mueva de lugar los ya existentes.
ii. Debe renombrar cada uno de los componentes gráficos para ofrecer
una mayor claridad sobre la función que desempeña cada uno.
iii. NO se deben realizar verificaciones de los datos de entrada del lado de
la vista.
iv. En los módulos de registro de libros y de compras de stands se debe
garantizar que, al añadir un elemento a un TextArea, solo se añada
exactamente una vez, i.e., NO debe haber elementos repetidos en los
TextArea.
v. Se debe invocar a los controladores y esperar una respuesta de estos.
vi. Al obtener una respuesta, debe notificar al usuario del resultado de la
operación (sea exitosa o no).
vii. Si la respuesta es exitosa, se debe limpiar la información de los
componentes visuales correspondientes.
viii. Al recibir una respuesta exitosa de la creación de los objetos, se deben
actualizar los ComboBox correspondientes en las pestañas necesarias.
ix. La vista NO debe ejecutarse a sí misma, i.e., la vista se debe ejecutar
mediante un archivo Main distinto.
b. Para los controladores:
i. Utilizar el sistema de respuestas y códigos de estado del ejemplo
desarrollado en clase, lo puede encontrar en el repositorio
MVCExample.
ii. Realizar los controladores que permitan:
• Crear un Stand.
• Registrar un Autor.
• Registrar un Gerente.
• Registrar un Narrador.
• Registrar una Editorial.
• Registrar un Libro de un tipo (Impreso, Digital Audiolibro)
específico.
• Realizar la compra de un Stand por parte de una o varias
Editoriales, i.e., vincular cada Stand con la(s) Editorial(es)
correspondiente(s).
• Obtener la información necesaria para su correcta visualización
en las tablas correspondientes.
o Visualizar Stands.
o Visualizar Personas.
o Visualizar Editoriales.
o Visualizar Libros por Tipo.
o Visualizar Libros por Autor.
o Visualizar Libros por Formato.
o Visualizar Autores con más Libros en diferentes Editoriales.
iii. En los controladores a desarrollar se debe tener en cuenta los siguientes
requerimientos:
• Los id de los stands deben ser únicos, mayores o iguales que 0 y
tener a lo más 15 dígitos.
• El precio de los stands debe ser superior a 0.
• Los id de las personas deben ser únicos, mayores o iguales que 0
y tener a lo más 15 dígitos.
• El resto de los campos de las personas no deben ser vacíos.
• Los NIT de las editoriales deben ser únicos y deben seguir el
formato XXX.XXX.XXX-X, donde cada X corresponde a un dígito
del 0 al 9.
• El gerente de una editorial debe ser válido, i.e., estar creado
previamente.
• El resto de los campos de las editoriales no deben ser vacíos.
• Los ISBN de los libros deben ser únicos y deben seguir el formato
XXX-X-XX-XXXXXX-X, donde cada X corresponde a un dígito
del 0 al 9.
• Los autores de un libro deben ser válidos, i.e., estar creados
previamente.
• La editorial de un libro debe ser válida, i.e., estar creada
previamente.
• El narrador de un audiolibro debe ser válido, i.e., estar creado
previamente.
• El valor de los libros debe ser superior a 0.
• Cada autor en la lista de autores de un libro debe ser diferente,
i.e., NO se deben repetir autores en un mismo libro.
• El resto de los campos de los libros no deben ser vacíos, a
excepción del hipervínculo.
• Para realizar la compra de un stand por parte de una o varias
editoriales es necesario que los stands y las editoriales sean
válidos, i.e., estar creados previamente.
• Todos los stands y las editoriales a la hora de realizar una compra
deben ser diferentes, i.e., NO se deben repetir ni stands, ni
editoriales.
• Los stands se deben obtener de manera ordenada (respecto a
su id).
• Las personas se deben obtener de manera ordenada (respecto
a su id).
• Las editoriales se deben obtener de manera ordenada (respecto
a su NIT).
• Los libros se deben obtener de manera ordenada (respecto a su
ISBN).
• Para realizar la búsqueda de los libros por autor dicho autor debe
ser válido, i.e., estar creado previamente.
• Los libros en las búsquedas por autor y formato se deben obtener
de manera ordenada (respecto a su ISBN).
• Los autores con más libros en diferentes editoriales se deben
obtener de manera ordenada (respecto a su id).
iv. Al retornar un objeto en la Response, este debe ser una copia del que
está guardado en los modelos (patrón Prototype).
• Nota: También puede serializar los objetos al momento de
retornar la respuesta, esto lo puede consultar en la rama
experimental del repositorio MVCExample.
c. Para los modelos:
i. Diseñe los modelos necesarios siguiendo los principios SOLID.
ii. En caso de ser necesario, simule un almacenamiento como en el
ejemplo desarrollado en clase.
3. (Bonificación) Implemente los principios SOLID en los controladores del proyecto.
4. (Bonificación) Implemente adecuadamente el patrón observador para que cada
vez que haya una creación o modificación sobre alguno de los modelos la tabla
correspondiente se actualice automáticamente.
Criterios de calificación
• MVC (3.0)
o Realiza una correcta implementación y cumple con lo solicitado para los
modelos (1.0)
o Realiza una correcta implementación y cumple con lo solicitado para los
controladores (1.0)
o Realiza una correcta implementación y cumple con lo solicitado para las
vistas (1.0)
• SOLID (2.0)
o Cumple con Single Responsability Principle (0.4)
o Cumple con Open/Close Principle (0.4)
o Cumple con Liskov’s Substitution Principle (0.4)
o Cumple con Interface Segregation Principle (0.4)
o Cumple con Dependancy Inversion Principle (0.4)
• Bonificación (1.0)
o Implementa adecuadamente los principios SOLID en los controladores del
proyecto (0.5)
o Implementa adecuadamente el patrón observador para la actualización
automática de las tablas (0.5)
Método e indicaciones para la entrega
• Este parcial es para desarrollar en grupos de máximo 3 integrantes, todo tipo de
fraude será castigado según el reglamento.
o Los integrantes pueden ser de cualquiera de los tres NRC.
o Se debe enviar un mensaje donde se especifique los integrantes del grupo y
su NRC al correo edangulo@uninorte.edu.co a más tardar el día 17 de
noviembre de 2025 a las 11:59 p.m., esto para poder registrarlos en el grupo
correspondiente en Brightspace.
• Al momento de calificar se revisará el último commit realizado antes de la hora límite.
• Se deberá realizar un fork del repositorio de Github donde deberán cargar el código
desarrollado.
o Se debe enviar el enlace del repositorio por la actividad correspondiente en
el curso de Brightspace a más tardar antes de la hora límite del parcial.
o Los nombres completos de los integrantes y el NRC al que pertenecen deberán
estar escritos en el archivo README.md.
o Todos los integrantes deben tener commits en el repositorio, en caso contrario
se asumirá que aquellos que tengan commits fueron los únicos que trabajaron.
• El incumplimiento de alguna de estas indicaciones implicará una penalización a la
nota final del examen
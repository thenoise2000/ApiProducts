                                              Api rest para consulta de productos por categorias de un ecommerce

Con los siguientes datos:

En la web de Zara.com tenemos distintas categorías de producto (camisetas, zapatos , jeans,pantalones, etc.), que sirven para organizar el producto en la web de forma que los clientes puedan encontrar lo que están buscando.  
Nos gustaría tener un algoritmo que permita ordenar los productos dentro de las categorías en base a una métrica (p.ej. unidades vendidas, stock disponible, etc.) o una combinación ponderada de las mismas (p.ej. 80% unidades vendidas y 20% stock disponible).  

Para este ejercicio, usaremos las siguientes métricas, pero puede que se quieran incorporar nuevas métricas en el futuro:  

• Ventas por unidades: número de unidades vendidas.  
• Ratio de stock: ratio de tallas con stock en ese momento.  

El listado de productos es el siguiente: 

![image](https://github.com/user-attachments/assets/147bddcd-8064-49e7-b73a-be3fe6b3d7a2)


El algoritmo de ordenación debe exponerse a través de un servicio REST de manera que reciba las 
métricas y los pesos asociados a cada métrica y devuelva el listado de productos ordenados.


Se desarrollo una API REST para operaciones de ecommerce con Spring Boot y empleando una DB MySQL.

Herramientas

Java 17

SpringBoot

JPA

MySQL

Maven

Junit

Para instalar

Clonamos o desacargamos el repositorio git clone https://github.com/thenoise2000/ApiProducts Dirijase a la raiz del proyecto: cd ApiEcommerce Compilamos: mvn clean install Ejecutamos mvn spring-boot:run

Para desplegarlo ejecute

comando mvn spring-boot:run

Pruebas en las Api:

En primera instancia se debe crear las categorias para asignarlas a productos en nuestro caso de pruebas creamos la categoria "camisetas" para realizar las pruebas de funcionalidad con los datos proporcionados. Adicionalmente creamos otra categoria "pantalones".

Estos datos se cargaran automaticamente al iniciar el proyecto, una vez creada la base de datos products en tu gestor mysql!


Url de pruebas para la Api = http://localhost:8080/api/products/sorted?categoryId=1&salesWeight=0.8&stockWeight=0.2


Ejemplo

![productsget](https://github.com/user-attachments/assets/a0aeeb78-6a72-441a-86cd-157084fe9424)


parametros de entrada de las pruebas:

categoryId=1

salesWeight=0.8

stockWeight=0.2


## Para ejecutar tests

utilice el comando:

bash mvnw test

O ejecutelos en el IDE de su preferencia

                                         /** Analisis Domain Driven Design **/

Domain Driven Design consiste, principalmente, en dos procesos: el modelado del dominio y la implementación de la lógica del dominio

Arquitectura (Architecture)

Segun (Murillo Paredes. Universisdad de Alcala, 2021) Para el diseño e implementación de cualquier aplicación se tienen que realizar múltiples tipos de tarea:

lógica de negocio, interacción con el usuario, comunicación otros sistemas, etc. Si se mezclan las responsabilidades entre las diferentes tareas se obtienen sistemas acoplados, poco cohesionados, complejos y difícilmente mantenibles y probables.

Las arquitecturas por capas son de gran utilidad para paliar la problemática mencionada anteriormente. El principio esencial que sustenta este tipo de arquitecturas es que un componente de una capa solo depende de otros componentes de esta o de capas inferiores, promoviendo el bajo acoplamiento y la alta cohesión. Evidentemente la elección de cada una de estas capas toma un papel relevante para un buen diseño arquitectónico. Como definición clásica, este tipo de arquitectura se suele dividir en las siguientes capas:

• Capa de presentación. Muestra la información e interpreta las acciones realizadas por los usuarios

• Capa de aplicación. Coordina las diferentes interacciones de una tarea y delega el trabajo en los colaboradores de dominio. No contiene lógica de negocio

• Capa de dominio. Representa las reglas de negocio. Suele ser denominada como “el corazón del negocio”

• Capa de infraestructura. Proporciona mecanismos para la integración con proveedores externos: persistencia de los objetos de dominio, comunicación mediante mensajes, etc.

![hexa1](https://github.com/user-attachments/assets/9a2e1638-fa1e-471f-99bf-51bece0bda5d)


Es necesario concentrar todo el código relativo al modelado de dominio en una única capa y aislarla del resto de tareas asociadas. Los componentes de la capa de dominio no tienen como responsabilidad mostrar información al usuario, persistir sus propios datos, etc.

Los beneficios que se contienen con el uso de este tipo de arquitectura son: • Código fácil de leer, entender, cambiar y mantener • Código simétrico (predecible) • Alta cohesión y bajo acoplamiento • Modelos de dominio enriquecido • Componentes aislados y separados por su responsabilidad • Facilidad de realización de pruebas • Útil para sistemas distribuidos • Flexibilidad en el diseño

Para el diseño de aplicación que siguen el patrón DDD se suele utilizar la arquitectura hexagonal ya que aísla el modelado del dominio de los componentes externos. Este aislamiento permite que los cambios en los componentes externos no afecten al modelado del dominio. Los cambios en el dominio solo deben permitirse cuando son realizados por criterios del negocio. Los puertos son una definición del contrato público y los adaptadores son la implementación de un puerto para un contexto en concreto.

![hexa2](https://github.com/user-attachments/assets/2eac928a-a45a-44b1-9570-60d64438947b)



Para este caso de analisis de esta arquitectura por capas implementamos el flujo de una petición REST en la arquitectura hexagonal en donde el controlador forma parte de la capa de infraestructura y delega en un servicio de la capa aplicación la ejecución de la acción requerida por el usuario. El servicio de la capa de aplicación representa el caso de uso de manera atómica y coordina con ayuda de los elementos de la capa de dominio las tareas asociadas a este.

![hexa3](https://github.com/user-attachments/assets/34b1b8e5-c529-4c5d-a6f3-da4c609fc109)



Para abordar este requerimiento desde una perspectiva de Domain-Driven Design (DDD), podemos identificar dos entidades principales en el contexto del problema: Product y Category. Cada una de estas entidades tiene su propia identidad y atributos específicos que se relacionan entre sí en el dominio del comercio electrónico.

![image](https://github.com/user-attachments/assets/76478801-c514-4952-a003-3f459d8a26f0)



Product (Producto)

Atributos: id , name , salesUnit , stock , category.

Relaciones: Se relaciona con Category.

Category (Categorias)

Atributos: id , name.

Relaciones: Puede tener múltiples prodcutos asociados.

## Para implementar la funcionalidad solicitada en Spring Boot, podemos seguir los siguientes pasos:

- Definir las Entidades: Crear las clases Java que representen las entidades Product y Category, con sus atributos y relaciones correspondientes.

- Definir los Repositorios: Crear interfaces de repositorio para cada entidad que permitan acceder a los datos en la base de datos.

- Implementar el Servicio de Consulta de Productos: Crear un servicio que contenga la lógica para consultar los productos ordenados dada las metricas solicitadas aplicables según los parámetros de entrada recibidos (categoria, unidades vendidas, disponibilidad en stock).

- Crear el Controlador REST: Implementar un controlador REST en Spring Boot que exponga un endpoint para la consulta de productos. Este controlador invocará al servicio creado en el paso anterior.

- Manejar las Excepciones: Implementar un manejo adecuado de excepciones para casos como cateogrias no encontradas o no válidas, etc.

- Pruebas Unitarias: Es importante realizar pruebas unitarias para validar el comportamiento de los servicios y controladores.

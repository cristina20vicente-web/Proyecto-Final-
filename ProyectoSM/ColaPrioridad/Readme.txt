SISTEMA DE SUPERMERCADO - PROYECTO FINAL Algoritmos y Procesos II
Pamela Reynoso- noviembre 2025 LENGUAJE: Java
	QUE HACE ESTE PROYECTO
Este proyecto es un sistema para manejar un supermercado. Tiene dos partes:
•	INVENTARIO: Guarda los productos usando un Árbol AVL
o	Permite agregar, buscar, modificar y eliminar productos
o	Los productos se ordenan automáticamente por código
•	ATENCION DE CLIENTES: Usa una Cola de Prioridad
o	Los clientes VIP se atienden primero
o	Luego los adultos mayores
o	Al final los clientes normales
	ARCHIVOS DEL PROYECTO
Son 7 archivos en total:
1.	Producto.java - Guarda los datos de cada producto
2.	NodoAVL.java - Un nodo del árbol (tiene producto e hijos)
3.	ArbolAVL.java - El árbol completo con las rotaciones
4.	Cliente.java - Datos de los clientes
5.	ColaPrioridad.java - La cola que ordena por prioridad
6.	Supermercado.java - Conecta todo
7.	Menu.java - El menú que ve el usuario
	COMO COMPILAR Y EJECUTAR
PASO 1: Verifica que tengas Java
Abre la terminal y escribe: java -version
Si no tienes Java, descárgalo de: https://www.oracle.com/java/technologies/downloads/
PASO 2: Pon todos los archivos en una carpeta
Crea una carpeta llamada "SupermercadoAVL" y mete los 7 archivos .java
PASO 3: Abre la terminal en esa carpeta
•	En Windows: Abre la carpeta, escribe "cmd" en la barra de arriba
•	En VS Code: Click derecho → Open in Terminal
PASO 4: Compila los archivos
Escribe esto en la terminal:
javac *.java
Esto crea los archivos .class (que son los que Java puede ejecutar)
PASO 5: Ejecuta el programa
Escribe:
java Menu
Y listo! El programa debería empezar.
	COMO USAR EL PROGRAMA
Cuando ejecutes el programa verás un menú con 11 opciones:
OPCIONES DEL INVENTARIO:
1.	Insertar producto
o	Te pide código (ejemplo: PROD006)
o	Nombre (ejemplo: Galletas)
o	Categoría (ejemplo: Snacks)
o	Precio en quetzales (ejemplo: 25.50)
o	Stock (ejemplo: 100)
2.	Buscar producto
o	Busca un producto por su código
o	Te muestra todos sus datos
3.	Actualizar producto
o	Cambiar el precio, stock, nombre, etc.
o	Solo escribe lo que quieres cambiar
4.	Eliminar producto
o	Borra un producto del inventario
o	Te pide confirmar antes de borrar
5.	Ver inventario
o	Muestra TODOS los productos ordenados
OPCIONES DE CLIENTES:
6.	Agregar cliente
o	Pones el nombre del cliente
o	Eliges si es VIP, adulto mayor o regular
o	Dices cuántos productos va a comprar
7.	Atender cliente
o	Atiende al siguiente cliente según su prioridad
8.	Ver cola
o	Muestra quiénes están esperando
9.	Atender a todos
o	Atiende a todos los clientes de la cola
10.	Estadísticas
o	Muestra cuántos productos hay
o	Cuántos clientes se han atendido
o	Etc.
11.	Salir
o	Cierra el programa
	PRODUCTOS DE EJEMPLO
El programa ya trae 5 productos para probar:
•	PROD001: Aceite de oliva - Q85.50
•	PROD002: Leche entera - Q22.00
•	PROD003: Pan integral - Q15.50
•	PROD004: Arroz blanco 1kg - Q28.75
•	PROD005: Café molido - Q65.00
Estos se cargan solos cuando inicia el programa.
PROBLEMA: Sale un montón de errores al compilar
SOLUCION: Revisa que copiaste bien el código en cada archivo.
PROBLEMA: No puedo escribir en la terminal
SOLUCION: Asegúrate de estar ejecutando desde la terminal, no con doble click.
	LO QUE APRENDÍ
	ÁRBOL AVL:
Es un árbol que siempre se mantiene balanceado. Cuando agregas o quitas algo, el árbol se "acomoda solo" usando rotaciones. Esto hace que buscar sea rápido.
COLA DE PRIORIDAD:
Es como la fila del banco, pero los VIP pasan primero. Se implementa con un "heap" que es como un árbol especial donde el más importante siempre está arriba.
ROTACIONES:
Son como "mover" los nodos del árbol para que quede balanceado. Hay 4 tipos:
•	Rotación derecha
•	Rotación izquierda
•	Doble derecha-izquierda
•	Doble izquierda-derecha
COMPLEJIDAD:
Insertar, buscar y eliminar en el árbol AVL es O(log n), que significa que es muy rápido incluso con muchos productos.
	NOTAS IMPORTANTES
•	Los precios están en Quetzales (Q)
•	El árbol se balancea automáticamente
•	La cola respeta las prioridades
•	Si dos clientes tienen la misma prioridad, se atiende al que llegó primero
•	No usé librerías externas para el árbol ni la cola (lo hice desde cero)

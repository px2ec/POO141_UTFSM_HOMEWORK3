Tarea 3: Applet para Simulaci�n Bolas, Puntos fijos, Resortes y Osciladores como Objetos de Software
=============

Archivos de la tarea
-------------
En este repositorio se encuentra todo el c�digo fuente para visualizar un Applet que Simula Bolas, Resortes, Osciladores, entre otros, en un mundo simple. Se debe descargar, compilar utilizando _make_, y correr abriendo el archivo **PhyisicsLab.html** en un navegador o con _appletviewer_.

Compilaci�n y Ejecuci�n
-------------
Para compilar correctamente, se debe tener previamente instalado en el computador la librer�a de gr�ficos **JFreeChart**. Instrucciones para su instalaci�n se encuentran en el Anexo A.

Para compilar, se debe situar el terminal en la carpeta de la tarea y luego ejecutar *make all*. Con esto se compilar�n todos los archivos y quedar� listo para su ejecuci�n.

El archivo **PhyisicsLab.html** contiene el applet de la tarea, por lo que para ejecutar el applet se debe abrir este archivo con un navegador que soporte Applets y que cuente los permisos correspondientes para el correcto funcionamiento. O bien se puede realizar una prueba, ejecutando el comando *make runApplet*, el cual abrir� el archivo utilizando el programa _appletviewer_.

Anexo A: Instalaci�n JFreeChart
-------------
1.  Se descarga desde esta p�gina: http://www.jfree.org/jfreechart/download.html.
2.  Se descomprime
3.  De la carpeta lib, copiar jfreechart-\<versi�n>.jar y jcommon-\<versi�n>.jar en la carpeta de extensiones de la librer�a de Java:
    *   Mac OS X: /library/java/extensions
    *   Linux: /usr/java/packages/lib/ext
    *   m�s info: http://docs.oracle.com/javase/tutorial/ext/basics/install.html
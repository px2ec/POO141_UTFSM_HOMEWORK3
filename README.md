Tarea 3: Applet para Simulación Bolas, Puntos fijos, Resortes y Osciladores como Objetos de Software
=============

Archivos de la tarea
-------------
En este repositorio se encuentra todo el código fuente para visualizar un Applet que Simula Bolas, Resortes, Osciladores, entre otros, en un mundo simple. Se debe descargar, compilar utilizando _make_, y correr abriendo el archivo **PhyisicsLab.html** en un navegador o con _appletviewer_.

Compilación y Ejecución
-------------
Para compilar correctamente, se debe tener previamente instalado en el computador la librería de gráficos **JFreeChart**. Instrucciones para su instalación se encuentran en el Anexo A.

Para compilar, se debe situar el terminal en la carpeta de la tarea y luego ejecutar *make all*. Con esto se compilarán todos los archivos y quedará listo para su ejecución.

El archivo **PhyisicsLab.html** contiene el applet de la tarea, por lo que para ejecutar el applet se debe abrir este archivo con un navegador que soporte Applets y que cuente los permisos correspondientes para el correcto funcionamiento. O bien se puede realizar una prueba, ejecutando el comando *make runApplet*, el cual abrirá el archivo utilizando el programa _appletviewer_.

Anexo A: Instalación JFreeChart
-------------
1.  Se descarga desde esta página: http://www.jfree.org/jfreechart/download.html.
2.  Se descomprime
3.  De la carpeta lib, copiar jfreechart-\<versión>.jar y jcommon-\<versión>.jar en la carpeta de extensiones de la librería de Java:
    *   Mac OS X: /library/java/extensions
    *   Linux: /usr/java/packages/lib/ext
    *   más info: http://docs.oracle.com/javase/tutorial/ext/basics/install.html
El proyecto está dividido en dos actividades principales, ya que no hay vistas más complejas.

La primera actividad solo se encarga de hacer la petición a la api mediante el servicio Retrofit, recuperar los datos y mostrar una vista reciclada con 10 usuarios distintos.
Uno de los problemas que he tenido ha sido el scroll infinito con paginación, nunca lo he realizado y aunque he probado varias opciones no funcionaban correctamente así que he preferido dejar solo los diez primeros usuarios que devolviera la APi y hacer la aplicación estable.
La vista que muestra la lista de usuarios también hace uso de la librería Glide para poder mostrar la imagen que la API nos devuelve como "foto de perfil" del usuario.

La segunda actividad solo se encarga de mostrar más detalles sobre el usuario seleccionado.
Esta actividad también hace uso de la librería Glide para volver a mostrar la imagen que devuelve la API.
Además la actividad también hace uso de la librería de Google para mostrar el mapa interactivo con la localización del usuario que nos devuelve la API.

Como el objetivo de la segunda actividad es mostrar deralles sobre el usuario seleccionado y hay bastantes más datos que no se utilizan, he creído recomendable mandar todos los datos del usuario de una actividad a otra.
De esta forma, si alguna vez se quisiera aumentar la cantidad de información mostrada solamente se tendría que añadir una nueva vista en el RecyclerView creado, ya que esta actividad tiene todo lo devuelto por la API.
Para poder enviar los datos complejos de una actividad a otra he visto factible cambiar esos datos a formato Json para poder enviarlos como una simple cadena y luego volver a recuperarlos de la cadena en la otra actividad. De esta forma también nos ahorramos el tener que
mandar atributo por atributo todos los datos que se quieren mostrar en la segunda actividad (los cuales tendrían que aumentar conforme la segunda actividad quisiera escalar y mostrar más datos).

Un problema que he tenido ha sido mostrar la imagen de los usuario de forma circular. He intentado utilizar shapes y layer-list para superponer un circulo sobre la imagen y modificarla de esa forma pero no he conseguido que se vea bien.
El último problema ha sido implementar la búsqueda y el filtro por personas o email que no he podido completar por falta de tiempo.

Lo más curioso del proyecto ha sido aprender a utilizar la API de Google para mostrar el mapa y poder interactuar con él, nunca lo había implementado y ha sido bonito descubrirlo.

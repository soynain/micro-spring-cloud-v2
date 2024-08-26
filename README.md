Actualizando el readme de este repo con caps.

Hola, producto de una entrevista de trabajo, me he decidido a volver a recordar mis
skills en java que no he podido refinar en alguito de tiempo por haberme enfocado en php :C

Resumen de avances, una práctica de microservicios tratando de usar lo mas posible de spring cloud,
hasta ahora 20-08-2024 tengo 3 servicios: usuario, tarea y el gateway mismo.

![image](https://github.com/user-attachments/assets/906dc10c-378b-420c-9d04-8787c62e0782)
![image](https://github.com/user-attachments/assets/efae2a4b-8c5a-4d48-ab98-d22eb8d79d9c)


como se puede observar en la imagen, las peticiones se enrutan por gateway. No se necesita eureka a fuerzas para
indicar donde se hostean, cloud te da sa flexibilidad.

Avances domingo 25/08
La autenticación pude implementarla, realmente si me confudi pór los approachs programáticos, ya que el yaml te lo puede 
simplificar si usas yaml. La complejidad se desborda si quieres mas personalizaciones.

El microservicio si me detecta las rutas y las valida con el token como cookie:
![image](https://github.com/user-attachments/assets/67c3ee91-de89-4fb8-9d0d-37914279d285)

![image](https://github.com/user-attachments/assets/b652c7e3-012e-4e72-924d-a9e6e770b550)


Falta circuit breaker, que faltqa mejorar sobre estos conceptos que absorvo de aqui?
-Spring webflux (algo que voy descubriendo).
-Pudiera refinar el bcrypt, al estar abrumado por la documentacion solo queria setear la auth con contraseña sin proteccion
-Saber las diferencias de un resource server y authorization server, eso lo haré en otra práctica.
-Los eventos (rabbitmq) ya los tengo en otras mini practicas.
-Async java (FUCKING HILOS)

En general si pasa algo, puedo medio defenderme en una chamba, y adaptarlo por medio de marcos sobre lo que
otros hayan hecho internamente en la empresa.

Falta darle

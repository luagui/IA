;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Module MAIN

;--INFORMACION USUARIO--

(deftemplate usuario
    (slot nombre)

    (slot edad
        (type INTEGER)
        (default 20))

    (slot añoMovil
        (type INTEGER)
        (default 2017))
    
    (slot sexo
        (type SYMBOL)
        (allowed-symbols m h))
    
    ;--Para saber si ha pagado alguna vez--

    (slot haPagado 
        (type SYMBOL)
        (default n)
        (allowed-symbols s n))

    (slot espacioDisponible 
        (type INTEGER))

    (multislot aficiones
        (type SYMBOL)
        (allowed-symbols juegos belleza fotografia herramientas deportes compras musica citas)
    )
)

(deftemplate usuarioProtrotipo
    (slot nombre)

    (slot tipo
        (type SYMBOL)
        (allowed-symbols joven jovenYPaga adulto medio))
    
    (slot sexo
        (type SYMBOL)
        (allowed-symbols m h))

    (slot versionAndroid
        (type FLOAT))
    
    ;--Como la app más pesada son 100M, vamos a suponer que tiene espacio es si tiene más de 50 megas--

    (slot tieneEspacio 
        (type SYMBOL)
        (allowed-symbols s n))  
		
	;--guardamos los gustos en el prototipo--

	(multislot aficiones
        (type SYMBOL)
        (allowed-symbols juegos fotografia herramientas deportes compras musica citas))
)

(deftemplate app
    (slot nombre)

    (multislot categoria
        (type SYMBOL)
        (allowed-symbols juegos belleza fotografia herramientas deportes compras musica citas social)
    )

    (slot nota
        (type FLOAT))
    
    (slot numReviews
        (type INTEGER))

    (slot contentRating
        (type SYMBOL)
        (allowed-symbols everyone everyone10 mature17 teen))
    
    ;--En MB--
    (slot tamano
        (type INTEGER))

    ;-- 0 = Gratis--

    (slot precio
        (type FLOAT)
        (default 0.0))
    
    ;--Por comodidad, usaremos float. El valor indica la version minima necesaria

    (slot versionAndroid
        (type FLOAT))    
)

;Reorganizamos las características de las apps
;(deftemplate CaracApp
;    (slot nombre)
;
;    (multislot categoria
;        (type SYMBOL)
;       (allowed-symbols juegos belleza fotografia herramientas deportes compras musica citas social)
;   )
;
;    (slot bienValorada
;        (type SYMBOL))
;
;    (slot contentRating
;        (type SYMBOL)
;        (allowed-symbols everyone everyone10 mature17 teen))
;   
;    (slot grandePeq
;        (type SYMBOL)
;		(allowed-symbols grande peq))
;
;    (slot barataCara
;        (type SYMBOL)
;        (default 0.0))
;    
;    ;--Por comodidad, usaremos float. El valor indica la version minima necesaria
;
;    (slot versionAndroid
;        (type FLOAT))    
;)

(deftemplate conviene
	(slot nombre)
	(multislot tipos) ;-- herramientas, deporte,...  depende de gustos y sexo (deportes, belleza,...)
	(slot ligera) ;-> si tiene espacio s
	(slot gratis) ;-> tipo joven, medio o adulto
	(slot nueva) ;-> depende de la versiond de android 
)

;--INFORMACION PUNTUACION--

(deftemplate puntuacion
	(slot nombreApp)

	(slot puntos
		(type FLOAT)
		(default 0.0))
)

;Contador para mostrar solo los N mejores resultados
(deftemplate contadorMostrados
    (slot contador 
        (type INTEGER)
        (default 0)
    )
)

;se mete el contador como hecho inicializado a 0
(deffacts contador
    (contadorMostrados (contador 0))
)



;--Clasificamos el tipo de usuario según el contenido de la memoria.
;-- Joven = menor de 18 años, no ha pagado por ninguna app. Solo recomenda everyone, everyone10, teen
;-- Medio = mayor de 18 años, pero menor de 35, no ha pagado por ninguna app
;-- Adulto = mayor de 35 años,gustos como herramientas, deportes, compras. Puede haber pagado o no por apps, pero consideramos que su economia es estable y podemos ofrecerle apps de pago

;-- Habria que crear tal vez algun usuario más por si no se engloba en ninguno de estos grupos


(deffacts appps
    (app (nombre Facebook) (categoria social) (nota 4.1) (numReviews 78128208) (contentRating teen ) (tamano 60) (precio 0.0) (versionAndroid 4.1))
    (app (nombre Instagram) (categoria social fotografia) (nota 4.5) (numReviews 66509917) (contentRating teen) (tamano 34) (precio 0.0) (versionAndroid 4.1))
    (app (nombre GoogleNews) (categoria herramientas) (nota 3.9) (numReviews 877643) (contentRating teen) (tamano 13) (precio 0.0) (versionAndroid 4.4))
    (app (nombre Minecraft) (categoria juegos) (nota 4.5) (numReviews 2376564) (contentRating everyone10) (tamano 55) (precio 6.99) (versionAndroid 4.1))
    (app (nombre Mahjong) (categoria juegos) (nota 4.5) (numReviews 33983) (contentRating everyone) (tamano 22) (precio 0.0) (versionAndroid 4.0))
    (app (nombre DrBatery) (categoria herramientas) (nota 4.5) (numReviews 101738) (contentRating everyone) (tamano 10) (precio 0.0) (versionAndroid 4.2))
    (app (nombre Tinder) (categoria social citas) (nota 4.0) (numReviews 3106611) (contentRating mature17) (tamano 24) (precio 0.0) (versionAndroid 4.4))
    (app (nombre Spotify) (categoria musica social) (nota 4.2) (numReviews 12347621) (contentRating everyone) (tamano 36) (precio 0.0) (versionAndroid 4.0))
    (app (nombre 8BallPool) (categoria deportes) (nota 4.5) (numReviews 14184910) (contentRating everyone) (tamano 52) (precio 0.0) (versionAndroid 4.0))
    (app (nombre PicsArtPhoto) (categoria social fotografia) (nota 4.5) (numReviews 7594559) (contentRating teen) (tamano 34) (precio 0.0) (versionAndroid 4.0))
    (app (nombre Groupon) (categoria compras) (nota 4.6) (numReviews 1371082) (contentRating teen) (tamano 46) (precio 0.0) (versionAndroid 4.2))
    (app (nombre WeatherRadar) (categoria herramientas) (nota 4.5) (numReviews 25243) (contentRating everyone) (tamano 26) (precio 2.99) (versionAndroid 4.4))
    (app (nombre Facetune) (categoria fotografia social) (nota 4.4) (numReviews 49553) (contentRating everyone) (tamano 48) (precio 5.99) (versionAndroid 4.1))
	(app (nombre UltraBeauty) (categoria belleza) (nota 4.7) (numReviews 42050) (contentRating everyone) (tamano 48) (precio 0.0) (versionAndroid 4.1))

     ;-- apps con poca relevancia

    (app (nombre FreeDatingApp) (categoria citas social) (nota 4.4) (numReviews 667) (contentRating mature17) (tamano 27) (precio 0.0) (versionAndroid 4.1))
    (app (nombre IAmRich) (categoria social) (nota 3.8) (numReviews 718) (contentRating everyone) (tamano 26) (precio 399.99) (versionAndroid 4.4))
)

;-- En principio vamos a trabajar con un usuario solo, para asi no tener que tener asserts puntuacion por cada user
(deffacts usuarios
    ;(usuario (nombre Luis) (edad 15) (añoMovil 2016) (sexo h) (haPagado s) (espacioDisponible 55) (aficiones fotografia deportes musica))
    (usuario (nombre Andrea) (edad 24) (añoMovil 2015) (sexo m) (haPagado n) (espacioDisponible 70) (aficiones citas compras herramientas))
    ;(usuario (nombre Jose) (edad 55) (añoMovil 2018) (sexo h) (haPagado s) (espacioDisponible 120) (aficiones juegos musica herramientas))
)

(deffunction calcularVersion (?year) 
    (if (eq ?year 2011) then
		4.0
	else (if (eq ?year 2012) then
        4.1
        else (if (eq ?year 2013) then
            4.2
            else (if (eq ?year 2014) then
                4.3
                else (if (eq ?year 2015) then
                    4.4
                    else (if (eq ?year 2016) then
                        5.0
                        else (if (eq ?year 2017) then
                            5.1
                            else (if (eq ?year 2018) then
                                5.2
                            else 
                                3.0)
                            )
                        )
                    )
                )
            )
        )
    )
)

(deffunction espacioGrande (?espacioDisponible)
    (if (>= ?espacioDisponible 50) then 
        s
    else 
        n)
)

(deffunction asignaPuntos(?sexo ?espacio ?nota ?numReviews ?tamApp ?catApp1 ?catApp2 $?aficiones )

    ;--Normalizamos el numero de reviews con el numero de reviews de facebook, que es la que más tiene de la store. 
	;--Esto nos permite ponderar la nota de la app
    (bind ?puntos (* ?nota (/ ?numReviews 78128208)))


    (if (eq ?espacio si) then
        (bind ?puntos (+ ?puntos 1.0))
    else 
        (if (< ?tamApp 50) then
            (bind ?puntos (- ?puntos 0.5))
        else 
            (bind ?puntos (+ ?puntos 1.0))
        )
    )

    (if (neq (member$ ?catApp1 $?aficiones) FALSE) then
        (bind ?puntos (+ ?puntos 3.0))
    else 
        (bind ?puntos (+ ?puntos 0.0))
    )
    
    ;Las apps como mucho tienen 2 categorias. Si la segunda categoria es no vacia, la comprobamos

    (if (neq ?catApp2 (create$ )) then
        (if (neq (member$ catApp2 $?aficiones) FALSE) then
            (bind ?puntos (+ ?puntos 3.0))
         else 
            (bind ?puntos (+ ?puntos 0.0))
        )
    else 
        (bind ?puntos (+ ?puntos 0.0))
    )

    ;--Ademas, queremos mostrar generalmente las sociales, ya que son las mas usadas

    (if (eq ?catApp1 social) then
        (bind ?puntos (+ ?puntos 1.0))
    else 
         (bind ?puntos (+ ?puntos 0.0))
    )

    (if (eq ?catApp2 social) then
        (bind ?puntos (+ ?puntos 1.0))
    else 
         (bind ?puntos (+ ?puntos 0.0))
    )

  
	
	;-- si es mujer daremos puntos a las aplicaciones de belleza 
	;-- si es hombre daremos puntos a las aplicaciones de deporte
	(if (eq ?sexo m) then
		(if (eq ?catApp1 belleza) then
			(bind ?puntos (+ ?puntos 0.5))
		else 
            (bind ?puntos (+ ?puntos 0.0))
		)
    else 
		(if (eq ?catApp1 deporte) then
            (bind ?puntos (+ ?puntos 0.5))
        else 
            (bind ?puntos (+ ?puntos 0.0))
        )
    )
	
	(if (eq ?sexo m) then
		(if (eq ?catApp2 belleza) then
			(bind ?puntos (+ ?puntos 0.5))
		else 
            (bind ?puntos (+ ?puntos 0.0))
		)
    else 
		(if (eq ?catApp2 deporte) then
            (bind ?puntos (+ ?puntos 0.5))
        else 
            (bind ?puntos (+ ?puntos 0.0))
        )
    )
	
)



(defrule joven
    (usuario (nombre ?nombre) (edad ?edad) (añoMovil ?añoMovil) (sexo ?s) (haPagado n) (espacioDisponible ?espacioDisponible) (aficiones $?afi))
    (test(<= ?edad 17))
    => 
    (assert(usuarioProtrotipo (nombre ?nombre) (tipo joven) (sexo ?s) (versionAndroid (calcularVersion ?añoMovil)) (tieneEspacio (espacioGrande ?espacioDisponible)) (aficiones $?afi)))
)

;-----------------------------------------------------------------------------------------------------------------------------------------------

(defrule jovenYPaga
    (usuario (nombre ?nombre) (edad ?edad) (añoMovil ?añoMovil) (sexo ?s) (haPagado s) (espacioDisponible ?espacioDisponible) (aficiones $?afi))
    (test(<= ?edad 17))
    => 
    (assert(usuarioProtrotipo (nombre ?nombre) (tipo jovenYPaga) (sexo ?s) (versionAndroid (calcularVersion ?añoMovil)) (tieneEspacio (espacioGrande ?espacioDisponible)) (aficiones $?afi)))
)

;-----------------------------------------------------------------------------------------------------------------------------------------------

(defrule medio
    (usuario (nombre ?nombre) (edad ?edad) (añoMovil ?añoMovil) (sexo ?s) (haPagado n) (espacioDisponible ?espacioDisponible) (aficiones $?afi))
    (test(> ?edad 17))
    (test(<= ?edad 35))
    => 
    (assert(usuarioProtrotipo (nombre ?nombre) (tipo medio) (sexo ?s) (versionAndroid (calcularVersion ?añoMovil)) (tieneEspacio (espacioGrande ?espacioDisponible)) (aficiones $?afi)))
)
;-----------------------------------------------------------------------------------------------------------------------------------------------
(defrule adulto
    (usuario (nombre ?nombre) (edad ?edad) (añoMovil ?añoMovil) (sexo ?s) (haPagado ?pay) (espacioDisponible ?espacioDisponible) (aficiones $?afi))
    (test(> ?edad 35))
    => 
    (assert(usuarioProtrotipo (nombre ?nombre) (tipo adulto) (sexo ?s) (versionAndroid (calcularVersion ?añoMovil)) (tieneEspacio (espacioGrande ?espacioDisponible)) (aficiones $?afi)))
)
;-----------------------------------------------------------------------------------------------------------------------------------------------

;--Una vez clasificados los usuarios en usuarios prototipos vamos a valorar cada app

;-++++++++++++++++++++++++++++++++++++++++++++++++++++USERJOVEN++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


(defrule userJoven
    (usuarioProtrotipo (nombre ?nombre) (tipo joven) (sexo ?s) (versionAndroid ?v) (tieneEspacio ?e) (aficiones $?afi))
    (app (nombre ?nombreApp) (categoria $?catApp) (nota ?nApp) (numReviews ?rApp) (contentRating ?c&:(member$ ?c (create$ everyone everyone10 teen))) (tamano ?tam) (precio 0.0) (versionAndroid ?vApp&:(<= ?vApp ?v)))
    =>
    (assert(puntuacion (nombreApp ?nombreApp) (puntos (asignaPuntos ?s ?e ?nApp ?rApp ?tam (first$ $?catApp) (rest$ $?catApp) $?afi)))) ;--El rest de una lista de un elemento devuelve lista vacia, creo--
)

;-+++++++++++++++++++++++++++++++++++++++++++++++++++++USERJOVENQUEPAGA+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

(defrule userJovenQuePaga
    (usuarioProtrotipo (nombre ?nombre) (tipo jovenYPaga) (sexo ?s) (versionAndroid ?v) (tieneEspacio ?e) (aficiones $?afi))
    (app (nombre ?nombreApp) (categoria $?catApp) (nota ?nApp) (numReviews ?rApp) (contentRating ?c&:(member$ ?c (create$ everyone everyone10 teen))) (tamano ?tam) (precio ?preci) (versionAndroid ?vApp&:(<= ?vApp ?v)))
    =>
    (assert(puntuacion (nombreApp ?nombreApp) (puntos (asignaPuntos ?s ?e ?nApp ?rApp ?tam (first$ $?catApp) (rest$ $?catApp) $?afi)))) ;--El rest de una lista de un elemento devuelve lista vacia, creo--
)

;-+++++++++++++++++++++++++++++++++++++++++++++++++++++USERMEDIO+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

(defrule userMedio
    (usuarioProtrotipo (nombre ?nombre) (tipo medio) (sexo ?s) (versionAndroid ?v) (tieneEspacio ?e) (aficiones $?afi))
    (app (nombre ?nombreApp) (categoria $?catApp) (nota ?nApp) (numReviews ?rApp) (contentRating ?c&:(member$ ?c (create$ everyone everyone10 mature17 teen))) (tamano ?tam) (precio 0.0) (versionAndroid ?vApp&:(<= ?vApp ?v)))
    =>
    (assert(puntuacion (nombreApp ?nombreApp) (puntos (asignaPuntos ?s ?e ?nApp ?rApp ?tam (first$ $?catApp) (rest$ $?catApp) $?afi)))) ;--El rest de una lista de un elemento devuelve lista vacia, creo--
)


;-+++++++++++++++++++++++++++++++++++++++++++++++++++++USERADULTO+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

(defrule userAdulto
    (usuarioProtrotipo (nombre ?nombre) (tipo adulto) (sexo ?s) (versionAndroid ?v) (tieneEspacio ?e) (aficiones $?afi))
    (app (nombre ?nombreApp) (categoria $?catApp) (nota ?nApp) (numReviews ?rApp) (contentRating ?c&:(member$ ?c (create$ everyone everyone10 mature17 teen))) (tamano ?tam) (precio ?preci) (versionAndroid ?vApp&:(<= ?vApp ?v)))
    =>
    (assert(puntuacion (nombreApp ?nombreApp) (puntos (asignaPuntos ?s ?e ?nApp ?rApp ?tam (first$ $?catApp) (rest$ $?catApp) $?afi)))) ;--El rest de una lista de un elemento devuelve lista vacia, creo--
)

;-++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


(defrule ordenaMuestra
  ?r1 <- (puntuacion (nombreApp ?nombreApp1) (puntos ?p1))
  (not (puntuacion (nombreApp ?nombreApp2) (puntos ?p2&:(< ?p1 ?p2))))
  (app (nombre ?nombreApp1) (categoria $?catApp) (nota ?nApp) (numReviews ?rApp) (contentRating ?c) (tamano ?tam) (precio ?p) (versionAndroid ?vApp))
  ?varCont <- (contadorMostrados (contador ?cont))
  (test (< ?cont 100))
  =>
  
  (modify ?varCont (contador (+ ?cont 1)))
  (printout t crlf)
  (printout t "La app recomendado es : " ?nombreApp1 ". Cuya puntuacion es: "  ?p1 crlf)
  (printout t "Es una app " (first$ $?catApp) " y su precio es de " ?p " euros." crlf)
  (printout t "Esta app ocupa " ?tam " MB. Y esta disponible a partir de la version " ?vApp crlf)
  (retract ?r1))











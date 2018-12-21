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
        (allowed-symbols juegos fotografia herramientas deportes compras musica citas)
    )
)

(deftemplate usuarioProtrotipo
    (slot nombre)

    (slot tipo
        (type SYMBOL)
        (allowed-symbols joven adulto medio))
    
    (slot sexo
        (type SYMBOL)
        (allowed-symbols m h))

    (slot versionAndroid
        (type FLOAT))
    
    ;--Como la app más pesada son 100M, vamos a suponer que tiene espacio es si tiene más de 50 megas--

    (slot tieneEspacio 
        (type SYMBOL)
        (allowed-symbols s n))  
)

(deftemplate app
    (slot nombre)

    (multislot categoria
        (type SYMBOL)
        (allowed-symbols juegos fotografia herramientas deportes compras musica citas social)
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

;--Clasificamos el tipo de usuario según el contenido de la memoria.
;-- Joven = menor de 18 años, no ha pagado por ninguna app, gustos como social, jugar, deporte, musica, fotografia. Solo recomenda everyone, everyone10, teen
;-- Medio = mayor de 18 años, pero menor de 35, no ha pagado por ninguna app, gustos como social, citas, musica, compras.
;-- Adulto = mayor de 35 años,gustos como herramientas, deportes, compras. Puede haber pagado o no por apps, pero consideramos que su economia es estable y podemos ofrecerle apps de pago

;-- Habria que crear tal vez algun usuario más por si no se engloba en ninguno de estos grupos


(deffacts appps
    (app (nombre Facebook) (categoria social) (nota 4.1) (numReviews 78128208) (contentRating teen ) (tamano 60) (precio 0.0 ) (versionAndroid 4.1))
    (app (nombre Instagram) (categoria social fotografia) (nota 4.5) (numReviews 66509917) (contentRating teen) (tamano 34) (precio 0.0) (versionAndroid 4.1))
    (app (nombre GoogleNews) (categoria herramientas) (nota 3.9) (numReviews 877643) (contentRating teen) (tamano 13) (precio 0.0) (versionAndroid 4.4))
    (app (nombre Minecraft) (categoria juegos) (nota 4.5) (numReviews 2376564) (contentRating everyone10) (tamano 55) (precio 6.99) (versionAndroid 4.1))
    (app (nombre Mahjong) (categoria juegos) (nota 4.5) (numReviews 33983) (contentRating everyone) (tamano 22) (precio 0.0) (versionAndroid 4.0))
    (app (nombre DrBatery) (categoria herramientas) (nota 4.5) (numReviews 101738) (contentRating everyone) (tamano 10) (precio 0.0) (versionAndroid 4.2))
    (app (nombre Tinder) (categoria social citas) (nota 4.0) (numReviews 3106611) (contentRating mature17) (tamano 24) (precio 0.0) (versionAndroid 4.4))
    (app (nombre Spotify) (categoria musica social) (nota 4.2) (numReviews 12347621) (contentRating everyone) (tamano 36) (precio 0.0) (versionAndroid 4.0))
    (app (nombre 8BallPool) (categoria deportes) (nota 4.5) (numReviews 14184910) (contentRating everyone) (tamano 52) (precio 0.0) (versionAndroid 4.0))
    (app (nombre PicsArtPhoto) (categoria fotografia social) (nota 4.5) (numReviews 7594559) (contentRating teen) (tamano 34) (precio 0.0) (versionAndroid 4.0))
    (app (nombre Groupon) (categoria compras) (nota 4.6) (numReviews 1371082) (contentRating teen) (tamano 46) (precio 0.0) (versionAndroid 4.2))
    (app (nombre WeatherRadar) (categoria herramientas) (nota 4.5) (numReviews 25243) (contentRating everyone) (tamano 26) (precio 2.99) (versionAndroid 4.4))
    (app (nombre Facetune) (categoria fotografia social) (nota 4.4) (numReviews 49553) (contentRating everyone) (tamano 48) (precio 5.99) (versionAndroid 4.1))

     ;-- apps con poca relevancia

    (app (nombre FreeDatingApp) (categoria citas social) (nota 4.4) (numReviews 667) (contentRating mature17) (tamano 27) (precio 0.0) (versionAndroid 4.1))
    (app (nombre IAmRich) (categoria social) (nota 3.8) (numReviews 718) (contentRating everyone) (tamano 26) (precio 399.99) (versionAndroid 4.4))
)

(deffacts usuarios
    (usuario (nombre Luis) (edad 15) (añoMovil 2016) (sexo h) (haPagado n) (espacioDisponible 55) (aficiones juegos musica))
    (usuario (nombre Andrea) (edad 24) (añoMovil 2008) (sexo m) (haPagado n) (espacioDisponible 44) (aficiones citas herramientas))
    (usuario (nombre Jose) (edad 55) (añoMovil 2018) (sexo h) (haPagado s) (espacioDisponible 120) (aficiones juegos musica herramientas))
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

(defrule joven
    (usuario (nombre ?nombre) (edad ?edad) (añoMovil ?añoMovil) (sexo ?s) (haPagado n) (espacioDisponible ?espacioDisponible) (aficiones $?afi))
    (test(<= ?edad 17))

    ;-- Comprueba si los gustos del usuario son subconjunto de los gustos del perfil prototípico joven
    (test (subsetp $?afi (create$ social juegos deportes musica fotografia))) 
    => 
    (assert(usuarioProtrotipo (nombre ?nombre) (tipo joven) (sexo ?s) (versionAndroid (calcularVersion ?añoMovil)) (tieneEspacio (espacioGrande ?espacioDisponible))))
)

(defrule medio
    (usuario (nombre ?nombre) (edad ?edad) (añoMovil ?añoMovil) (sexo ?s) (haPagado n) (espacioDisponible ?espacioDisponible) (aficiones $?afi))
    (test(> ?edad 17))
    (test(<= ?edad 35))

    ;-- Comprueba si los gustos del usuario son subconjunto de los gustos del perfil prototípico medio
    (test (subsetp $?afi (create$ citas musica compras))) 
    => 
    (assert(usuarioProtrotipo (nombre ?nombre) (tipo medio) (sexo ?s) (versionAndroid (calcularVersion ?añoMovil)) (tieneEspacio (espacioGrande ?espacioDisponible))))
)

(defrule adulto
    (usuario (nombre ?nombre) (edad ?edad) (añoMovil ?añoMovil) (sexo ?s) (haPagado ?pay) (espacioDisponible ?espacioDisponible) (aficiones $?afi))
    (test(> ?edad 35))

    ;-- Comprueba si los gustos del usuario son subconjunto de los gustos del perfil prototípico joven
    (test (subsetp $?afi (create$ herramientas deportes compras))) 
    => 
    (assert(usuarioProtrotipo (nombre ?nombre) (tipo adulto) (sexo ?s) (versionAndroid (calcularVersion ?añoMovil)) (tieneEspacio (espacioGrande ?espacioDisponible))))
)

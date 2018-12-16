


(deftemplate producto
    (slot nombre)
    (slot tipo (type SYMBOL) (allowed-values F P N) (default N))
    (slot envuelto (type SYMBOL) (allowed-values S N) (default N))
    (slot volumen (type INTEGER) (range 0 200))
)

(deftemplate caja
    (slot tipo (type SYMBOL) (allowed-values F P N) (default N))
    (slot volumen (type INTEGER) (range 0 300) (default 300))
)


(deffacts ini
    (producto (nombre patatasFritas) (tipo F) (envuelto S) (volumen 5))
    (producto (nombre garrafaAgua) (tipo P) (envuelto S) (volumen 200))
    (producto (nombre cebolla) (tipo F) (envuelto N) (volumen 20))
    (producto (nombre sandia) (tipo P) (envuelto S) (volumen 150))
    (producto (nombre lata) (tipo N) (envuelto S) (volumen 150))
    (producto (nombre naranjas) (tipo F) (envuelto N) (volumen 150))
    (producto (nombre melon) (tipo P) (envuelto S) (volumen 200))

    (caja (tipo P) (volumen 300))
    (caja (tipo F) (volumen 300))
    (caja (tipo N) (volumen 300))
)


(defrule envolProd
    ?ind <- (producto (nombre ?nombre)  (tipo ?tipoProd) (envuelto N) (volumen ?volProd))
    =>
    (modify ?ind (envuelto S))
	(printout t "hemos1 envuelto el producto " ?nombre crlf ) )
)

/*esta se debe de ejecutar antes que cajaNueva*/
(defrule empaquetarCajaExistente
    ?indProd <- (producto (nombre ?nombre)  (tipo ?tipoProd) (envuelto S) (volumen ?volProd))
    ?indCaja <- (caja (tipo ?tipoCaja) (volumen ?volCaja))
    (test (eq ?tipoCaja ?tipoProd))
    (test (>= ?volCaja ?volProd))
    => 
    (modify ?indCaja (volumen (- ?volCaja ?volProd)))
	(retract ?indProd) /*quitamos el producto porque ya lo hemos empaquetado, se podria preguntar si el producto empaquetado*/
    (assert (Producto ?nombre empaquetado)))
	(printout t "hemos metido " ?nombre " y a la caja le queda  " (- ?volCaja ?volProd)  " volumen" crlf) )
)


(defrule cajaNueva
    (producto (nombre ?nombre)  (tipo ?tipoProd) (envuelto S) (volumen ?volProd))
    (caja (tipo ?tipoCaja) (volumen ?volCaja))
    (test (eq ?tipoCaja ?tipoProd))
    (test (< ?volCaja ?volProd))
    =>
    (assert (caja (tipo ?tipoCaja) (volumen 300)))
	(printout t "hemos creado una caja nueva de tipo " ?tipoCaja " crlf) )
)





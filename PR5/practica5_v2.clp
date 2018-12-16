

(deftemplate producto
    (slot nombre)
    (slot tipo (type SYMBOL) (allowed-values F P N) (default N))
    (slot envuelto (type SYMBOL) (allowed-values S N) (default N))
    (slot volumen (type INTEGER) (range 0 200))
	(slot empaquetado (type SYMBOL) (allowed-values S N) (default N))
	(slot idCaja (type INTEGER))
)

(deftemplate caja
	(slot id (type INTEGER))
    (slot tipo (type SYMBOL) (allowed-values F P N) (default N))
    (slot volumen (type INTEGER) (range 0 300) (default 300))
)

(deftemplate idDeCaja
	(slot idC (type INTEGER))
)

(deffacts ini
    (producto (nombre patatasFritas) (tipo F) (envuelto S) (volumen 5) (empaquetado N) (idCaja 0))
    (producto (nombre garrafaAgua) (tipo P) (envuelto S) (volumen 199)(empaquetado N) (idCaja 0))
    (producto (nombre cebolla) (tipo F) (envuelto N) (volumen 20) (empaquetado N) (idCaja 0))
    (producto (nombre sandia) (tipo P) (envuelto S) (volumen 150) (empaquetado N) (idCaja 0))
    (producto (nombre lata) (tipo N) (envuelto S) (volumen 150) (empaquetado N) (idCaja 0))
    (producto (nombre naranjas) (tipo F) (envuelto N) (volumen 150) (empaquetado N) (idCaja 0))
    (producto (nombre melon) (tipo P) (envuelto S) (volumen 200) (empaquetado N) (idCaja 0))

    (caja (id 1) (tipo P) (volumen 300))
    (caja (id 2) (tipo F) (volumen 300))
    (caja (id 3) (tipo N) (volumen 300))
	
	(idDeCaja (idC 4))
)


(defrule envolProd
	(declare (salience 15))
    ?ind <- (producto (nombre ?nombre)  (tipo ?tipoProd) (envuelto N) (volumen ?volProd) (empaquetado N) (idCaja ?id))
    =>
    (modify ?ind (envuelto S))
	(printout t "hemos1 envuelto el producto " ?nombre crlf ) )
)

/*esta se debe de ejecutar antes que cajaNueva*/
(defrule empaquetarCajaExistente
	(declare (salience 10))
    ?indProd <- (producto (nombre ?nombre)  (tipo ?tipoProd) (envuelto S) (volumen ?volProd) (empaquetado N) (idCaja ?idCajProd))
    ?indCaja <- (caja (id ?identCaja) (tipo ?tipoCaja) (volumen ?volCaja))
    (test (eq ?tipoCaja ?tipoProd))
    (test (>= ?volCaja ?volProd))
    => 
    (modify ?indCaja (volumen (- ?volCaja ?volProd)))
	(modify ?indProd (empaquetado S) (idCaja ?identCaja))
    (assert (Producto ?nombre empaquetado)))
	(printout t "hemos metido " ?nombre " y a la caja le queda  " (- ?volCaja ?volProd)  " volumen" crlf) )
)


(defrule cajaNueva
    (producto (nombre ?nombre)  (tipo ?tipoProd) (envuelto S) (volumen ?volProd) (empaquetado N) (idCaja ?idCajProd))
    (caja (id ?identCaja) (tipo ?tipoCaja) (volumen ?volCaja))
    (test (eq ?tipoCaja ?tipoProd))
    (test (< ?volCaja ?volProd))
	?indCont <- (idDeCaja (idC ?cont))
    =>
    (assert (caja (id ?cont) (tipo ?tipoCaja) (volumen 300)))
	(modify ?indCont (idC (+ ?cont 1)))
	(printout t "hemos creado una caja nueva de tipo " ?tipoCaja  crlf)
)


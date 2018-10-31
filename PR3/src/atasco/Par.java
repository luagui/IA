package atasco;

public class Par<A,B> {

	/**
	 * Primer elemento del par.
	 */
	private A primero;

	/**
	 * Segundo elemento del par.
	 */
	private B segundo;

	/**
	 * Construye un par.
	 */
	public Par(A primero, B segundo) {
		this.primero = primero;
		this.segundo = segundo;
	}

	/**
	 * Proporciona el primer elemento.
	 *  */
	public A daPrimero() {
		return primero;
	}

	/**
	 * Proporciona el segundo elemento.
	 */
	public B daSegundo() {
		return segundo;
	}

}

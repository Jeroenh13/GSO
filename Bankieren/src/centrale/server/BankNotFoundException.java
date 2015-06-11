/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centrale.server;

/**
 *
 * @author Cypher
 */
public class BankNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1440942789246305013L;

	public BankNotFoundException(String message) {
		super(message);
	}
}

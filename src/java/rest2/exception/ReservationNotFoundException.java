/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest2.exception;

/**
 *
 * @author Dennnis
 */
public class ReservationNotFoundException extends Exception {
    
    public ReservationNotFoundException(String message) {
        super(message);
        
    }
}

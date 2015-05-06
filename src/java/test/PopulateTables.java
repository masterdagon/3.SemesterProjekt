/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import facade.Facade;

/**
 *
 * @author Muggi
 */
public class PopulateTables {
    
    static Facade f = new Facade();
    
    public static void main(String[] args) {
        f.createCityInfo("Kokkedal", 2980);
        f.createAirport("CPH", "Copenhagen","Denmark");
        f.createPlane("Airbus A350", 253);
        f.createCustomer("Martin", "Rasmussen", "Egedalsvænge", "Denmark", 2980);
    }
    
}

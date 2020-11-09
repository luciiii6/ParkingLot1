/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot1.ejb;

import com.park.parkinglot1.common.CarDetails;
import com.park.parkinglot1.common.UserDetails;
import com.park.parkinglot1.entity.Car;
import com.park.parkinglot1.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lucis
 */
@Stateless
public class CarBean {

    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());

    @PersistenceContext
    private EntityManager em;
    
    public List<CarDetails> getAllCars(){
        LOG.info("getAllCars");
        
        try {
            
            List<Car> cars = (List<Car>) em.createQuery("SELECT c FROM Car c").getResultList();
            return copyCarsToDetails(cars);
        } catch (Exception ex) {
           throw new EJBException(ex);
        }
    }
    
    public List<CarDetails> copyCarsToDetails(List<Car> cars){
        List<CarDetails> detailsList = new ArrayList<>();
        for(Car car : cars){
            CarDetails carDetails = new CarDetails(car.getId(),
                                car.getLicensePlate(),
                                car.getParkingSpot(),
                                car.getUser().getUsername());
            detailsList.add(carDetails);
        }
        
        return detailsList;
        
    }
    
    
    public List<UserDetails> getAllUsers(){
        LOG.info("getAllUsers");
        
        try {
            
            List<User> users = (List<User>) em.createQuery("SELECT u FROM Car u").getResultList();
            return copyUsersToDetails(users);
        } catch (Exception ex) {
           throw new EJBException(ex);
        }
    }
    
    public List<UserDetails> copyUsersToDetails(List<User> users){
        List<UserDetails> detailsList = new ArrayList<>();
        for(User user : users){
            UserDetails userDetails = new UserDetails(user.getId(),
                                user.getUsername(),
                                user.getEmail(),
                                user.getPosition());
            detailsList.add(userDetails);
        }
        
        return detailsList;
        
    }
        
 }
 

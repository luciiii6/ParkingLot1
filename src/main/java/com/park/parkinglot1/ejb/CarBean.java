/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot1.ejb;

import com.park.parkinglot1.common.CarDetails;
import com.park.parkinglot1.common.PhotoDetails;
import com.park.parkinglot1.common.UserDetails;
import com.park.parkinglot1.entity.Car;
import com.park.parkinglot1.entity.Photo;
import com.park.parkinglot1.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lucis
 */
@Stateless
public class CarBean {

    private static final Logger LOG = Logger.getLogger(CarBean.class.getName());

    @PersistenceContext
    private EntityManager em;
    
    public CarDetails findById(Integer carId){
        
        Car car = em.find(Car.class, carId);
        return new CarDetails(car.getId(), car.getLicensePlate(), car.getParkingSpot(), car.getUser().getUsername());
        
    }
    
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
         
    public void createCar( String licensePlate, String parkingSpot, Integer userId){
        LOG.info("createCar");
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        
        User user = em.find(User.class, userId);
        user.getCars().add(car);
        car.setUser(user);
        
        em.persist(car);
        
    }

    public void updateCar(Integer carId, String licensePlate, String parkingSpot, Integer userId) {
       LOG.info("updateCar");
       
       Car car = em.find(Car.class, carId);
       car.setLicensePlate(licensePlate);
       car.setParkingSpot(parkingSpot);
       
       //remove car from old owner
       User oldUser = car.getUser();
       oldUser.getCars().remove(car);
       
       User user = em.find(User.class, userId);
       user.getCars().add(car);
       car.setUser(user);
    }

    public void deleteCarsByIds(Collection<Integer> ids) {
        LOG.info("deleteCarsByIds");
        for(Integer id:ids){
            Car car = em.find(Car.class, id);
            em.remove(car);
            
        }
    }
    
    public void addPhotoToCar(Integer carId, String filename, String fileType, byte[] fileContent){
        LOG.info("addPhotoToCar");
        Photo photo = new Photo();
        photo.setFilename(filename);
        photo.setFileType(fileType);
        photo.setFileContent(fileContent);
        
        Car car = em.find(Car.class, carId);
        car.setPhoto(photo);
        
        photo.setCar(car);
        em.persist(photo);
        
    }
    
    public PhotoDetails findPhotoByCarId(Integer carId){
        
        TypedQuery<Photo> typedQuery = em.createQuery("SELECT p FROM Photo p WHERE p.car.id = :id", Photo.class).setParameter("id", carId);
        List<Photo> photos = typedQuery.getResultList();
        if(photos.isEmpty()){
            return null;
        
        }
        
        Photo photo = photos.get(0);
        return new PhotoDetails(photo.getId(), photo.getFilename(), photo.getFileType(), photo.getFileContent());
        
        
    }
    
 }
 

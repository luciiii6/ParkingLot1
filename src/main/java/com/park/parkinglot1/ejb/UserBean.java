/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.park.parkinglot1.ejb;

import com.park.parkinglot1.common.UserDetails;
import com.park.parkinglot1.entity.User;
import java.util.ArrayList;
import java.util.Collection;
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
public class UserBean {

     private static final Logger LOG = Logger.getLogger(CarBean.class.getName());

    @PersistenceContext
    private EntityManager em;
    
        
    public void createUser(String username, String email, String passwordSha256, String position){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordSha256);
        user.setPosition(position);
        
        em.persist(user);
    }
    
      public List<UserDetails> getAllUsers(){
        LOG.info("getAllUsers");
        
        try {
            
            List<User> users = (List<User>) em.createQuery("SELECT u FROM User u").getResultList();
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
    
    public Collection<String> findUsernames(Collection<Integer> userIds){
        LOG.info("findUsernames");
        List<String> usernames = (List<String>) em.createQuery("SELECT u.username from User u WHERE u.id IN ?1")
                     .setParameter(1, userIds)
                     .getResultList();
        
        return usernames;
        
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

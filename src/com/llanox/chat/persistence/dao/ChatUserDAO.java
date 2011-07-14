/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.llanox.chat.persistence.dao;

import com.llanox.chat.persistence.entities.ChatUser;

/**
 *
 * @author llanox
 */
public class ChatUserDAO extends AbstractDAO{

Class entityClazz = ChatUser.class;

    public ChatUser find(int id){
        return (ChatUser) super.find(entityClazz,id);
    }
    
    
    public ChatUser findByEmail(String email){
    	
    	return (ChatUser) super.findByProperty(entityClazz, "emailUser", email);
    }
    
    
    public ChatUser findByNickName(String nickName){
    	
    	return (ChatUser) super.findByProperty(entityClazz, "nickName", nickName);
    }
    
    
    public void save(ChatUser chatUser){    	
    	super.save(chatUser);
    }
    
    public void saveOrUpdate(ChatUser chatUser){
    	
    	super.saveOrUpdate(chatUser);
    	
    }
    
    



}

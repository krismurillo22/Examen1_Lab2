/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1__lab2;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public abstract class SocialClass {
    public ArrayList<String> friends;
    public ArrayList<String> post;
    public String username;
    public String informacion;
    protected String postsHechos;
    
    public SocialClass(String username) {
        this.username = username;
        this.friends = new ArrayList<>();
        this.post = new ArrayList<>();
    }

    public boolean addFriend(String user) {
        if (!user.equals(username) && !friends.contains(user)) {
            friends.add(user);
            return true;
        }
        return false;
    }

    public void addPost(String msg){
        post.add(msg);
    }

    public abstract void timeline();

    public void myProfile() {
        informacion="Usuario: " + username +"\nTimeline: \n";
        timeline();
        informacion += postsHechos;
        informacion+="\nAmigos: "+amigos();
    }

    public String amigos(){
        String amigos="";
        for (int contar = 0; contar < friends.size(); contar++) {
            if (friends.get(contar) != null) {
                amigos += "\n- " + friends.get(contar);
            }
        }
        if (amigos.isEmpty()) {
            return "No tiene amigos en la lista.";
        }

        return amigos;
    }
    
    public String getMyProfile(){
        return informacion;
    }
}

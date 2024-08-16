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
        String informacion="Usuario: " + username +"\nTimeline: ";
        timeline();
        informacion+="Amigos: ";
        AmigosFila();
    }

    private void AmigosFila() {
        for (int i = 0; i < friends.size(); i++) {
            System.out.print(friends.get(i) + " ");
            if ((i + 1) % 10 == 0 || i == friends.size() - 1) {
                System.out.println();
            }
        }
    }
}

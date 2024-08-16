/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1__lab2;

/**
 *
 * @author User
 */
public class Twitter extends SocialClass{
    
    public Twitter(String user){
        super(user);
    }
    
    public void timeline(){
        postsHechos="";
        int contador = 1;
        for (String posts : post) {
            postsHechos += "Post " + contador + ": " + posts + "\n";
            contador++;
        }
    }
    
    public String getPostsHechos() {
        return postsHechos;
    }
}

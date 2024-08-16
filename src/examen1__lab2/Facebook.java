/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1__lab2;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Facebook extends SocialClass implements Commentable{
    public ArrayList<Comment> comentarios;
    
    public Facebook(String username){
       super(username);
       this.comentarios = new ArrayList<>(); 
    }
    
    public boolean addComment(Comment comment) {
        if (comment.getPostID() >= 0 && comment.getPostID() < post.size()) {
            comentarios.add(comment);
            return true;
        }
        return false;
    }
    
    public void timeline() {
        postsHechos = "";
        for (int index = 0; index<post.size(); index++) {
            postsHechos ="Post "+(index + 1);
            for (Comment comment : comentarios) {
                if (comment.getPostID()==index) {
                    postsHechos+=comment.print();
                }
            }
        }
    }
    
    

}

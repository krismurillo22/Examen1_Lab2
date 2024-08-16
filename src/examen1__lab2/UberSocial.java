/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen1__lab2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class UberSocial extends JFrame{
    private ArrayList<SocialClass> Cuentas;
    
    public SocialClass buscar(String username){
        for (SocialClass cuenta: Cuentas) {
            if (cuenta.username.equals(username)) {
                return cuenta;
            }
        }
        return null;
    }
    
    public void agregarCuenta(String username, String tipo){
        if (buscar(username) == null) {
            if (tipo.equals("FACEBOOK")) {
                Cuentas.add(new Facebook(username));
                JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente: " + username);
            } else if (tipo.equals("TWITTER")) {
                Cuentas.add(new Twitter(username));
                JOptionPane.showMessageDialog(null, "Cuenta agregada exitosamente: " + username);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ya existe esa cuenta: " + username);
        }
    }
    
    public void agregarPost(String user, String post) {
        SocialClass cuenta = buscar(user);
        if (cuenta != null) {
            cuenta.addPost(post);
            JOptionPane.showMessageDialog(null, "Post creado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado: " + user);
        }
    }
    
    public void agregarAmigo(String user1, String user2) {
        SocialClass cuenta1 = buscar(user1);
        SocialClass cuenta2 = buscar(user2);
        boolean agregado=false;
        if (cuenta1 != null && cuenta2 != null && cuenta1.getClass() == cuenta2.getClass()) {
            agregado = cuenta1.addFriend(user2) && cuenta2.addFriend(user1);
            if (agregado) {
                JOptionPane.showMessageDialog(null, "Amigos agregados exitosamente: " + user1 + " y " + user2);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar tu amigo. Ambos tienen que ser tipo de cuentas iguales.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuarios no encontrados o son de diferente red social.");
        }
    }
    
    public void agregarComment(String user, int postID, String autor, String comment) {
        SocialClass cuenta = buscar(user);
        if (cuenta instanceof Facebook) {
            Comment newComment = new Comment(postID, autor, comment);
            if (((Facebook) cuenta).addComment(newComment)) {
                JOptionPane.showMessageDialog(null, "Comentario agregado exitosamente para: " + user);
            } else {
                JOptionPane.showMessageDialog(null, "Su comentario no se pudo agregar. Verifique el PostID.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Su usuario no fue encontrado o no es de facebook: " + user);
        }
    }
    
    public void profileFrom(String user) {
        SocialClass cuenta = buscar(user);
        if (cuenta != null) {
            cuenta.myProfile(); 
            String profileContent = cuenta.getMyProfile(); 
            JOptionPane.showMessageDialog(null, profileContent, "Perfil de " + user, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public UberSocial(){
        this.Cuentas = new ArrayList<>();
        initUI();
    }
    
    private void initUI() {
        setTitle("UberSocial");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Bienvenido a UBER SOCIAL");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        
        JPanel buttonPanel = new JPanel();
        JButton Agregar=crearBoton("Agregar Cuenta");
        JButton AgregarPost=crearBoton("Agregar Post");
        JButton AgregarAmigo=crearBoton("Agregar Amigo");
        JButton AgregarComen=crearBoton("Agregar Comentario");
        JButton Perfil=crearBoton("Ver Perfil");
        JButton Salir=crearBoton("Salir");

        buttonPanel.add(Agregar);
        buttonPanel.add(AgregarPost);
        buttonPanel.add(AgregarAmigo);
        buttonPanel.add(AgregarComen);
        buttonPanel.add(Perfil);
        buttonPanel.add(Salir);

        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtUsername = new JTextField(10);
                JComboBox<String> comboTipoCuenta = new JComboBox<>(new String[]{"FACEBOOK", "TWITTER"});
                Object[] message = {
                    "Username:", txtUsername,
                    "Tipo de cuenta:", comboTipoCuenta
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Agregar Cuenta", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String username = txtUsername.getText();
                    String tipo = (String) comboTipoCuenta.getSelectedItem();
                    agregarCuenta(username, tipo);
                }
            }
        });

        AgregarPost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtUserPost = new JTextField(10);
                JTextField txtPost = new JTextField(20);
                Object[] message = {
                    "Username:", txtUserPost,
                    "Post:", txtPost
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Agregar Post", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String user = txtUserPost.getText();
                    String post = txtPost.getText();
                    agregarPost(user, post);
                }
            }
        });

        AgregarAmigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtUser1 = new JTextField(10);
                JTextField txtUser2 = new JTextField(10);
                Object[] message = {
                    "User 1:", txtUser1,
                    "User 2:", txtUser2
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Agregar Amigo", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String user1 = txtUser1.getText();
                    String user2 = txtUser2.getText();
                    agregarAmigo(user1, user2);
                }
            }
        });

        AgregarComen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtUserComment = new JTextField(10);
                JTextField txtAutorComment = new JTextField(10);
                JTextField txtComment = new JTextField(20);
                JTextField txtPostId = new JTextField(5);
                Object[] message = {
                    "Username:", txtUserComment,
                    "Autor:", txtAutorComment,
                    "Post ID:", txtPostId,
                    "Comentario:", txtComment
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Agregar Comentario", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String user = txtUserComment.getText();
                    String autor = txtAutorComment.getText();
                    String comment = txtComment.getText();
                    int postId = Integer.parseInt(txtPostId.getText());
                    agregarComment(user, postId, autor, comment);
                }
            }
        });

        Perfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txtProfileUser = new JTextField(10);
                Object[] message = {
                    "Username:", txtProfileUser
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Ver Perfil", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String user = txtProfileUser.getText();
                    profileFrom(user);
                }
            }
        });
        
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    
    private JButton crearBoton(String nombre){
        JButton boton = new JButton(nombre);
        boton.setPreferredSize(new Dimension(180, 50));
        Color colorNuevoOtros = new Color(6, 57, 112);
        boton.setBackground(colorNuevoOtros);
        boton.setForeground(Color.WHITE);
        boton.setFocusable(false);
        return boton;
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            UberSocial app = new UberSocial();
            app.setVisible(true);
        });
    }
}

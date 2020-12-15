/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entity.Etat;
import Entity.Ticket;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jbuffeteau
 */
public class FonctionsMetier implements IMetier
{
    //Création de la classe fonction métier
    @Override
    public User GetUnUser(String login, String mdp)
    {
    // Déclarer l'utilisateur en null    
        User u = null;
        try {
    // On etablie la connection à notre base de données.
  Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idUser, nomUser, prenomUser, statutUser from users where loginUser =1 and pwdUser = 2");
            ps.setString(1, login);
            ps.setString(2, mdp);
            ResultSet rs = ps.executeQuery();
            rs.next();
            u = new User(rs.getInt("idUser"), rs.getString("nomUser"), rs.getString("prenomUser"),rs.getString("statutUser"));
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        } return u;
    }

    @Override
    public ArrayList<Ticket> GetAllTickets()
    {
        
        return null;
    }

    @Override
    public ArrayList<Ticket> GetAllTicketsByIdUser(int idUser)
    {
        ArrayList<Ticket> lesTickets = new ArrayList<>();
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("SELECT tick.idTicket, tick.nomTicket, tick.dateTicket, use.nomEtat from tickets tick, etats use where numUser = 3 and tick.numEtat = use.idEtat");
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Ticket tick = new Ticket(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4));
                lesTickets.add(tick);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        } return lesTickets;
    }

    @Override
    public void InsererTicket(int idTicket, String nomTicket, String dateTicket, int idUser, int idEtat) 
    {
         try { Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("insert into tickets values (1,2,3)");
            ps.setInt(1, idTicket );
            ps.setString(2, nomTicket );
            ps.setString(3, dateTicket);
            ps.setInt(3, idUser);
            ps.setInt(3, idEtat);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void ModifierEtatTicket(int idTicket, int idEtat) 
    {
        
        
    }

    @Override
    public ArrayList<User> GetAllUsers()
    {
        ArrayList<User> lesUsers = new ArrayList<>();
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select idUser, nomUser, prenomUser, statutUser from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                User u = new User(rs.getInt("idUser"), rs.getString("nomUser"), rs.getString("prenomUser"),rs.getString("statutUser"));
                lesUsers.add(u);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesUsers;
    }

    @Override
    public int GetLastIdTicket()
    {
         return 0;
    }

    @Override
    public int GetIdEtat(String nomEtat)
    {
        
        return 0;
    }

    @Override
    public ArrayList<Etat> GetAllEtats()
    {
        ArrayList<Etat> lesEtats = new ArrayList<>();
        try {
            Connection cnx = ConnexionBDD.getCnx();
            PreparedStatement ps = cnx.prepareStatement("select * from etats");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Etat e = new Etat(rs.getInt(1), rs.getString(2));
                lesEtats.add(e);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(FonctionsMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesEtats;
    }
}

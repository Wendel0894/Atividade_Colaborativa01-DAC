
package br.edu.ifpb.domain.persistency.memory;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.Bandas;
import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.graalvm.compiler.nodeinfo.InputType;


public class BandasEmJDBC implements Bandas {

    private Connection connection;

    public BandasEmJDBC() throws SQLException{
        
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                "jdbc:postgresql://host-banco:5432/Atividade1",
                "ads","123"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }

    
    
    
    @Override
    public void inserir(Banda banda) {
       
        try {
            //Inserindo os dados da banda no banco
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Banda (localDeOrigem, NomeFantasia) VALUES (?, ?)"
            );
            
            statement.setString(1, banda.getLocalDeOrigem());
            statement.setString(2, banda.getNomeFantasia());
            statement.executeQuery();
           
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
    @Override
    public List<Banda> lista() {
       
        try {
            
            List<Banda> bandas = new ArrayList<>();
            
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM Banda"
            ).executeQuery();
            
            
            
            while ( result.next() ) {
                bandas.add(
                        criarBanda(result)
                );
                        
            }
            
            return bandas;
                        
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
        
        
    }
    
    public Banda criarBanda ( ResultSet result ) throws SQLException {
        
         int id = result.getInt("id");
         String localDeOrigem = result.getString("LocalDeOrigem");
         String nomeFantasia = result.getString("NomeFantasia");
         List<Integrante> integrantes = new ArrayList<>();
         
         return new Banda(id, localDeOrigem, nomeFantasia, integrantes);
           
    }
    

    @Override
    public void atualizar(Banda banda) {
        
        try {
            
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Banda SET LocalDeOrigem = ?, NomeFantasia = ? WHERE Id = ?"
            );
            
            statement.setString(1, banda.getLocalDeOrigem());
            statement.setString(2, banda.getNomeFantasia());
            statement.setInt(3, banda.getId());
            statement.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void remover(Banda banda) {
        
        try {
           
            //Excluindo a Banda;
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Banda WHERE Id = ?"
            );
            
            statement.setInt(1, banda.getId());
            statement.executeQuery();
            

        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    @Override
    public List<Banda> recuperarBandasPorLocalDeOrigem(String localDeOrigem) {
        
        
        try {
            
            List<Banda> bandas = new ArrayList<>();
            
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Banda WHERE LocalDeOrigem = ?"
            );
            
            statement.setString(1, localDeOrigem);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            
            while (result.next()) {
                
                bandas.add(
                        criarBanda(result)
                );
               
            }
            
            return bandas;
                
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
       
        
    }
    
}

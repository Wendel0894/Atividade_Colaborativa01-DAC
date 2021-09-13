
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
            
            int idDaBanda = getIdBanda(banda.getNomeFantasia());
            int idDoIntegrante = getIdIntegrante(banda.getIntegrantes().get(0).getCpf().getNumero());
            
 
            statement = connection.prepareStatement(
                    "INSERT INTO Integrante_Banda VALUES(?, ?)"
            );
            
            statement.setInt(1, idDaBanda);
            statement.setInt(2, idDoIntegrante);
            statement.executeQuery();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
    public int getIdIntegrante(String cpf) {
        
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT Id from Integrante WHERE Cpf = ?"
            );
            
            statement.setString(1, cpf);
            statement.executeQuery();
          
            int result = statement.getResultSet().getInt("Id");
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
                
        
    }
    
    public int getIdBanda(String nomeFantasia) {
        
         try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT Id from Banda WHERE NomeFantasia = ?"
            );
            
            statement.setString(1, nomeFantasia);
            statement.executeQuery();
          
            int result = statement.getResultSet().getInt("Id");
            
            return result;
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
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
         int idIntegrante = recuperarIdIntegranteBanda(id);
         Integrante integrante = recuperarIntegrantePorId(idIntegrante);
         List<Integrante> integrantes = new ArrayList<>();
         integrantes.add(integrante);
         
         System.out.println("Integrante encontrado na função criar banda: " + integrantes.get(0).getNome());
         return new Banda(id, localDeOrigem, nomeFantasia, integrantes);
           
    }
    
    public int recuperarIdIntegranteBanda(int idBanda) {
        
        try {
            
            int id = -1;
            
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT Id_Integrante FROM Integrante_Banda WHERE Id_Banda = ?"
            );
            
            statement.setInt(1, idBanda);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            
            while ( result.next() ) {
                id = result.getInt("Id_Integrante");
            }
            
            return id;
           
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
       
        
    }
    
    public Integrante recuperarIntegrantePorId(int id) {
        
        try {
            
            Integrante integrante = new Integrante("", LocalDate.of(1, 1, 1), new CPF(""));
            
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Integrante WHERE Id = ?"
            );
            
            statement.setInt(1, id);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            
            while( result.next() ) {
                  
                integrante = criarIntegrante(result);
                
            }
            
            return integrante;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return new Integrante("", LocalDate.of(1, 1, 1), new CPF(""));
        }
        
    }
    
    private Integrante criarIntegrante(ResultSet result) throws SQLException {
        int id = result.getInt("Id");
        String nome = result.getString("nome");
        String date = result.getString("dataDeNascimento");
        LocalDate dataDeNascimento = LocalDate.of(
                Integer.parseInt(date.substring(0, 4)), 
                Integer.parseInt(date.substring(5, 7)), 
                Integer.parseInt(date.substring(8, 10))
        );
        CPF cpf = new CPF(result.getString("cpf"));
        return new Integrante(id, nome,dataDeNascimento, cpf);
    }
    

    @Override
    public void atualizar(Banda banda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Banda banda) {
     
        System.out.println("Id da banda a ser excluida: " + banda.getId());
        
        try {
            //Excluindo os integrantes da banda;
            removerIntegrantesBanda(banda.getId());
            
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
    public List<Integrante> listarIntegrantes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void adicionarIntegrante(int idBanda, int idIntegrante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removerIntegrantesBanda(int idBanda) {
        
        try {
            PreparedStatement statament = connection.prepareCall(
                    "DELETE FROM Integrante_Banda WHERE Id_Banda = ?"
            );
            
            statament.setInt(1, idBanda);
            statament.executeQuery();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public Banda recuperarBandaPorNomeFantasia(String localDeOrigem) {
        
        
        try {
            
            
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Banda WHERE LocalDeOrigem = ?"
            );
            
            statement.setString(1, localDeOrigem);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            
            while (result.next()) {
                System.out.println("Result: " + result.getString("nomeFantasia"));
                return criarBanda(result);
            }
            
            return new Banda("", "", new ArrayList<>());
                
            
        } catch (SQLException ex) {
            Logger.getLogger(BandasEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return new Banda("", "", new ArrayList<>());
        }
       
        
    }
    
}

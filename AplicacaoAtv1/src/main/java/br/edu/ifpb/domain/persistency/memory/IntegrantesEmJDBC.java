
package br.edu.ifpb.domain.persistency.memory;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.Integrantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//Classe de acesso as dados atráves do banco de dados (Postgreesql)

public class IntegrantesEmJDBC implements Integrantes{
    
    private Connection connection;
    
    
    public IntegrantesEmJDBC() throws SQLException {
        
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
    
    //Inserção de integrantes
    
    @Override
    public void inserir(Integrante integrante) {
        
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO integrante (Nome, dataDeNascimento, CPF) VALUES(?,?,?)"
            );
            statement.setString(1, integrante.getNome());
            statement.setDate(2, java.sql.Date.valueOf(integrante.getDataDeNascimento()));
            statement.setString(3,integrante.getCpf().getNumero());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }

    @Override
    public List<Integrante> listar() {
       
       try {
            List<Integrante> lista = new ArrayList<>();
            ResultSet result = connection.prepareStatement(
                    "SELECT * FROM Integrante"
            ).executeQuery();
            while (result.next()) {
                lista.add(
                    criarIntegrante(result)
                );
            }
            return lista;
        } catch (SQLException ex) {
//            Logger.getLogger(EmJDBC.class.getName()).log(Level.SEVERE,null,ex);
            return Collections.EMPTY_LIST;
        }
        
    }
    
    private Integrante criarIntegrante(ResultSet result) throws SQLException {
        int id = result.getInt("id");
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
    public void atualizar(Integrante integrante) {
        
        try {
            
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Integrante SET Nome = ?, dataDeNascimento = ?, Cpf = ? WHERE id = ?"
            );
            
            statement.setString(1, integrante.getNome());
            statement.setDate(2, java.sql.Date.valueOf(integrante.getDataDeNascimento()));
            statement.setString(3, integrante.getCpf().getNumero());
            statement.setInt(4, integrante.getId());
            statement.executeUpdate();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void remover(Integrante integrante) {
        
        try {
            
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM Integrante WHERE Id = ?"
            );
            
            statement.setInt(1, integrante.getId());
            statement.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public Integrante recuperarIntegrantePorCpf(String Cpf) {
       
        
        try {
           
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Integrante WHERE Cpf = ?"
            );
            
            statement.setString(1, Cpf);
            statement.executeQuery();
            
            ResultSet result = statement.getResultSet();
            
            while(result.next()){
                return criarIntegrante(result);
            }
            
            return new Integrante("Não Encontrado", LocalDate.of(1, 1, 1), new CPF(""));
            
                         
        } catch (SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return new Integrante("", LocalDate.of(1, 1, 1), new CPF(""));
        }
        
    }
    
}


package br.edu.ifpb.domain.persintency.memory;

import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.Integrantes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                "jdbc:postgresql://host-banco:5432/integrantes",
                "ads","123"
            );
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(IntegrantesEmJDBC.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    
    @Override
    public void inserir(Integrante integrante) {
        
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO integrante (nome, dataDeNascimento, CPF) VALUES(?,?,?)"
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Integrante integrante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Integrante integrante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integrante recuperarIntegrantePorID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}

package net.uni5.ztec.api.dao;

import org.springframework.stereotype.Repository;

import net.uni5.ztec.api.entities.Tarefa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Repository
public class TarefaDAO {

    private static final String SQL_INSERT = "INSERT INTO tarefas (usuario_id, titulo, descricao, data_tarefa) VALUES (?,?,?,NOW())";
    private static final String SQL_UPDATE = "UPDATE tarefas SET usuario_id=?, titulo=?, descricao=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM tarefas WHERE id=?";
    private static final String SQL_SELECT = "SELECT id, usuario_id, titulo, descricao, data_tarefa FROM tarefas";
    private static final String SQL_SELECT_POR_ID = "SELECT id, usuario_id, titulo, descricao, data_tarefa FROM tarefas WHERE id=?";

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("JDBC_DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    public Tarefa buscarPorId(Long id) {
        Tarefa tarefa = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://pgsql.ztec.uni5.net/ztec", "ztec", "sxred65");
            PreparedStatement pStatement = con.prepareStatement(SQL_SELECT_POR_ID);
            ResultSet rs = pStatement.executeQuery();
            pStatement.setInt(1, id.intValue());
            if (rs.next()) {
                tarefa = new Tarefa();
                tarefa.setId(rs.getLong("id"));
                tarefa.setUsuarioId(rs.getInt("usuario_id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setDataTarefa(rs.getDate("data_tarefa"));
            }
            rs.close();
            pStatement.close();
            return tarefa;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Long inserirTarefa(Tarefa tarefa) {
        try {
            Long idReturn = 0L;
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://pgsql.ztec.uni5.net/ztec", "ztec", "sxred65");
            PreparedStatement pStatement = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pStatement.setInt(1, tarefa.getUsuarioId());
            pStatement.setString(2, tarefa.getTitulo());
            pStatement.setString(3, tarefa.getDescricao());
            int affectedRows = pStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        idReturn = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            con.close();
            pStatement.close();
            return Long.valueOf(affectedRows);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public Tarefa atualizarTarefa(Tarefa tarefa) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://pgsql.ztec.uni5.net/ztec", "ztec", "sxred65");
            PreparedStatement pStatement = con.prepareStatement(SQL_UPDATE);
            pStatement.setInt(1, tarefa.getUsuarioId());
            pStatement.setString(2, tarefa.getTitulo());
            pStatement.setString(3, tarefa.getDescricao());
            pStatement.setLong(4, tarefa.getId());
            pStatement.executeUpdate();
            con.close();
            pStatement.close();
            
            return tarefa;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int deletarTarefa(Long id) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://pgsql.ztec.uni5.net/ztec", "ztec", "sxred65");
            PreparedStatement pStatement = con.prepareStatement(SQL_DELETE);
            pStatement.setInt(1, id.intValue());
            return pStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Tarefa> listar() {
        List<Tarefa> lista = new ArrayList<>();
        Tarefa tarefa;
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://pgsql.ztec.uni5.net/ztec", "ztec", "sxred65");


//            Connection con = getConnection()
//            String Sql_Create_database = "CREATE TABLE Tarefas (" +
//                    "id, usuario_id, titulo, descricao, data_tarefa " +
//                    "id serial PRIMARY KEY, " +
//                    "usuario_id INT NOT NULL, " +
//                    "titulo VARCHAR(50) NOT NULL, " +
//                    "descricao VARCHAR(255) NOT NULL, " +
//                    "data_tarefa TIMESTAMP NOT NULL )";
//
//            Statement stmt = con.createStatement();
//            stmt.executeUpdate(Sql_Create_database);

            PreparedStatement pStatement = con.prepareStatement(SQL_SELECT);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                tarefa = new Tarefa();
                tarefa.setId(rs.getLong("Id"));
                tarefa.setUsuarioId(rs.getInt("usuario_id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setDataTarefa(rs.getDate("data_tarefa"));
                lista.add(tarefa);
            }
            con.close();
            pStatement.close();
            rs.close();
            return lista;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

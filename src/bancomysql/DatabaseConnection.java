package bancomysql;

import java.sql.*;
import javax.swing.JOptionPane;

class DatabaseConnection {
    final private String driver = "com.mysql.jdbc.Driver";
    final public String banco = "clientes";
    final private String url = "jdbc:mysql://localhost:3307/" + banco;
    final private String usuario = "root";
    final private String senha = "usbw";
    
    private Connection connection;
    public Statement statement;
    public ResultSet resultSet;
    
    public boolean connect() {
        boolean result = true;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, usuario, senha);
            JOptionPane.showMessageDialog(null, "Conexão Estabelecida", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException err) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + err, "Erro!", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException err) {
            handleSQLException(err);
        }
        
        return result;
    }
    
    public void disconnect() throws SQLException {
        connection.close();
        JOptionPane.showMessageDialog(null, "Conexão fechada com o banco", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void execute(String sql) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            handleSQLException(ex);
        }
        
    }
    
    public void handleSQLException(SQLException err) {
        JOptionPane.showMessageDialog(null, "Erro no comando SQL: " + err, "Erro!", JOptionPane.INFORMATION_MESSAGE);
    }
}

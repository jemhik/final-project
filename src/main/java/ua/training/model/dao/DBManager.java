package ua.training.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBManager {

    private static final Logger log = LoggerFactory.getLogger(DBManager.class);

    private static DBManager datasource;
    private DBManager() {
    }
    private ComboPooledDataSource cpds = null;
    /**
     * Create instance of Connection Pool
     *
     * @return
     */
    public static DBManager getInstance() {
        if (datasource == null) {
            ResourceBundle rb = ResourceBundle.getBundle("DBResources.System");
            datasource = new DBManager();
            datasource.cpds = new ComboPooledDataSource();
            try {
                datasource.cpds.setDriverClass(rb.getString("driver"));
            } catch (Exception e) {
                e.printStackTrace();
                log.error(String.valueOf(e));
            }
            datasource.cpds.setJdbcUrl(rb.getString("url"));
            datasource.cpds.setUser(rb.getString("username"));
            datasource.cpds.setPassword(rb.getString("dbpassword"));
            datasource.cpds.setInitialPoolSize(new Integer((String) rb .getString("initialPoolSize")));
            datasource.cpds.setAcquireIncrement(new Integer((String) rb.getString("acquireIncrement")));
            datasource.cpds.setMaxPoolSize(new Integer((String) rb.getString("maxPoolSize")));
            datasource.cpds.setMaxIdleTime(Integer.parseInt(rb.getString("timeout")));
            datasource.cpds.setMinPoolSize(new Integer((String) rb.getString("minPoolSize")));
        }
        return datasource;
    }
    /**
     * Gets the connection from ComboPooledDataSource
     *
     * @return connection
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = getInstance().cpds.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    /**
     * Closes a connection4
     *
     * @param connection
     * @throws Exception
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                log.error(String.valueOf(e));
            }
        }
    }
//    public static Connection getConnection() {
//        //logger.debug("Creating connection");
//        Context context;
//        Connection connection = null;
//        try {
//            context = new InitialContext();
//            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
//            connection = ds.getConnection();
//        } catch (NamingException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
//    static void close(Connection connection){
//        //logger.debug("Closing connection");
//        if(connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}

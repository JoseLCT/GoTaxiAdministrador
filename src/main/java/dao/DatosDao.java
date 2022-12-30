package dao;

import conexion.Conexion;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DatosDao {

    private Connection connection;

    public DatosDao() {
        connection = Conexion.conectar().getConnection();
    }

    public DefaultTableModel getDatos() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM get_tipo_vehiculo_origen");
            ResultSetMetaData rsmd = rs.getMetaData();

            int columnas = rsmd.getColumnCount();

            modelo.addColumn("UBICACION");
            modelo.addColumn("COMFORT");
            modelo.addColumn("ESTANDAR");
            modelo.addColumn("PREMIUM");

            while (rs.next()) {
                Object[] fila = new Object[columnas];

                for (int i = 0; i < columnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelo;
    }
}

package vistas;

import dao.DatosDao;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class AIMenu extends JPanel implements Interfaz {

    private Dimension tamano;

    private JLabel lb_titulo;

    private JTable tbl_historial;
    private JScrollPane scroll;

    private JButton btn_menu;
    private JButton btn_pasajero;
    private JButton btn_conductor;

    private JLabel lb_fondo_inferior;

    private PropertyChangeSupport notificador;

    public AIMenu(Dimension tamano) {
        this.tamano = tamano;
        notificador = new PropertyChangeSupport(this);
        init();
    }

    public void addObserver(Ventana ventana) {
        notificador.addPropertyChangeListener(ventana);
    }

    private void init() {
        setLayout(null);
        setBackground(new Color(255, 160, 0));

        //superior
        lb_titulo = new JLabel("DATOS", SwingConstants.CENTER);
        lb_titulo.setBounds(tamano.width / 2 - 150, 100, 300, 40);
        lb_titulo.setFont(new Font("poppins", Font.PLAIN, 25));
        add(lb_titulo);

        //medio
        tbl_historial = new JTable();
        scroll = new JScrollPane(tbl_historial);

        scroll.setBounds(50, 200, 500, 550);

        add(scroll);

        //inferior
        btn_menu = new JButton();
        btn_pasajero = new JButton();
        btn_conductor = new JButton();

        lb_fondo_inferior = new JLabel();

        btn_menu.setBounds(tamano.width / 2 - 130, tamano.height - 85, 30, 30);
        btn_pasajero.setBounds(tamano.width / 2 - 15, tamano.height - 85, 30, 30);
        btn_conductor.setBounds(tamano.width / 2 + 85, tamano.height - 85, 30, 30);

        lb_fondo_inferior.setBounds(0, tamano.height - 100, tamano.width, 100);

        BufferedImage bimg_menu = null;
        BufferedImage bimg_pasajero = null;
        BufferedImage bimg_conductor = null;

        Image img_menu;
        Image img_pasajero;
        Image img_conductor;

        ImageIcon icon_menu;
        ImageIcon icon_pasajero;
        ImageIcon icon_conductor;

        try {
            bimg_menu = ImageIO.read(new File("Imagenes/menu.png"));
            bimg_pasajero = ImageIO.read(new File("Imagenes/pasajero.png"));
            bimg_conductor = ImageIO.read(new File("Imagenes/conductor.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_menu = bimg_menu.getScaledInstance(btn_menu.getWidth(), btn_menu.getHeight(), Image.SCALE_SMOOTH);
        img_pasajero = bimg_pasajero.getScaledInstance(btn_pasajero.getWidth(), btn_pasajero.getHeight(), Image.SCALE_SMOOTH);
        img_conductor = bimg_conductor.getScaledInstance(btn_conductor.getWidth(), btn_conductor.getHeight(), Image.SCALE_SMOOTH);

        icon_menu = new ImageIcon(img_menu);
        icon_pasajero = new ImageIcon(img_pasajero);
        icon_conductor = new ImageIcon(img_conductor);

        btn_menu.setIcon(icon_menu);
        btn_pasajero.setIcon(icon_pasajero);
        btn_conductor.setIcon(icon_conductor);

        btn_menu.setBorder(null);
        btn_pasajero.setBorder(null);
        btn_conductor.setBorder(null);

        btn_menu.setContentAreaFilled(false);
        btn_pasajero.setContentAreaFilled(false);
        btn_conductor.setContentAreaFilled(false);

        lb_fondo_inferior.setOpaque(true);
        lb_fondo_inferior.setBackground(Color.black);

        add(btn_menu);
        add(btn_pasajero);
        add(btn_conductor);
        add(lb_fondo_inferior);

        btn_pasajero.addActionListener(e -> {
            notificador.firePropertyChange("aimenu_aipasajero", null, null);
        });
        btn_conductor.addActionListener(e -> {
            notificador.firePropertyChange("aimenu_aiconductor", null, null);
        });
    }

    @Override
    public void actualizarDatos() {
        DatosDao datosDao = new DatosDao();
        tbl_historial.setModel(datosDao.getDatos());
    }
}

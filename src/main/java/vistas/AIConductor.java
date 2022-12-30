package vistas;

import dao.ConductorDao;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeSupport;
import java.io.File;

public class AIConductor extends JPanel implements Interfaz{

    private Dimension tamano;

    private JLabel lb_titulo;

    private JButton btn_ordenar;
    private boolean ordenar;

    private JTable tbl_historial;
    private JScrollPane scroll;

    private JButton btn_menu;
    private JButton btn_pasajero;
    private JButton btn_conductor;

    private JLabel lb_fondo_inferior;

    private PropertyChangeSupport notificador;

    public AIConductor(Dimension tamano) {
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
        lb_titulo = new JLabel("CONDUCTORES", SwingConstants.CENTER);
        lb_titulo.setBounds(tamano.width / 2 - 150, 100, 300, 40);
        lb_titulo.setFont(new Font("poppins", Font.PLAIN, 25));
        add(lb_titulo);

        //medio
        btn_ordenar = new JButton();
        tbl_historial = new JTable();
        scroll = new JScrollPane(tbl_historial);

        btn_ordenar.setBounds(530, 175, 20, 20);
        scroll.setBounds(50, 200, 500, 550);

        BufferedImage bimg_ordenar = null;
        Image img_ordenar;
        ImageIcon icon_ordenar;

        try {
            bimg_ordenar = ImageIO.read(new File("Imagenes/ordenar.png"));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las imágenes");
        }

        img_ordenar = bimg_ordenar.getScaledInstance(btn_ordenar.getWidth(), btn_ordenar.getHeight(), Image.SCALE_SMOOTH);
        icon_ordenar = new ImageIcon(img_ordenar);
        btn_ordenar.setIcon(icon_ordenar);

        btn_ordenar.setBorder(null);
        btn_ordenar.setContentAreaFilled(false);

        add(btn_ordenar);
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

        btn_ordenar.addActionListener(e -> {
            if (ordenar == false) {
                ConductorDao conductorDao = new ConductorDao();
                tbl_historial.setModel(conductorDao.getDatosAsc());
                ordenar = true;
            } else {
                ConductorDao conductorDao = new ConductorDao();
                tbl_historial.setModel(conductorDao.getDatosDesc());
                ordenar = false;
            }
        });
        btn_menu.addActionListener(e -> {
            notificador.firePropertyChange("aiconductor_aimenu", null, null);
        });
        btn_pasajero.addActionListener(e -> {
            notificador.firePropertyChange("aiconductor_aipasajero", null, null);
        });
    }

    @Override
    public void actualizarDatos() {
        ConductorDao conductorDao = new ConductorDao();
        tbl_historial.setModel(conductorDao.getDatosDesc());
    }
}

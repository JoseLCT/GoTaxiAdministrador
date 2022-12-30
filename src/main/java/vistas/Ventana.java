package vistas;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Ventana extends JFrame implements PropertyChangeListener {

    private AIMenu aiMenu;
    private AIPasajero aiPasajero;
    private AIConductor aiConductor;

    public Ventana() {
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setSize(600, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        aiMenu = new AIMenu(getSize());
        aiPasajero = new AIPasajero(getSize());
        aiConductor = new AIConductor(getSize());

        aiMenu.addObserver(this);
        aiPasajero.addObserver(this);
        aiConductor.addObserver(this);

        add(aiMenu);
        aiMenu.actualizarDatos();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String tipo = evt.getPropertyName();
        switch (tipo) {
            case "aimenu_aipasajero":
                aiMenu.setVisible(false);
                add(aiPasajero);
                aiPasajero.setVisible(true);

                aiPasajero.actualizarDatos();
                break;
            case "aimenu_aiconductor":
                aiMenu.setVisible(false);
                add(aiConductor);
                aiConductor.setVisible(true);

                aiConductor.actualizarDatos();
                break;
            case "aipasajero_aimenu":
                aiPasajero.setVisible(false);
                aiMenu.setVisible(true);
                remove(aiPasajero);

                aiMenu.actualizarDatos();
                break;
            case "aipasajero_aiconductor":
                aiPasajero.setVisible(false);
                add(aiConductor);
                aiConductor.setVisible(true);
                remove(aiPasajero);

                aiConductor.actualizarDatos();
                break;
            case "aiconductor_aimenu":
                aiConductor.setVisible(false);
                aiMenu.setVisible(true);
                remove(aiConductor);

                aiConductor.actualizarDatos();
                break;
            case "aiconductor_aipasajero":
                aiConductor.setVisible(false);
                add(aiPasajero);
                aiPasajero.setVisible(true);
                remove(aiConductor);

                aiPasajero.actualizarDatos();
                break;
        }
    }
}

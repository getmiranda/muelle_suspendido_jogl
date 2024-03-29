package muelle;

public class PanelMuelle extends javax.swing.JPanel {

    private PanelCanvas panelCanvas;
    
    public PanelMuelle() {
        initComponents();
        
    }
    
    public void addEventos(GraphicListener listener) {

        this.deslizador.addChangeListener(listener);
        this.botonStart.addActionListener(listener);
        this.panelCanvas.addMouseListener(listener);
        this.panelCanvas.addMouseMotionListener(listener);
        this.panelCanvas.addMouseWheelListener(listener);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDerecho = new javax.swing.JPanel();
        botonStart = new javax.swing.JButton();
        deslizador = new javax.swing.JSlider();

        setBorder(javax.swing.BorderFactory.createLineBorder(getBackground(), 10));
        setLayout(new java.awt.BorderLayout());

        panelDerecho.setBorder(javax.swing.BorderFactory.createTitledBorder("Nivel"));
        panelDerecho.setLayout(new java.awt.BorderLayout());

        botonStart.setText("Start");
        panelDerecho.add(botonStart, java.awt.BorderLayout.PAGE_START);

        deslizador.setMajorTickSpacing(3);
        deslizador.setMaximum(60);
        deslizador.setMinimum(1);
        deslizador.setMinorTickSpacing(3);
        deslizador.setOrientation(javax.swing.JSlider.VERTICAL);
        deslizador.setPaintLabels(true);
        deslizador.setPaintTicks(true);
        deslizador.setValue(60);
        panelDerecho.add(deslizador, java.awt.BorderLayout.CENTER);

        add(panelDerecho, java.awt.BorderLayout.LINE_END);
        panelDerecho.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonStart;
    private javax.swing.JSlider deslizador;
    private javax.swing.JPanel panelDerecho;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the deslizador
     */
    public javax.swing.JSlider getDeslizador() {
        return deslizador;
    }

    /**
     * @return the panelCanvas
     */
    public PanelCanvas getPanelCanvas() {
        return panelCanvas;
    }

    /**
     * @param panelCanvas the panelCanvas to set
     */
    public void setPanelCanvas(PanelCanvas panelCanvas) {
        this.panelCanvas = panelCanvas;
    }
}

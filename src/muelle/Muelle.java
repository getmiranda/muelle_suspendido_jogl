package muelle;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import java.util.Timer;
import javax.swing.JFrame;

//Autor: Jose Manuel Miranda Villagran

public class Muelle {

    public static void main(String[] args) {
        // TODO code application logic here
        
        //GLCanvas
        GLProfile perfil = GLProfile.getDefault();
        GLCapabilities capacidades = new GLCapabilities(perfil);
        capacidades.setHardwareAccelerated(true);
        capacidades.setNumSamples(2);
        capacidades.setSampleBuffers(true);
        PanelCanvas panelCanvas = new PanelCanvas(capacidades);
        
        //Creacion de la vista PanelMuelle, necesita conocer al PanelCanvas
        PanelMuelle panelResorte = new PanelMuelle();
        panelResorte.add(panelCanvas);
        panelResorte.setPanelCanvas(panelCanvas);
        
        //Listener, todos los eventos
        GraphicListener listener = new GraphicListener(panelResorte);
        panelCanvas.addGLEventListener(listener);
        panelResorte.addEventos(listener);
        
        //Animacion
        Timer m_timer = new Timer();
        AnimacionEscena m_animator=new AnimacionEscena(panelCanvas);
        m_timer.scheduleAtFixedRate(m_animator, 100, 100);
        
        //Listener necesita conocer al animador
        listener.setM_timer(m_timer);
        listener.setM_animator(m_animator);
        
        //Creacion de la Ventana
        JFrame frame = new JFrame("Muelle con masa suspendida");
        frame.add(panelResorte);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
}

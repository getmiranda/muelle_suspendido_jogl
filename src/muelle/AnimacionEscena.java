package muelle;

import com.jogamp.opengl.awt.GLCanvas;
import java.util.TimerTask;

public class AnimacionEscena extends TimerTask {

    GLCanvas target;
    
    public AnimacionEscena(GLCanvas p ) {
        target = p;
    }

    @Override
    public void run() {
        target.display();
    }

}

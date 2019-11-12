package muelle;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GraphicListener extends MouseAdapter implements GLEventListener, ChangeListener, ActionListener {
    
    PanelMuelle panel;
    
    // ángulos de rotación, establecidos en mouseDragged
    // utilizado en Display
    float rotx=-90.0f;
    float rotz=0.0f;
    float lastx=0.0f;
    float lastz=0.0f;
    float tz = -20f;

    // resorte
    float R=0.5f;                     // radio
    float circles=10.0f;              // devanador

    // sphere
    final float m_SphereZ_Start=4.5f; // Altura de inicio
    float m_SphereZ;                  // Altura sobre el plano xy
    final float m_SphereR=0.75f;      // radio

    // animacion
    double dt = 0.05;                 // segundos entre frames as fastest
    int framecount=0;                 // ver: mostrarEscena, ajustado como sea posible
    final float gravedad=9.81f;       // aceleracion de la gravedad universal
    final float mass=1.0f;            // masa de la esfera
    float rigidez=30.0f;              // stiffness of net. Pick a value / rigidez de la red. Elija un valor
    float friccion=0.02f;             // Relentizado o friccion

    float fk=0.05f;                   // exp in cosinversion

    float aceleracion;                // recordar de cuadro a cuadro
    float velocidad;                  // remember from frame to frame
    float aniTime=0.0f;               // solo para la solución de Cosinus comentada
    
    //variables animador y mouse
    private int oldMouseX;
    private int oldMouseY;
    private Timer m_timer;
    private AnimacionEscena m_animator;
    private float view_rotx=0.0f;
    private float view_roty=0.0f;
    
    //Textura
    private Texture portada;

    GraphicListener(PanelMuelle panel) {
        this.panel = panel;
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        
        GL2 gl = drawable.getGL().getGL2();
        
        // habilitar la configuración del material a dos caras
        gl.glLightModeli(GL2.GL_LIGHT_MODEL_TWO_SIDE,1);

        float ambient[] = {0.4f,0.4f,0.4f,1.0f };
        float diffuse[] = {1.0f,1.0f,1.0f,1.0f };
        float position[] = {0.0f,0.0f,10.0f,0.0f };
        float light_specular[]={1.0f,1.0f,1.0f,0.0f};
        
        float ambient1[] = {0.4f,0.4f,0.4f,1.0f };
        float diffuse1[] = {1.0f,1.0f,1.0f,1.0f };
        float position1[] = {0.0f,0.0f,-10.0f,0.0f };
        float light_specular1[]={1.0f,1.0f,1.0f,0.0f};
        
        float ambient2[] = {0.5f,0.5f,0.5f,1.0f };
        float diffuse2[] = {1.0f,1.0f,1.0f,1.0f };
        float position2[] = {-10.0f,-10.0f,0.0f,0.0f };
        float light_specular2[]={1.0f,1.0f,1.0f,0.0f};        
        
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, ambient,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, diffuse,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, position,0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, light_specular,0);
        gl.glEnable(GL2.GL_LIGHT0);
        
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambient1,0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuse1,0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, position1,0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, light_specular1,0);
        gl.glEnable(GL2.GL_LIGHT1);
        
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_AMBIENT, ambient2,0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_DIFFUSE, diffuse2,0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_POSITION, position2,0);
        gl.glLightfv(GL2.GL_LIGHT2, GL2.GL_SPECULAR, light_specular2,0);
        gl.glEnable(GL2.GL_LIGHT2);
        
        gl.glEnable(GL2.GL_LIGHTING);

        // suavizar el dibujo
        gl.glShadeModel(GL2.GL_SMOOTH);

        // clasificación en profundidad
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LESS);

        // set background to light black
        gl.glClearColor(0f, 0f, 0f, 1.0f);
        
        cargaTextura(gl);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        
        GL2 gl = drawable.getGL().getGL2();
        
        mostarEscena(gl);

        // hacer incrementos aquí, calcular la nueva posición de las esferas: m_SphereZ
        //////////////////
        // Alternativa 1 : versión de aceleración
        // Experimenta con rigidez y fricción
        
//        aceleracion = - gravedad - rigidez * m_SphereZ / mass;
//        velocidad += aceleracion * dt;
//        m_SphereZ += velocidad * dt;
//        
//        if(velocidad>0)
//            velocidad -= friccion * Math.abs(velocidad);
//        else
//            velocidad += friccion * Math.abs(velocidad);

        
        //////////////////////////////////////
        // Alternativa 2 : version cosinus 
        // Experimentar con valores numéricos en fk
        
        aniTime += dt;
        double t = 2.0f * Math.PI * aniTime;
        m_SphereZ = (float)(m_SphereZ_Start * Math.exp( - fk * t) * Math.cos(t));
//        
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        
        GL2 gl = drawable.getGL().getGL2();
        GLU glu = new GLU();

        if (height <= 0)
            height = 1;
        
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 200.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    //Deslizador
    @Override
    public void stateChanged(ChangeEvent e) {

        int value = panel.getDeslizador().getValue();
        
        m_timer.cancel();
        m_timer=new Timer();
        m_animator=new AnimacionEscena(panel.getPanelCanvas());
        
        //Programa la tarea especificada para la ejecución de tasa fija repetida , 
        //comenzando después del retraso especificado.
        m_timer.scheduleAtFixedRate(m_animator, 100, value);

    }
    
    //Boton
    @Override
    public void actionPerformed(ActionEvent e) {
        m_SphereZ = m_SphereZ_Start;
        aniTime = 0.0f;
    }  


    private void mostarEscena(GL2 gl) {
        
        framecount++;

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClear(GL2.GL_ACCUM_BUFFER_BIT);
        gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_DECAL);
        gl.glLoadIdentity();

        // trasladar Z
        gl.glTranslatef(0.0f,0.0f,tz);
        // rotar en X
        gl.glRotatef(rotx,1.0f,0.0f,0.0f);
        // rotar en Z
        gl.glRotatef(rotz,0.0f,0.0f,1.0f);        

        //////////////////////////////////
        // dibuja el espiral
        
        //Material Silver
        Materiales.setMaterial(gl, Materiales.MAT_SILVER, GL2.GL_FRONT_AND_BACK);        
        
        // comienza 0.2 arriba de las esferas startpos: "m_SphereZ_Start"
        // termina 0.2 arriba de las esferas posición actual: "m_SphereZ"
        // tiene devanados "circulares" y un radio básico de "R"

        // dibujar espiral
        float topOfHelix = m_SphereZ_Start + m_SphereR + 0.2f;
        float incr = (topOfHelix - m_SphereZ - m_SphereR - 0.2f) / circles;
        
        // reducir el radio según el estiramiento
        float topi = (float)(2 * Math.PI);
        float actualR = R - incr / topi;

        gl.glLineWidth(2.0f);
        gl.glBegin(gl.GL_LINE_STRIP);
            float t=0.0f;
            float dt=0.01f;

            while(t< circles) {
                gl.glVertex3f((float)(actualR*Math.cos(t*topi)), (float)(actualR*Math.sin(t*topi)), topOfHelix-incr*t);
                t+=dt;
            }
            
            // final al centro y abajo a la esfera
            gl.glVertex3f(0.0f,0.0f,topOfHelix-incr*t);
            gl.glVertex3f(0.0f,0.0f,m_SphereZ);
        gl.glEnd();
        // fin dibujar espiral
        
        Materiales.setMaterial(gl, Materiales.MAT_TIN, GL2.GL_FRONT_AND_BACK);
        
        dibujarRejilla(gl, topOfHelix);
        dibujarEjes(gl);

        // dibuja esfera
        Materiales.setMaterial(gl, Materiales.MAT_RED_PLASTIC, GL2.GL_FRONT_AND_BACK);
        
        GLU glu = new GLU();
        GLUquadric glpQ = glu.gluNewQuadric();

        gl.glPushMatrix();
            gl.glTranslated(0.0f,0.0f,m_SphereZ);
            glu.gluSphere(glpQ, m_SphereR ,20,20);
        gl.glPopMatrix();
        
        glu.gluDeleteQuadric(glpQ);
        
        //Texturas
        
        portada.enable(gl);
        portada.bind(gl);
        
        gl.glBegin(GL2.GL_POLYGON);
            gl.glTexCoord2f(0.0f, 0.0f);
            gl.glVertex3f(-10.0f,4.0f, topOfHelix + 0.1f);//esquina inferior izquierda

            gl.glTexCoord2f(1.0f, 0.0f);
            gl.glVertex3f(0f, 4.0f, topOfHelix+ 0.1f);//esquina inferior derecha

            gl.glTexCoord2f(1.0f, 1.0f);
            gl.glVertex3f(0.0f, 10.0f, topOfHelix+ 0.1f); //superiror derecha

            gl.glTexCoord2f(0.0f,1.0f);
            gl.glVertex3f(-10.0f, 10.0f, topOfHelix+ 0.1f);//superiror izquierda
        gl.glEnd();
        portada.disable(gl);
        
        gl.glFlush();
    }
    
    public void setRotation(float rx,float rz) {
        rotx = rx;
        rotz = rz;
    }

    @Override
    public void mousePressed(MouseEvent evt) {
        oldMouseX = evt.getX();
        oldMouseY = evt.getY();
 
    }

    /**
     * @param m_timer the m_timer to set
     */
    public void setM_timer(Timer m_timer) {
        this.m_timer = m_timer;
    }

    /**
     * @param m_animator the m_animator to set
     */
    public void setM_animator(AnimacionEscena m_animator) {
        this.m_animator = m_animator;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();

        float thetaY = 360.0f * ( (float)(x-oldMouseX)/(float)size.width);
        float thetaX = 360.0f * ( (float)(oldMouseY-y)/(float)size.height);

        oldMouseX = x;
        oldMouseY = y;

        view_rotx += thetaX;
        view_roty += thetaY;

        setRotation(view_rotx, view_roty);
        panel.getPanelCanvas().display();

    }
    
    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        int raton = e.getWheelRotation();
        switch(raton){
            case -1:
                tz += 1.0f;
                break;
            case 1:
                tz -= 1.0f;
                break;
        }
    }

    private void dibujarEjes(GL2 gl) {
        
        gl.glPointSize(10f);
            
        //Creamos los ejes del plano x,y,z
        // EJE X  rojo
        gl.glBegin(GL2.GL_LINES);
            Materiales.setMaterial(gl, Materiales.MAT_RED_PLASTIC, GL2.GL_FRONT_AND_BACK);
            gl.glVertex3f(-100, 0, 0);
            gl.glVertex3f(100, 0, 0);
        gl.glEnd();

        //EJE Y verde
        gl.glBegin(GL2.GL_LINES);
            Materiales.setMaterial(gl, Materiales.MAT_GREEN_PLASTIC, GL2.GL_FRONT_AND_BACK);
            gl.glVertex3f(0, -100, 0);
            gl.glVertex3f(0, 100, 0);
        gl.glEnd();

        //EJE Z Azul
        gl.glBegin(GL2.GL_LINES);
            Materiales.setMaterial(gl, Materiales.MAT_CYAN_PLASTIC, GL2.GL_FRONT_AND_BACK);
            gl.glVertex3f(0, 0, -100);
            gl.glVertex3f(0, 0, 100);
        gl.glEnd();
        
    }
    
    private void dibujarRejilla(GL2 gl, float topOfHelix){
        
        gl.glPointSize(5f);
        
        float zExtent, xExtent;
        float xLocal, zLocal;
        int loopX, loopZ;
        float GridScale = 1f;
        int XSteps = 10;
        int ZSteps = 10;


        gl.glBegin( GL2.GL_LINES );

           zExtent = GridScale*ZSteps;
           for(loopX = -XSteps; loopX <=XSteps; loopX++ ) {
               xLocal = GridScale*loopX;
               gl.glVertex3f( xLocal, -zExtent, topOfHelix );
               gl.glVertex3f( xLocal, zExtent,  topOfHelix );
           }

           xExtent = GridScale * XSteps ;

           for(loopZ = -ZSteps; loopZ <= ZSteps; loopZ++ ) {
               zLocal = GridScale * loopZ;
               gl.glVertex3f( -xExtent, zLocal, topOfHelix );
               gl.glVertex3f(  xExtent, zLocal, topOfHelix );
           }

        gl.glEnd();

    }

    private void cargaTextura(GL2 gl) {
        
        //Establecemos la ruta de la textura y la variable que tomara dicha textura
            try {
                //Se indica la localizacion de la figura                
                portada = TextureIO.newTexture(new File("src/imagen/Portada.png"), true);
      
            } catch (IOException e) {
                System.err.print("No se puede cargar textura" + e);
                System.exit(1);
            }            
            
    }
}

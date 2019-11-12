package muelle;

import com.jogamp.opengl.GL2;

public class Materiales {
    public static final int MAT_BRASS=0;
    public static final int MAT_BRONZE=1;
    public static final int MAT_POLISHED_BRONZE=2;
    public static final int MAT_CHROME=3;
    public static final int MAT_COPPER=4;
    public static final int MAT_POLISHED_COPPER=5;
    public static final int MAT_GOLD=6;
    public static final int MAT_POLISHED_GOLD=7;
    public static final int MAT_TIN=8;
    public static final int MAT_SILVER=9;
    public static final int MAT_POLISHED_SILVER=10;
    public static final int MAT_EMERALD=11;
    public static final int MAT_JADE=12;
    public static final int MAT_OBSIDIAN=13;
    public static final int MAT_PERL=14;
    public static final int MAT_RUBY=15;
    public static final int MAT_TURQUOISE =16;
    public static final int MAT_BLACK_PLASTIC=17;
    public static final int MAT_CYAN_PLASTIC=18;
    public static final int MAT_GREEN_PLASTIC=19;
    public static final int MAT_RED_PLASTIC=20;
    public static final int MAT_WHITE_PLASTIC=21;
    public static final int MAT_YELLOW_PLASTIC=22;
    public static final int MAT_BLACK_RUBBER=23;
    public static final int MAT_CYAN_RUBBER=24;
    public static final int MAT_GREEN_RUBBER=25;
    public static final int MAT_RED_RUBBER=26;
    public static final int MAT_WHITE_RUBBER=27;
    public static final int MAT_YELLOW_RUBBER=28;
    public static final int MAT_BRIGHT_WHITE=29;
    public static final int MAT_LESS_BRIGHT_WHITE=30;
    public static final int MAT_WARM_WHITE=31;

    private static final float[]ambient =
    {
        0.329412f, 0.223529f, 0.027451f,1.0f,  //MAT_BRASS=0;
        0.2125f, 0.1275f, 0.054f,1.0f,  //MAT_BRONZE=1;
        0.25f, 0.148f, 0.06475f,1.0f,  //MAT_POLISHED_BRONZE=2;
        0.25f, 0.25f, 0.25f,1.0f,  //MAT_CHROME=3;
        0.19125f, 0.0735f, 0.0225f,1.0f,   //MAT_CUPPER=4;
        0.2295f, 0.08825f, 0.0275f,1.0f,  //MAT_POLISHED_CUPPER=5;
        0.24725f, 0.1995f, 0.0745f,1.0f,  //MAT_GOLD=6;
        0.24725f, 0.2245f, 0.0645f,1.0f,  //MAT_POLISHED_GOLD=7;
        0.105882f, 0.058824f, 0.113725f,1.0f,  //MAT_TIN=8;
        0.19225f, 0.19225f, 0.19225f,1.0f,  //MAT_SILVER=9;
        0.23125f, 0.23125f, 0.23125f,1.0f,  //MAT_POLISHED_SILVER=10;
        0.0215f, 0.1745f, 0.0215f, 0.55f,  //MAT_EMERALD=11;
        0.135f, 0.2225f, 0.1575f, 0.95f,  //MAT_JADE=12;
        0.05375f, 0.05f, 0.06625f, 0.82f,  //MAT_OBSIDIAN=13;
        0.25f, 0.20725f, 0.20725f, 0.922f,  //MAT_PERL=14;
        0.1745f, 0.01175f, 0.01175f, 0.55f, //MAT_RUBY=15;
        0.1f, 0.18725f, 0.1745f, 0.8f,  //MAT_TURQUOISE=16;
        0.0f, 0.0f, 0.0f, 1.0f, //MAT_BLACK_PLASTIC=17;
        0.0f,0.1f,0.06f ,1.0f, //MAT_CYAN_PLASTIC=18;
        0.0f,0.0f,0.0f,1.0f, //MAT_GREEN_PLASTIC=19;
        0.0f,0.0f,0.0f,1.0f,  //MAT_RED_PLASTIC=20;
        0.0f,0.0f,0.0f,1.0f,  //MAT_WHITE_PLASTIC=21;
        0.0f,0.0f,0.0f,1.0f, //MAT_YELLOW_PLASTIC=22;
        0.02f, 0.02f, 0.02f, 1.0f,  //MAT_BLACK_RUBBER=23;
        0.0f,0.05f,0.05f,1.0f,  //MAT_CYAN_RUBBER=24;
        0.0f,0.05f,0.0f,1.0f,  //MAT_GREEN_RUBBER=25;
        0.05f,0.0f,0.0f,1.0f, //MAT_RED_RUBBER=26;
        0.05f,0.05f,0.05f,1.0f,  //MAT_WHITE_RUBBER=27;
        0.05f,0.05f,0.0f,1.0f,//MAT_YELLOW_RUBBER=28;
        0.2f, 0.2f, 0.2f,1.0f, //MAT_BRIGHT_WHITE=29;
        0.2f, 0.2f, 0.2f,1.0f, //MAT_LESS_BRIGHT_WHITE=30;
        0.3f, 0.2f, 0.2f,1.0f //MAT_WARM_WHITE=31;

    };

    private static final float[]diffuse=
    {
        0.780392f, 0.568627f, 0.113725f, 1.0f,  //MAT_BRASS=0;
        0.714f, 0.4284f, 0.18144f, 1.0f, //MAT_BRONZE=1;
        0.4f, 0.2368f, 0.1036f, 1.0f,  //MAT_POLISHED_BRONZE=2;
        0.4f, 0.4f, 0.4f, 1.0f, //MAT_CHROME=3;
        0.7038f, 0.27048f, 0.0828f, 1.0f,  //MAT_CUPPER=4;
        0.5508f, 0.2118f, 0.066f, 1.0f,  //MAT_POLISHED_CUPPER=5;
        0.75164f, 0.60648f, 0.22648f, 1.0f, //MAT_GOLD=6;
        0.34615f, 0.3143f, 0.0903f, 1.0f,//MAT_POLISHED_GOLD=7;
        0.427451f, 0.470588f, 0.541176f, 1.0f,  //MAT_TIN=8;
        0.50754f, 0.50754f, 0.50754f, 1.0f, //MAT_SILVER=9;
        0.2775f, 0.2775f, 0.2775f, 1.0f,  //MAT_POLISHED_SILVER=10;
        0.07568f, 0.61424f, 0.07568f, 0.55f,  //MAT_EMERALD=11;
        0.54f, 0.89f, 0.63f, 0.95f , //MAT_JADE=12;
        0.18275f, 0.17f, 0.22525f, 0.82f, //MAT_OBSIDIAN=13;
        1.0f, 0.829f, 0.829f, 0.922f,  //MAT_PERL=14;
        0.61424f, 0.04136f, 0.04136f, 0.55f, //MAT_RUBY=15;
        0.396f, 0.74151f, 0.69102f, 0.8f,  //MAT_TURQUOISE=16;
        0.01f, 0.01f, 0.01f, 1.0f,  //MAT_BLACK_PLASTIC=17;
        0.0f,0.50980392f,0.50980392f,1.0f, //MAT_CYAN_PLASTIC=18;
        0.1f,0.35f,0.1f,1.0f, //MAT_GREEN_PLASTIC=19;
        0.5f,0.0f,0.0f,1.0f,//MAT_RED_PLASTIC=20;
        0.55f,0.55f,0.55f,1.0f, //MAT_WHITE_PLASTIC=21;
        0.5f,0.5f,0.0f,1.0f, //MAT_YELLOW_PLASTIC=22;
        0.01f, 0.01f, 0.01f, 1.0f, //MAT_BLACK_RUBBER=23;
        0.4f,0.5f,0.5f,1.0f, //MAT_CYAN_RUBBER=24;
        0.4f,0.5f,0.4f,1.0f, //MAT_GREEN_RUBBER=25;
        0.5f,0.4f,0.4f,1.0f,//MAT_RED_RUBBER=26;
        0.5f,0.5f,0.5f,1.0f,//MAT_WHITE_RUBBER=27;
        0.5f,0.5f,0.4f,1.0f,//MAT_YELLOW_RUBBER=28;
        1.0f, 1.0f, 1.0f, 1.0f, //MAT_BRIGHT_WHITE=29;
        0.8f, 0.8f, 0.8f, 1.0f, //MAT_LESS_BRIGHT_WHITE=30;
        1.0f, 0.9f, 0.8f, 1.0f //MAT_WARM_WHITE=31;
    };

    private static final float[]specular=
    {
        0.992157f, 0.941176f, 0.807843f, 1.0f,  //MAT_BRASS=0;
        0.393548f, 0.271906f, 0.166721f, 1.0f, //MAT_BRONZE=1;
        0.774597f, 0.458561f, 0.200621f, 1.0f, //MAT_POLISHED_BRONZE=2;
        0.774597f, 0.774597f, 0.774597f, 1.0f, //MAT_CHROME=3;
        0.256777f, 0.137622f, 0.086014f, 1.0f, //MAT_CUPPER=4;
        0.580594f, 0.223257f, 0.0695701f, 1.0f, //MAT_POLISHED_CUPPER=5;
        0.628281f, 0.555802f, 0.366065f, 1.0f,  //MAT_GOLD=6;
        0.797357f, 0.723991f, 0.208006f, 1.0f ,//MAT_POLISHED_GOLD=7;
        0.333333f, 0.333333f, 0.521569f, 1.0f, //MAT_TIN=8;
        0.508273f, 0.508273f, 0.508273f, 1.0f, //MAT_SILVER=9;
        0.773911f, 0.773911f, 0.773911f, 1.0f, //MAT_POLISHED_SILVER=10;
        0.633f, 0.727811f, 0.633f, 0.55f,  //MAT_EMERALD=11;
        0.316228f, 0.316228f, 0.316228f, 0.95f, //MAT_JADE=12;
        0.332741f, 0.328634f, 0.346435f, 0.82f,  //MAT_OBSIDIAN=13;
        0.296648f, 0.296648f, 0.296648f, 0.922f, //MAT_PERL=14;
        0.727811f, 0.626959f, 0.626959f, 0.55f, //MAT_RUBY=15;
        0.297254f, 0.30829f, 0.306678f, 0.8f, //MAT_TURQUOISE=16;
        0.50f, 0.50f, 0.50f, 1.0f,//MAT_BLACK_PLASTIC=17;
        0.50196078f,0.50196078f,0.50196078f,1.0f,  //MAT_CYAN_PLASTIC=18;
        0.45f,0.55f,0.45f,1.0f, //MAT_GREEN_PLASTIC=19;
        0.7f,0.6f,0.6f,1.0f,  //MAT_RED_PLASTIC=20;
        0.70f,0.70f,0.70f,1.0f,  //MAT_WHITE_PLASTIC=21;
        0.60f,0.60f,0.50f,1.0f,  //MAT_YELLOW_PLASTIC=22;
        0.4f, 0.4f, 0.4f, 1.0f, //MAT_BLACK_RUBBER=23;
        0.04f,0.7f,0.7f,1.0f,  //MAT_CYAN_RUBBER=24;
        0.04f,0.7f,0.04f,1.0f, //MAT_GREEN_RUBBER=25;
        0.7f,0.04f,0.04f,1.0f, //MAT_RED_RUBBER=26;
        0.7f,0.7f,0.7f,1.0f, //MAT_WHITE_RUBBER=27;
        0.7f,0.7f,0.04f,1.0f ,//MAT_YELLOW_RUBBER=28;
        0.8f, 0.8f, 0.8f, 1.0f,//MAT_BRIGHT_WHITE=29;
        0.5f, 0.5f, 0.5f, 1.0f, //MAT_LESS_BRIGHT_WHITE=30;
        0.2f, 0.2f, 0.2f, 1.0f //MAT_WARM_WHITE=31;
    };

    private static final float[]shine=
    {
        27.8974f, //MAT_BRASS=0;
        25.6f, //MAT_BRONZE=1;
        76.8f,  //MAT_POLISHED_BRONZE=2;
        76.8f, //MAT_CHROME=3;
        12.8f, //MAT_CUPPER=4;
        51.2f, //MAT_POLISHED_CUPPER=5;
        51.2f,  //MAT_GOLD=6;
        83.2f,  //MAT_POLISHED_GOLD=7;
        9.84615f,//MAT_TIN=8;
        51.2f, //MAT_SILVER=9;
        89.6f,  //MAT_POLISHED_SILVER=10;
        76.8f, //MAT_EMERALD=11;
        12.8f, //MAT_JADE=12;
        38.4f,  //MAT_OBSIDIAN=13;
        11.264f, //MAT_PERL=14;
        76.8f, //MAT_RUBY=15;
        12.8f, //MAT_TURQUOISE=16;
        32.0f, //MAT_BLACK_PLASTIC=17;
        32.0f, //MAT_CYAN_PLASTIC=18;
        32.0f, //MAT_GREEN_PLASTIC=19;
        32.0f , //MAT_RED_PLASTIC=20;
        32.0f,  //MAT_WHITE_PLASTIC=21;
        32.0f,  //MAT_YELLOW_PLASTIC=22;
        10.0f, //MAT_BLACK_RUBBER=23;
        10.0f, //MAT_CYAN_RUBBER=24;
        10.0f, //MAT_GREEN_RUBBER=25;
        10.0f,//MAT_RED_RUBBER=26;
        10.0f, //MAT_WHITE_RUBBER=27;
        10.0f, //MAT_YELLOW_RUBBER=28;
        0.4f,//MAT_BRIGHT_WHITE=29;
        0.35f, //MAT_LESS_BRIGHT_WHITE=30;
        0.35f //MAT_WARM_WHITE=31;
    };

    /**
     * Set up material
     * @param gl The GL object in question
     * @param mat The material we want
     * @param side Must be GL_FRONT, GL_BACK, GL_FRONT_AND_BACK
     */
    static public void setMaterial(GL2 gl, int mat, int side)
    {
        if((mat<0) || (mat > shine.length-1))
            mat=MAT_BLACK_RUBBER; // saddest we got ?

        gl.glMaterialfv(side,GL2.GL_AMBIENT,ambient,mat*4);
        gl.glMaterialfv(side,GL2.GL_DIFFUSE,diffuse,mat*4);
        gl.glMaterialfv(side,GL2.GL_SPECULAR, specular, mat*4);
        gl.glMaterialf (side,GL2.GL_SHININESS,shine[mat]);
    }

    /**
     * Return the sopec for a material
     * @param mat
     * @param type
     * @return an array of floats, or null if noe material found or wrong spec
     */
    static public float[] getValue(int mat,int type)
    {
         if((mat<0) || (mat > shine.length-1))
            return null;
         float[] ret=null;
        switch (type) {
            case GL2.GL_AMBIENT:
                ret = new float[]{ambient[mat*4],ambient[mat*4+1],ambient[mat*4+2],ambient[mat*4+3]};
                break;
            case GL2.GL_DIFFUSE:
                ret = new float[]{diffuse[mat*4],diffuse[mat*4+1],diffuse[mat*4+2],diffuse[mat*4+3]};
                break;
            case GL2.GL_SPECULAR:
                ret = new float[]{specular[mat*4],specular[mat*4+1],specular[mat*4+2],specular[mat*4+3]};
                break;
            case GL2.GL_SHININESS:
                ret = new float[]{shine[mat*4]};
                break;
            default:
                break;
        }
         return ret;
    }
}

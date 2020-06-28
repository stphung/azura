package experiment.physics.debug.engine;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLException;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureCoords;

import experiment.physics.debug.objects.Sphere;
import experiment.physics.debug.objects.World;
import experiment.texture.TextureUtil;

public class Renderer {
    public void render(GLAutoDrawable drawable, World world) {
        final GL gl = drawable.getGL();
        final GLUT glut = new GLUT();
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        for (Sphere sphere : world) {
            gl.glPushMatrix();
            sphere.draw(gl, glut);
            gl.glPopMatrix();
        }

        // gl.glEnable(GL.GL_TEXTURE_2D);
        // Texture testTexture = null;
        // try {
        // testTexture = TextureUtil.loadTexture(gl, new
        // File("textures/test.png"), true);
        // } catch (GLException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        //        
        // gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE,
        // GL.GL_REPLACE);
        // TextureCoords tc = testTexture.getImageTexCoords();
        // testTexture.bind();
        //
        // gl.glBegin(GL.GL_QUADS);
        // gl.glTexCoord2f(tc.left(), tc.bottom());
        // gl.glVertex3d(-0.5, -0.5, 0.0);
        // gl.glTexCoord2f(tc.left(), tc.top());
        // gl.glVertex3d(-0.5, 0.5, 0.0);
        // gl.glTexCoord2f(tc.right(), tc.top());
        // gl.glVertex3d(0.5, 0.5, 0.0);
        // gl.glTexCoord2f(tc.right(), tc.bottom());
        // gl.glVertex3d(0.5, -0.5, 0.0);
        // gl.glEnd();
    }
}

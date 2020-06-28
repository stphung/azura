package experiment.texture;

import java.io.File;
import java.io.IOException;

import javax.media.opengl.GL;
import javax.media.opengl.GLException;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

public class TextureUtil {

    public static Texture loadTexture(GL gl, File textureFile, boolean mipmapped) throws GLException, IOException {
        Texture texture = TextureIO.newTexture(textureFile, mipmapped);
        texture.setTexParameteri(GL.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
        texture.setTexParameteri(GL.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
        return texture;
    }
}

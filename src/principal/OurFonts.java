package principal;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OurFonts {

    public static Font getTitleFont(float tamanho) {
        Font font = null;
        /*try {
         InputStream is = new FileInputStream("src/resources/texto.ttf");
         try {
         font = Font.createFont(Font.TRUETYPE_FONT, is);
         System.out.println("a funcao fonte ta funfando");
         } catch (FontFormatException ex) {
         //Logger.getLogger(OurFonts.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("font format Exception");
         } catch (IOException ex) {
         System.out.println("IO exception");
         }

         } catch (FileNotFoundException e) {
         System.out.println("File not found");
         e.printStackTrace();
         }*/
        try {
            File file = new File("src/resources/titulos.ttf");
            FileInputStream fis = new FileInputStream(file);
            font = Font.createFont(Font.TRUETYPE_FONT, fis);

        } catch (Exception e) {

        }
        font = font.deriveFont(Font.PLAIN, tamanho);

        return font;
    }
       public static Font getTextFont(float tamanho) {
        Font font = null;
        try {
            File file = new File("src/resources/texto.ttf");
            FileInputStream fis = new FileInputStream(file);
            font = Font.createFont(Font.TRUETYPE_FONT, fis);

        } catch (Exception e) {

        }
        font = font.deriveFont(Font.PLAIN, tamanho);

        return font;
    }

}

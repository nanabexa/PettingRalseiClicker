import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class OrganizadorArchivos {
    public static void main(String[] args) {
        // Cambia esta ruta a la carpeta donde tienes los archivos
        String carpetaOrigen = "";

        File carpeta = new File(carpetaOrigen);
        if (!carpeta.isDirectory()) {
            System.out.println("La ruta especificada no es una carpeta.");
            return;
        }

        File[] archivos = carpeta.listFiles();
        if (archivos == null) {
            System.out.println("No se pudieron leer los archivos.");
            return;
        }

        for (File archivo : archivos) {
            if (archivo.isFile()) {
                String nombre = archivo.getName();
                String[] partes = nombre.split("_", 2);
                String nombreCarpeta = partes[0];

                File subcarpeta = new File(carpeta, nombreCarpeta);
                if (!subcarpeta.exists()) {
                    subcarpeta.mkdir();
                }

                try {
                    Path destino = new File(subcarpeta, archivo.getName()).toPath();
                    Files.move(archivo.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Movido: " + archivo.getName() + " → " + subcarpeta.getName());
                } catch (IOException e) {
                    System.out.println("Error moviendo el archivo " + archivo.getName() + ": " + e.getMessage());
                }
            }
        }
    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(33, 111, 2, 13);
        GameProgress game2 = new GameProgress(23, 50, 33, 4444);
        GameProgress game3 = new GameProgress(55, 200, 12, 555);
        File game1Dat = new File("c://Games//savegames//game1.dat");
        File game2Dat = new File("c://Games//savegames//game2.dat");
        File game3Dat = new File("c://Games//savegames//game3.dat");
        saveGame("c://Games//savegames//game1.dat", game1);
        saveGame("c://Games//savegames//game2.dat", game2);
        saveGame("c://Games//savegames//game3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("c://Games//savegames//game1.dat");
        arrayList.add("c://Games//savegames//game2.dat");
        arrayList.add("c://Games//savegames//game3.dat");

        zipFiles("c://Games//savegames//zip.zip", arrayList);

        game1Dat.delete();
        game2Dat.delete();
        game3Dat.delete();
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

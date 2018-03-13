import db.DBHelper;
import models.Folder;
import models.File;
import models.Owner;

import java.util.List;

public class Runner {

    public static void main(String[] args) {



        Owner owner1 = new Owner("Jeff Bridges", "Jeffski196");
        DBHelper.save(owner1);
        Owner owner2 = new Owner("Kevin Bacon", "FarFaePuken");
        DBHelper.save(owner2);

        Folder folder1 = new Folder("Pics of me", owner1);
        DBHelper.save(folder1);
        Folder folder2 = new Folder("Pics of Jeff", owner2);
        DBHelper.save(folder2);
        Folder folder3 = new Folder("Poetry by me", owner1);
        DBHelper.save(folder3);
        Folder folder4 = new Folder("More pics of Jeff", owner2);
        DBHelper.save(folder4);

        File file1 = new File("hotdogs", "jpeg", 78, folder1);
        DBHelper.save(file1);
        File file2 = new File("bathtime", "jpeg", 124, folder2);
        DBHelper.save(file2);
        File file3 = new File("kevin_bacons_nose", "txt", 53, folder1);
        DBHelper.save(file3);
        File file4 = new File("riding_a_giraffe", "jpeg", 290, folder2);
        DBHelper.save(file4);


        List<File> allFiles = DBHelper.getAll(File.class);

        List<Folder> allFolders = DBHelper.getAll(Folder.class);

        List<Owner> allOwners = DBHelper.getAll(Owner.class);

        File foundFileById = DBHelper.find(File.class, file2.getId());

        Folder foundFolderById = DBHelper.find(Folder.class, folder1.getId());

        Owner foundOwnerById = DBHelper.find(Owner.class, owner2.getId());

        List<File> foundFileByFolder = DBHelper.getFilesByFolder(folder2);

        List<Folder> foundFolderByOwner = DBHelper.getFolderByOwner(owner1);




    }
}

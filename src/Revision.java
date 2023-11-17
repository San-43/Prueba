/**
 * Sandro Martínez Pérez
 * Fernando David Gómez Gutierrez
 * Carlos Alberto Lara Hernández*/
import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Revision implements Serializable {
    private int ID_Revision;
    private int ID_Animal;
    private int ID_Veterinarian;
    private LocalDate revisionDate;
    private String remarks;
    private String animalType;

    public Revision(int ID_Revision, int ID_Animal, int ID_Veterinarian, LocalDate revisionDate, String remarks) {
        this.ID_Revision = ID_Revision;
        this.ID_Animal = ID_Animal;
        this.ID_Veterinarian = ID_Veterinarian;
        this.revisionDate = revisionDate;
        this.remarks = remarks;
    }

    public Revision(int x) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Animal> mammalList = new ArrayList<>();
        ArrayList<Animal> birdList = new ArrayList<>();
        ArrayList<Veterinarian> veterinarianList = new ArrayList<>();

        mammalList = Mammal.getAnimals(mammalList);
        birdList = Bird.getAnimals(birdList);
        if (mammalList.isEmpty() && birdList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ninguna id perteneciente a animales registrada hasta el momento, por lo tanto no se puede generar la revisión", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        veterinarianList = Veterinarian.getVeterinarians(veterinarianList);
        if (veterinarianList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ninguna id perteneciente a veterinarios registrada hasta el momento, por lo tanto no se puede generar la revisión", "", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int ID_Revision;
        do {
            ID_Revision = -1;
            try {
                ID_Revision = Integer.parseInt(JOptionPane.showInputDialog("ID de la revisión"));
                if (ID_Revision <= 0) {
                    ID_Revision = 0;
                    JOptionPane.showMessageDialog(null, "La id debe ser mayor a 1", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                list = Main.getIds(list);
                for (Integer i :
                        list) {
                    if (i == ID_Revision) {
                        JOptionPane.showMessageDialog(null, "La id ya se encuentra registrada.", "", JOptionPane.WARNING_MESSAGE);
                        ID_Revision = -1;
                        break;
                    }
                }
                if (ID_Revision != -1) {
                    Main.setId(list, ID_Revision);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La id debe ser numérica", "", JOptionPane.ERROR_MESSAGE);
            }
        } while (ID_Revision <= 0);
        this.ID_Revision = ID_Revision;

        int ID_Animal;
        do {
            ID_Animal = -1;
            try {
                ID_Animal = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la id del animal"));
                if (ID_Animal <= 0) {
                    ID_Animal = 0;
                    JOptionPane.showMessageDialog(null, "La id debe ser mayor a 1", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                boolean isMammal = false, isBird = false;
                for (Animal i :
                        mammalList) {
                    if (i.getID() == ID_Animal) {
                        this.ID_Animal = ID_Animal;
                        this.animalType = "mammal";
                        isMammal = true;
                        break;
                    }
                }
                for (Animal i :
                        birdList) {
                    if (i.getID() == ID_Animal) {
                        this.ID_Animal = ID_Animal;
                        this.animalType = "bird";
                        isBird = true;
                        break;
                    }
                }
                if (!isBird && !isMammal) {
                    JOptionPane.showMessageDialog(null, "La id no pertenece a un animal registrado", "", JOptionPane.ERROR_MESSAGE);
                    ID_Animal = -1;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La id debe ser numérica", "", JOptionPane.ERROR_MESSAGE);
            }
        } while (ID_Animal <= 0);

        int ID_Veterinarian;
        do {
            ID_Veterinarian = -1;
            try {
                ID_Veterinarian = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la id del veterianario"));
                if (ID_Veterinarian <= 0) {
                    ID_Veterinarian = 0;
                    JOptionPane.showMessageDialog(null, "La id debe ser mayor a 1", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                boolean isVeterinarian = false;
                for (Veterinarian i :
                        veterinarianList) {
                    if (i.getID() == ID_Veterinarian) {
                        this.ID_Veterinarian = ID_Veterinarian;
                        isVeterinarian = true;
                        break;
                    }
                }
                if (!isVeterinarian) {
                    JOptionPane.showMessageDialog(null, "La id no pertenece a un veterinario registrado", "", JOptionPane.ERROR_MESSAGE);
                    ID_Veterinarian = -1;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La id debe ser numérica", "", JOptionPane.ERROR_MESSAGE);
            }
        } while (ID_Veterinarian <= 0);

        Veterinarian tmp2 = null;
        for (Veterinarian i :
                veterinarianList) {
            if (i.getID() == ID_Veterinarian)
                tmp2 = i;
        }

        LocalDate revisionDate;
        do {
            try {
                revisionDate = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de la revisión con el formato dd/MM/yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (revisionDate.isBefore(tmp2.getDateOfHire()) || revisionDate.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "La fecha debe estar en un rango entre la fecha de contratación del veterinario y la actual", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy y debe ser correcta", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);

        this.revisionDate = revisionDate;

        this.remarks = JOptionPane.showInputDialog("Observaciones");

    }
    public Revision() {
        this.ID_Revision = 0;
        this.ID_Animal = 0;
        this.ID_Veterinarian = 0;
        this.revisionDate = null;
        this.remarks = null;
    }

    public int getID_Revision() {
        return ID_Revision;
    }

    public int getID_Animal() {
        return ID_Animal;
    }

    public int getID_Veterinarian() {
        return ID_Veterinarian;
    }

    public LocalDate getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDate revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getData() {

        ArrayList<Veterinarian> veterinarianList = new ArrayList<>();
        veterinarianList = Veterinarian.getVeterinarians(veterinarianList);
        ArrayList<Animal> animals = new ArrayList<>();


        Animal tmp = this.animalType.equals("mammal") ? new Mammal() : new Bird();
        if (this.animalType.equals("mammal")) {
            animals = Mammal.getAnimals(animals);
            for (Animal i :
                    animals) {
                if (i.getID() == ID_Animal)
                    tmp = i;
            }
        } else {
            animals = Bird.getAnimals(animals);
            for (Animal i :
                    animals) {
                if (i.getID() == ID_Animal)
                    tmp = i;
            }
        }

        Veterinarian tmp2 = null;
        for (Veterinarian i :
                veterinarianList) {
            if (i.getID() == ID_Veterinarian)
                tmp2 = i;
        }

        assert tmp2 != null;
        return """
                    DETALLES DE UNA REVISIÓN
                ANIMAL
                    %s
                VETERINARIO
                    %s
                Fecha de Revisión: %s
                Observaciones: %s
                """.formatted(tmp.getData(), tmp2.getData(), getRevisionDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")), getRemarks());
    }

    static ArrayList<Revision> getRevisions(ArrayList<Revision> list) {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("revisions.dat"));
            list = (ArrayList) input.readObject();
            input.close();
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    public void serialize(ArrayList<Revision> list) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("revisions.dat"));
            list.add(this);
            output.writeObject(list);
            output.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encuentra el archivo", "", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}

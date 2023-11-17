/**
 * Sandro Martínez Pérez
 * Fernando David Gómez Gutierrez
 * Carlos Alberto Lara Hernández*/

import javax.swing.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int opt;
        do {
            do {
                opt = 0;
                try {
                    opt = Integer.parseInt(JOptionPane.showInputDialog("""
                                    ------ MENÚ CONTROL VETERINARIO ------
                                    1) Alta de un mamífero
                                    2) Alta de una ave
                                    3) Alta de un veterinario
                                    4) Alta de una revisión
                                    5) Listar mamíferos
                                    6) Listar aves
                                    7) Listar veterinarios
                                    8) Listar revisiones
                                    9) Ver detalle de un mamífero
                                    10) Ver detalle de una ave
                                    11) Ver detalle de un veterinario
                                    12) Ver detalle de una revisión
                                    13) Salir
                            Elija una opción ->"""));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "La opción debe ser numérica", "", JOptionPane.WARNING_MESSAGE);
                }
            } while (opt < 1);
            switch (opt) {
                case 1 -> {
                    ArrayList<Animal> list = new ArrayList<>();
                    Mammal m = new Mammal(1);
                    list = Mammal.getAnimals(list);
                    Animal.serialize(list, m);
                }
                case 2 -> {
                    ArrayList<Animal> list = new ArrayList<>();
                    Bird b = new Bird(1);
                    list = Bird.getAnimals(list);
                    Animal.serialize(list, b);
                }
                case 3 -> {
                    ArrayList<Veterinarian> list = new ArrayList<>();
                    Veterinarian v = new Veterinarian(1);
                    list = Veterinarian.getVeterinarians(list);
                    v.serialize(list);
                }
                case 4 -> {
                    ArrayList<Revision> list = new ArrayList<>();
                    Revision r = new Revision(1);
                    list = Revision.getRevisions(list);
                    r.serialize(list);
                }
                case 5 -> {
                    ArrayList<Animal> list = new ArrayList<>();
                    list = Mammal.getAnimals(list);

                    if (!list.isEmpty()) {
                        StringBuilder s = new StringBuilder("""
                                Id. Animal          Nombre          Fecha de Nacimiento           Pelaje     Categoría
                                ---------------------------------------------------------------------------------------
                                                     
                                """);
                        for (Animal i :
                                list) {
                            Mammal j = (Mammal) i;
                            s.append(j.getID()).append("                   ").append(j.getName()).append("                ").append(j.getDateOfBirth()).append("                  ").append(j.getHairType()).append("            ").append(j.getCategory()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, s, "LISTA DE MAMÍFEROS", JOptionPane.INFORMATION_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "No hay mamíferos registrados", "LISTA DE MAMÍFEROS", JOptionPane.WARNING_MESSAGE);

                }
                case 6 -> {
                    ArrayList<Animal> list = new ArrayList<>();
                    list = Bird.getAnimals(list);

                    if (!list.isEmpty()) {
                        StringBuilder s = new StringBuilder("""
                                Id. Animal          Nombre          Sexo        Vuela             Tamaño
                                ---------------------------------------------------------------------------------------
                                                     
                                """);
                        for (Animal i :
                                list) {
                            Bird j = (Bird) i;
                            s.append(j.getID()).append("                   ").append(j.getName()).append("                ").append(j.getSex()).append("                  ").append(j.isItFlights()).append("            ").append(j.getSize()).append("\n");
                        }

                        JOptionPane.showMessageDialog(null, s, "LISTA DE AVES", JOptionPane.INFORMATION_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "No hay aves registradas", "LISTA DE AVES", JOptionPane.WARNING_MESSAGE);
                }
                case 7 -> {
                    ArrayList<Veterinarian> list = new ArrayList<>();
                    list = Veterinarian.getVeterinarians(list);

                    if (!list.isEmpty()) {
                        StringBuilder s = new StringBuilder("""
                                Id. Animal          Nombre          Especialidad        Fecha de Contratación
                                ---------------------------------------------------------------------------------------
                                                     
                                """);
                        for (Veterinarian i :
                                list) {
                            s.append(i.getID()).append("                   ").append(i.getName()).append("                ").append(i.getSpecialization()).append("                  ").append(i.getDateOfHire().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, s, "LISTA DE VETERINARIOS", JOptionPane.INFORMATION_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "No hay veterinarios registrados", "LISTA DE VETERINARIOS", JOptionPane.WARNING_MESSAGE);
                }
                case 8 -> {
                    ArrayList<Revision> list = new ArrayList<>();
                    list = Revision.getRevisions(list);

                    if (!list.isEmpty()) {
                        StringBuilder s = new StringBuilder("""
                                Id. Revisión          Id. Animal          Id. Veterinario           Fecha de Revisión
                                ---------------------------------------------------------------------------------------
                                                     
                                """);
                        for (Revision i :
                                list) {
                            s.append(i.getID_Revision()).append("                   ").append(i.getID_Animal()).append("                ").append(i.getID_Veterinarian()).append("                  ").append(i.getRevisionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, s, "LISTA DE VETERINARIOS", JOptionPane.INFORMATION_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "No hay revisiones registradas", "LISTA DE REVISIONES", JOptionPane.WARNING_MESSAGE);
                }
                case 9, 10, 11, 12 -> {

                    ArrayList<Integer> list = new ArrayList<>();
                    ArrayList<Animal> mammalList = new ArrayList<>();
                    ArrayList<Animal> birdList = new ArrayList<>();
                    ArrayList<Veterinarian> veterinarianList = new ArrayList<>();
                    ArrayList<Revision> revisionList = new ArrayList<>();

                    list = Main.getIds(list);
                    if (list.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay ninguna id registrada hasta el momento", "", JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                    mammalList = Mammal.getAnimals(mammalList);
                    if (mammalList.isEmpty() && opt == 9) {
                        JOptionPane.showMessageDialog(null, "No hay ninguna id perteneciente a mamíferos registrada hasta el momento", "", JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                    birdList = Bird.getAnimals(birdList);
                    if (birdList.isEmpty() && opt == 10) {
                        JOptionPane.showMessageDialog(null, "No hay ninguna id perteneciente a aves registrada hasta el momento", "", JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                    veterinarianList = Veterinarian.getVeterinarians(veterinarianList);
                    if (veterinarianList.isEmpty() && opt == 11) {
                        JOptionPane.showMessageDialog(null, "No hay ninguna id perteneciente a veterinarios registrada hasta el momento", "", JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                    revisionList = Revision.getRevisions(revisionList);
                    if (revisionList.isEmpty() && opt == 12) {
                        JOptionPane.showMessageDialog(null, "No hay ninguna id perteneciente a revisiones registrada hasta el momento", "", JOptionPane.WARNING_MESSAGE);
                        continue;
                    }

                    int id;
                    do {
                        id = -1;
                        try {
                            id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la id a buscar"));
                            if (id <= 0) {
                                id = 0;
                                JOptionPane.showMessageDialog(null, "La id debe ser mayor a 1", "", JOptionPane.WARNING_MESSAGE);
                                continue;
                            }
                            for (Integer i : list) {
                                if (i == id) {
                                    switch (opt) {
                                        case 9 -> {
                                            for (Animal m :
                                                    mammalList) {
                                                if (m.getID() == id) {
                                                    JOptionPane.showMessageDialog(null, m.getData(), "DETALLES MAMÍFERO", JOptionPane.INFORMATION_MESSAGE);
                                                    id = -1;
                                                    break;
                                                }
                                            }
                                        }
                                        case 10 -> {
                                            for (Animal b :
                                                    birdList) {
                                                if (b.getID() == id) {
                                                    JOptionPane.showMessageDialog(null, b.getData(), "DETALLES AVE", JOptionPane.INFORMATION_MESSAGE);
                                                    id = -1;
                                                    break;
                                                }
                                            }
                                        }
                                        case 11 -> {
                                            for (Veterinarian v :
                                                    veterinarianList) {
                                                if (v.getID() == id) {
                                                    JOptionPane.showMessageDialog(null, v.getData(), "DETALLES VETERINARIO", JOptionPane.INFORMATION_MESSAGE);
                                                    id = -1;
                                                    break;
                                                }
                                            }
                                        }
                                        case 12 -> {
                                            for (Revision r :
                                                    revisionList) {
                                                if (r.getID_Revision() == id) {
                                                    JOptionPane.showMessageDialog(null, r.getData(), "DETALLES REVISIÓN", JOptionPane.INFORMATION_MESSAGE);
                                                    id = -1;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (id != -1) {
                                JOptionPane.showMessageDialog(null, "No se encontró la id", "", JOptionPane.INFORMATION_MESSAGE);
                                id = -1;
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La id debe ser numérica", "", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (id <= 0);
                }
                case 13 -> {
                    JOptionPane.showMessageDialog(null, "Sandro Martínez Pérez\nFernando David Gómez Gutierrez\nCarlos Alberto Lara Hernández", "Copyright", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } while (opt != 13);
    }

    static ArrayList<Integer> getIds(ArrayList<Integer> list) {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("ids.dat"));
            list = (ArrayList) input.readObject();
            input.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra el archivo ids.dat, se intentará crear", "", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error E/S", "", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Clase no encontrada", "", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }

    static void setId(ArrayList<Integer> list, Integer id) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("ids.dat"));
            list.add(id);
            output.writeObject(list);
            output.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo","", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S","", JOptionPane.ERROR_MESSAGE);
        }
    }
}
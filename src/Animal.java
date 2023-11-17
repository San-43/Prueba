/**
 * Sandro Martínez Pérez
 * Fernando David Gómez Gutierrez
 * Carlos Alberto Lara Hernández*/
import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Animal implements Serializable {
    private final int ID;
    private String name;
    private String color;
    private String sex;
    private final LocalDate dateOfBirth;

    public Animal(int ID, String name, String color, String sex, LocalDate dateOfBirth) {
        this.ID = ID;
        this.name = name;
        this.color = color;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }
    public Animal(int x) {
        int id;
        do {
            id = -1;
            try {
                id = Integer.parseInt(JOptionPane.showInputDialog("ID"));
                if (id <= 0) {
                    id = 0;
                    JOptionPane.showMessageDialog(null, "La id debe ser mayor a 1", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                ArrayList<Integer> list = new ArrayList<>();
                list = Main.getIds(list);
                if (list.isEmpty()) {
                    Main.setId(list, id);
                } else {
                    for (Integer i :
                            list) {
                        if (i == id) {
                            JOptionPane.showMessageDialog(null, "La id ya se encuentra registrada.", "", JOptionPane.WARNING_MESSAGE);
                            id = -1;
                            break;
                        }
                    }
                    if (id != -1) {
                        Main.setId(list, id);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "La id debe ser numérica", "", JOptionPane.ERROR_MESSAGE);
            }
        }while (id <= 0);

        String name = JOptionPane.showInputDialog("Ingrese el nombre");
        String color = JOptionPane.showInputDialog("Ingrese el color");
        String sex = JOptionPane.showInputDialog("Ingrese el sexo");

        LocalDate birthDate;
        do {
            try {
                birthDate = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de nacimiento con el formato dd/MM/yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (birthDate.getYear() < 2000 || birthDate.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "La fecha debe estar en un rango de año entre 2000 y el actual", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy y debe ser correcta", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);

        this.name = name;
        this.ID = id;
        this.dateOfBirth = birthDate;
        this.color = color;
        this.sex = sex;
    }

    public Animal() {
        this.ID = 0;
        this.name = null;
        this.color = null;
        this.sex = null;
        this.dateOfBirth = null;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Period getAge() {
        return Period.between(Objects.requireNonNull(dateOfBirth), LocalDate.now());
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return Objects.requireNonNull(dateOfBirth).format(formatter);
    }

    public String getData() {
        return """
                Id. Animal: %s
                Nombre: %s
                Color: %s
                Edad: %s años y %s y %s
                Sexo: %s
                Fecha de nacimiento: %s
                """.formatted(getID(), getName(), getColor(), getAge().getYears(), (getAge().getMonths() != 1) ? (getAge().getMonths() + " meses") : (getAge().getMonths() + " mes"), (getAge().getDays() != 1) ? (getAge().getDays() + " días") : (getAge().getDays() + " día") , getSex(), getDateOfBirth());
    }
    public static void serialize(ArrayList<Animal> list, Animal a) {
        try {
            ObjectOutputStream output;
            if (a instanceof Mammal) {
                output = new ObjectOutputStream(new FileOutputStream("mammals.dat"));
            } else {
                output = new ObjectOutputStream(new FileOutputStream("birds.dat"));
            }
            list.add(a);
            output.writeObject(list);
            output.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo","", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error E/S","", JOptionPane.ERROR_MESSAGE);
        }
    }
}

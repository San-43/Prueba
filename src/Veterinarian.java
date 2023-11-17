/**
 * Sandro Martínez Pérez
 * Fernando David Gómez Gutierrez
 * Carlos Alberto Lara Hernández*/

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Veterinarian implements Serializable {
    private final int ID;
    private String name;
    private String specialization;
    private String phone;
    private final LocalDate dateOfBirth;
    private final LocalDate dateOfHire;

    public Veterinarian(int ID, String name, String specialization, String phone, LocalDate dateOfBirth, LocalDate dateOfHire) {
        this.ID = ID;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.dateOfHire = dateOfHire;
    }

    public Veterinarian(int x) {
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
        } while (id <= 0);

        this.ID = id;
        this.name = JOptionPane.showInputDialog("Nombre");
        this.specialization = JOptionPane.showInputDialog("Especialización");
        this.phone = JOptionPane.showInputDialog("Teléfono");

        LocalDate dateOfBirth;
        do {
            try {
                dateOfBirth = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de nacimiento con el formato dd/MM/yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (dateOfBirth.getYear() < 1950 || dateOfBirth.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "La fecha debe estar en un rango de año entre 1950 y el actual", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy y debe ser correcta", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);
        this.dateOfBirth = dateOfBirth;

        LocalDate dateOfHire;
        do {
            try {
                dateOfHire = LocalDate.parse(JOptionPane.showInputDialog("Ingrese la fecha de contratación con el formato dd/MM/yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if(dateOfHire.isAfter(LocalDate.now())) {
                    JOptionPane.showMessageDialog(null, "La fecha debe estar en un rango de año entre 2000 y el actual", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                } else if (Period.between(dateOfBirth, dateOfHire).getYears() < 20 || Period.between(dateOfBirth, dateOfHire).getYears() > 80) {
                    JOptionPane.showMessageDialog(null, "La fecha de contratación debe ser 20 años posterior a la de nacimiento y menor a 80", "", JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La fecha debe tener formato dd/MM/yyyy y debe ser correcta", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);
        this.dateOfHire = dateOfHire;
    }
    public Veterinarian() {
        this.ID = 0;
        this.name = null;
        this.specialization = null;
        this.phone = null;
        this.dateOfBirth = null;
        this.dateOfHire = null;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public String getData() {
        return """
                Id. Veterinario: %s
                Nombre: %s
                Especialidad: %s
                Teléfono: %s
                Fecha de nacimiento: %s
                Fecha de Contratación: %s
                """.formatted(getID(), getName(), getSpecialization(), getPhone(), getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yy")), getDateOfHire().format(DateTimeFormatter.ofPattern("dd/MM/yy")));
    }

    static ArrayList<Veterinarian> getVeterinarians(ArrayList<Veterinarian> list) {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("veterinarians.dat"));
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

    public void serialize(ArrayList<Veterinarian> list) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("veterinarians.dat"));
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
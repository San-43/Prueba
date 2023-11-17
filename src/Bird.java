/**
 * Sandro Martínez Pérez
 * Fernando David Gómez Gutierrez
 * Carlos Alberto Lara Hernández*/
import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Bird extends Animal implements Serializable {
    private String typeOfBeak;
    private boolean itFlights;
    private String size;

    public Bird(int ID, String name, String color, String sex, LocalDate birthDate, String typeOfBeak, boolean itFlights, String size) {
        super(ID, name, color, sex, birthDate);
        this.typeOfBeak = typeOfBeak;
        this.itFlights = itFlights;
        this.size = size;
    }
    public Bird(int x) {
        super(1);
        this.typeOfBeak = JOptionPane.showInputDialog("Tipo de pico");
        this.size = JOptionPane.showInputDialog("Tamaño");

        do {
            String itFlights = JOptionPane.showInputDialog("¿Puede Volar?").toLowerCase();
            if (itFlights.equals("si")) {
                this.itFlights = true;
                break;
            } else if (itFlights.equals("no")) {
                this.itFlights = false;
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Escriba si o no por favor", "", JOptionPane.WARNING_MESSAGE);
            }
        } while (true);

    }
    public Bird() {
        super();
        this.typeOfBeak = null;
        this.itFlights = false;
        this.size = null;
    }

    public String getTypeOfBeak() {
        return typeOfBeak;
    }

    public void setTypeOfBeak(String typeOfBeak) {
        this.typeOfBeak = typeOfBeak;
    }

    public boolean isItFlights() {
        return itFlights;
    }

    public void setItFlights(boolean itFlights) {
        this.itFlights = itFlights;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String getData() {
        return super.getData() + """
                Tipo de pico: %s
                Vuela: %s
                Categoría: %s
                """.formatted(getTypeOfBeak(), isItFlights() ? "si" : "no", getSize());
    }
    static ArrayList<Animal> getAnimals(ArrayList<Animal> list) {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("birds.dat"));
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
}

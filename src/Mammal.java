/**
 * Sandro Martínez Pérez
 * Fernando David Gómez Gutierrez
 * Carlos Alberto Lara Hernández*/
import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Mammal extends Animal implements Serializable {
    private String hairType;
    private int numberOfTeeth;
    private String category;

    public Mammal(int ID, String name, String color, String sex, LocalDate birthDate, String hairType, int numberOfTeeth, String category) {
        super(ID, name, color, sex, birthDate);
        this.hairType = hairType;
        this.numberOfTeeth = numberOfTeeth;
        this.category = category;
    }

    public Mammal(int x) {
        super(1);
        String hairType = JOptionPane.showInputDialog("Tipo de pelo");

        int numberOfTeeth;
        do {
            numberOfTeeth = -1;
            try {
                numberOfTeeth = Integer.parseInt(JOptionPane.showInputDialog("Número de dientes"));
                if (numberOfTeeth < 0 || numberOfTeeth > 9280) {
                    JOptionPane.showMessageDialog(null, "Solo se acepta un rango 0-9280", null, JOptionPane.WARNING_MESSAGE);
                    numberOfTeeth = -1;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Solo se aceptan números", "", JOptionPane.ERROR_MESSAGE);
            }
        }while (numberOfTeeth < 0);

        String category = JOptionPane.showInputDialog("Categoría");

        this.hairType = hairType;
        this.numberOfTeeth = numberOfTeeth;
        this.category = category;
    }
    public Mammal() {
        super();
        this.hairType = null;
        this.numberOfTeeth = 0;
        this.category = null;
    }

    public String getHairType() {
        return hairType;
    }

    public void setHairType(String hairType) {
        this.hairType = hairType;
    }

    public int getNumberOfTeeth() {
        return numberOfTeeth;
    }

    public void setNumberOfTeeth(int numberOfTeeth) {
        this.numberOfTeeth = numberOfTeeth;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String getData() {
        return super.getData() + """
                Tipo de pelo: %s
                Cantidad de dientes: %s
                Categoría: %s
                """.formatted(getHairType(), getNumberOfTeeth(), getCategory());
    }
    static ArrayList<Animal> getAnimals(ArrayList<Animal> list) {
        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("mammals.dat"));
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

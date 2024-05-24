import java.util.ArrayList;
import java.io.*;

class PersonsManager implements Serializable {
    private ArrayList<Person> persons = new ArrayList<>();

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(int id) {
        persons.removeIf(e -> e.getId() == id);
    }

    public void savePersons(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(persons);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadPersons(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            persons = (ArrayList<Person>) in.readObject();
        }
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

}

import java.util.*;

public class PhoneBook {

    private HashMap<String, HashSet<String>> book = new HashMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        String existingName = getPureNameByPhone(phone);

        if (PhoneBookChecker.isName(name) && PhoneBookChecker.isPhone(phone)) {
            if (book.containsKey(name)) {
                HashSet nameSet = book.get(name);
                nameSet.add(phone);
                book.put(name, nameSet);

            } else {
                HashSet<String> nameSet = new HashSet<>();
                nameSet.add(phone);
                book.put(name, nameSet);
            }

        }

        if ((!existingName.isEmpty()) && !(existingName.equals(name))) {
            deletePhoneFromContact(phone, existingName);
        }
    }

    public void deletePhoneFromContact(String phone, String name) {
        if (book.get(name).size() == 1) {
            book.remove(name);
        } else {
            book.get(name).remove(phone);
        }
    }

    public String getNameByPhone(String phone) {
//        // формат одного контакта "Имя - Телефон"
//        // если контакт не найдены - вернуть пустую строку

        String name = getPureNameByPhone(phone);
        String tmp = "";

        if (!name.isEmpty()) {
            tmp = name + " - ";
            for (String tmpPhone : book.get(name)) {
                tmp = tmp + tmpPhone + ", ";
            }
            tmp = tmp.substring(0, tmp.length() - 2);
        }
        return tmp;
    }

    public String getPureNameByPhone(String phone) {
//        // формат одного контакта "Имя - Телефон"
//        // если контакт не найдены - вернуть пустую строку
        String name = "";
        for (Map.Entry<String, HashSet<String>> entry : book.entrySet()) {
            if (entry.getValue().contains(phone)) {
                name = entry.getKey();
            }
        }
        return name;
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet<String> nameContacts = new TreeSet<>();

        if (book.containsKey(name)) {
            String tmp = name + " - ";

            for (String phone : book.get(name)) {
                tmp = tmp + phone + ", ";
            }
            tmp = tmp.substring(0, tmp.length() - 2);
            nameContacts.add(tmp);
        }
        return nameContacts;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet

        TreeSet<String> allContacts = new TreeSet<>();

        String tmp = "";
        for (Map.Entry<String, HashSet<String>> entry : book.entrySet()) {

            tmp = entry.getKey() + " - ";
            for (String value : entry.getValue()) {
                tmp = tmp + value + ", ";
            }

            tmp = tmp.substring(0, tmp.length() - 2);
            allContacts.add(tmp);
        }
        return allContacts;
    }

}
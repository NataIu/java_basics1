import java.util.ArrayList;

public class TodoList {

    private ArrayList<String> arrayList = new ArrayList<>();

    public void add(String todo) {
        arrayList.add(todo);
    }

    public void add(int index, String todo) {
        if (indexExists(index,arrayList.size())) {
            arrayList.add(index, todo);
        }
        else {
            add(todo);
        }
    }

    public void edit(String todo, int index) {

        if (indexExists(index,arrayList.size())) {
            arrayList.set(index,todo);
        }
    }

    public void delete(int index) {
        if (indexExists(index,arrayList.size())) {
            arrayList.remove(index);
        }
    }

    public ArrayList<String> getTodos() {
        return arrayList;
    }

    private boolean indexExists(int index, int size) {

        if (index>=0 && index <size) {
           return true;
        }
        else {
            return false;
        }
    }

}
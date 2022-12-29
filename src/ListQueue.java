import java.util.List;

public class ListQueue<T> {
    private List<T> list;
    int index = 0;

    public ListQueue(List<T> list) {
        this.list = list;
    }

    public T pop() {
        index = (index + 1) % list.size();
        return list.get(index++);
    }

}

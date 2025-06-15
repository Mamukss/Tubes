package Interface_DAO;
import java.util.List;

public interface IDAOTransaksi<T, I> { // OK
    public void insert(T data);
    public List<T> showData(I data);
    public void update(T data, I data2, I data3);
    public void delete(I data, I data2);
}

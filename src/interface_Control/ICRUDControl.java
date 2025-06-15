/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interface_Control;

/**
 *
 * @author yobel
 */
public interface ICRUDControl<T, ID> {
    void insert(T entity);
    void update(T entity);
    void delete(ID id);
    
    ID generateId();
}

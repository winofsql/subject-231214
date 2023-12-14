# subject-231214

- ### コンストラクタのみ
  ```java
  package action;

  import java.sql.*;
  import javax.swing.*;
  import java.io.*;
  import java.util.*;
  
  public class LboxComboBox<E> extends JComboBox<E> {
  
      public LboxComboBox() {
          super();
      }
  
      public LboxComboBox(E[] items) {
          super(items);
      }
  
      public LboxComboBox(ComboBoxModel<E> aModel) {
          super(aModel);
      }
  
      public LboxComboBox(Vector<E> items) {
          super(items);
      }
  
  }
  ``` 

- ### コンボボックスのメソッドを拡張( v2 )
  ```java
  private int[] values;
  
  // 実際の値で、コンボボックスの該当する表示を行う
  public void setSelectedValue( int value ) {
  
      for( int i = 0; i < values.length; i++ ) {
  
          if ( values[i] == value ) {
              setSelectedIndex(i);
              break;
          }
  
      }
  
  }
  
  // 現在の状態の選択された文字列に対する実際の値を取得する
  public int getSelectedValue() {
  
      int target = this.getSelectedIndex();
      return values[target];
  
  }
  
  // コンボボックスの内部処理用のコンストラクタ
  public LboxComboBox(E[] items, int[] values) {
      super(items);
      this.values = values;
      this.views = items;     // 未使用
  }
  ```   

package action;

import java.sql.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class LboxComboBox<E> extends JComboBox<E> {

    private E[] views;

    private int[] values;
    private String[] values_string;

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

    public LboxComboBox() {
        super();
    }

    // 配列を渡すオリジナルコンストラクタ
    public LboxComboBox(E[] items) {
        super(items);
    }

    // コンボボックスの内部処理用のコンストラクタ
    public LboxComboBox(E[] items, int[] values) {
        super(items);
        this.values = values;
        this.views = items;     // 未使用
    }

    // 未使用 / 内部に保存する配列が文字列の配列の場合に使用する予定
    public LboxComboBox(E[] items, String[] values) {
        super(items);
        this.values_string = values;
    }

    public LboxComboBox(ComboBoxModel<E> aModel) {
        super(aModel);
    }

    public LboxComboBox(Vector<E> items) {
        super(items);
    }

    // @Override
    // public String toString() {

    //     return "ABC";

    // }


}
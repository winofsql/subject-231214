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
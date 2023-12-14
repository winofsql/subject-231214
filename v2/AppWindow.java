package action;

import java.sql.*;
import javax.swing.*;
import java.io.*;

public class AppWindow extends BaseWindow {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private JPanel jContentPane = null;

    // ボタン
    private JButton jButtonKakunin = null;
    private JButton jButtonCancel = null;
    private JButton jButtonUpdate = null;

    // 社員コード
    private JLabel lblScode = null;
    private JTextField jScode = null;

    // 氏名
    private JLabel lblSname = null;
    private JTextField jSname = null;

    // フリガナ
    private JLabel lblFuri = null;
    private JTextField jFuri = null;

    // 性別
    private JLabel lblSeibetu = null;
    private JTextField jSeibetu = null;

    private LboxComboBox<String> genderComboBox;
    private int[] genders = {0, 1};
    private String[] genderLabels = {"男性", "女性"};

    public int mainWidth = 600;
    public int mainHeight = 400;
    public String titleString = "Swing 社員マスタ更新処理( MySQL )";

    // *****************************************************
    // ボタン作成とクリックイベント
    // *****************************************************
    private JButton getJButtonKakunin() {
        if (jButtonKakunin == null) {
            jButtonKakunin = new JButton();
            // メインウインドウに対して、100x22 のボタンを追加
            jButtonKakunin.setBounds( 250, 30-3, 100, 22 );
            jButtonKakunin.setText("確認");
            jButtonKakunin.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    try {
                        conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost/lightbox?user=root&password=&characterEncoding=UTF-8"
                        );

                        // ステートメント
                        stmt = conn.createStatement();
                        // SQL 実行
                        String sCode = jScode.getText();
                        rs = stmt.executeQuery("select * from 社員マスタ where 社員コード = '" + sCode + "'");

                        // 一行取り出し
                        rs.next();

                        // 社員名
                        String sName = rs.getString("氏名");
                        jSname.setText(sName);

                        // 社員名
                        String sFuri = rs.getString("フリガナ");
                        jFuri.setText(sFuri);

                        // 整数
                        int seibetu = rs.getInt("性別");
                        jSeibetu.setText(seibetu+"");
                        // genderComboBox.setSelectedIndex(seibetu);
                        genderComboBox.setSelectedValue(seibetu);

                        // 文字列
                        String seibetu2 = String.format("%d", seibetu);
                        if ( seibetu2.equals("0") ) {
                            System.out.println("男性");
                        }
                        else {
                            System.out.println("女性");
                        }

                        rs.close();
                        stmt.close();
                        conn.close();

                    }
                    catch (Exception ex) {
                        System.out.println( ex.getMessage() );
                        ex.printStackTrace();
                    }

                    // 編集状態の切り替え
                    // 第一会話
                    jScode.setEditable(false);
                    jButtonKakunin.setEnabled(false);

                    // 第二会話
                    jSname.setEditable(true);
                    jFuri.setEditable(true);
                    genderComboBox.setEnabled(true);
                    jButtonCancel.setEnabled(true);
                    jButtonUpdate.setEnabled(true);


                }
            });
        }
        return jButtonKakunin;
    }

    private JButton getJButtonCancel() {
        if (jButtonCancel == null) {
            jButtonCancel = new JButton();
            jButtonCancel.setEnabled(false);
            // メインウインドウに対して、100x22 のボタンを追加
            jButtonCancel.setBounds( 250, 30+50+30+30+30, 100, 22 );
            jButtonCancel.setText("キャンセル");
            jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    // 編集状態の切り替え
                    // 第一会話
                    jScode.setEditable(true);
                    jButtonKakunin.setEnabled(true);

                    // 第二会話
                    jSname.setEditable(false);
                    jFuri.setEditable(false);
                    genderComboBox.setEnabled(false);
                    jButtonCancel.setEnabled(false);
                    jButtonUpdate.setEnabled(false);

                    jSname.setText("");
                    jFuri.setText("");
                    // 0 でコンボボックス先頭
                    genderComboBox.setSelectedIndex(0);

                    // フォーカス変更
                    jScode.requestFocus();

                }
            });
        }
        return jButtonCancel;
    }

    private JButton getJButtonUpdate() {
        if (jButtonUpdate == null) {
            jButtonUpdate = new JButton();
            jButtonUpdate.setEnabled(false);
            // メインウインドウに対して、100x22 のボタンを追加
            jButtonUpdate.setBounds( 60, 30+50+30+30+30, 100, 22 );
            jButtonUpdate.setText("更新");
            jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {


                    try {
                        conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost/lightbox?user=root&password=&characterEncoding=UTF-8"
                        );

                        // ステートメント
                        stmt = conn.createStatement();
                        // SQL 実行
                        String sCode = jScode.getText();
                        // rs = stmt.executeQuery("select * from 社員マスタ where 社員コード = '" + sCode + "'");

                        // 一行取り出し
                        // rs.next();

                        // 社員名
                        String sName = jSname.getText();

                        // 社員名
                        String sFuri = jFuri.getText();

                        // 性別の処理
                        // Object targetGender = genderComboBox.getSelectedItem();
                        int target = genderComboBox.getSelectedValue();
                        String sSeibetu = target + "";

                        String sql = "UPDATE 社員マスタ SET 氏名 = '"+ sName +"', フリガナ = '"+ sFuri +"', 性別 = " + sSeibetu + " WHERE 社員コード = '" + sCode + "'";
                        System.out.println(sql);

                        stmt.executeUpdate(sql);

                        stmt.close();
                        conn.close();

                    }
                    catch (Exception ex) {
                        System.out.println( ex.getMessage() );
                        ex.printStackTrace();
                    }


                    // 編集状態の切り替え
                    // 第一会話
                    jScode.setEditable(true);
                    jButtonKakunin.setEnabled(true);

                    // 第二会話
                    jSname.setEditable(false);
                    jFuri.setEditable(false);
                    genderComboBox.setEnabled(false);
                    jButtonCancel.setEnabled(false);
                    jButtonUpdate.setEnabled(false);

                    jSname.setText("");
                    jFuri.setText("");
                    // 0 でコンボボックス先頭
                    genderComboBox.setSelectedIndex(0);

                    // フォーカス変更
                    jScode.requestFocus();

                }
            });
        }
        return jButtonUpdate;
    }

    // *****************************************************
    // コンストラクタ
    // *****************************************************
    public AppWindow() {
        super();
        initialize();
    }

    // *****************************************************
    // 初期処理
    // *****************************************************
    private void initialize() {
        // ウインドウサイズの決定
        this.setSize(mainWidth, mainHeight);

        // ウインドウ位置の変更
        centerWindow();

        // パネルを適用
        this.setContentPane(getJContentPane());

        // タイトルセット
        this.setTitle(titleString);

        // カレントディレクトリの表示
        File cur = new File("");
        System.out.println(cur.getAbsolutePath());
    }

    // *****************************************************
    // 画面( パネル作成 )
    // *****************************************************
    private JPanel getJContentPane() {

        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);

            // 確認ボタンを画面に追加
            jContentPane.add(getJButtonKakunin(), null);
            jContentPane.add(getJButtonCancel(), null);
            jContentPane.add(getJButtonUpdate(), null);

            lblScode = new JLabel("社員コード");
            lblScode.setBounds(60, 30, 80, 19);
            jContentPane.add(lblScode);

            jScode = new JTextField();
            jScode.setBounds(150, 30, 60, 19);
            jContentPane.add(jScode);

            lblSname = new JLabel("氏名");
            lblSname.setBounds(60, 30+50, 80, 19);
            jContentPane.add(lblSname);

            jSname = new JTextField();
            jSname.setBounds(150, 30+50, 120, 19);
            jContentPane.add(jSname);

            lblFuri = new JLabel("フリガナ");
            lblFuri.setBounds(60, 30+50+30, 80, 19);
            jContentPane.add(lblFuri);

            jFuri = new JTextField();
            jFuri.setBounds(150, 30+50+30, 120, 19);
            jContentPane.add(jFuri);

            lblSeibetu = new JLabel("性別");
            lblSeibetu.setBounds(60, 30+50+30+30, 80, 19);
            jContentPane.add(lblSeibetu);

            // 非表示の性別テキストフィールド
            jSeibetu = new JTextField();
            jSeibetu.setBounds(150, 30+50+30+30, 30, 19);
            jContentPane.add(jSeibetu);
            jSeibetu.setVisible(false);

            genderComboBox = new LboxComboBox<String>(genderLabels,genders);
            genderComboBox.setBounds(150, 30+50+30+30, 150, 19);
            jContentPane.add(genderComboBox);


            // 初期状態の画面制御
            jSname.setEditable(false);
            jFuri.setEditable(false);
            genderComboBox.setEnabled(false);

        }
        return jContentPane;
    }

}

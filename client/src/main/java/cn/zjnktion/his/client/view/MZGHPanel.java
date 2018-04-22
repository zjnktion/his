package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zjnktion
 */
public class MZGHPanel extends JPanel {

    private int contentWidth;
    private int contentHeight;
    private final int readWidth = 400;
    private final int formWidth;

    public MZGHPanel(int contentWidth, int contentHeight) {
        super(null);
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
        this.formWidth = contentWidth - readWidth;
        this.setBounds(0, 0, contentWidth, contentHeight);
        initComponents();
    }

    private void initComponents() {
        initFormPanel();
        initReadPanel();
    }

    private JTextField nameText;
    private JTextField cardnumText;
    private JComboBox<String> sexBox;
    private JComboBox<String> typeBox;
    private JTextField ageText;
    private JLabel dateLabel;
    private JTextField phoneText;
    private JTextField addressText;
    private JButton submitBtn;

    private void initFormPanel() {
        // 初始化表单区域
        JPanel formPanel = new JPanel(null);
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "挂号申请表单"));
        formPanel.setBounds(0, 0, formWidth, contentHeight);
        this.add(formPanel);
        int lineWidth = 780;
        int lineHeight = 30;
        int labelWidth = 80;
        int fieldWidth = 300;
        int xGrap = 20;
        int yGrap = 10;
        int baseY = 60;
        JLabel nameLable = new JLabel("姓名：", SwingConstants.RIGHT);
        nameLable.setBounds((formWidth - lineWidth) / 2, baseY, labelWidth, lineHeight);
        formPanel.add(nameLable);
        nameText = new JTextField();
        nameText.setBounds(nameLable.getX() + nameLable.getWidth(), baseY, fieldWidth, lineHeight);
        formPanel.add(nameText);
        JLabel cardnumLabel = new JLabel("电脑号：", SwingConstants.RIGHT);
        cardnumLabel.setBounds(nameText.getX() + nameText.getWidth() + xGrap, baseY, labelWidth, lineHeight);
        formPanel.add(cardnumLabel);
        cardnumText = new JTextField();
        cardnumText.setBounds(cardnumLabel.getX() + cardnumLabel.getWidth(), baseY, fieldWidth, lineHeight);
        formPanel.add(cardnumText);
        JLabel sexLabel = new JLabel("性别：", SwingConstants.RIGHT);
        sexLabel.setBounds((formWidth - lineWidth) / 2, baseY + lineHeight + yGrap, labelWidth, lineHeight);
        formPanel.add(sexLabel);
        sexBox = new JComboBox<>(new String[] {"男", "女"});
        sexBox.setBounds(sexLabel.getX() + sexLabel.getWidth(), baseY + lineHeight + yGrap, fieldWidth, lineHeight);
        formPanel.add(sexBox);
        JLabel typeLabel = new JLabel("就诊类型：", SwingConstants.RIGHT);
        typeLabel.setBounds(sexBox.getX() + sexBox.getWidth() + xGrap, baseY + lineHeight + yGrap, labelWidth, lineHeight);
        formPanel.add(typeLabel);
        typeBox = new JComboBox<>(new String[]{"全科", "内科", "外科"});
        typeBox.setBounds(typeLabel.getX() + typeLabel.getWidth(), baseY + lineHeight + yGrap, fieldWidth, lineHeight);
        formPanel.add(typeBox);
        JLabel ageLable = new JLabel("年龄：", SwingConstants.RIGHT);
        ageLable.setBounds((formWidth - lineWidth) / 2, baseY + 2 * (lineHeight + yGrap), labelWidth, lineHeight);
        formPanel.add(ageLable);
        ageText = new JTextField();
        ageText.setBounds(ageLable.getX() + ageLable.getWidth(), baseY + 2 * (lineHeight + yGrap), fieldWidth, lineHeight);
        formPanel.add(ageText);
        JLabel date = new JLabel("就诊时间：", SwingConstants.RIGHT);
        date.setBounds(nameText.getX() + nameText.getWidth() + xGrap, baseY + 2 * (lineHeight + yGrap), labelWidth, lineHeight);
        formPanel.add(date);
        dateLabel = new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        dateLabel.setBounds(date.getX() + date.getWidth(), baseY + 2 * (lineHeight + yGrap), fieldWidth, lineHeight);
        formPanel.add(dateLabel);
        JLabel phoneLable = new JLabel("联系电话：", SwingConstants.RIGHT);
        phoneLable.setBounds((formWidth - lineWidth) / 2, baseY + 3 * (lineHeight + yGrap), labelWidth, lineHeight);
        formPanel.add(phoneLable);
        phoneText = new JTextField();
        phoneText.setBounds(phoneLable.getX() + phoneLable.getWidth(), baseY + 3 * (lineHeight + yGrap), fieldWidth, lineHeight);
        formPanel.add(phoneText);
        JLabel addressLabel = new JLabel("联系地址：", SwingConstants.RIGHT);
        addressLabel.setBounds(nameText.getX() + nameText.getWidth() + xGrap, baseY + 3 * (lineHeight + yGrap), labelWidth, lineHeight);
        formPanel.add(addressLabel);
        addressText = new JTextField();
        addressText.setBounds(addressLabel.getX() + addressLabel.getWidth(), baseY + 3 * (lineHeight + yGrap), fieldWidth, lineHeight);
        formPanel.add(addressText);
        submitBtn = new JButton("提交");
        submitBtn.setBounds((formWidth - 120) / 2, baseY + 4 * (lineHeight + yGrap), 120, lineHeight);
        formPanel.add(submitBtn);
    }

    private JTextField icnumText;
    private JTextField icpwdText;
    private JButton readBtn;
    private JButton grepBtn;

    private void initReadPanel() {
        // 初始化读卡区域
        JPanel readPanel = new JPanel(null);
        readPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "读卡区域"));
        readPanel.setBounds(formWidth, 0, readWidth, contentHeight);
        this.add(readPanel);
        int readWidth = readPanel.getWidth();
        JLabel readLabel = new JLabel("读取IC卡", SwingConstants.RIGHT);
        readLabel.setFont(new Font(Font.DIALOG, 1, 20));
        readLabel.setBounds(readWidth / 2 - 60, 10, 120, 40);
        readPanel.add(readLabel);
        int lineWidth = 380;
        int lineHeight = 30;
        int labelWidth = 80;
        int fieldWidth = 300;
        int yGrap = 10;
        int baseY = 60;
        JLabel icnumLabel = new JLabel("IC卡编号：", SwingConstants.RIGHT);
        icnumLabel.setBounds((readWidth - lineWidth) / 2, baseY, labelWidth, lineHeight);
        readPanel.add(icnumLabel);
        icnumText = new JTextField();
        icnumText.setBounds(icnumLabel.getX() + icnumLabel.getWidth(), baseY, fieldWidth, lineHeight);
        readPanel.add(icnumText);
        JLabel icpwdLabel = new JLabel("IC卡密钥：", SwingConstants.RIGHT);
        icpwdLabel.setBounds((readWidth - lineWidth) / 2, baseY + lineHeight + yGrap, labelWidth, lineHeight);
        readPanel.add(icpwdLabel);
        icpwdText = new JTextField();
        icpwdText.setBounds(icpwdLabel.getX() + icpwdLabel.getWidth(), baseY + lineHeight + yGrap, fieldWidth, lineHeight);
        readPanel.add(icpwdText);
        readBtn = new JButton("读卡");
        readBtn.setBounds((readWidth - 240) / 2, baseY + 2 * (lineHeight + yGrap), 120, lineHeight);
        readPanel.add(readBtn);
        grepBtn = new JButton("获取信息");
        grepBtn.setBounds(readBtn.getX() + readBtn.getWidth(), baseY + 2 * (lineHeight + yGrap), 120, lineHeight);
        readPanel.add(grepBtn);
    }
}

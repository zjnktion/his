package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;

public class MZTHPanel extends JPanel {

    private int contentWidth;
    private int contentHeight;
    private final int readWidth = 400;
    private final int formWidth;

    public MZTHPanel(int contentWidth, int contentHeight) {
        super(null);
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
        this.formWidth = contentWidth - readWidth;
        this.searchHeight = 60;
        this.resultHeight = contentHeight - searchHeight;
        this.setBounds(0, 0, contentWidth, contentHeight);
        initComponents();
    }

    private void initComponents() {
        initSearchPanel();
        initResultPanel();
        initReadPanel();
    }

    private int searchHeight;
    private JTextField nameInput;
    private JTextField numInput;
    private JTextField codeInput;
    private JTextField icnumInput;
    private JButton searchBtn;
    private JButton resetBth;

    private void initSearchPanel() {
        // 初始搜索区域
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "搜索区域"));
        searchPanel.setBounds(0, 0, formWidth, searchHeight);
        this.add(searchPanel);
        int baseX = 20;
        int baseY = 20;
        int xGap = 10;
        int inputWidth = 130;
        JLabel nameLabel = new JLabel("姓名：", SwingConstants.RIGHT);
        nameLabel.setBounds(baseX, baseY, 40, 30);
        searchPanel.add(nameLabel);
        nameInput = new JTextField();
        nameInput.setBounds(nameLabel.getX() + nameLabel.getWidth(), baseY, inputWidth, 30);
        searchPanel.add(nameInput);
        JLabel numLabel = new JLabel("门诊号：", SwingConstants.RIGHT);
        numLabel.setBounds(nameInput.getX() + nameInput.getWidth() + xGap, baseY, 55, 30);
        searchPanel.add(numLabel);
        numInput = new JTextField();
        numInput.setBounds(numLabel.getX() + numLabel.getWidth(), baseY, inputWidth, 30);
        searchPanel.add(numInput);
        JLabel codeLabel = new JLabel("挂号单编码：", SwingConstants.RIGHT);
        codeLabel.setBounds(numInput.getX() + numInput.getWidth() + xGap, baseY, 80, 30);
        searchPanel.add(codeLabel);
        codeInput = new JTextField();
        codeInput.setBounds(codeLabel.getX() + codeLabel.getWidth(), baseY, inputWidth, 30);
        searchPanel.add(codeInput);
        JLabel icnumLabel = new JLabel("社保号：", SwingConstants.RIGHT);
        icnumLabel.setBounds(codeInput.getX() + codeInput.getWidth() + xGap, baseY, 55, 30);
        searchPanel.add(icnumLabel);
        icnumInput = new JTextField();
        icnumInput.setBounds(icnumLabel.getX() + icnumLabel.getWidth(), baseY, inputWidth, 30);
        searchPanel.add(icnumInput);
        searchBtn = new JButton("搜索");
        searchBtn.setBounds(icnumInput.getX() + icnumInput.getWidth() + xGap, baseY, 80, 30);
        searchPanel.add(searchBtn);
        resetBth = new JButton("重置");
        resetBth.setBounds(searchBtn.getX() + searchBtn.getWidth(), baseY, 80, 30);
        searchPanel.add(resetBth);
    }

    private int resultHeight;
    private JTable resultTable;

    private void initResultPanel() {
        // 初始化结果区域
        JPanel resultPanel = new JPanel(null);
        resultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "搜索结果"));
        resultPanel.setBounds(0, searchHeight, formWidth, resultHeight);
        this.add(resultPanel);
        resultTable = new JTable(new Object[][]{{"zjn", "12", "全科", "26980370", "201709010012", "男", "2017-09-01", "退号"}, {"zjn", "13", "全科", "26980370", "201709010012", "男", "2017-09-01", "退号"}}, new Object[]{"姓名", "门诊号", "就诊类型", "社保号", "挂号单编码", "性别", "挂号时间", "操作"});
        resultTable.setFillsViewportHeight(true);
        resultTable.getTableHeader().setPreferredSize(new Dimension(1, 24));
        resultTable.setRowHeight(22);
        JScrollPane jsp = new JScrollPane(resultTable);
        jsp.setBounds(0, 0, resultPanel.getWidth(), resultPanel.getHeight());
        resultPanel.add(jsp);
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
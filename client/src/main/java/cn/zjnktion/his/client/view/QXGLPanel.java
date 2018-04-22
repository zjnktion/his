package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;

public class QXGLPanel extends JPanel {

    private int contentWidth;
    private int contentHeight;
    private int tableWidth;
    private int functionWidth;
    private int searchHeight;
    private int tableHeight;

    public QXGLPanel(int contentWidth, int contentHeight) {
        super(null);
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
        this.tableWidth = 800;
        this.functionWidth = contentWidth - tableWidth;
        this.searchHeight = 60;
        this.tableHeight = contentHeight - searchHeight;
        this.setBounds(0, 0, contentWidth, contentHeight);
        initComponents();
    }

    private void initComponents() {
        initSearchPanel();
        initResultPanel();
        initFunctionPanel();
    }

    private JTextField nameInput;
    private JTextField accountInput;
    private JTextField codeInput;
    private JButton searchBtn;
    private JButton resetBth;

    private void initSearchPanel() {
        // 初始搜索区域
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "搜索区域"));
        searchPanel.setBounds(0, 0, tableWidth, searchHeight);
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
        JLabel numLabel = new JLabel("账号：", SwingConstants.RIGHT);
        numLabel.setBounds(nameInput.getX() + nameInput.getWidth() + xGap, baseY, 55, 30);
        searchPanel.add(numLabel);
        accountInput = new JTextField();
        accountInput.setBounds(numLabel.getX() + numLabel.getWidth(), baseY, inputWidth, 30);
        searchPanel.add(accountInput);
        JLabel codeLabel = new JLabel("编码：", SwingConstants.RIGHT);
        codeLabel.setBounds(accountInput.getX() + accountInput.getWidth() + xGap, baseY, 80, 30);
        searchPanel.add(codeLabel);
        codeInput = new JTextField();
        codeInput.setBounds(codeLabel.getX() + codeLabel.getWidth(), baseY, inputWidth, 30);
        searchPanel.add(codeInput);
        searchBtn = new JButton("搜索");
        searchBtn.setBounds(codeInput.getX() + codeInput.getWidth() + xGap, baseY, 80, 30);
        searchPanel.add(searchBtn);
        resetBth = new JButton("重置");
        resetBth.setBounds(searchBtn.getX() + searchBtn.getWidth(), baseY, 80, 30);
        searchPanel.add(resetBth);
    }

    private JTable resultTable;

    private void initResultPanel() {
        // 初始化结果区域
        JPanel resultPanel = new JPanel(null);
        resultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "搜索结果"));
        resultPanel.setBounds(0, searchHeight, tableWidth, tableHeight);
        this.add(resultPanel);
        resultTable = new JTable(new Object[][]{{"郑医生", "zhengjiangning", "YS076"},{"李护士", "lihushi", "HS086"}}, new Object[]{"姓名", "账号", "编码"});
        resultTable.setFillsViewportHeight(true);
        resultTable.getTableHeader().setPreferredSize(new Dimension(1, 24));
        resultTable.setRowHeight(22);
        JScrollPane jsp = new JScrollPane(resultTable);
        jsp.setBounds(0, 0, resultPanel.getWidth(), resultPanel.getHeight());
        resultPanel.add(jsp);
    }

    private JCheckBox mzghCheck;
    private JCheckBox mzthCheck;
    private JCheckBox mzsfCheck;
    private JCheckBox mztfCheck;
    private JCheckBox mzcfCheck;
    private JCheckBox sysfCheck;
    private JCheckBox sytfCheck;
    private JCheckBox mzblCheck;
    private JCheckBox sylsCheck;
    private JCheckBox ysglCheck;
    private JCheckBox ypglCheck;
    private JCheckBox qxglCheck;

    private void initFunctionPanel() {
        // 初始化权限管理
        JPanel functionPanel = new JPanel();
        functionPanel.setLayout(new GridLayout(0, 2));
        functionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "权限区域"));
        functionPanel.setBounds(tableWidth, 0, functionWidth, 200);
        this.add(functionPanel);
        mzghCheck = new JCheckBox("门诊挂号", true);
        functionPanel.add(mzghCheck);
        mzthCheck = new JCheckBox("门诊退号", true);
        functionPanel.add(mzthCheck);
        mzsfCheck = new JCheckBox("门诊收费", true);
        functionPanel.add(mzsfCheck);
        mztfCheck = new JCheckBox("门诊退费", true);
        functionPanel.add(mztfCheck);
        mzcfCheck = new JCheckBox("门诊处方", true);
        functionPanel.add(mzcfCheck);
        sysfCheck = new JCheckBox("售药收费", true);
        functionPanel.add(sysfCheck);
        sytfCheck = new JCheckBox("售药退费", true);
        functionPanel.add(sytfCheck);
        mzblCheck = new JCheckBox("门诊病历", true);
        functionPanel.add(mzblCheck);
        sylsCheck = new JCheckBox("售药历史", true);
        functionPanel.add(sylsCheck);
        ysglCheck = new JCheckBox("医生管理", true);
        functionPanel.add(ysglCheck);
        ypglCheck = new JCheckBox("药品管理", true);
        functionPanel.add(ypglCheck);
        qxglCheck = new JCheckBox("权限管理", true);
        functionPanel.add(qxglCheck);

        JPanel btnPanel = new JPanel(null);
        btnPanel.setBounds(tableWidth, 200, functionWidth, 60);
        this.add(btnPanel);
        JButton commitBtn = new JButton("应用");
        commitBtn.setBounds(functionWidth / 2 - 60, 0, 60, 30);
        btnPanel.add(commitBtn);
        JButton cancelBtn = new JButton("取消");
        cancelBtn.setBounds(functionWidth / 2, 0, 60, 30);
        btnPanel.add(cancelBtn);
    }

}
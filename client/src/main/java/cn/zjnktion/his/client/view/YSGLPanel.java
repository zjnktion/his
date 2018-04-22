package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;

public class YSGLPanel extends JPanel {

    private int contentWidth;
    private int contentHeight;
    private int tableWidth;
    private int functionWidth;
    private int searchHeight;
    private int tableHeight;
    private int updateHeight = 250;
    private int insertHeight = 250;

    public YSGLPanel(int contentWidth, int contentHeight) {
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
        initUpdatePanel();
        initInsertPanel();
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

    private JTextField nameUpdate;
    private JTextField accountUpdate;
    private JTextField codeUpdate;
    private JTextField pwdUpdate;

    private void initUpdatePanel() {
        // 初始化更新管理
        JPanel updatePanel = new JPanel(null);
        updatePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "修改信息区域"));
        updatePanel.setBounds(tableWidth, 0, functionWidth, updateHeight);
        this.add(updatePanel);
        int baseX = 20;
        int baseY = 20;
        int xGap = 20;
        int yGap = 10;
        int lineWidth = updatePanel.getWidth();
        int lineHeight = 30;
        int currentY = baseY + yGap;
        // 姓名
        JLabel nameLabel = new JLabel("姓名：", SwingConstants.RIGHT);
        nameLabel.setBounds(baseX, currentY, 40, lineHeight);
        updatePanel.add(nameLabel);
        nameUpdate = new JTextField("郑医生");
        nameUpdate.setBounds(nameLabel.getX() + nameLabel.getWidth(), currentY, lineWidth - nameLabel.getWidth() - nameLabel.getX() - xGap, lineHeight);
        updatePanel.add(nameUpdate);
        // 第二行
        currentY += (yGap + lineHeight);
        // 账号
        JLabel accountLabel = new JLabel("账号：", SwingConstants.RIGHT);
        accountLabel.setBounds(baseX, currentY, 40, lineHeight);
        updatePanel.add(accountLabel);
        accountUpdate = new JTextField("zhengjiangning");
        accountUpdate.setBounds(accountLabel.getX() + accountLabel.getWidth(), currentY, lineWidth - accountLabel.getX() - accountLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(accountUpdate);
        // 第三行
        currentY += (yGap + lineHeight);
        // 编码
        JLabel codeLabel = new JLabel("编码：", SwingConstants.RIGHT);
        codeLabel.setBounds(baseX, currentY, 40, lineHeight);
        updatePanel.add(codeLabel);
        codeUpdate = new JTextField("YS076");
        codeUpdate.setBounds(codeLabel.getX() + codeLabel.getWidth(), currentY, lineWidth - codeLabel.getX() - codeLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(codeUpdate);
        // 第四行
        currentY += (yGap + lineHeight);
        // 密码
        JLabel pwdLabel = new JLabel("密码：", SwingConstants.RIGHT);
        pwdLabel.setBounds(baseX, currentY, 40, lineHeight);
        updatePanel.add(pwdLabel);
        pwdUpdate = new JTextField("123456");
        pwdUpdate.setBounds(pwdLabel.getX() + pwdLabel.getWidth(), currentY, lineWidth - pwdLabel.getX() - pwdLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(pwdUpdate);

        // 第五行
        currentY += (yGap + lineHeight);
        JButton btn = new JButton("更新");
        btn.setBounds(lineWidth / 2 - 30, currentY, 60, 30);
        updatePanel.add(btn);
    }

    private JTextField nameInsert;
    private JTextField accountInsert;
    private JTextField codeInsert;
    private JTextField pwdInsert;

    private void initInsertPanel() {
        // 初始化添加管理
        JPanel updatePanel = new JPanel(null);
        updatePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "添加信息区域"));
        updatePanel.setBounds(tableWidth, updateHeight, functionWidth, updateHeight);
        this.add(updatePanel);
        int baseX = 20;
        int baseY = 20;
        int xGap = 20;
        int yGap = 10;
        int lineWidth = updatePanel.getWidth();
        int lineHeight = 30;
        int currentY = baseY + yGap;
        // 姓名
        JLabel nameLabel = new JLabel("姓名：", SwingConstants.RIGHT);
        nameLabel.setBounds(baseX, currentY, 40, lineHeight);
        updatePanel.add(nameLabel);
        nameInsert = new JTextField();
        nameInsert.setBounds(nameLabel.getX() + nameLabel.getWidth(), currentY, lineWidth - nameLabel.getWidth() - nameLabel.getX() - xGap, lineHeight);
        updatePanel.add(nameInsert);
        // 第二行
        currentY += (yGap + lineHeight);
        // 账号
        JLabel accountLabel = new JLabel("账号：", SwingConstants.RIGHT);
        accountLabel.setBounds(baseX, currentY, 40, lineHeight);
        updatePanel.add(accountLabel);
        accountInsert = new JTextField();
        accountInsert.setBounds(accountLabel.getX() + accountLabel.getWidth(), currentY, lineWidth - accountLabel.getX() - accountLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(accountInsert);
        // 第三行
        currentY += (yGap + lineHeight);
        // 编码
        JLabel codeLabel = new JLabel("编码：", SwingConstants.RIGHT);
        codeLabel.setBounds(baseX, currentY, 40, lineHeight);
        updatePanel.add(codeLabel);
        codeInsert = new JTextField();
        codeInsert.setBounds(codeLabel.getX() + codeLabel.getWidth(), currentY, lineWidth - codeLabel.getX() - codeLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(codeInsert);
        // 第四行
        currentY += (yGap + lineHeight);
        // 密码
        JLabel pwdLabel = new JLabel("密码：", SwingConstants.RIGHT);
        pwdLabel.setBounds(baseX, currentY, 40, lineHeight);
        updatePanel.add(pwdLabel);
        pwdInsert = new JTextField();
        pwdInsert.setBounds(pwdLabel.getX() + pwdLabel.getWidth(), currentY, lineWidth - pwdLabel.getX() - pwdLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(pwdInsert);

        // 第五行
        currentY += (yGap + lineHeight);
        JButton btn = new JButton("添加");
        btn.setBounds(lineWidth / 2 - 30, currentY, 60, 30);
        updatePanel.add(btn);
    }

}
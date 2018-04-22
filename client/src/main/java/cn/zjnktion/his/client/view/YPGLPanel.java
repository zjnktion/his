package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;

public class YPGLPanel extends JPanel {

    private int contentWidth;
    private int contentHeight;
    private int tableWidth;
    private int functionWidth;
    private int searchHeight;
    private int tableHeight;
    private int updateHeight = 250;
    private int insertHeight = 250;

    public YPGLPanel(int contentWidth, int contentHeight) {
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
        JLabel nameLabel = new JLabel("药品名称：", SwingConstants.RIGHT);
        nameLabel.setBounds(baseX, baseY, 40, 30);
        searchPanel.add(nameLabel);
        nameInput = new JTextField();
        nameInput.setBounds(nameLabel.getX() + nameLabel.getWidth(), baseY, inputWidth, 30);
        searchPanel.add(nameInput);
        JLabel numLabel = new JLabel("药品编号：", SwingConstants.RIGHT);
        numLabel.setBounds(nameInput.getX() + nameInput.getWidth() + xGap, baseY, 55, 30);
        searchPanel.add(numLabel);
        accountInput = new JTextField();
        accountInput.setBounds(numLabel.getX() + numLabel.getWidth(), baseY, inputWidth, 30);
        searchPanel.add(accountInput);
        searchBtn = new JButton("搜索");
        searchBtn.setBounds(accountInput.getX() + accountInput.getWidth() + xGap, baseY, 80, 30);
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
        resultTable = new JTable(new Object[][]{{"阿司匹林", "9000047822101301", "一盒十二片", "12.50元"},{"生理盐水", "9000047822109067", "一盒六支", "10.50元"}}, new Object[]{"药品名称", "药品编号", "药品规格", "价格"});
        resultTable.setFillsViewportHeight(true);
        resultTable.getTableHeader().setPreferredSize(new Dimension(1, 24));
        resultTable.setRowHeight(22);
        JScrollPane jsp = new JScrollPane(resultTable);
        jsp.setBounds(0, 0, resultPanel.getWidth(), resultPanel.getHeight());
        resultPanel.add(jsp);
    }

    private JTextField nameUpdate;
    private JTextField codeUpdate;
    private JTextField guigeUpdate;
    private JTextField priceUpdate;

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
        JLabel nameLabel = new JLabel("药品名称：", SwingConstants.RIGHT);
        nameLabel.setBounds(baseX, currentY, 80, lineHeight);
        updatePanel.add(nameLabel);
        nameUpdate = new JTextField("阿司匹林");
        nameUpdate.setBounds(nameLabel.getX() + nameLabel.getWidth(), currentY, lineWidth - nameLabel.getWidth() - nameLabel.getX() - xGap, lineHeight);
        updatePanel.add(nameUpdate);
        // 第二行
        currentY += (yGap + lineHeight);
        // 账号
        JLabel accountLabel = new JLabel("药品编号：", SwingConstants.RIGHT);
        accountLabel.setBounds(baseX, currentY, 80, lineHeight);
        updatePanel.add(accountLabel);
        codeUpdate = new JTextField("9000047822101301");
        codeUpdate.setBounds(accountLabel.getX() + accountLabel.getWidth(), currentY, lineWidth - accountLabel.getX() - accountLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(codeUpdate);
        // 第三行
        currentY += (yGap + lineHeight);
        // 编码
        JLabel codeLabel = new JLabel("药品规格：", SwingConstants.RIGHT);
        codeLabel.setBounds(baseX, currentY, 80, lineHeight);
        updatePanel.add(codeLabel);
        guigeUpdate = new JTextField("一盒十二篇");
        guigeUpdate.setBounds(codeLabel.getX() + codeLabel.getWidth(), currentY, lineWidth - codeLabel.getX() - codeLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(guigeUpdate);
        // 第四行
        currentY += (yGap + lineHeight);
        // 密码
        JLabel pwdLabel = new JLabel("价格：", SwingConstants.RIGHT);
        pwdLabel.setBounds(baseX, currentY, 80, lineHeight);
        updatePanel.add(pwdLabel);
        priceUpdate = new JTextField("123456");
        priceUpdate.setBounds(pwdLabel.getX() + pwdLabel.getWidth(), currentY, lineWidth - pwdLabel.getX() - pwdLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(priceUpdate);

        // 第五行
        currentY += (yGap + lineHeight);
        JButton btn = new JButton("更新");
        btn.setBounds(lineWidth / 2 - 30, currentY, 60, 30);
        updatePanel.add(btn);
    }

    private JTextField nameInsert;
    private JTextField guigeInsert;
    private JTextField codeInsert;
    private JTextField priceInsert;

    private void initInsertPanel() {
        // 初始化添加管理
        JPanel updatePanel = new JPanel(null);
        updatePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "修改信息区域"));
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
        JLabel nameLabel = new JLabel("药品名称：", SwingConstants.RIGHT);
        nameLabel.setBounds(baseX, currentY, 80, lineHeight);
        updatePanel.add(nameLabel);
        nameInsert = new JTextField();
        nameInsert.setBounds(nameLabel.getX() + nameLabel.getWidth(), currentY, lineWidth - nameLabel.getWidth() - nameLabel.getX() - xGap, lineHeight);
        updatePanel.add(nameInsert);
        // 第二行
        currentY += (yGap + lineHeight);
        // 账号
        JLabel accountLabel = new JLabel("药品编号：", SwingConstants.RIGHT);
        accountLabel.setBounds(baseX, currentY, 80, lineHeight);
        updatePanel.add(accountLabel);
        codeInsert = new JTextField();
        codeInsert.setBounds(accountLabel.getX() + accountLabel.getWidth(), currentY, lineWidth - accountLabel.getX() - accountLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(codeInsert);
        // 第三行
        currentY += (yGap + lineHeight);
        // 编码
        JLabel codeLabel = new JLabel("药品规格：", SwingConstants.RIGHT);
        codeLabel.setBounds(baseX, currentY, 80, lineHeight);
        updatePanel.add(codeLabel);
        guigeInsert = new JTextField();
        guigeInsert.setBounds(codeLabel.getX() + codeLabel.getWidth(), currentY, lineWidth - codeLabel.getX() - codeLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(guigeInsert);
        // 第四行
        currentY += (yGap + lineHeight);
        // 密码
        JLabel pwdLabel = new JLabel("价格：", SwingConstants.RIGHT);
        pwdLabel.setBounds(baseX, currentY, 80, lineHeight);
        updatePanel.add(pwdLabel);
        priceInsert = new JTextField();
        priceInsert.setBounds(pwdLabel.getX() + pwdLabel.getWidth(), currentY, lineWidth - pwdLabel.getX() - pwdLabel.getWidth() - xGap, lineHeight);
        updatePanel.add(priceInsert);

        // 第五行
        currentY += (yGap + lineHeight);
        JButton btn = new JButton("添加");
        btn.setBounds(lineWidth / 2 - 30, currentY, 60, 30);
        updatePanel.add(btn);
    }

}
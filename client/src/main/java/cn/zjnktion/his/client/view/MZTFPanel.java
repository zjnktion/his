package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;

public class MZTFPanel extends JPanel {

    private int contentWidth;
    private int contentHeight;
    private final int readWidth = 400;
    private final int formWidth;

    public MZTFPanel(int contentWidth, int contentHeight) {
        super(null);
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
        this.formWidth = contentWidth - readWidth;
        this.resultHeight = 300;
        this.readHeight = 200;
        this.kaiyaoHeight = contentHeight - searchHeight - resultHeight;
        this.feiyongHeight = contentHeight - readHeight;
        initComponents();
    }

    private void initComponents() {
        initSearchPanel();
        initResultPanel();
        initYaopinPanel();
        initReadPanel();
        initFeiyongPanel();
    }

    private int searchHeight = 60;
    private JTextField nameInput;
    private JTextField numInput;
    private JTextField codeInput;
    private JTextField icnumInput;
    private JButton searchBtn;
    private JButton resetBth;

    private void initSearchPanel() {
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "搜索区域"));
        searchPanel.setBounds(0, 0, contentWidth - readWidth, searchHeight);
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
        JLabel codeLabel = new JLabel("收费单编码：", SwingConstants.RIGHT);
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

    private int resultHeight = 300;
    private JTable resultTable;

    private void initResultPanel() {
        JPanel resultPanel = new JPanel(null);
        resultPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "搜索结果"));
        resultPanel.setBounds(0, searchHeight, contentWidth - readWidth, resultHeight);
        this.add(resultPanel);
        int baseX = 20;
        int baseY = 20;
        resultTable = new JTable(new Object[][]{{"201709010012", "zjn", "12", "全科", "感冒", "男", "2017-09-01", "退费"}, {"201709010012", "zjn", "12", "全科", "发烧", "男", "2017-09-01", "退费"}}, new Object[]{"收费单编码", "姓名", "门诊号", "就诊类型", "诊断结果", "性别", "收费时间", "操作"});
        resultTable.setFillsViewportHeight(true);
        resultTable.getTableHeader().setPreferredSize(new Dimension(1, 24));
        resultTable.setRowHeight(22);
        JScrollPane jsp = new JScrollPane(resultTable);
        jsp.setBounds(baseX, baseY, resultPanel.getWidth() - 2 * baseX, resultPanel.getHeight() - 2 * baseY);
        resultPanel.add(jsp);
    }

    private int kaiyaoHeight;
    private JTable kaiyaoTable;

    private void initYaopinPanel() {
        JPanel yaopinPanel = new JPanel(null);
        yaopinPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "药品结果展示"));
        yaopinPanel.setBounds(0, searchHeight + resultHeight, contentWidth - readWidth, kaiyaoHeight);
        this.add(yaopinPanel);
        // 添加药品
        int xGap = 20;
        int yGap = 20;
        kaiyaoTable = new JTable(new Object[][]{{"1", "测试数据药品1", "6支每盒", "一次1支", "一天一次", "饭后用药", "1", "50.5", "删除"}, {"2", "测试数据药品2", "6支每盒", "一次1支", "一天一次", "饭后用药", "2", "50.5", "删除"}}, new Object[]{"序号", "药品名称", "药品规格", "单次用量", "用法", "备注", "总量", "单价", "操作"});
        kaiyaoTable.setFillsViewportHeight(true);
        kaiyaoTable.getTableHeader().setPreferredSize(new Dimension(1, 24));
        kaiyaoTable.setRowHeight(22);
        JScrollPane jsp = new JScrollPane(kaiyaoTable);
        jsp.setBounds(xGap, yGap, yaopinPanel.getWidth() - 2 * xGap, yaopinPanel.getHeight() - 2 * yGap - 40);
        yaopinPanel.add(jsp);
    }

    private int readHeight;
    private JTextField icnumText;
    private JTextField icpwdText;
    private JButton readBtn;
    private JButton grepBtn;

    private void initReadPanel() {
        // 初始化读卡区域
        JPanel readPanel = new JPanel(null);
        readPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "读卡区域"));
        readPanel.setBounds(formWidth, 0, readWidth, readHeight);
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

    private int feiyongHeight;
    private JTextField chuzhenfeiText;
    private JTextField yaopinfeiText;
    private JTextField zongeText;
    private JTextField yibaobaoxiaoText;
    private JTextField zifeiText;
    private JButton tuifeiBtn;
    private JButton cancleBtn;

    private void initFeiyongPanel() {
        JPanel feiyongPanel = new JPanel(null);
        feiyongPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "费用信息窗口"));
        feiyongPanel.setBounds(formWidth, readHeight, readWidth, feiyongHeight);
        this.add(feiyongPanel);
        int baseX = 20;
        int baseY = 20;
        int lineWidth = readWidth - 2 * baseX;
        int lineHeight = 35;
        int labelWidth = 65;
        int fieldWidth = lineWidth - labelWidth;
        int currentY = baseY;
        // 出诊费
        JLabel chuzhenfeiLabel = new JLabel("出诊费：", SwingConstants.RIGHT);
        chuzhenfeiLabel.setBounds(baseX, currentY, labelWidth, lineHeight);
        feiyongPanel.add(chuzhenfeiLabel);
        chuzhenfeiText = new JTextField("3.0");
        chuzhenfeiText.setBounds(chuzhenfeiLabel.getX() + chuzhenfeiLabel.getWidth(), currentY, fieldWidth, lineHeight);
        feiyongPanel.add(chuzhenfeiText);
        // 药品费
        currentY += (lineHeight + baseY);
        JLabel yaopinfeiLabel = new JLabel("药品费：", SwingConstants.RIGHT);
        yaopinfeiLabel.setBounds(baseX, currentY, labelWidth, lineHeight);
        feiyongPanel.add(yaopinfeiLabel);
        yaopinfeiText = new JTextField("130.5");
        yaopinfeiText.setBounds(yaopinfeiLabel.getX() + yaopinfeiLabel.getWidth(), currentY, fieldWidth, lineHeight);
        feiyongPanel.add(yaopinfeiText);
        // 总额
        currentY += (lineHeight + baseY);
        JLabel zongeLabel = new JLabel("总额：", SwingConstants.RIGHT);
        zongeLabel.setBounds(baseX, currentY, labelWidth, lineHeight);
        feiyongPanel.add(zongeLabel);
        zongeText = new JTextField("133.5");
        zongeText.setBounds(zongeLabel.getX() + zongeLabel.getWidth(), currentY, fieldWidth, lineHeight);
        feiyongPanel.add(zongeText);
        // 医保
        currentY += (lineHeight + baseY);
        JLabel yibaobaoxiaoLabel = new JLabel("医保报销：", SwingConstants.RIGHT);
        yibaobaoxiaoLabel.setBounds(baseX, currentY, labelWidth, lineHeight);
        feiyongPanel.add(yibaobaoxiaoLabel);
        yibaobaoxiaoText = new JTextField("132.5");
        yibaobaoxiaoText.setBounds(yibaobaoxiaoLabel.getX() + yibaobaoxiaoLabel.getWidth(), currentY, fieldWidth, lineHeight);
        feiyongPanel.add(yibaobaoxiaoText);
        // 自费
        currentY += (lineHeight + baseY);
        JLabel zifeiLabel = new JLabel("自费：", SwingConstants.RIGHT);
        zifeiLabel.setBounds(baseX, currentY, labelWidth, lineHeight);
        feiyongPanel.add(zifeiLabel);
        zifeiText = new JTextField("1");
        zifeiText.setBounds(zifeiLabel.getX() + zifeiLabel.getWidth(), currentY, fieldWidth, lineHeight);
        feiyongPanel.add(zifeiText);
        // m
        currentY += (lineHeight + baseY);
        tuifeiBtn = new JButton("确认退费");
        tuifeiBtn.setBounds(baseX, currentY, 80, lineHeight);
        feiyongPanel.add(tuifeiBtn);
        cancleBtn = new JButton("取消退费");
        cancleBtn.setBounds(tuifeiBtn.getX() + tuifeiBtn.getWidth(), currentY, 80, lineHeight);
        feiyongPanel.add(cancleBtn);
    }
}
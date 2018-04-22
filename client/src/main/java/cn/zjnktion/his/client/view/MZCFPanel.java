package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MZCFPanel extends JPanel {

    private int contentWidth;
    private int contentHeight;
    private final int readWidth = 400;
    private final int formWidth;

    public MZCFPanel(int contentWidth, int contentHeight) {
        super(null);
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
        this.formWidth = contentWidth - readWidth;
        this.readHeight = 200;
        this.yaopinHeight = contentHeight - readHeight;
        this.setBounds(0, 0, contentWidth, contentHeight);
        initComponents();
    }

    private void initComponents() {
        initZhenduanPanel();
        initReadPanel();
        initYaopinPanel();
    }

    private JTextField nameText;
    private JComboBox<String> sexBox;
    private JTextField ageText;
    private JComboBox<String> jiuzhenTypeBox;
    private JLabel dateValue;
    private JTextField phoneText;
    private JTextField addressText;
    private JButton moreBtn;
    private JTextArea yizhuArea;
    private JTable kaiyaoTable;

    private void initZhenduanPanel() {
        JPanel zhenduanPanel = new JPanel(null);
        zhenduanPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "门诊统一处方笺"));
        zhenduanPanel.setBounds(0, 0, contentWidth - readWidth, contentHeight);
        this.add(zhenduanPanel);
        int baseX = 20;
        int baseY = 20;
        int xGap = 20;
        int yGap = 10;
        int lineWidth = zhenduanPanel.getWidth();
        int lineHeight = 30;
        int currentY = baseY + yGap;
        // 姓名
        JLabel nameLabel = new JLabel("姓名：", SwingConstants.RIGHT);
        nameLabel.setBounds(baseX, currentY, 40, lineHeight);
        zhenduanPanel.add(nameLabel);
        nameText = new JTextField();
        nameText.setBounds(nameLabel.getX() + nameLabel.getWidth(), currentY, 200, lineHeight);
        zhenduanPanel.add(nameText);
        // 性别
        JLabel sexLabel = new JLabel("性别：", SwingConstants.RIGHT);
        sexLabel.setBounds(nameText.getX() + nameText.getWidth() + xGap, currentY, 40, lineHeight);
        zhenduanPanel.add(sexLabel);
        sexBox = new JComboBox<>(new String[]{"男", "女"});
        sexBox.setBounds(sexLabel.getX() + sexLabel.getWidth(), currentY, 100, lineHeight);
        zhenduanPanel.add(sexBox);
        // 年龄
        JLabel ageLabel = new JLabel("年龄：", SwingConstants.RIGHT);
        ageLabel.setBounds(sexBox.getX() + sexBox.getWidth() + xGap, currentY, 40, lineHeight);
        zhenduanPanel.add(ageLabel);
        ageText = new JTextField();
        ageText.setBounds(ageLabel.getX() + ageLabel.getWidth(), currentY, 100, lineHeight);
        zhenduanPanel.add(ageText);
        // 就诊类型
        JLabel typeLabel = new JLabel("就诊类型：", SwingConstants.RIGHT);
        typeLabel.setBounds(ageText.getX() + ageText.getWidth() + xGap, currentY, 65, lineHeight);
        zhenduanPanel.add(typeLabel);
        jiuzhenTypeBox = new JComboBox<>(new String[]{"全科", "内科", "外科"});
        jiuzhenTypeBox.setBounds(typeLabel.getX() + typeLabel.getWidth(), currentY, 100, lineHeight);
        zhenduanPanel.add(jiuzhenTypeBox);
        // 就诊日期
        JLabel dateLabel = new JLabel("就诊日期：", SwingConstants.RIGHT);
        dateLabel.setBounds(jiuzhenTypeBox.getX() + jiuzhenTypeBox.getWidth() + xGap, currentY, 65, lineHeight);
        zhenduanPanel.add(dateLabel);
        dateValue = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        dateValue.setBounds(dateLabel.getX() + dateLabel.getWidth(), currentY, lineWidth - dateLabel.getX() - dateLabel.getWidth(), lineHeight);
        zhenduanPanel.add(dateValue);
        // 第二行
        currentY += (yGap + lineHeight);
        // 联系电话
        JLabel phoneLabel = new JLabel("联系电话：", SwingConstants.RIGHT);
        phoneLabel.setBounds(baseX, currentY, 65, lineHeight);
        zhenduanPanel.add(phoneLabel);
        phoneText = new JTextField();
        phoneText.setBounds(phoneLabel.getX() + phoneLabel.getWidth(), currentY, 300, lineHeight);
        zhenduanPanel.add(phoneText);
        // 联系地址
        JLabel addressLabel = new JLabel("联系地址：", SwingConstants.RIGHT);
        addressLabel.setBounds(phoneText.getX() + phoneText.getWidth() + xGap, currentY, 65, lineHeight);
        zhenduanPanel.add(addressLabel);
        addressText = new JTextField();
        addressText.setBounds(addressLabel.getX() + addressLabel.getWidth(), currentY, lineWidth - addressLabel.getX() - addressLabel.getWidth() - baseX, lineHeight);
        zhenduanPanel.add(addressText);
        // 第三行
        currentY += (yGap + lineHeight);
        // 诊断结果
        JLabel zhenduanResultLabel = new JLabel("诊断：", SwingConstants.RIGHT);
        zhenduanResultLabel.setBounds(baseX, currentY, 40, lineHeight);
        zhenduanPanel.add(zhenduanResultLabel);
        JLabel ganmao = new JLabel("感冒");
        ganmao.setBounds(zhenduanResultLabel.getX() + zhenduanResultLabel.getWidth(), currentY, 40, lineHeight);
        zhenduanPanel.add(ganmao);
        JLabel fashao = new JLabel("发烧");
        fashao.setBounds(ganmao.getX() + ganmao.getWidth(), currentY, 40, lineHeight);
        zhenduanPanel.add(fashao);
        int moreWidth = 80;
        moreBtn = new JButton("更多");
        moreBtn.setBounds(lineWidth - baseX - moreWidth, currentY, moreWidth, lineHeight);
        zhenduanPanel.add(moreBtn);
        // 第四行
        currentY += (yGap + lineHeight);
        // 医嘱
        yizhuArea = new JTextArea();
        yizhuArea.setBounds(baseX, currentY, lineWidth - 2 * baseX, 5 * lineHeight);
        zhenduanPanel.add(yizhuArea);
        // 第五行
        currentY = yizhuArea.getY() + yizhuArea.getHeight() + xGap;
        // 添加药品
        kaiyaoTable = new JTable(new Object[][]{{"1", "测试数据药品1", "6支每盒", "一次1支", "一天一次", "饭后用药", "1", "50.5", "删除"}, {"2", "测试数据药品2", "6支每盒", "一次1支", "一天一次", "饭后用药", "2", "50.5", "删除"}}, new Object[]{"序号", "药品名称", "药品规格", "单次用量", "用法", "备注", "总量", "单价", "操作"});
        kaiyaoTable.setFillsViewportHeight(true);
        kaiyaoTable.getTableHeader().setPreferredSize(new Dimension(1, 24));
        kaiyaoTable.setRowHeight(22);
        JScrollPane jsp = new JScrollPane(kaiyaoTable);
        jsp.setBounds(baseX, currentY, lineWidth - 2 * baseX, contentHeight - currentY - baseY - 40);
        zhenduanPanel.add(jsp);
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

    private int yaopinHeight;
    private JComboBox<String> typeBox;
    private JTextField searchInput;
    private JButton searchBtn;
    private JTable yaopinTable;

    private void initYaopinPanel() {
        JPanel yaopinPanel = new JPanel(null);
        yaopinPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "药品搜索区域"));
        yaopinPanel.setBounds(formWidth, readHeight, readWidth, yaopinHeight);
        this.add(yaopinPanel);
        int baseX = 20;
        int baseY = 20;
        int lineWidth = readWidth - 2 * baseX;
        int lineHeight = 30;
        int labelWidth = 65;
        int fieldWidth = lineWidth - labelWidth;
        // 药品类型
        JLabel typeLabel = new JLabel("药品类型：", SwingConstants.RIGHT);
        typeLabel.setBounds(baseX, baseY, labelWidth, lineHeight);
        yaopinPanel.add(typeLabel);
        typeBox = new JComboBox<>(new String[]{"全部", "中药", "西药"});
        typeBox.setBounds(typeLabel.getX() + typeLabel.getWidth(), baseY, fieldWidth, lineHeight);
        yaopinPanel.add(typeBox);
        // 药品名称+搜索按钮
        int btnWidth = 50;
        JLabel searchLabel = new JLabel("药品名称：", SwingConstants.RIGHT);
        searchLabel.setBounds(baseX, baseY + lineHeight, labelWidth, lineHeight);
        yaopinPanel.add(searchLabel);
        searchInput = new JTextField();
        searchInput.setBounds(baseX + searchLabel.getWidth(), baseY + lineHeight, fieldWidth - btnWidth, lineHeight);
        yaopinPanel.add(searchInput);
        searchBtn = new JButton("搜索");
        searchBtn.setBounds(baseX + searchLabel.getWidth() + searchInput.getWidth(), baseY + lineHeight, btnWidth, lineHeight);
        yaopinPanel.add(searchBtn);
        // 药品搜索结果
        yaopinTable = new JTable(new Object[][]{{"阿司匹林", "25mg*18片", "添加"}, {"退热贴", "6片", "添加"}}, new Object[]{"药品名称", "药品规格", "操作"});
        yaopinTable.setFillsViewportHeight(true);
        yaopinTable.getTableHeader().setPreferredSize(new Dimension(1, 24));
        yaopinTable.setRowHeight(22);
        JScrollPane jsp = new JScrollPane(yaopinTable);
        jsp.setBounds(baseX, baseY + 2 * lineHeight, lineWidth, yaopinPanel.getHeight() - 2 * lineHeight - 2 * baseY - 40);
        yaopinPanel.add(jsp);
    }
}
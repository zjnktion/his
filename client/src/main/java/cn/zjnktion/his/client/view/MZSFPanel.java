package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MZSFPanel extends JPanel {

    private int contentWidth;
    private int contentHeight;
    private final int readWidth = 400;
    private final int formWidth;

    public MZSFPanel(int contentWidth, int contentHeight) {
        super(null);
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
        this.formWidth = contentWidth - readWidth;
        this.dukaHeight = 200;
        this.feiyongHeight = contentHeight - dukaHeight;
        this.setBounds(0, 0, contentWidth, contentHeight);
        initComponents();
    }

    private void initComponents() {
        initZhenduanPanel();
        initReadPanel();
        initFeiyongPanel();
    }

    private JTextField nameText;
    private JComboBox<String> sexBox;
    private JTextField ageText;
    private JComboBox<String> jiuzhenTypeBox;
    private JLabel dateValue;
    private JTextField phoneText;
    private JTextField addressText;
    private JLabel yisheng;
    private JTextArea yizhuArea;
    private JTable kaiyaoTable;

    private void initZhenduanPanel() {
        // 初始化诊断区域
        JPanel zhenduanPanel = new JPanel(null);
        zhenduanPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "诊断区域"));
        zhenduanPanel.setBounds(0, 0, formWidth, contentHeight);
        this.add(zhenduanPanel);
        int baseX = 20;
        int baseY = 20;
        int xGap = 20;
        int yGap = 10;
        int lineWidth = zhenduanPanel.getWidth();
        int lineHeight = 30;
        // 标题
        int titleWidth = 200;
        int titleHeight = 40;
        JLabel titleLabel = new JLabel("XXX门诊统一收费表单");
        titleLabel.setFont(new Font(Font.DIALOG, 1, 20));
        titleLabel.setBounds((lineWidth - baseX - titleWidth) / 2, baseY, titleWidth, titleHeight);
        zhenduanPanel.add(titleLabel);
        int currentY = titleLabel.getY() + titleLabel.getHeight() + yGap;
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
        jsp.setBounds(baseX, currentY, lineWidth - 2 * baseX, contentHeight - currentY - baseY - 2 * lineHeight);
        zhenduanPanel.add(jsp);
        // 第五行
        currentY = currentY + yGap + jsp.getHeight();
        // 处置医生
        JLabel czys = new JLabel("处置医生：", SwingConstants.LEFT);
        czys.setBounds(baseX, currentY, 70, lineHeight);
        zhenduanPanel.add(czys);
        yisheng = new JLabel("郑医生", SwingConstants.LEFT);
        yisheng.setBounds(czys.getX() + czys.getWidth(), currentY, 100, lineHeight);
        zhenduanPanel.add(yisheng);
    }

    private int dukaHeight;
    private JTextField icnumText;
    private JTextField icpwdText;
    private JButton readBtn;
    private JButton grepBtn;

    private void initReadPanel() {
        // 初始化读卡区域
        JPanel readPanel = new JPanel(null);
        readPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "读卡区域"));
        readPanel.setBounds(formWidth, 0, readWidth, dukaHeight);
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
    private JButton shoufeiBtn;
    private JButton cancleBtn;

    private void initFeiyongPanel() {
        JPanel feiyongPanel = new JPanel(null);
        feiyongPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "费用信息窗口"));
        feiyongPanel.setBounds(formWidth, dukaHeight, readWidth, feiyongHeight);
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
        shoufeiBtn = new JButton("确认收费");
        shoufeiBtn.setBounds(baseX, currentY, 80, lineHeight);
        feiyongPanel.add(shoufeiBtn);
        cancleBtn = new JButton("取消收费");
        cancleBtn.setBounds(shoufeiBtn.getX() + shoufeiBtn.getWidth(), currentY, 80, lineHeight);
        feiyongPanel.add(cancleBtn);
    }
}
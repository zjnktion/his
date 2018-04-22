package cn.zjnktion.his.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author zjnktion
 */
public class MainFrame extends JFrame {

    private static final int WIDTH = 1440;
    private static final int HEIGHT = 900;
    private static final int NAV_HEIGHT = 60;
    private static final int CONTENT_WIDTH = WIDTH - 20;
    private static final int CONTENT_HEIGHT = HEIGHT - NAV_HEIGHT - 20;

    private JPanel mzghPanel = new MZGHPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel mzthPanel = new MZTHPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel mzsfPanel = new MZSFPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel mztfPanel = new MZTFPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel mzcfPanel = new MZCFPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel sysfPanel = new SYSFPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel sytfPanel = new SYTFPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel mzblPanel = new MZBLPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel sylsPanel = new SYLSPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel ysglPanel = new YSGLPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel ypglPanel = new YPGLPanel(CONTENT_WIDTH, CONTENT_HEIGHT);
    private JPanel qxglPanel = new QXGLPanel(CONTENT_WIDTH, CONTENT_HEIGHT);

    public MainFrame() {
        this.setTitle("云科医疗信息系统");
        this.setUndecorated(false);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        initComponent();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initComponent() {
        initNavPanel();
        initTab();
    }

    private void initNavPanel() {
        // 导航panel
        JPanel navPanel = new JPanel(null);
        navPanel.setBorder(BorderFactory.createEtchedBorder());
        navPanel.setBounds(0, 0, WIDTH, NAV_HEIGHT);
        navPanel.setBackground(Color.GREEN);
        // 欢迎标题
        int welcomeWidth = 800;
        int welcomeHeight = NAV_HEIGHT;
        JLabel welcomeLabel = new JLabel("欢迎使用云科医疗信息系统", SwingConstants.CENTER);
        welcomeLabel.setVerticalTextPosition(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("宋体", Font.BOLD, 40));
        welcomeLabel.setBounds((WIDTH - welcomeWidth) / 2, 0, welcomeWidth, welcomeHeight);
        navPanel.add(welcomeLabel);
        // 登录信息
        int nameWidth = 200;
        int functionHeight = 30;
        JLabel nameLabel = new JLabel("郑医生，欢迎您！", SwingConstants.RIGHT);
        nameLabel.setBounds(welcomeLabel.getX() + welcomeWidth + 50, NAV_HEIGHT - functionHeight, nameWidth, functionHeight);
        navPanel.add(nameLabel);
        int btnWidth = 60;
        JButton exitBtn = new JButton("退出");
        exitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        exitBtn.setBounds(nameLabel.getX() + nameWidth + 2, nameLabel.getY(), btnWidth, functionHeight);
        navPanel.add(exitBtn);
        this.add(navPanel);
    }

    private void initTab() {
        // 初始化选项卡
        JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
        jtp.setBounds(0, NAV_HEIGHT, WIDTH, CONTENT_HEIGHT);
        jtp.add("门诊挂号", mzghPanel);
        jtp.add("门诊退号", mzthPanel);
        jtp.add("门诊收费", mzsfPanel);
        jtp.add("门诊退费", mztfPanel);
        jtp.add("门诊处方", mzcfPanel);
        jtp.add("售药收费", sysfPanel);
        jtp.add("售药退费", sytfPanel);
        jtp.add("门诊病历", mzblPanel);
        jtp.add("售药历史", sylsPanel);
        jtp.add("医生管理", ysglPanel);
        jtp.add("药品管理", ypglPanel);
        jtp.add("权限管理", qxglPanel);
        this.add(jtp);
    }
}

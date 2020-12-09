package greedysnake3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartView implements MouseListener {
    // 创建开始游戏欢迎界面
    private Frame jf = new Frame("贪吃蛇");
    // 提示性标签
    private JLabel jl;

    // 设置进入游戏按钮
    private JButton jb;

    // 实现按钮方法
    private void setButton() {
        jf.setLayout(null);
        jb = new JButton("开始游戏");   // 按钮提示内容
        jb.setBounds(300, 500, 400, 70);
        jb.setFont(new Font("楷体_2312", Font.BOLD, 50));   // 按钮字体及大小
        jb.setBackground(new Color(177, 120, 205, 205));
        jf.add(jb);      // 加入窗口
        jb.addMouseListener(this);        // 为按钮添加监听
    }

    // 实现标签方法
    private void setLabel() {
        jf.setLayout(null);
        jl = new JLabel("欢迎来到贪吃蛇游戏");       // 标签内容
        jl.setFont(new Font("楷体_2312", Font.BOLD, 30)); // 字体
        jl.setBackground(new Color(196, 34, 169));  // 字体颜色
        jl.setBounds(380, 100, 400, 80); // 相对位置
        jf.add(jl);  // 加到窗口
    }

    StartView() {
        // 设置窗口背景颜色
        jf.setBackground(new Color(190, 44, 79));
        // 设置按钮信息
        setButton();
        // 设置标签并加入窗口
        setLabel();
        // 设置点击右上角的关闭窗口结束程序运行
        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // 设置窗口绝对位置
        jf.setLocation(500, 200);
        // 设置窗口大小
        jf.setSize(1000, 800);

        // 窗口大小不可变
        jf.setResizable(false);
        // 窗口可见
        jf.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(jb)) {
            // 进入游戏窗口
            new SnakeGameView().showView();
            // 设置原窗口不可见
            jf.setVisible(false);
            // 释放原窗口资源
            jf.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

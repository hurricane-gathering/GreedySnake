package greedysnake3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class SnakeGameView extends Frame implements MouseListener {
    // 定义游戏窗口名
    //JFrame jf = new JFrame("Greedy-Snake");
    // 声明画笔
    private DrawView drawView;
    // 声明开始，重新开始，退出游戏按钮
    JButton start, reset, exit;
    // 声明分数标签
    JLabel grade;
    // 声明分数文本框
    JTextField score;
    // 声明游戏操作方法文本域
    JTextArea hint;

    private Random random;
    public static boolean gameState = true;
    public static int gameScore = 0;
    private Snake snake;
    private Node egg;
    private GameRunThread grt;
    public static Thread thread;

    public SnakeGameView() {
        random = new Random();
        //初始化Snake
        snake = new Snake(10 * DrawView.VIEW_NUMBER + random.nextInt(19) * DrawView.VIEW_NUMBER,
                10 * DrawView.VIEW_NUMBER + random.nextInt(19) * DrawView.VIEW_NUMBER, this);
        //初始化Egg
        egg = new Node(random.nextInt(DrawView.VIEW_WIDTH - 1) * DrawView.VIEW_NUMBER,
                random.nextInt(DrawView.VIEW_WIDTH - 1) * DrawView.VIEW_NUMBER);
        //初始化画板
        drawView = new DrawView(snake, egg);
        grt = new GameRunThread(drawView, snake);
        thread = new Thread(grt);
        // 初始化画板
        setPanel();
        // 设置按钮
        setButton();
        // 设置标签
        setLabel();
        // 设置文本框
        setText();
        // 初始化文本域操作方法
        setJTA();
    }

    //设置鸡蛋
    public void setEgg(int eggX, int eggY) {

        this.egg.setNodeX(eggX);
        this.egg.setNodeY(eggY);
    }

    // 获得分数
    public JTextField getGameScores() {
        return score;
    }

    public void showView() {

        drawView.setBackground(new Color(51, 51, 51));
        drawView.setBounds(20, 50, DrawView.VIEW_WIDTH * DrawView.VIEW_NUMBER + 1, DrawView.VIEW_HEIGHT * DrawView.VIEW_NUMBER + 1);
        this.setTitle("Greedy-Snake");
        this.setSize(1000, 800);
        this.setLocation(500, 200);
        this.setLayout(null);
        this.setBackground(new Color(2, 250, 250));
        this.add(drawView);
        this.addKeyListener(new GameControl(snake));
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    // 初始化画板
    private void setPanel() {
        drawView = new DrawView(snake, egg);
        grt = new GameRunThread(drawView, snake);
        thread = new Thread(grt);
    }

    // 实现按钮方法
    private void setButton() {
        this.setLayout(null);
        // 设置开始按钮
        start = new JButton("开始");
        start.setBounds(800, 60, 180, 50);
        start.setFont(new Font("宋体", 1, 18));
        this.add(start);
        // 设置重新开始按钮
        reset = new JButton("重新开始");
        reset.setFont(new Font("宋体", 1, 18));
        reset.setBounds(800, 150, 180, 50);
        this.add(reset);
        // 设置退出按钮
        exit = new JButton("退出游戏");
        exit.setFont(new Font("宋体", 1, 18));
        exit.setBounds(800, 240, 180, 50);
        this.add(exit);
        // 初始化按钮添加监听器
        initial();
    }

    // 给各按钮添加监听器
    void initial() {
        start.addMouseListener(this);
        reset.addMouseListener(this);
        exit.addMouseListener(this);
    }

    // 设置标签的方法
    private void setLabel() {
        this.setLayout(null);
        grade = new JLabel("分数");
        grade.setFont(new Font("宋体", 1, 20));
        grade.setBounds(800, 330, 100, 50);
        this.add(grade);
    }

    // 显示分数的方法
    private void setText() {
        this.setLayout(null);
        score = new JTextField("0", JTextField.CENTER);
        score.setFont(new Font("宋体", 1, 20));
        score.setBounds(800, 420, 100, 50);
        score.setEditable(false);
        this.add(score);
    }

    // 显示操作方法
    private void setJTA() {
        this.setLayout(null);
        hint = new JTextArea("操作指导   ← 向左  → 向右  ↑ 向上  ↓ 向下  Esc 退出");
        hint.setFont(new Font("宋体", 0, 24));
        hint.setBackground(new Color(243, 5, 100));
        hint.setBounds(40, 700, 920, 60);
        hint.setEditable(false);
        this.add(hint);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(start)) {
            SnakeGameView newSnake = new SnakeGameView();
            newSnake.initial();
            this.setVisible(false);
            this.dispose();
            new SnakeGameView().showView();
            thread.start();
        }
        if (e.getSource().equals(reset)) {
            SnakeGameView newSnake = new SnakeGameView();
            newSnake.initial();
            this.setVisible(false);
            this.dispose();
            new SnakeGameView().showView();
            thread.start();
        }
        if (e.getSource().equals(exit)) {
            System.exit(0);
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

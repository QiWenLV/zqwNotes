package com.zqw.principle.ocp.improve;

/**
 * 开闭原则 （遵守）
 *
 * 如果新增三角形，只需要新增一个三角形的类，使用完全不需要修改
 */
public class Ocp {

    public static void main(String[] args) {
        //存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
    }
}

//一个用于绘图的类
class GraphicEditor {
    //接受不同的Shape，绘制不同的图形
    public void drawShape(Shape s){
        s.draw();
    }

}

//基类
abstract class Shape {
    int m_type;

    public abstract void draw();
}

class Rectangle extends Shape {
    public Rectangle() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制矩形");
    }
}

class Circle extends Shape {
    public Circle() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println(" 绘制圆形");
    }
}
package com.zqw.principle.ocp;

/**
 * 开闭原则 （违背）
 *
 * 如果新增三角形，修改会特别多：
 *  使用方需要 增加绘制三角形的方法，需要在if判断中添加新情况
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
        if(s.m_type == 1){
            drawRectangle(s);
        } else if (s.m_type == 2) {
            drawCircle(s);
        }
    }

    public void drawRectangle(Shape r){
        System.out.println(" 绘制矩形");
    }
    public void drawCircle(Shape r){
        System.out.println(" 绘制圆形");
    }
}

//基类
class Shape {
    int m_type;
}

class Rectangle extends Shape {
    public Rectangle() {
        super.m_type = 1;
    }
}
class Circle extends Shape {
    public Circle() {
        super.m_type = 2;
    }
}
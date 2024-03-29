package com.example.administrator.k_means;

import java.util.Random;

public class Kcluster {
    point[] ypo;// 点集
    point[] pacore = null;// old聚类中心
    point[] pacoren = null;// new聚类中心
    public int mPointTotal = 5 ;
    // 初试聚类中心，点集
    public void productpoint(float x1,float y1) {
        ypo = new point[mPointTotal];
        // 随机产生点
        for (int i = 0; i < mPointTotal; i++) {

            float x = (int) (new Random().nextInt((int)x1));
            float y = (int) (new Random().nextInt((int)y1));

            ypo[i] = new point();// 对象创建
            ypo[i].setX(x);
            ypo[i].setY(y);

        }

        this.pacore = new point[1];// 存放聚类中心
        this.pacoren = new point[1];

        Random rand = new Random();
        int temp[] = new int[1];
        temp[0] = rand.nextInt(mPointTotal);
        pacore[0] = new point();
        pacore[0] = ypo[temp[0]];
        // 避免产生重复的中心
        for (int i = 1; i < 1; i++) {
            int flage = 0;
            int thistemp = rand.nextInt(mPointTotal);
            for (int j = 0; j < i; j++) {
                if (temp[j] == thistemp) {
                    flage = 1;// 有重复
                    break;

                }
            }
            if (flage == 1) {
                i--;
            } else {
                pacore[i] = new point();
                pacore[i] = ypo[thistemp];
                pacore[i].flage = 0;// 0表示聚类中心
            }

        }
        System.out.println("初始聚类中心：");
        for (int i = 0; i < pacore.length; i++) {
            System.out.println(pacore[i].x + " " + pacore[i].y);
        }

    }

    // ///找出每个点属于哪个聚类中心
    public void searchbelong()// 找出每个点属于哪个聚类中心
    {

        for (int i = 0; i < ypo.length; i++) {
            double dist = 999;
            int lable = -1;
            for (int j = 0; j < pacore.length; j++) {

                double distance = distpoint(ypo[i], pacore[j]);
                if (distance < dist) {
                    dist = distance;
                    lable = j;
                    // po[i].flage = j + 1;// 1,2,3......

                }
            }
            ypo[i].flage = lable + 1;

        }

    }

    // 更新聚类中心
    public void calaverage() {

        for (int i = 0; i < pacore.length; i++) {
            System.out.println("以<" + pacore[i].x + "," + pacore[i].y + ">为中心的点：");
            int numc = 0;
            point newcore = new point();
            for (int j = 0; j < ypo.length; j++) {

                if (ypo[j].flage == (i + 1)) {
                    numc += 1;
                    newcore.x += ypo[j].x;
                    newcore.y += ypo[j].y;
                    System.out.println(ypo[j].x + "," + ypo[j].y);
                }
            }
            // 新的聚类中心
            pacoren[i] = new point();
            pacoren[i].x = newcore.x / numc;
            pacoren[i].y = newcore.y / numc;
            pacoren[i].flage = 0;
            System.out.println("新的聚类中心：" + pacoren[i].x + "," + pacoren[i].y);

        }
    }

    public double distpoint(point px, point py) {

        return Math.sqrt(Math.pow((px.x - py.x), 2) + Math.pow((px.y - py.y), 2));

    }

    public void change_oldtonew(point[] old, point[] news) {
        for (int i = 0; i < old.length; i++) {
            old[i].x = news[i].x;
            old[i].y = news[i].y;
            old[i].flage = 0;// 表示为聚类中心的标志。
        }
    }

    public void movecore() {
        // this.productpoint();//初始化，样本集，聚类中心，
        this.searchbelong();
        this.calaverage();//
        double movedistance = 0;
        int biao = -1;// 标志，聚类中心点的移动是否符合最小距离
        for (int i = 0; i < pacore.length; i++) {
            movedistance = distpoint(pacore[i], pacoren[i]);
            System.out.println("distcore:" + movedistance);// 聚类中心的移动距离
            if (movedistance < 0.01) {
                biao = 0;

            } else {

                biao = 1;
                break;

            }
        }
        if (biao == 0) {
            System.out.print("迭代完毕！！！！！");
        } else {
            change_oldtonew(pacore, pacoren);
            movecore();
        }
    }

    public void setmPointTotal(int i) {
        mPointTotal = i;
    }
}

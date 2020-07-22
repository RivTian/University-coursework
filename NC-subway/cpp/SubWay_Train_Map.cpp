//#include<bits/stdc++.h>
#include<vector>
#include<string>
#include<Windows.h>
#include<iostream>
#include <fstream>
using namespace std;

//月卡用户信息存储
struct Node {
    string name, id;
};

//站点信息:站名 + 所处号线
struct node {
    string name;
    int ID;
};

/*-全局变量区-*/
//统计每条线的站数，并可以为之后要显示全部站有帮助
int ID_Conut_1;
int ID_Conut_2;
//int ID_Conut_3;
//int ID_Conut_4;
const int N = 60;
const float inf = 1 << 30;//无穷

float mp[N][N];//邻接矩阵
vector<node>STN;//存放站点信息 name id
vector<float> Dis;
vector<Node> Vip;//事业补贴ID（月卡）

/* -函数声明区- */
int Checkindex(node& name);//获取站点下标
float GetDis(int& t1, int& t2);//获取距离
void countMoney(int& people, double& dis);/*计算需要金额*/
void Graph(); //构建地铁图的邻接矩阵
void Floyd(int& max_n); //最短路径算法
void menu(); //绘制菜单
void Change(float& Paympney);/*找零*/
void PrintLine(); //打印线路

/*-函数定义区-*/
int main() {
    //控制台样式设计代码
    system("mode con cols=105");
    system("color F4");
    string stn, ID; float dis;
    ifstream infile;// 以读模式打开文件
    /*
    * 对于未增加的3、4号线
    * 只需要要下面按照格式找到
    * 线路站点信息 data.txt 进行文件输入
    */
    /*------------------------------*/
    infile.open("1.txt");
    while (infile >> stn >> dis) {//读入1号线信息
        STN.push_back({ stn,1 });
        Dis.push_back(dis);
    }
    ID_Conut_1 = STN.size();
    infile.close();// 关闭打开的文件
    /*------------------------------*/
    infile.open("2.txt");
    while (infile >> stn >> dis) {//读入2号线信息
        STN.push_back({ stn,2 });
        Dis.push_back(dis);
    }
    ID_Conut_2 = STN.size() - ID_Conut_1;
    infile.close();// 关闭打开的文件
    /*-----------------------------*/

    //infile.open("3.txt");
    //while (infile >> stn >> dis) {//读入3号线信息
    //    STN.push_back({ stn,3 });
    //    Dis.push_back(dis);
    //}
    //ID_Conut_3 = STN.size() - ID_Conut_1 - ID_Count_2;
    //infile.close();// 关闭打开的文件

    /*-----------------------------*/

    //infile.open("4.txt");
    //while (infile >> stn >> dis) {//读入4号线信息
    //    STN.push_back({ stn,4 });
    //    Dis.push_back(dis);
    //}
    //ID_Conut_4 = STN.size() - ID_Conut_1 - ID_Count_2 - ID_Count_3;
    //infile.close();// 关闭打开的文件

    /*-----------------------------*/

    //读入月卡用户信息
    infile.open("vip.txt");
    while (infile >> stn >> ID) {
        Vip.push_back({ stn,ID });
    }
    /*------------------------------*/

    //构建邻接矩阵和路径最短化
    Graph();
    menu();//绘制欢迎菜单
    return 0;
}

/*找零*/
void Change(float& Paymoney) {
    int addMoney = 0, t;
    string name, id;//用于查询月卡
    bool flag = false; int f = 0;
    printf("请问是否是地铁月卡用户？请输入月卡信息：(ID 使用人姓名,是：1，否：0 注意：如果是月卡用户验证账户机会仅一次 \n");
    cin >> f;
    if (f) {
        cin >> id >> name;
        for (auto p : Vip)
            if (p.id == id && p.name == name) {
                flag = true;
                break;
            }
        if (flag) { cout << "验证成功，系统将显示折扣金额\n"; }
        else { cout << "账户查询失败，系统将显示原价金额\n"; }
    }

    if (flag && f) Paymoney *= 0.8;

    printf("\n应付金额为：  %.1f\n", Paymoney);

    while (addMoney < Paymoney) {
        printf("\n请输入投放金额： "); cin >> t;
        addMoney += t;
        if (addMoney >= Paymoney) {
            cout << "\n找零金额： " << addMoney - Paymoney << " 元（￥）" << endl;
            cout << "\n请在取票口即时取票.\n\n";
        }
        else
            cout << "\n投放金额不足，需添加 " << Paymoney - addMoney << " 元（￥）" << endl;
    }
}

/*计算需要金额*/
void countMoney(int& people, double& dis) {
    float Paymoney = 0;
    double dt = dis - (int)(dis);
    if (dt >= 0.5)dis = ceil(dis);
    else dis = floor(dis);
    if (dis >= 0 && dis <= 6)  Paymoney = people * 2;
    else if (dis <= 12) Paymoney = people * 3;
    else if (dis <= 20) Paymoney = people * 4;
    else if (dis <= 28) Paymoney = people * 5;
    else Paymoney = people * (5 + ceil((dis - 28) / 10));
    Change(Paymoney);
}

//获得站号下标
int Checkindex(node& tmp) {
    int i = 0;
    for (auto p : STN) {
        if (p.name == tmp.name)return i;
        ++i;
    }
    return -1;
}

//获取距离
float GetDis(int& t1, int& t2) {
    float t = 0;
    //同线直接数组操作
    if (STN[t1].ID == STN[t2].ID) {
        if (t1 < t2) {
            for (int i = t1 + 1; i <= t2; ++i)
                t += Dis[i];
        }
        else if (t1 > t2) {
            for (int i = t2 + 1; i <= t1; ++i)
                t += Dis[i];
        }
    }
    else {
        return mp[t1][t2];
    }
    return t;
}

void menu() {
    //system("mode con cols=104 lines=40  ");
    printf("********************************************************************************************************\n");
    printf("                                     欢迎来到南昌地铁路线查询系统\n");
    printf("                                 Nanchang Metro Route Inquiry System\n");
    printf("\n本系统售票规则：\n\n");
    printf("             依照“分级递进、递远递减”的原则，\n");
    printf("             起步价为2元，起步6公里，6-12公里每增1元可乘里程为6公里，\n");
    printf("             12-28公里每增1元可乘里程为8公里，\n");
    printf("             28公里以上每增1元可乘里程为10公里。\n");
    printf("本规则自2015年11月3日南昌市物价局关于召开“南昌市制定轨道交通票价听证会”的公告开始有效\n\n");
    printf("********************************************************************************************************\n");

    //freopen("in.txt", "r", stdin);//站点距离测试测试

    printf("\n请问您是否要使用本系统 ？ （1 是，0 否）");
    string Exit; cin >> Exit;
    if (Exit == "0" || Exit == "否") {
        system("cls");
        printf("********************************************************************************************************\n");
        printf("                                   南昌地铁路线查询系统已经安全退出\n");
        printf("                       Nanchang subway route query system has been safely exited\n");
        printf("********************************************************************************************************\n");
        return;
    }

    node stst, endst; int people;
    //string startStation, endStation; 
    while (1) {
        printf("\n请问是否需要显示所有站点信息 ？（1 是，0 否）\n"); cin >> Exit;
        if (Exit == "1" || Exit == "是") PrintLine();
        cout << "请输入起点站：（如 1号线 双港站）\n"; cin >> stst.ID >> stst.name; cout << endl;
        cout << "请输入终点站：                 \n"; cin >> endst.ID >> endst.name; cout << endl;

        int si = Checkindex(stst), ti = Checkindex(endst);
        //错误站名处理
        if (si == -1 || ti == -1) {
            cout << "站名输入错误，请重新输入\n" << endl;
            continue;
        }

        double Distance = GetDis(si, ti);
        cout << stst.name << " -> " << endst.name << "两站最短距离为：" << Distance << endl;

        if (stst.name == endst.name) {
            printf("\n亲，您真的需要坐地铁么？\n");
            printf("请重新输入信息\n\n");
            continue;
        }

        cout << "请输入乘坐人数:           \n"; cin >> people; cout << endl;

        //引用计算distance的函数（类，使用BFS disjtra floyd寻找最短路）
        countMoney(people, Distance);//计算金额时候向上取

        cout << "是否继续使用票价查询系统 ？（1 是，0 否)";
        cout << endl << endl; cin >> Exit;
        if (Exit == "0" || Exit == "否") {
            system("cls");
            printf("********************************************************************************************************\n");
            printf("                                   南昌地铁路线查询系统已经安全退出\n");
            printf("                       Nanchang subway route query system has been safely exited\n");
            printf("********************************************************************************************************\n");
            printf("\n\n\n\n\n\n\n\n\n\n");
            return;
        }
        else
            system("cls");
    }
}

//构建地铁图的邻接矩阵,并flody算法直接取最短
void Graph() {
    //memset(mp, 1000.0, sizeof mp);
    int max_n = STN.size();
    for (int i = 0; i < max_n; ++i)
        for (int j = 0; j < max_n; ++j)
            mp[i][j] = 500;

    for (int i = 0; i < max_n; ++i)
        mp[i][i] = 0;

    for (int i = 1; i < max_n; ++i)
        mp[i - 1][i] = mp[i][i - 1] = Dis[i];

    //对于1号线末和2号线首站问题 利用之前存储的号线站数直接设置为inf（无穷）
    mp[ID_Conut_1][ID_Conut_1 - 1] = mp[ID_Conut_1 - 1][ID_Conut_1] = 500;

    //换乘点
    for (int i = 0; i < max_n; ++i)
        for (int j = i + 1; j < max_n; ++j)
            if (STN[i].name == STN[j].name)
                mp[i][j] = mp[j][i] = 0;
    Floyd(max_n);
}

void Floyd(int& max_n) {
    //Floyd算法,可修改为BFS，Dijkstra等最短路径算法
    for (int k = 0; k < max_n; ++k)
        for (int i = 0; i < max_n; ++i)
            for (int j = 0; j < max_n; ++j)
                if (mp[i][j] > (mp[i][k] + mp[k][j]))
                    mp[i][j] = mp[i][k] + mp[k][j];
}

void PrintLine() {
    printf("\n1号线站点名：\n");
    for (int i = 0; i < ID_Conut_1; ++i) {
        cout << STN[i].name << (i == ID_Conut_1 - 1 ? "\n" : " -> ");
    }
    printf("\n2号线站点名：\n");
    for (int i = ID_Conut_1; i < ID_Conut_1 + ID_Conut_2; ++i) {
        cout << STN[i].name << (i == ID_Conut_1 + ID_Conut_2 - 1 ? "\n" : " -> ");
    }
    cout << endl;
}
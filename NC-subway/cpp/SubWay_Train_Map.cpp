//#include<bits/stdc++.h>
#include<vector>
#include<string>
#include<Windows.h>
#include<iostream>
#include <fstream>
using namespace std;

//�¿��û���Ϣ�洢
struct Node {
    string name, id;
};

//վ����Ϣ:վ�� + ��������
struct node {
    string name;
    int ID;
};

/*-ȫ�ֱ�����-*/
//ͳ��ÿ���ߵ�վ����������Ϊ֮��Ҫ��ʾȫ��վ�а���
int ID_Conut_1;
int ID_Conut_2;
//int ID_Conut_3;
//int ID_Conut_4;
const int N = 60;
const float inf = 1 << 30;//����

float mp[N][N];//�ڽӾ���
vector<node>STN;//���վ����Ϣ name id
vector<float> Dis;
vector<Node> Vip;//��ҵ����ID���¿���

/* -����������- */
int Checkindex(node& name);//��ȡվ���±�
float GetDis(int& t1, int& t2);//��ȡ����
void countMoney(int& people, double& dis);/*������Ҫ���*/
void Graph(); //��������ͼ���ڽӾ���
void Floyd(int& max_n); //���·���㷨
void menu(); //���Ʋ˵�
void Change(float& Paympney);/*����*/
void PrintLine(); //��ӡ��·

/*-����������-*/
int main() {
    //����̨��ʽ��ƴ���
    system("mode con cols=105");
    system("color F4");
    string stn, ID; float dis;
    ifstream infile;// �Զ�ģʽ���ļ�
    /*
    * ����δ���ӵ�3��4����
    * ֻ��ҪҪ���水�ո�ʽ�ҵ�
    * ��·վ����Ϣ data.txt �����ļ�����
    */
    /*------------------------------*/
    infile.open("1.txt");
    while (infile >> stn >> dis) {//����1������Ϣ
        STN.push_back({ stn,1 });
        Dis.push_back(dis);
    }
    ID_Conut_1 = STN.size();
    infile.close();// �رմ򿪵��ļ�
    /*------------------------------*/
    infile.open("2.txt");
    while (infile >> stn >> dis) {//����2������Ϣ
        STN.push_back({ stn,2 });
        Dis.push_back(dis);
    }
    ID_Conut_2 = STN.size() - ID_Conut_1;
    infile.close();// �رմ򿪵��ļ�
    /*-----------------------------*/

    //infile.open("3.txt");
    //while (infile >> stn >> dis) {//����3������Ϣ
    //    STN.push_back({ stn,3 });
    //    Dis.push_back(dis);
    //}
    //ID_Conut_3 = STN.size() - ID_Conut_1 - ID_Count_2;
    //infile.close();// �رմ򿪵��ļ�

    /*-----------------------------*/

    //infile.open("4.txt");
    //while (infile >> stn >> dis) {//����4������Ϣ
    //    STN.push_back({ stn,4 });
    //    Dis.push_back(dis);
    //}
    //ID_Conut_4 = STN.size() - ID_Conut_1 - ID_Count_2 - ID_Count_3;
    //infile.close();// �رմ򿪵��ļ�

    /*-----------------------------*/

    //�����¿��û���Ϣ
    infile.open("vip.txt");
    while (infile >> stn >> ID) {
        Vip.push_back({ stn,ID });
    }
    /*------------------------------*/

    //�����ڽӾ����·����̻�
    Graph();
    menu();//���ƻ�ӭ�˵�
    return 0;
}

/*����*/
void Change(float& Paymoney) {
    int addMoney = 0, t;
    string name, id;//���ڲ�ѯ�¿�
    bool flag = false; int f = 0;
    printf("�����Ƿ��ǵ����¿��û����������¿���Ϣ��(ID ʹ��������,�ǣ�1����0 ע�⣺������¿��û���֤�˻������һ�� \n");
    cin >> f;
    if (f) {
        cin >> id >> name;
        for (auto p : Vip)
            if (p.id == id && p.name == name) {
                flag = true;
                break;
            }
        if (flag) { cout << "��֤�ɹ���ϵͳ����ʾ�ۿ۽��\n"; }
        else { cout << "�˻���ѯʧ�ܣ�ϵͳ����ʾԭ�۽��\n"; }
    }

    if (flag && f) Paymoney *= 0.8;

    printf("\nӦ�����Ϊ��  %.1f\n", Paymoney);

    while (addMoney < Paymoney) {
        printf("\n������Ͷ�Ž� "); cin >> t;
        addMoney += t;
        if (addMoney >= Paymoney) {
            cout << "\n����� " << addMoney - Paymoney << " Ԫ������" << endl;
            cout << "\n����ȡƱ�ڼ�ʱȡƱ.\n\n";
        }
        else
            cout << "\nͶ�Ž��㣬����� " << Paymoney - addMoney << " Ԫ������" << endl;
    }
}

/*������Ҫ���*/
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

//���վ���±�
int Checkindex(node& tmp) {
    int i = 0;
    for (auto p : STN) {
        if (p.name == tmp.name)return i;
        ++i;
    }
    return -1;
}

//��ȡ����
float GetDis(int& t1, int& t2) {
    float t = 0;
    //ͬ��ֱ���������
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
    printf("                                     ��ӭ�����ϲ�����·�߲�ѯϵͳ\n");
    printf("                                 Nanchang Metro Route Inquiry System\n");
    printf("\n��ϵͳ��Ʊ����\n\n");
    printf("             ���ա��ּ��ݽ�����Զ�ݼ�����ԭ��\n");
    printf("             �𲽼�Ϊ2Ԫ����6���6-12����ÿ��1Ԫ�ɳ����Ϊ6���\n");
    printf("             12-28����ÿ��1Ԫ�ɳ����Ϊ8���\n");
    printf("             28��������ÿ��1Ԫ�ɳ����Ϊ10���\n");
    printf("��������2015��11��3���ϲ�����۾ֹ����ٿ����ϲ����ƶ������ͨƱ����֤�ᡱ�Ĺ��濪ʼ��Ч\n\n");
    printf("********************************************************************************************************\n");

    //freopen("in.txt", "r", stdin);//վ�������Բ���

    printf("\n�������Ƿ�Ҫʹ�ñ�ϵͳ �� ��1 �ǣ�0 ��");
    string Exit; cin >> Exit;
    if (Exit == "0" || Exit == "��") {
        system("cls");
        printf("********************************************************************************************************\n");
        printf("                                   �ϲ�����·�߲�ѯϵͳ�Ѿ���ȫ�˳�\n");
        printf("                       Nanchang subway route query system has been safely exited\n");
        printf("********************************************************************************************************\n");
        return;
    }

    node stst, endst; int people;
    //string startStation, endStation; 
    while (1) {
        printf("\n�����Ƿ���Ҫ��ʾ����վ����Ϣ ����1 �ǣ�0 ��\n"); cin >> Exit;
        if (Exit == "1" || Exit == "��") PrintLine();
        cout << "���������վ������ 1���� ˫��վ��\n"; cin >> stst.ID >> stst.name; cout << endl;
        cout << "�������յ�վ��                 \n"; cin >> endst.ID >> endst.name; cout << endl;

        int si = Checkindex(stst), ti = Checkindex(endst);
        //����վ������
        if (si == -1 || ti == -1) {
            cout << "վ�������������������\n" << endl;
            continue;
        }

        double Distance = GetDis(si, ti);
        cout << stst.name << " -> " << endst.name << "��վ��̾���Ϊ��" << Distance << endl;

        if (stst.name == endst.name) {
            printf("\n�ף��������Ҫ������ô��\n");
            printf("������������Ϣ\n\n");
            continue;
        }

        cout << "�������������:           \n"; cin >> people; cout << endl;

        //���ü���distance�ĺ������࣬ʹ��BFS disjtra floydѰ�����·��
        countMoney(people, Distance);//������ʱ������ȡ

        cout << "�Ƿ����ʹ��Ʊ�۲�ѯϵͳ ����1 �ǣ�0 ��)";
        cout << endl << endl; cin >> Exit;
        if (Exit == "0" || Exit == "��") {
            system("cls");
            printf("********************************************************************************************************\n");
            printf("                                   �ϲ�����·�߲�ѯϵͳ�Ѿ���ȫ�˳�\n");
            printf("                       Nanchang subway route query system has been safely exited\n");
            printf("********************************************************************************************************\n");
            printf("\n\n\n\n\n\n\n\n\n\n");
            return;
        }
        else
            system("cls");
    }
}

//��������ͼ���ڽӾ���,��flody�㷨ֱ��ȡ���
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

    //����1����ĩ��2������վ���� ����֮ǰ�洢�ĺ���վ��ֱ������Ϊinf�����
    mp[ID_Conut_1][ID_Conut_1 - 1] = mp[ID_Conut_1 - 1][ID_Conut_1] = 500;

    //���˵�
    for (int i = 0; i < max_n; ++i)
        for (int j = i + 1; j < max_n; ++j)
            if (STN[i].name == STN[j].name)
                mp[i][j] = mp[j][i] = 0;
    Floyd(max_n);
}

void Floyd(int& max_n) {
    //Floyd�㷨,���޸�ΪBFS��Dijkstra�����·���㷨
    for (int k = 0; k < max_n; ++k)
        for (int i = 0; i < max_n; ++i)
            for (int j = 0; j < max_n; ++j)
                if (mp[i][j] > (mp[i][k] + mp[k][j]))
                    mp[i][j] = mp[i][k] + mp[k][j];
}

void PrintLine() {
    printf("\n1����վ������\n");
    for (int i = 0; i < ID_Conut_1; ++i) {
        cout << STN[i].name << (i == ID_Conut_1 - 1 ? "\n" : " -> ");
    }
    printf("\n2����վ������\n");
    for (int i = ID_Conut_1; i < ID_Conut_1 + ID_Conut_2; ++i) {
        cout << STN[i].name << (i == ID_Conut_1 + ID_Conut_2 - 1 ? "\n" : " -> ");
    }
    cout << endl;
}
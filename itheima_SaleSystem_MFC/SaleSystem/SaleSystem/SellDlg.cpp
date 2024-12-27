// SellDlg.cpp : 实现文件
//

#include "stdafx.h"
#include "SaleSystem.h"
#include "SellDlg.h"
#include "InfoFile.h"

// CSellDlg

IMPLEMENT_DYNCREATE(CSellDlg, CFormView)

CSellDlg::CSellDlg()
	: CFormView(CSellDlg::IDD)
	, m_price(0)
	, m_num(0)
	, m_sellinfo(_T(""))
{

}

CSellDlg::~CSellDlg()
{
}

void CSellDlg::DoDataExchange(CDataExchange* pDX)
{
	CFormView::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_COMBO1, m_combo);
	DDX_Text(pDX, IDC_EDIT1, m_price);
	DDX_Text(pDX, IDC_EDIT4, m_num);
	DDX_Text(pDX, IDC_EDIT2, m_sellinfo);
}

BEGIN_MESSAGE_MAP(CSellDlg, CFormView)
	ON_CBN_SELCHANGE(IDC_COMBO1, &CSellDlg::OnCbnSelchangeCombo1)
	ON_BN_CLICKED(IDC_BUTTON1, &CSellDlg::OnBnClickedButton1)
	ON_BN_CLICKED(IDC_BUTTON2, &CSellDlg::OnBnClickedButton2)
END_MESSAGE_MAP()


// CSellDlg 诊断

#ifdef _DEBUG
void CSellDlg::AssertValid() const
{
	CFormView::AssertValid();
}

#ifndef _WIN32_WCE
void CSellDlg::Dump(CDumpContext& dc) const
{
	CFormView::Dump(dc);
}
#endif
#endif //_DEBUG


// CSellDlg 消息处理程序


void CSellDlg::OnInitialUpdate()
{
	CFormView::OnInitialUpdate();

	// TODO:  在此添加专用代码和/或调用基类
	CInfoFile file;
	file.ReadDocline();  // 读取商品
	for (list<msg>::iterator it = file.ls.begin(); it != file.ls.end(); it++)
	// for (auto it = file.ls.begin(); it != file.ls.end(); it++)
	{
		m_combo.AddString((CString)it->name.c_str());
	}

	file.ls.clear(); // 清空list 内容

	m_combo.SetCurSel(0);
}


void CSellDlg::OnCbnSelchangeCombo1()
{
	// TODO:  在此添加控件通知处理程序代码

	CString text;

	int idx = m_combo.GetCurSel();
	m_combo.GetLBText(idx, text);

	CInfoFile file;
	file.ReadDocline();
	for (list<msg>::iterator it = file.ls.begin(); it != file.ls.end(); it++)
	// for (auto it = file.ls.begin(); it != file.ls.end(); it++)
	{
		if (text == it->name.c_str())
		{
			m_price = it->price;
			m_num = it->num;
			UpdateData(FALSE);
		}
	}

	file.ls.clear(); // 清空list 内容
}


void CSellDlg::OnBnClickedButton1()
{
	// TODO:  在此添加控件通知处理程序代码
	//获取控件上的内容，更新到对应关联的变量中
	UpdateData(TRUE);

	if (m_num == 0)
	{
		MessageBox(TEXT("个数不能为0"));
		return;
	}

	CString type;
	//获取当前选中项
	int index = m_combo.GetCurSel();
	//获取组合框当前内容
	m_combo.GetLBText(index, type);

	CString str;
	str.Format(_T("商品：%s \r\n单价：%d \r\n个数：%d \r\n总价：%d"), type, m_price, m_num, m_price*m_num);

	m_sellinfo = str; //销售信息
	UpdateData(FALSE);
	MessageBox(str);


	//需要包含#include "InfoFile.h"
	CInfoFile file;
	file.ReadDocline(); //读取商品信息
	for (list<msg>::iterator it = file.ls.begin(); it != file.ls.end(); it++)
	{
		if (type == it->name.c_str())
		{
			it->num = it->num - m_num;
		}
	}
	file.WirteDocline(); //更新文件内容

	file.ls.clear(); //清空list的内容

	m_sellinfo.Empty();
	m_num = 0;
	UpdateData(FALSE); //更新到对应的控件
}


void CSellDlg::OnBnClickedButton2()
{
	// TODO:  在此添加控件通知处理程序代码
	m_combo.SetCurSel(0); //选择第0项目
	m_sellinfo = "";
	m_num = 0;
	OnCbnSelchangeCombo1();
}